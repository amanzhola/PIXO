package com.company.pixo.feature.onboarding

import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.company.pixo.core.navigation.AppRoute
import com.company.pixo.core.ui.PixoOnboardingBeforeAfterScreen
import com.company.pixo.core.ui.PixoPromptsOnboardingScreen
import com.company.pixo.core.ui.PixoTemplatesOnboardingScreen
import com.company.pixo.domain.model.ToolType

fun NavGraphBuilder.premiumOnboardingNavGraph(
    onToolOnboardingContinue: (ToolType) -> Unit,
    onTemplatesOnboardingContinue: () -> Unit,
    onPromptsOnboardingContinue: () -> Unit
) {
    PixoPremiumOnboardingDestinations.tools.forEach { destination ->
        composable(destination.route) {
            if (destination.afterImageRes == null) {
                PixoOnboardingBeforeAfterScreen(
                    beforeImageRes = destination.beforeImageRes,
                    title = stringResource(destination.titleRes),
                    subtitle = stringResource(destination.subtitleRes),
                    onContinueClick = {
                        onToolOnboardingContinue(destination.toolType)
                    }
                )
            } else {
                PixoOnboardingBeforeAfterScreen(
                    beforeImageRes = destination.beforeImageRes,
                    afterImageRes = destination.afterImageRes,
                    title = stringResource(destination.titleRes),
                    subtitle = stringResource(destination.subtitleRes),
                    onContinueClick = {
                        onToolOnboardingContinue(destination.toolType)
                    }
                )
            }
        }
    }

    composable(AppRoute.TemplatesOnboarding.route) {
        PixoTemplatesOnboardingScreen(
            onContinueClick = onTemplatesOnboardingContinue
        )
    }

    composable(AppRoute.PromptsOnboarding.route) {
        PixoPromptsOnboardingScreen(
            onContinueClick = onPromptsOnboardingContinue
        )
    }
}