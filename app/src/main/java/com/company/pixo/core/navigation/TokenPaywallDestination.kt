package com.company.pixo.core.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.dialog
import com.company.pixo.core.ui.PixoTokensScreen
import com.company.pixo.domain.repository.TokenRepository
import kotlinx.coroutines.delay

fun NavGraphBuilder.tokenPaywallDestination(
    navController: NavHostController,
    tokenRepository: TokenRepository
) {
    dialog(
        route = AppRoute.TokenPaywall.route,
        dialogProperties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        val tokenBalance by tokenRepository
            .getBalance()
            .collectAsState(initial = 0)

        var showTokenCloseButton by remember {
            mutableStateOf(false)
        }

        LaunchedEffect(showTokenCloseButton) {
            if (showTokenCloseButton) {
                delay(10_000)
                showTokenCloseButton = false
            }
        }

        PixoTokensScreen(
            tokens = tokenBalance.toString(),
            showCloseButton = showTokenCloseButton,
            onScreenTouch = {
                showTokenCloseButton = true
            },
            onCloseClick = {
                navController.popBackStack()
            },
            onTokenPackClick = {},
            onTermsClick = {},
            onPrivacyClick = {},
            onRestoreClick = {}
        )
    }
}