package com.company.pixo.domain.model

data class GenerationResult(
    val generationId: String,
    val taskId: String,
    val toolType: ToolType,
    val resultImageUrl: String?,
    val status: GenerationStatus,
    val historyIdentity: String,
    val templateId: String? = null,
    val errorMessage: String? = null
)