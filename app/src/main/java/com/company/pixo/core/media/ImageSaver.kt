package com.company.pixo.core.media

import android.content.ContentValues
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.provider.MediaStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.core.net.toUri

class ImageSaver(
    private val context: Context
) {
    suspend fun saveImageToGallery(
        imageUri: String,
        fileName: String = "pixo_${System.currentTimeMillis()}.jpg"
    ): Boolean {
        return withContext(Dispatchers.IO) {
            runCatching {
                val uri = imageUri.toUri()

                val bitmap = context.contentResolver
                    .openInputStream(uri)
                    ?.use { input ->
                        BitmapFactory.decodeStream(input)
                    }
                    ?: return@withContext false

                val values = ContentValues().apply {
                    put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
                    put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/Pixo")
                        put(MediaStore.Images.Media.IS_PENDING, 1)
                    }
                }

                val collection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    MediaStore.Images.Media.getContentUri(
                        MediaStore.VOLUME_EXTERNAL_PRIMARY
                    )
                } else {
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                }

                val savedUri = context.contentResolver.insert(
                    collection,
                    values
                ) ?: return@withContext false

                context.contentResolver
                    .openOutputStream(savedUri)
                    ?.use { output ->
                        bitmap.compress(
                            android.graphics.Bitmap.CompressFormat.JPEG,
                            95,
                            output
                        )
                    }
                    ?: return@withContext false

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    values.clear()
                    values.put(MediaStore.Images.Media.IS_PENDING, 0)

                    context.contentResolver.update(
                        savedUri,
                        values,
                        null,
                        null
                    )
                }

                true
            }.getOrElse {
                false
            }
        }
    }
}