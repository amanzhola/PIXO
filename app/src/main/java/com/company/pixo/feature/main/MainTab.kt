package com.company.pixo.feature.main

import androidx.annotation.DrawableRes
import com.company.pixo.R

enum class MainTab(
    val title: String,
    @DrawableRes val iconRes: Int
) {
    Tools("Tools", R.drawable.ic_tools),
    Templates("Templates", R.drawable.ic_templates),
    Prompts("Prompts", R.drawable.ic_prompts),
    History("History", R.drawable.ic_history)
}