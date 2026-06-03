package com.company.pixo.domain.model

sealed interface BeforeAfterAsset {
    data class Combined(
        val image: RemoteImageAsset
    ) : BeforeAfterAsset

    data class Separate(
        val before: RemoteImageAsset,
        val after: RemoteImageAsset
    ) : BeforeAfterAsset
}