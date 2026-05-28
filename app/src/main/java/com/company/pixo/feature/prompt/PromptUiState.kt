package com.company.pixo.feature.prompt

data class PromptUiState(
    val promptText: String = "",
    val imageUri: String? = null,
    val showMediaLibraryDialog: Boolean = false
)