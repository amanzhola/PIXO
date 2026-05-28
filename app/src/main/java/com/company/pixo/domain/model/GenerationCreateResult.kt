package com.company.pixo.domain.model

data class GenerationCreateResult(
    val taskId: String,
    val status: GenerationStatus
)