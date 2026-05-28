package com.company.pixo.core.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.company.pixo.R
import com.company.pixo.core.navigation.DEBUG_TOKENS_BALANCE
import com.company.pixo.core.theme.AccentBlack
import com.company.pixo.core.theme.AccentPrimary
import com.company.pixo.core.theme.AccentSecondYellow
import com.company.pixo.core.theme.AccentWhite
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.BgWhite200
import com.company.pixo.core.theme.TextIconWeak50
import com.company.pixo.core.theme.PixoTheme

enum class PixoTokenBadgeVariant {
    GetPro,
    BalanceOutline,
    TokensBalance,
    ActiveSubscriptionBalance
}

@Composable
fun PixoTokenBadge(
    variant: PixoTokenBadgeVariant,
    modifier: Modifier = Modifier,
    tokens: String? = null,
    @StringRes textRes: Int? = null
) {
    when (variant) {
        PixoTokenBadgeVariant.GetPro -> {
            val getProText = stringResource(textRes ?: R.string.common_get_pro)

            val getProTextStyle = if (getProText.length > 7) {
                MaterialTheme.typography.labelSmall.copy(
                    fontSize = 9.sp,
                    lineHeight = 11.sp
                )
            } else {
                MaterialTheme.typography.labelMedium
            }

            PixoBadgeContainer(
                modifier = modifier.width(dimensionResource(R.dimen._109)),
                background = AccentPrimary,
                borderColor = null
            ) {
                PixoBadgeIcon(tint = AccentBlack)

                Text(
                    text = getProText,
                    color = AccentBlack,
                    style = getProTextStyle,
                    maxLines = 1,
                    softWrap = false
                )
            }
        }

        PixoTokenBadgeVariant.BalanceOutline -> {
            PixoBadgeContainer(
                modifier = modifier.width(dimensionResource(R.dimen._81)),
                background = BackgroundPrimary,
                borderColor = AccentPrimary
            ) {
                PixoBadgeIcon(tint = AccentPrimary)

                Text(
                    text = tokens.orEmpty(),
                    color = AccentSecondYellow,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }

        PixoTokenBadgeVariant.TokensBalance -> {
            PixoBadgeContainer(
                modifier = modifier.width(dimensionResource(R.dimen._157)),
                background = BgWhite200,
                borderColor = null,
                radiusRes = R.dimen._150
            ) {
                Text(
                    text = stringResource(R.string.common_my_tokens),
                    color = TextIconWeak50,
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = tokens.orEmpty(),
                    color = AccentWhite,
                    style = MaterialTheme.typography.bodyMedium
                )

                PixoBadgeIcon(tint = AccentPrimary)
            }
        }

        PixoTokenBadgeVariant.ActiveSubscriptionBalance -> {
            PixoBadgeContainer(
                modifier = modifier.widthIn(min = dimensionResource(R.dimen._73)),
                background = AccentPrimary,
                borderColor = null
            ) {
                PixoBadgeIcon(tint = AccentBlack)

                Text(
                    text = tokens.orEmpty(),
                    color = AccentBlack,
                    style = MaterialTheme.typography.labelMedium,
                    maxLines = 1,
                    softWrap = false
                )
            }
        }
    }
}

@Composable
private fun PixoBadgeContainer(
    modifier: Modifier,
    background: androidx.compose.ui.graphics.Color,
    borderColor: androidx.compose.ui.graphics.Color?,
    radiusRes: Int = R.dimen._47,
    content: @Composable () -> Unit
) {
    val shape = RoundedCornerShape(dimensionResource(radiusRes))

    Row(
        modifier = modifier
            .height(dimensionResource(R.dimen._32))
            .clip(shape)
            .background(background)
            .then(
                if (borderColor != null) {
                    Modifier.border(
                        BorderStroke(
                            width = dimensionResource(R.dimen._1),
                            color = borderColor
                        ),
                        shape = shape
                    )
                } else {
                    Modifier
                }
            )
            .padding(
                PaddingValues(
                    horizontal = dimensionResource(R.dimen._8),
                    vertical = dimensionResource(R.dimen._4)
                )
            ),
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen._4),
            Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
}

@Composable
private fun PixoBadgeIcon(
    tint: androidx.compose.ui.graphics.Color
) {
    Icon(
        painter = painterResource(R.drawable.ic_sparkle),
        contentDescription = null,
        tint = tint,
        modifier = Modifier
            .width(dimensionResource(R.dimen._20))
            .height(dimensionResource(R.dimen._20))
    )
}

@Preview(name = "PixoTokenBadge / Get PRO", showBackground = true)
@Composable
private fun PixoTokenBadgeGetProPreview() {
    PixoTokenBadgePreviewContainer {
        PixoTokenBadge(
            variant = PixoTokenBadgeVariant.GetPro
        )
    }
}

@Preview(name = "PixoTokenBadge / Balance outline", showBackground = true)
@Composable
private fun PixoTokenBadgeBalanceOutlinePreview() {
    PixoTokenBadgePreviewContainer {
        PixoTokenBadge(
            variant = PixoTokenBadgeVariant.BalanceOutline,
            tokens = DEBUG_TOKENS_BALANCE
        )
    }
}

@Preview(name = "PixoTokenBadge / My tokens balance", showBackground = true)
@Composable
private fun PixoTokenBadgeTokensBalancePreview() {
    PixoTokenBadgePreviewContainer {
        PixoTokenBadge(
            variant = PixoTokenBadgeVariant.TokensBalance,
            tokens = DEBUG_TOKENS_BALANCE
        )
    }
}

@Preview(name = "PixoTokenBadge / Active subscription", showBackground = true)
@Composable
private fun PixoTokenBadgeActiveSubscriptionPreview() {
    PixoTokenBadgePreviewContainer {
        PixoTokenBadge(
            variant = PixoTokenBadgeVariant.ActiveSubscriptionBalance,
            tokens = DEBUG_TOKENS_BALANCE
//            tokens = "240"
        )
    }
}

@Composable
private fun PixoTokenBadgePreviewContainer(
    content: @Composable () -> Unit
) {
    PixoTheme {
        Row(
            modifier = Modifier
                .width(dimensionResource(R.dimen._390))
                .background(BackgroundPrimary)
                .padding(dimensionResource(R.dimen._16)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            content()
        }
    }
}