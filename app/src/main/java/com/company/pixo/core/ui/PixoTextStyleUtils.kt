package com.company.pixo.core.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun pixoCompactTextStyleForLanguage(
    text: String,
    asciiStyle: TextStyle = MaterialTheme.typography.bodyLarge
): TextStyle {
    val isAscii = text.all { it.code <= 127 }

    return when {
        isAscii -> asciiStyle

        text.length > 12 -> MaterialTheme.typography.labelSmall.copy(
            fontSize = 8.sp,
            lineHeight = 10.sp
        )

        text.length > 7 -> MaterialTheme.typography.labelMedium.copy(
            fontSize = 9.sp,
            lineHeight = 12.sp
        )

        else -> MaterialTheme.typography.labelMedium.copy(
            fontSize = 10.sp,
            lineHeight = 14.sp
        )
    }
}