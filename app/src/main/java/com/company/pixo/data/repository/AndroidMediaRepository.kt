package com.company.pixo.data.repository

import android.content.Context
import com.company.pixo.core.media.ImageSaver
import com.company.pixo.core.media.ImageSharer
import com.company.pixo.domain.repository.MediaRepository

class AndroidMediaRepository(
    context: Context
) : MediaRepository {

    private val imageSaver = ImageSaver(context)
    private val imageSharer = ImageSharer(context)

    override suspend fun saveImageToGallery(
        imageUri: String
    ): Boolean {
        return imageSaver.saveImageToGallery(
            imageUri = imageUri
        )
    }

    override suspend fun shareImage(
        imageUri: String
    ): Boolean {
        return imageSharer.shareImage(
            imageUri = imageUri
        )
    }
}