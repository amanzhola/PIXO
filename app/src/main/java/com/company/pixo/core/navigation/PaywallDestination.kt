package com.company.pixo.core.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.company.pixo.core.ui.PixoPaywallScreen
import com.company.pixo.feature.main.MainTab
import com.company.pixo.feature.paywall.PaywallViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.paywallDestination(
    navController: NavHostController
) {
    composable(AppRoute.Paywall.route) {
        val viewModel: PaywallViewModel = koinViewModel()

        val uiState by viewModel
            .uiState
            .collectAsStateWithLifecycle()

        LaunchedEffect(Unit) {
            viewModel.loadProducts()
        }

        PixoPaywallScreen(
            yearlySelected = uiState.selectedProductId == uiState.yearly?.id,
            weeklySelected = uiState.selectedProductId == uiState.weekly?.id,

            yearlyProduct = uiState.yearly,
            weeklyProduct = uiState.weekly,
            isLoading = uiState.isLoading,
            errorMessageRes = uiState.errorMessageRes,
            purchaseEnabled = uiState.purchaseEnabled,

            onYearlyClick = {
                viewModel.selectYearly()
            },

            onWeeklyClick = {
                viewModel.selectWeekly()
            },

            onContinueClick = {
                viewModel.purchaseSelected(
                    onSuccess = {
                        navController.navigate(AppRoute.Main.createRoute(MainTab.Tools)) {
                            popUpTo(0) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
                )
            },

            onRestoreClick = {
                viewModel.restore(
                    onSuccess = {
                        navController.navigate(AppRoute.Main.createRoute(MainTab.Tools)) {
                            popUpTo(0) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
                )
            },

            onPrivacyClick = {},
            onTermsClick = {},

            onCloseClick = {
                navController.navigate(AppRoute.Main.createRoute(MainTab.Tools)) {
                    popUpTo(0) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
        )
    }
}