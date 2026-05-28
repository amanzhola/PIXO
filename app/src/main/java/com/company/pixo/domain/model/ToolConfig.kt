package com.company.pixo.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ToolConfig(
    val type: ToolType,
    val backendType: ToolBackendType,
    val flowType: ToolFlowType,
    val optionsType: ToolOptionsType,

    @StringRes val titleRes: Int,
    @StringRes val subtitleRes: Int?,

    @DrawableRes val previewBeforeRes: Int,
    @DrawableRes val previewAfterRes: Int?,

    val generation: ToolGenerationConfig,
    val optionConfig: ToolOptionConfig? = null,

    val requiresUserPhoto: Boolean,
    val requiresPrompt: Boolean,

    val historyIdentity: String,
    val serverAction: String,

    val defaultPrompt: String? = null,
    val templateId: String? = null
)