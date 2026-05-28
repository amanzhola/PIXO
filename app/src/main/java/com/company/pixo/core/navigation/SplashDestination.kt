package com.company.pixo.core.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.company.pixo.data.datastore.AppPreferencesDataSource
import com.company.pixo.feature.main.MainTab
import com.company.pixo.feature.splash.PixoCardSplash
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first

fun NavGraphBuilder.splashDestination(
    navController: NavHostController,
    preferences: AppPreferencesDataSource,
    hasActiveSubscription: Boolean
) {
    composable(AppRoute.Splash.route) {
        LaunchedEffect(Unit) {
            delay(1500)

            val onboardingCompleted = preferences
                .isOnboardingCompleted()
                .first()

            val shouldOpenGeneralOnboarding =
                ENABLE_GENERAL_FIRST_LAUNCH_ONBOARDING &&
                        !hasActiveSubscription &&
                        !onboardingCompleted

            if (shouldOpenGeneralOnboarding) {
                if (
                    PixoDebugConfig.scenario ==
                    PixoDebugScenario.NoSubscriptionGeneralFirstThenPremium
                ) {
                    preferences.clearPremiumOnboardingsShown()
                    preferences.setOnboardingCompleted(true)
                }

                navController.navigate(AppRoute.Onboarding.route) {
                    popUpTo(AppRoute.Splash.route) {
                        inclusive = true
                    }
                }
            } else {
                navController.navigate(AppRoute.Main.createRoute(MainTab.Tools)) {
                    popUpTo(AppRoute.Splash.route) {
                        inclusive = true
                    }
                }
            }
        }

        PixoCardSplash()
    }
}

//package com.company.pixo.core.navigation
//
//import androidx.compose.runtime.LaunchedEffect
//import androidx.navigation.NavGraphBuilder
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.composable
//import com.company.pixo.data.datastore.AppPreferencesDataSource
//import com.company.pixo.feature.main.MainTab
//import com.company.pixo.feature.splash.PixoCardSplash
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.flow.first
//
//fun NavGraphBuilder.splashDestination(
//    navController: NavHostController,
//    preferences: AppPreferencesDataSource,
//    hasActiveSubscription: Boolean
//) {
//    composable(AppRoute.Splash.route) {
//        LaunchedEffect(Unit) {
//            delay(1500)
//
//            val onboardingCompleted = preferences
//                .isOnboardingCompleted()
//                .first()
//
//            val shouldOpenGeneralOnboarding =
//                ENABLE_GENERAL_FIRST_LAUNCH_ONBOARDING &&
//                        !hasActiveSubscription &&
//                        !onboardingCompleted
//
//            if (shouldOpenGeneralOnboarding) {
//                navController.navigate(AppRoute.Onboarding.route) {
//                    popUpTo(AppRoute.Splash.route) {
//                        inclusive = true
//                    }
//                }
//            } else {
//                navController.navigate(AppRoute.Main.createRoute(MainTab.Tools)) {
//                    popUpTo(AppRoute.Splash.route) {
//                        inclusive = true
//                    }
//                }
//            }
//        }
//
//        PixoCardSplash()
//    }
//}