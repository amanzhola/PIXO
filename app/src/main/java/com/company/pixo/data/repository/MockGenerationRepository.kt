package com.company.pixo.data.repository

import android.content.Context
import com.company.pixo.R
import com.company.pixo.data.db.dao.HistoryDao
import com.company.pixo.data.db.entity.HistoryEntity
import com.company.pixo.core.navigation.PixoDebugConfig
import com.company.pixo.domain.model.GenerationCreateRequest
import com.company.pixo.domain.model.GenerationCreateResult
import com.company.pixo.domain.model.GenerationResult
import com.company.pixo.domain.model.GenerationStatus
import com.company.pixo.domain.model.ImageUploadResult
import com.company.pixo.domain.model.PixoToolConfigs
import com.company.pixo.domain.model.ToolType
import com.company.pixo.domain.repository.GenerationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

class MockGenerationRepository(
    private val context: Context,
    private val historyDao: HistoryDao
) : GenerationRepository {

    private val scope = CoroutineScope(
        SupervisorJob() + Dispatchers.IO
    )

    private val generationCounter = AtomicInteger(0)

    private val taskStatuses = ConcurrentHashMap<String, MutableStateFlow<GenerationStatus>>()

    private val taskRequests = ConcurrentHashMap<String, GenerationCreateRequest>()

    override suspend fun uploadImage(
        localImageUri: String
    ): ImageUploadResult {
        val imageId = "mock_image_${UUID.randomUUID()}"

        return ImageUploadResult(
            imageId = imageId,
            imageUrl = "https://mock.pixo.local/uploads/$imageId.jpg"
        )
    }

    override suspend fun createGeneration(
        request: GenerationCreateRequest
    ): GenerationCreateResult {
        val taskId = "mock_task_${UUID.randomUUID()}"
        val currentCount = generationCounter.incrementAndGet()

        val initialStatus = GenerationStatus.Processing(
            taskId = taskId,
            progressPercent = 0
        )

        taskRequests[taskId] = request
        taskStatuses[taskId] = MutableStateFlow(initialStatus)

        historyDao.insert(
            HistoryEntity(
                id = taskId,
                toolType = request.toolType.name,
                sourceImageUri = request.sourceImageUri ?: request.sourceImageUrl,
                resultImageUrl = null,
                status = "loading",
                prompt = request.prompt ?: request.templateId,
                createdAt = System.currentTimeMillis(),
                errorMessage = null
            )
        )

        scope.launch {
            runMockGeneration(
                taskId = taskId,
                request = request,
                generationNumber = currentCount
            )
        }

        return GenerationCreateResult(
            taskId = taskId,
            status = initialStatus
        )
    }

    override fun observeGeneration(
        taskId: String
    ): Flow<GenerationStatus> {
        val statusFlow = taskStatuses.getOrPut(taskId) {
            MutableStateFlow(
                GenerationStatus.Processing(
                    taskId = taskId,
                    progressPercent = 0
                )
            )
        }

        return statusFlow.asStateFlow()
    }

    override suspend fun getResult(
        taskId: String
    ): GenerationResult {
        val request = taskRequests[taskId]

        val status = taskStatuses[taskId]?.value
            ?: GenerationStatus.Error(
                taskId = taskId,
                message = "Task not found"
            )

        val resultImageUrl = when (status) {
            is GenerationStatus.Success -> status.resultImageUrl
            else -> null
        }

        return GenerationResult(
            generationId = "mock_generation_$taskId",
            taskId = taskId,
            toolType = request?.toolType ?: ToolType.PROMPT,
            resultImageUrl = resultImageUrl,
            status = status,
            historyIdentity = request?.historyIdentity.orEmpty(),
            templateId = request?.templateId,
            errorMessage = if (status is GenerationStatus.Error) {
                status.message
            } else {
                null
            }
        )
    }

    private suspend fun runMockGeneration(
        taskId: String,
        request: GenerationCreateRequest,
        generationNumber: Int
    ) {
        val stepDelay = PixoDebugConfig.mockGenerationDelayMs / 4

        updateProgress(taskId, 15)
        delay(stepDelay)

        updateProgress(taskId, 35)
        delay(stepDelay)

        updateProgress(taskId, 65)
        delay(stepDelay)

        updateProgress(taskId, 90)
        delay(stepDelay)

//        val shouldReturnError =
//            request.options["mockError"] == "true" ||
//                    generationNumber % 5 == 0
//        val shouldReturnError = true

        val shouldReturnError =
            PixoDebugConfig.mockGenerationError ||
                    request.options["mockError"] == "true"

        if (shouldReturnError) {
            finishWithError(
                taskId = taskId,
                request = request,
                message = "Network error. Check your connection and try again."
            )
        } else {
            finishWithSuccess(
                taskId = taskId,
                request = request
            )
        }
    }

    private suspend fun updateProgress(
        taskId: String,
        progressPercent: Int
    ) {
        taskStatuses[taskId]?.value = GenerationStatus.Processing(
            taskId = taskId,
            progressPercent = progressPercent
        )
    }

    private suspend fun finishWithSuccess(
        taskId: String,
        request: GenerationCreateRequest
    ) {
        val resultImageUrl = createMockResultImageUrl(request)
        val generationId = "mock_generation_$taskId"

        val successStatus = GenerationStatus.Success(
            generationId = generationId,
            resultImageUrl = resultImageUrl
        )

        taskStatuses[taskId]?.value = successStatus

        historyDao.insert(
            HistoryEntity(
                id = taskId,
                toolType = request.toolType.name,
                sourceImageUri = request.sourceImageUri ?: request.sourceImageUrl,
                resultImageUrl = resultImageUrl,
                status = "success",
                prompt = request.prompt ?: request.templateId,
                createdAt = System.currentTimeMillis(),
                errorMessage = null
            )
        )
    }

    private suspend fun finishWithError(
        taskId: String,
        request: GenerationCreateRequest,
        message: String
    ) {
        val errorStatus = GenerationStatus.Error(
            taskId = taskId,
            message = message
        )

        taskStatuses[taskId]?.value = errorStatus

        historyDao.insert(
            HistoryEntity(
                id = taskId,
                toolType = request.toolType.name,
                sourceImageUri = request.sourceImageUri ?: request.sourceImageUrl,
                resultImageUrl = null,
                status = "error",
                prompt = request.prompt ?: request.templateId,
                createdAt = System.currentTimeMillis(),
                errorMessage = message
            )
        )
    }

    private fun createMockResultImageUrl(
        request: GenerationCreateRequest
    ): String {
        val drawableRes = when {
            request.templateId != null -> {
                PixoToolConfigs
                    .findTemplateById(request.templateId)
                    ?.previewBeforeRes
                    ?: R.drawable.template_cherry
            }

            else -> {
                val config = PixoToolConfigs.findByType(request.toolType)

                config?.previewAfterRes
                    ?: config?.previewBeforeRes
                    ?: R.drawable.tools_ghibli_look
            }
        }

        return "android.resource://${context.packageName}/$drawableRes"
    }

    override suspend fun regenerate(
        taskId: String
    ): GenerationCreateResult {
        val oldRequest = taskRequests[taskId]
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
        return taskRequests[taskId]
    }
}