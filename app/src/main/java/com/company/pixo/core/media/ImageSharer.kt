package com.company.pixo.core.media

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.core.content.FileProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import androidx.core.net.toUri

class ImageSharer(
    private val context: Context
) {
    suspend fun shareImage(
        imageUri: String
    ): Boolean {
        return withContext(Dispatchers.IO) {
            runCatching {
                val shareUri = prepareShareUri(imageUri)

                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "image/jpeg"
                    putExtra(Intent.EXTRA_STREAM, shareUri)
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }

                val chooser = Intent.createChooser(
                    shareIntent,
                    "Share image"
                ).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }

                context.startActivity(chooser)

                true
            }.getOrElse {
                false
            }
        }
    }

    private fun prepareShareUri(
        imageUri: String
    ): Uri {
        val sourceUri = imageUri.toUri()

        if (sourceUri.scheme == "content") {
            return sourceUri
        }

        val bitmap = context.contentResolver
            .openInputStream(sourceUri)
            ?.use { input ->
                BitmapFactory.decodeStream(input)
            } ?: error("Unable to decode image")

        val cacheDir = File(
            context.cacheDir,
            "shared_images"
        ).apply {
            mkdirs()
        }

        val file = File(
            cacheDir,
            "pixo_share_${System.currentTimeMillis()}.jpg"
        )

        file.outputStream().use { output ->
            bitmap.compress(
                android.graphics.Bitmap.CompressFormat.JPEG,
                95,
                output
            )
        }

        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            file
        )
    }
}