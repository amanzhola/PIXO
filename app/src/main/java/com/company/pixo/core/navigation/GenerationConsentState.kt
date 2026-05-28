package com.company.pixo.core.navigation

import com.company.pixo.domain.model.GenerationCreateRequest

data class GenerationConsentState(
    val show: Boolean = false,
    val pendingRequest: GenerationCreateRequest? = null,
    val checked: Boolean = false,
    val onDismiss: (() -> Unit)? = null,
)
