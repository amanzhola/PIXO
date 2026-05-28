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

    fun setImageUri(uri: String?) {
        _uiState.update {
            it.copy(imageUri = uri)
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