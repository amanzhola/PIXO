package com.company.pixo.feature.prompt

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class PromptViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PromptUiState())
    val uiState: StateFlow<PromptUiState> = _uiState

    fun onPromptTextChange(text: String) {
        _uiState.update {
            it.copy(promptText = text)
        }
    }

    fun addImageUri(uri: String) {
        _uiState.update { state ->
            state.copy(
                imageUris = (state.imageUris + uri).distinct().take(4),
                imageUri = uri
            )
        }
    }

    fun removeImageUri(uri: String) {
        _uiState.update { state ->
            val nextUris = state.imageUris.filterNot { it == uri }

            state.copy(
                imageUris = nextUris,
                imageUri = nextUris.firstOrNull()
            )
        }
    }

    fun clearImages() {
        _uiState.update {
            it.copy(
                imageUris = emptyList(),
                imageUri = null
            )
        }
    }

    fun showMediaDialog() {
        _uiState.update {
            it.copy(showMediaLibraryDialog = true)
        }
    }

    fun hideMediaDialog() {
        _uiState.update {
            it.copy(showMediaLibraryDialog = false)
        }
    }
}