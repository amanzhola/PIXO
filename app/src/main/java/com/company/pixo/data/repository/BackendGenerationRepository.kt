package com.company.pixo.data.repository

import com.company.pixo.data.db.dao.HistoryDao
import com.company.pixo.data.db.entity.HistoryEntity
import com.company.pixo.data.image.ImageCompressor
import com.company.pixo.data.mapper.toDomain
import com.company.pixo.data.mapper.toDomainProcessing
import com.company.pixo.data.mapper.toDto
import com.company.pixo.data.remote.PixoBackendApi
import com.company.pixo.data.url.BackendUrlResolver
import com.company.pixo.domain.model.GenerationCreateRequest
import com.company.pixo.domain.model.GenerationCreateResult
import com.company.pixo.domain.model.GenerationResult
import com.company.pixo.domain.model.GenerationStatus
import com.company.pixo.domain.model.ImageUploadResult
import com.company.pixo.domain.model.ToolType
import com.company.pixo.domain.repository.GenerationRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

class BackendGenerationRepository(
    private val api: PixoBackendApi,
    private val historyDao: HistoryDao,
    private val imageCompressor: ImageCompressor,
    private val urlResolver: BackendUrlResolver,
    private val json: Json
) : GenerationRepository {

    private val taskRequests = mutableMapOf<String, GenerationCreateRequest>()

    override suspend fun uploadImage(
        localImageUri: String
    ): ImageUploadResult {
        val bytes = imageCompressor.compressToJpegBytes(localImageUri)

        val body = bytes.toRequestBody(
            contentType = "image/jpeg".toMediaType()
        )

        return api.uploadImage(body).toDomain(urlResolver)
    }

    override suspend fun createGeneration(
        request: GenerationCreateRequest
    ): GenerationCreateResult {
        return runCatching {
            val requestJson = json.encodeToString(
                GenerationCreateRequest.serializer(),
                request
            )

            val uploadedUrls = request.sourceImageUris
                .filter { it.isNotBlank() }
                .map { uri ->
                    uploadImage(uri).imageUrl
                }

            val uploadedSingleUrl = when {
                !request.sourceImageUrl.isNullOrBlank() -> {
                    urlResolver.resolve(request.sourceImageUrl)
                }

                !request.sourceImageUri.isNullOrBlank() -> {
                    uploadImage(request.sourceImageUri).imageUrl
                }

                uploadedUrls.isNotEmpty() -> {
                    uploadedUrls.first()
                }

                else -> null
            }

            val dto = request.toDto(
                uploadedSingleUrl = uploadedSingleUrl,
                uploadedUrls = uploadedUrls
            )

            val response = api.createGeneration(dto)

            taskRequests[response.taskId] = request

            historyDao.insert(
                HistoryEntity(
                    id = response.taskId,
                    toolType = request.toolType.name,
                    sourceImageUri = request.sourceImageUri ?: uploadedSingleUrl,
                    resultImageUrl = null,
                    status = "loading",
                    prompt = request.prompt ?: request.templateId,
                    createdAt = System.currentTimeMillis(),
                    errorMessage = null,
                    requestJson = requestJson
                )
            )

            response.toDomainProcessing()

        }.getOrElse { error ->
            val taskId = "failed_${System.currentTimeMillis()}"

            GenerationCreateResult(
                taskId = taskId,
                status = GenerationStatus.Error(
                    taskId = taskId,
                    message = error.message ?: "Backend is unavailable"
                )
            )
        }
    }

    override fun observeGeneration(
        taskId: String
    ): Flow<GenerationStatus> = flow {
        while (true) {
            val status = runCatching {
                api.getGeneration(taskId).toDomain(urlResolver)
            }.getOrElse { error ->
                GenerationStatus.Error(
                    taskId = taskId,
                    message = error.message ?: "Backend is unavailable"
                )
            }

            if (status is GenerationStatus.Success) {
                val request = taskRequests[taskId]

                val requestJson = request?.let {
                    json.encodeToString(
                        GenerationCreateRequest.serializer(),
                        it
                    )
                }

                historyDao.insert(
                    HistoryEntity(
                        id = taskId,
                        toolType = request?.toolType?.name ?: ToolType.PROMPT.name,
                        sourceImageUri = request?.sourceImageUri,
                        resultImageUrl = status.resultImageUrl,
                        status = "success",
                        prompt = request?.prompt ?: request?.templateId,
                        createdAt = System.currentTimeMillis(),
                        errorMessage = null,
                        requestJson = requestJson
                    )
                )

                emit(status)
                return@flow
            }

            if (status is GenerationStatus.Error) {
                val request = taskRequests[taskId]

                val requestJson = request?.let {
                    json.encodeToString(
                        GenerationCreateRequest.serializer(),
                        it
                    )
                }

                historyDao.insert(
                    HistoryEntity(
                        id = taskId,
                        toolType = request?.toolType?.name ?: ToolType.PROMPT.name,
                        sourceImageUri = request?.sourceImageUri,
                        resultImageUrl = null,
                        status = "error",
                        prompt = request?.prompt ?: request?.templateId,
                        createdAt = System.currentTimeMillis(),
                        errorMessage = status.message,
                        requestJson = requestJson
                    )
                )

                emit(status)
                return@flow
            }

            emit(status)
            delay(1_000)
        }
    }

    override suspend fun getResult(
        taskId: String
    ): GenerationResult {
        val request = taskRequests[taskId]

        return runCatching {
            api.getResult(taskId).toDomain(
                urlResolver = urlResolver,
                toolType = request?.toolType ?: ToolType.PROMPT,
                historyIdentity = request?.historyIdentity.orEmpty(),
                templateId = request?.templateId
            )
        }.getOrElse { error ->
            GenerationResult(
                generationId = "generation_$taskId",
                taskId = taskId,
                toolType = request?.toolType ?: ToolType.PROMPT,
                resultImageUrl = null,
                status = GenerationStatus.Error(
                    taskId = taskId,
                    message = error.message ?: "Backend is unavailable"
                ),
                historyIdentity = request?.historyIdentity.orEmpty(),
                templateId = request?.templateId,
                errorMessage = error.message
            )
        }
    }

    override suspend fun regenerate(
        taskId: String
    ): GenerationCreateResult {
        val oldRequest = taskRequests[taskId]
            ?: historyDao.getById(taskId)
                ?.requestJson
                ?.let { requestJson ->
                    runCatching {
                        json.decodeFromString(
                            GenerationCreateRequest.serializer(),
                            requestJson
                        )
                    }.getOrNull()
                }
            ?: return GenerationCreateResult(
                taskId = taskId,
                status = GenerationStatus.Error(
                    taskId = taskId,
                    message = "Original generation request not found"
                )
            )

        return createGeneration(oldRequest)
    }

    override suspend fun getRequest(
        taskId: String
    ): GenerationCreateRequest? {
        taskRequests[taskId]?.let {
            return it
        }

        val requestJson = historyDao.getById(taskId)?.requestJson
            ?: return null

        return runCatching {
            json.decodeFromString(
                GenerationCreateRequest.serializer(),
                requestJson
            )
        }.getOrNull()
    }
}
