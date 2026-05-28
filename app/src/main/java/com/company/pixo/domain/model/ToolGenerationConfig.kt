package com.company.pixo.domain.model

data class ToolGenerationConfig(
    val tokenCost: Int,
    val defaultOutputCount: Int = 1,
    val cartoonOutputCount: Int? = null
)