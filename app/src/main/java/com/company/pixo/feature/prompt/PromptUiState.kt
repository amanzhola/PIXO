package com.company.pixo.feature.prompt

data class PromptUiState(
    val promptText: String = "",
    val imageUri: String? = null,
    val imageUris: List<String> = emptyList(),
    val showMediaLibraryDialog: Boolean = false
)