package com.company.pixo.data.adapty

import android.content.Context
import android.util.Log
import com.adapty.Adapty
import com.adapty.models.AdaptyConfig

class AdaptyInitializer(
    private val context: Context
) {

    fun initialize() {
        if (!PixoAdaptyConfig.isConfigured) {
            Log.w("AdaptyInitializer", "Adapty SDK key is empty. SDK initialization skipped.")
            return
        }

        Adapty.activate(
            context.applicationContext,
            AdaptyConfig.Builder(PixoAdaptyConfig.PUBLIC_SDK_KEY)
                .build()
        )
    }
}