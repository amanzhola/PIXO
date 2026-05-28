package com.company.pixo.domain.repository

interface MediaRepository {

    suspend fun saveImageToGallery(
        imageUri: String
    ): Boolean

    suspend fun shareImage(
        imageUri: String
    ): Boolean
}