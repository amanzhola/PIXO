package com.company.pixo.feature.editor

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PixoGhostfaceEditRoute(
    imageUri: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onGenerateClick: () -> Unit = {}
) {
    PixoGhostfaceEditScreen(
        imageUri = imageUri,
        modifier = modifier,
        onBackClick = onBackClick,
        onGenerateClick = onGenerateClick
    )
}