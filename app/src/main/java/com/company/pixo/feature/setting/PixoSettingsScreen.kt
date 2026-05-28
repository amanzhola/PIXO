package com.company.pixo.feature.setting

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.company.pixo.R
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.PixoTheme
import com.company.pixo.core.ui.PixoSettingsButtons
import com.company.pixo.core.ui.PixoTopBar
import com.company.pixo.core.ui.PixoTopBarVariant
import com.company.pixo.core.navigation.DEBUG_TOKENS_BALANCE

@Composable
fun PixoSettingsScreen(
    showBanner: Boolean,
    modifier: Modifier = Modifier,
    tokens: String? = null,
    onCloseClick: () -> Unit,
    onContactUsClick: () -> Unit,
    onShareWithFriendsClick: () -> Unit,
    onPrivacyPolicyClick: () -> Unit,
    onTermsOfUseClick: () -> Unit,
    onUpgradeClick: () -> Unit = {},
) {
    Log.d(
        "SettingsBalance",
        "SCREEN showBanner=$showBanner tokens=$tokens"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundPrimary)
    ) {
        PixoTopBar(
            variant = if (showBanner) {
                PixoTopBarVariant.SettingsSimple
            } else {
                PixoTopBarVariant.SettingsWithBalance
            },
            tokens = tokens.orEmpty(),
            onCloseClick = onCloseClick,
            onGetProClick = onUpgradeClick,
            onTokensClick = onUpgradeClick
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(R.dimen._16)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (showBanner) {
                Image(
                    painter = painterResource(R.drawable.settings_banner),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimensionResource(R.dimen._16))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = onUpgradeClick
                        ),
                    contentScale = ContentScale.FillWidth
                )

                Spacer(
                    modifier = Modifier.height(dimensionResource(R.dimen._28))
                )
            }

            PixoSettingsButtons(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(R.dimen._16)),
                onContactUsClick = onContactUsClick,
                onShareWithFriendsClick = onShareWithFriendsClick,
                onPrivacyPolicyClick = onPrivacyPolicyClick,
                onTermsOfUseClick = onTermsOfUseClick
            )
        }
    }
}

@Preview(
    name = "Pixo / Settings Screen Simple",
    showBackground = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
private fun PixoSettingsScreenSimplePreview() {
    PixoTheme {
        PixoSettingsScreen(
            showBanner = true,
            onCloseClick = {},
            onContactUsClick = {},
            onShareWithFriendsClick = {},
            onPrivacyPolicyClick = {},
            onTermsOfUseClick = {}
        )
    }
}

@Preview(
    name = "Pixo / Settings Screen Balance",
    showBackground = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
private fun PixoSettingsScreenBalancePreview() {
    PixoTheme {
        PixoSettingsScreen(
            showBanner = false,
            tokens = DEBUG_TOKENS_BALANCE,
            onCloseClick = {},
            onContactUsClick = {},
            onShareWithFriendsClick = {},
            onPrivacyPolicyClick = {},
            onTermsOfUseClick = {}
        )
    }
}