package com.company.pixo.domain.model

sealed class GenerationStatus {

    data object Idle : GenerationStatus()

    data class Processing(
        val taskId: String,
        val progressPercent: Int = 0
    ) : GenerationStatus()

    data class Success(
        val generationId: String,
        val resultImageUrl: String
    ) : GenerationStatus()

    data class Error(
        val message: String,
        val taskId: String? = null
    ) : GenerationStatus()
}