package com.company.pixo.feature.tokens

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.company.pixo.R
import com.company.pixo.core.config.PixoRemoteAssets
import com.company.pixo.core.theme.AccentPrimary
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.BgSurface400
import com.company.pixo.core.theme.LabelPrimary
import com.company.pixo.core.theme.PixoTheme
import com.company.pixo.core.theme.TokensRowBackground
import com.company.pixo.core.ui.PaywallDot
import com.company.pixo.core.ui.PaywallFooterText
import com.company.pixo.core.ui.PixoFloatingCloseButton
import com.company.pixo.core.ui.PixoTokensGhostFace
import com.company.pixo.core.ui.PixoTopBar
import com.company.pixo.core.ui.PixoTopBarVariant
import com.company.pixo.domain.model.RemoteImageAsset

data class PixoTokenPack(
    @StringRes val tokensRes: Int,
    @StringRes val priceRes: Int
)

@Composable
fun PixoTokensScreen(
    modifier: Modifier = Modifier,
    tokens: String = "0",
    tokenPacks: List<PixoTokenPack> = listOf(
        PixoTokenPack(R.string.tokens_100, R.string.tokens_price_100),
        PixoTokenPack(R.string.tokens_500, R.string.tokens_price_500),
        PixoTokenPack(R.string.tokens_1000, R.string.tokens_price_1000),
        PixoTokenPack(R.string.tokens_2000, R.string.tokens_price_2000)
    ),
    showCloseButton: Boolean = false,
    onScreenTouch: () -> Unit = {},
    onCloseClick: () -> Unit = {},
    onTokenPackClick: (PixoTokenPack) -> Unit = {},
    onTermsClick: () -> Unit = {},
    onPrivacyClick: () -> Unit = {},
    onRestoreClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundPrimary)
            .pointerInput(Unit) {
                detectTapGestures {
                    onScreenTouch()
                }
            }
    ) {
        PixoTokensGhostFace(
            image = RemoteImageAsset.Remote(
                url = "${PixoRemoteAssets.TOKENS}/tokens_hostface.webp"
            )
        )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            PixoTopBar(
                variant = PixoTopBarVariant.Tokens,
                tokens = tokens
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(
                        start = dimensionResource(id = R.dimen._16),
                        end = dimensionResource(id = R.dimen._16),
                        top = dimensionResource(id = R.dimen._220),
                        bottom = dimensionResource(id = R.dimen._24)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(dimensionResource(id = R.dimen._32)))
                        .background(BgSurface400)
                        .padding(dimensionResource(id = R.dimen._20)),
                    verticalArrangement = Arrangement.spacedBy(
                        dimensionResource(id = R.dimen._16)
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PixoTokensHeader()

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(
                            dimensionResource(id = R.dimen._8)
                        )
                    ) {
                        tokenPacks.forEach { pack ->
                            PixoTokenPackRow(
                                pack = pack,
                                onClick = { onTokenPackClick(pack) }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._20)))

                PixoTokensFooter(
                    onTermsClick = onTermsClick,
                    onPrivacyClick = onPrivacyClick,
                    onRestoreClick = onRestoreClick
                )
            }
        }

        if (showCloseButton) {
            PixoFloatingCloseButton(
                onClick = onCloseClick
            )
        }
    }
}

@Composable
private fun PixoTokensHeader() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen._8)
        )
    ) {
        Text(
            text = stringResource(id = R.string.tokens_title),
            color = LabelPrimary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displaySmall
        )

        Text(
            text = stringResource(id = R.string.tokens_subtitle),
            color = LabelPrimary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Composable
private fun PixoTokenPackRow(
    pack: PixoTokenPack,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = dimensionResource(id = R.dimen._48))
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen._12)))
            .background(TokensRowBackground)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(dimensionResource(id = R.dimen._12)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen._4)
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_sparkle),
                contentDescription = null,
                tint = AccentPrimary,
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen._24))
                    .height(dimensionResource(id = R.dimen._24))
            )

            Text(
                text = stringResource(id = pack.tokensRes),
                color = LabelPrimary,
                style = MaterialTheme.typography.titleSmall
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen._4)
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = pack.priceRes),
                color = LabelPrimary,
                style = MaterialTheme.typography.titleSmall
            )

            Image(
                painter = painterResource(id = R.drawable.ic_token_right),
                contentDescription = null,
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen._24))
                    .height(dimensionResource(id = R.dimen._24))
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PixoTokensFooter(
    modifier: Modifier = Modifier,
    onTermsClick: () -> Unit,
    onPrivacyClick: () -> Unit,
    onRestoreClick: () -> Unit
) {
    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen._16)),
        horizontalArrangement = Arrangement.spacedBy(
            space = dimensionResource(id = R.dimen._8),
            alignment = Alignment.CenterHorizontally
        ),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen._4)
        )
    ) {
        PaywallFooterText(R.string.common_terms_of_use, onTermsClick)

        Box(
            modifier = Modifier.height(dimensionResource(R.dimen._20)),
            contentAlignment = Alignment.Center
        ) {
            PaywallDot()
        }

        PaywallFooterText(R.string.common_privacy_policy, onPrivacyClick)

        Box(
            modifier = Modifier.height(dimensionResource(R.dimen._20)),
            contentAlignment = Alignment.Center
        ) {
            PaywallDot()
        }

        PaywallFooterText(R.string.common_restore, onRestoreClick)
    }
}

@Preview(name = "Pixo / Tokens Screen", showBackground = true)
@Composable
private fun PixoTokensScreenPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._844))
                .background(BackgroundPrimary)
        ) {
            PixoTokensScreen()
        }
    }
}
