package com.company.pixo.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class HealthDto(
    val status: String
)

@Serializable
data class ImageUploadDto(
    val imageId: String,
    val imageUrl: String
)

@Serializable
data class GenerationCreateDto(
    val toolType: String,
    val serverAction: String,
    val sourceImageUrl: String?,
    val sourceImageUri: String?,
    val uploadedImageUrls: List<String> = emptyList(),
    val prompt: String?,
    val templateId: String?,
    val options: Map<String, String>,
    val outputCount: Int
)

@Serializable
data class GenerationCreateDtoResult(
    val taskId: String,
    val status: String? = null,
    val progressPercent: Int? = null
)

@Serializable
data class GenerationStatusDto(
    val taskId: String,
    val status: String,
    val progressPercent: Int? = null,
    val resultImageUrls: List<String> = emptyList(),
    val errorMessage: String? = null
)

@Serializable
data class GenerationResultDto(
    val generationId: String? = null,
    val taskId: String,
    val status: String,
    val resultImageUrls: List<String> = emptyList(),
    val errorMessage: String? = null
)