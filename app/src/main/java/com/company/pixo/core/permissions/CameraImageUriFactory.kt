package com.company.pixo.core.permissions

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

object CameraImageUriFactory {

    fun createImageUri(
        context: Context
    ): Uri {
        val imageFile = File(
            context.cacheDir,
            "pixo_camera_${System.currentTimeMillis()}.jpg"
        )

        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            imageFile
        )
    }
}