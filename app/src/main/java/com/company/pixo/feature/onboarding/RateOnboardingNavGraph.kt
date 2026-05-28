package com.company.pixo.feature.onboarding

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.company.pixo.core.navigation.AppRoute
import com.company.pixo.core.ui.PixoProRateScreen
import com.company.pixo.core.ui.RateSuccessSetupAnimatedScreen

fun NavGraphBuilder.rateOnboardingNavGraph(
    navController: NavController
) {
    composable(AppRoute.ProRate.route) {
        PixoProRateScreen(
            onContinueClick = {
                navController.navigate(AppRoute.RateSetup.route)
            }
        )
    }

    composable(AppRoute.RateSetup.route) {
        RateSuccessSetupAnimatedScreen(
            onFinished = {
                navController.navigate(AppRoute.Paywall.route) {
                    popUpTo(AppRoute.ProRate.route) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
        )
    }
}