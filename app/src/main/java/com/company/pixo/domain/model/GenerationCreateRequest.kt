package com.company.pixo.domain.model

data class GenerationCreateRequest(
    val toolType: ToolType,
    val backendType: ToolBackendType,
    val serverAction: String,
    val sourceImageUrl: String?,
    val sourceImageUri: String?,
    val prompt: String?,
    val templateId: String?,
    val options: Map<String, String>,
    val tokenCost: Int,
    val outputCount: Int,
    val historyIdentity: String
)