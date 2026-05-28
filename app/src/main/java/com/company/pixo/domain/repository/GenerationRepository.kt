package com.company.pixo.domain.repository

import com.company.pixo.domain.model.GenerationCreateRequest
import com.company.pixo.domain.model.GenerationCreateResult
import com.company.pixo.domain.model.GenerationResult
import com.company.pixo.domain.model.GenerationStatus
import com.company.pixo.domain.model.ImageUploadResult
import kotlinx.coroutines.flow.Flow

interface GenerationRepository {

    suspend fun uploadImage(
        localImageUri: String
    ): ImageUploadResult

    suspend fun createGeneration(
        request: GenerationCreateRequest
    ): GenerationCreateResult

    suspend fun regenerate(
        taskId: String
    ): GenerationCreateResult

    fun observeGeneration(
        taskId: String
    ): Flow<GenerationStatus>

    suspend fun getResult(
        taskId: String
    ): GenerationResult

    suspend fun getRequest(
        taskId: String
    ): GenerationCreateRequest?
}