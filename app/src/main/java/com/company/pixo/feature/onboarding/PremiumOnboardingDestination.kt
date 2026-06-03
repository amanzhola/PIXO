package com.company.pixo.feature.onboarding

import androidx.annotation.StringRes
import com.company.pixo.domain.model.BeforeAfterAsset
import com.company.pixo.domain.model.ToolType

data class PremiumOnboardingDestination(
    val route: String,
    val toolType: ToolType,
    val asset: BeforeAfterAsset,
    @StringRes val titleRes: Int,
    @StringRes val subtitleRes: Int
)
