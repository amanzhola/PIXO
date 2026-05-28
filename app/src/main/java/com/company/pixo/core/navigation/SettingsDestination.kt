package com.company.pixo.core.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.company.pixo.feature.setting.PixoSettingsScreen
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.company.pixo.domain.repository.TokenRepository

fun NavGraphBuilder.settingsDestination(
    navController: NavHostController,
    actions: AppNavigationActions,
    hasActiveSubscription: Boolean,
    showTokenBalanceInSettings: Boolean,
    tokenRepository: TokenRepository,
    onContactUsClick: () -> Unit,
    onShareWithFriendsClick: () -> Unit,
    onPrivacyPolicyClick: () -> Unit,
    onTermsOfUseClick: () -> Unit
) {
    composable(AppRoute.Settings.route) {

        val tokenBalance by tokenRepository
            .getBalance()
            .collectAsState(initial = 0)

        Log.d(
            "SettingsBalance",
            "DESTINATION hasActiveSubscription=$hasActiveSubscription tokenBalance=$tokenBalance"
        )

        PixoSettingsScreen(
            showBanner = !hasActiveSubscription,
            tokens = tokenBalance.toString(),
            onCloseClick = {
                navController.popBackStack()
            },
            onUpgradeClick = {
                if (hasActiveSubscription) {
                    navController.navigate(AppRoute.TokenPaywall.route)
                } else {
                    actions.openProFlow()
                }
            },
            onContactUsClick = onContactUsClick,
            onShareWithFriendsClick = onShareWithFriendsClick,
            onPrivacyPolicyClick = onPrivacyPolicyClick,
            onTermsOfUseClick = onTermsOfUseClick
        )
    }
}