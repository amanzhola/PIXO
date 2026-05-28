package com.company.pixo.data.adapty

object PixoAdaptyConfig {

    const val PUBLIC_SDK_KEY = ""

    const val PLACEMENT_ID = "main_paywall"

    const val ACCESS_LEVEL_ID = "premium"

    const val WEEKLY_PRODUCT_ID = "pixo_weekly"

    const val YEARLY_PRODUCT_ID = "pixo_yearly"

    val isConfigured: Boolean
        get() = PUBLIC_SDK_KEY.isNotBlank()
}