package com.company.pixo.data.image

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.graphics.scale
import androidx.core.net.toUri
import java.io.ByteArrayOutputStream

class AndroidImageCompressor(
    private val context: Context
) : ImageCompressor {

    override fun compressToJpegBytes(
        localImageUri: String
    ): ByteArray {
        val uri = localImageUri.toUri()

        val originalBitmap = context.contentResolver
            .openInputStream(uri)
            ?.use { input ->
                BitmapFactory.decodeStream(input)
            } ?: error("Unable to decode image: $localImageUri")

//        val maxSide = 1280
        val maxSide = 640

        val width = originalBitmap.width
        val height = originalBitmap.height

        val scale = minOf(
            maxSide.toFloat() / width.toFloat(),
            maxSide.toFloat() / height.toFloat(),
            1f
        )

        val targetWidth = (width * scale).toInt()
        val targetHeight = (height * scale).toInt()

        val resizedBitmap = if (scale < 1f) {
            originalBitmap.scale(targetWidth, targetHeight)
        } else {
            originalBitmap
        }

        return ByteArrayOutputStream().use { output ->
            resizedBitmap.compress(
                Bitmap.CompressFormat.JPEG,
                85,
                output
            )

            output.toByteArray()
        }
    }
}