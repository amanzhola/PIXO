package com.company.pixo.feature.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.company.pixo.domain.model.ToolType

data class PremiumOnboardingDestination(
    val route: String,
    val toolType: ToolType,
    @DrawableRes val beforeImageRes: Int,
    @DrawableRes val afterImageRes: Int? = null,
    @StringRes val titleRes: Int,
    @StringRes val subtitleRes: Int
)