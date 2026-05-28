package com.company.pixo.core.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.company.pixo.feature.onboarding.rateOnboardingNavGraph

fun NavGraphBuilder.rateOnboardingDestination(
    navController: NavHostController
) {
    rateOnboardingNavGraph(
        navController = navController
    )
}