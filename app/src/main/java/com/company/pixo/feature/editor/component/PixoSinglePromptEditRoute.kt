package com.company.pixo.feature.editor.component

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun PixoSinglePromptEditRoute(
    imageUri: String,
    @StringRes titleRes: Int,
    fieldTitle: String,
    fieldHint: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onGenerateClick: (String) -> Unit = {}
) {
    var value by rememberSaveable {
        mutableStateOf("")
    }

    PixoSinglePromptEditScreen(
        titleRes = titleRes,
        fieldTitle = fieldTitle,
        fieldHint = fieldHint,
        imageUri = imageUri,
        value = value,
        modifier = modifier,
        onValueChange = {
            value = it
        },
        onBackClick = onBackClick,
        onGenerateClick = {
            onGenerateClick(value)
        }
    )
}