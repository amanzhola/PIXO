package com.company.pixo.core.navigation

import com.company.pixo.domain.model.ToolType

sealed class GalleryPickTarget {

    data class Tool(
        val toolType: ToolType
    ) : GalleryPickTarget()

    data class Template(
        val templateId: String
    ) : GalleryPickTarget()

    data object Prompt : GalleryPickTarget()
}