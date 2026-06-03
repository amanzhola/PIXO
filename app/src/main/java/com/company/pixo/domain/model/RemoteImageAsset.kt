package com.company.pixo.domain.model

import androidx.annotation.DrawableRes

sealed interface RemoteImageAsset {
    data class Local(
        @DrawableRes val res: Int
    ) : RemoteImageAsset

    data class Remote(
        val url: String
    ) : RemoteImageAsset
}