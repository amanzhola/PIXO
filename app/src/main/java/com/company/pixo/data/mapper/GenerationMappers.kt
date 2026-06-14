package com.company.pixo.data.mapper

import com.company.pixo.data.remote.dto.GenerationCreateDto
import com.company.pixo.data.remote.dto.GenerationCreateDtoResult
import com.company.pixo.data.remote.dto.GenerationResultDto
import com.company.pixo.data.remote.dto.GenerationStatusDto
import com.company.pixo.data.remote.dto.ImageUploadDto
import com.company.pixo.data.url.BackendUrlResolver
import com.company.pixo.domain.model.GenerationCreateRequest
import com.company.pixo.domain.model.GenerationCreateResult
import com.company.pixo.domain.model.GenerationResult
import com.company.pixo.domain.model.GenerationStatus
import com.company.pixo.domain.model.ImageUploadResult
import com.company.pixo.domain.model.ToolType

fun ImageUploadDto.toDomain(
    urlResolver: BackendUrlResolver
): ImageUploadResult {
    return ImageUploadResult(
        imageId = imageId,
        imageUrl = urlResolver.resolve(imageUrl).orEmpty()
    )
}

fun GenerationCreateRequest.toDto(
    uploadedSingleUrl: String?,
    uploadedUrls: List<String>
): GenerationCreateDto {
    return GenerationCreateDto(
        toolType = toolType.name,
        serverAction = serverAction,
        sourceImageUrl = uploadedSingleUrl,
        sourceImageUri = sourceImageUri,
        uploadedImageUrls = uploadedUrls,
        prompt = prompt,
        templateId = templateId,
        options = options,
        outputCount = outputCount
    )
}

fun GenerationCreateDtoResult.toDomainProcessing(): GenerationCreateResult {
    return GenerationCreateResult(
        taskId = taskId,
        status = GenerationStatus.Processing(
            taskId = taskId,
            progressPercent = progressPercent ?: 0
        )
    )
}

fun GenerationStatusDto.toDomain(
    urlResolver: BackendUrlResolver
): GenerationStatus {
    return when (status.lowercase()) {
        "success", "completed", "done" -> {
            val resultUrl = urlResolver.resolve(resultImageUrls.firstOrNull())

            if (resultUrl != null) {
                GenerationStatus.Success(
                    generationId = "generation_$taskId",
                    resultImageUrl = resultUrl
                )
            } else {
                GenerationStatus.Error(
                    taskId = taskId,
                    message = errorMessage ?: "Result image not found"
                )
            }
        }

        "error", "failed" -> {
            GenerationStatus.Error(
                taskId = taskId,
                message = errorMessage ?: "Generation failed"
            )
        }

        else -> {
            GenerationStatus.Processing(
                taskId = taskId,
                progressPercent = progressPercent ?: 0
            )
        }
    }
}

fun GenerationResultDto.toDomain(
    urlResolver: BackendUrlResolver,
    toolType: ToolType,
    historyIdentity: String,
    templateId: String?
): GenerationResult {
    val resultUrl = urlResolver.resolve(resultImageUrls.firstOrNull())
    val realGenerationId = generationId ?: "generation_$taskId"

    val mappedStatus = if (resultUrl != null) {
        GenerationStatus.Success(
            generationId = realGenerationId,
            resultImageUrl = resultUrl
        )
    } else {
        GenerationStatus.Error(
            taskId = taskId,
            message = errorMessage ?: "Result not found"
        )
    }

    return GenerationResult(
        generationId = realGenerationId,
        taskId = taskId,
        toolType = toolType,
        resultImageUrl = resultUrl,
        status = mappedStatus,
        historyIdentity = historyIdentity,
        templateId = templateId,
        errorMessage = errorMessage
    )
}