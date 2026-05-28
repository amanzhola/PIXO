package com.company.pixo.core.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val ColorScheme = darkColorScheme(
    primary = AccentPrimary,
    background = BackgroundPrimary,
    surface = BackgroundSecondary,
    onPrimary = AccentBlack,
    onBackground = LabelPrimary,
    onSurface = LabelPrimary,
//    error = AccentRed,
//    onError = AccentWhite
)

@Composable
fun PixoTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = ColorScheme,
        typography = PixoTypography,
        content = content
    )
}