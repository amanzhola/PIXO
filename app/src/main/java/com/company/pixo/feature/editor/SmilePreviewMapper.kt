package com.company.pixo.feature.editor

import com.company.pixo.R

fun smilePreviewRes(
    level: Int
): Int {
    return when (level) {
        0 -> R.drawable.tools_smile_edit_camera_image0
        1 -> R.drawable.tools_smile_edit_camera_image1
        2 -> R.drawable.tools_smile_edit_camera_image2
        3 -> R.drawable.tools_smile_edit_camera_image3
        else -> R.drawable.tools_smile_edit_camera_image4
    }
}