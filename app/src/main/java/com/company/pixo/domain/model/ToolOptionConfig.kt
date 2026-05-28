package com.company.pixo.domain.model

import androidx.annotation.StringRes

data class ToolOptionConfig(
    val type: ToolOptionsType,
    @StringRes val titleRes: Int? = null,
    val samples: List<ToolOptionSample> = emptyList()
)

data class ToolOptionSample(
    val id: String,
    @StringRes val titleRes: Int? = null,
    val serverValue: String,
    val example: String? = null
)