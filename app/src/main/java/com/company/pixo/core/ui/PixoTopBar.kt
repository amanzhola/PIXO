package com.company.pixo.core.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.company.pixo.R
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.LabelPrimary
import com.company.pixo.core.theme.PixoTheme
import com.company.pixo.core.ui.extensions.noRippleClick
import androidx.compose.material3.Icon
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.company.pixo.core.navigation.DEBUG_TOKENS_BALANCE

enum class PixoTopBarVariant {
    MainGuest,
    MainGuestNoLogo,
    MainSubscribed,
    Detail,
    Result,
    Generation,
    Camera,
    Tokens,
    SettingsWithBalance,
    SettingsSimple,
    Paywall
}

@Composable
fun PixoTopBar(
    variant: PixoTopBarVariant,
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int? = null,
    tokens: String? = null,
    onGetProClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
    onCloseClick: () -> Unit = {},
    onFlashClick: () -> Unit = {},
    onChevronClick: () -> Unit = {},
    onTokensClick: () -> Unit = {},
    onTitleClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = dimensionResource(R.dimen._52))
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(
                start = dimensionResource(R.dimen._16),
                end = dimensionResource(R.dimen._16),
                top = dimensionResource(R.dimen._8),
                bottom = dimensionResource(R.dimen._12)
            )
    ) {
        when (variant) {
            PixoTopBarVariant.MainGuest -> {
                TopBarLeft(
                    modifier = Modifier.align(Alignment.CenterStart)
                )  {
                    PixoTokenBadge(
                        variant = PixoTokenBadgeVariant.GetPro,
                        modifier = Modifier.noRippleClick(onGetProClick)
                    )
                }

                TopBarCenter(
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    PixoLogoText()
                }

                TopBarRight(
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    PixoIconButton(
                        variant = PixoIconButtonVariant.SettingsRight,
                        contentDescription = stringResource(R.string.common_settings),
                        onClick = onSettingsClick
                    )
                }
            }

            PixoTopBarVariant.MainGuestNoLogo -> {
                TopBarLeft(
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    PixoTokenBadge(
                        variant = PixoTokenBadgeVariant.GetPro,
                        modifier = Modifier.noRippleClick(onGetProClick)
                    )
                }

                TopBarRight(
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    PixoIconButton(
                        variant = PixoIconButtonVariant.SettingsRight,
                        contentDescription = stringResource(R.string.common_settings),
                        onClick = onSettingsClick
                    )
                }
            }

            PixoTopBarVariant.MainSubscribed -> {
                TopBarLeft(
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Box(
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = onTokensClick
                        )
                    ) {
                        PixoTokenBadge(
                            variant = PixoTokenBadgeVariant.ActiveSubscriptionBalance,
                            tokens = tokens.orEmpty()
                        )
                    }
                }

                TopBarRight(
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    PixoIconButton(
                        variant = PixoIconButtonVariant.SettingsRight,
                        contentDescription = stringResource(R.string.common_settings),
                        onClick = onSettingsClick
                    )
                }
            }

            PixoTopBarVariant.Detail -> {
                TopBarLeft(
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    PixoIconButton(
                        variant = PixoIconButtonVariant.BackLeft,
                        contentDescription = stringResource(R.string.common_back),
                        onClick = onBackClick
                    )
                }

                TopBarCenter(
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    PixoScreenTitle(
                        titleRes = titleRes,
                        onClick = onTitleClick
                    )
                }
            }

            PixoTopBarVariant.Result -> {
                TopBarLeft(
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    PixoIconButton(
                        variant = PixoIconButtonVariant.CloseLeft,
                        contentDescription = stringResource(R.string.common_close),
                        onClick = onCloseClick
                    )
                }

                TopBarCenter(
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    PixoScreenTitle(
                        titleRes = titleRes,
                        onClick = onTitleClick
                    )
                }
            }

            PixoTopBarVariant.Generation -> {
                TopBarLeft(
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    PixoIconButton(
                        variant = PixoIconButtonVariant.CloseLeft,
                        contentDescription = stringResource(R.string.common_close),
                        onClick = onCloseClick
                    )
                }
            }

            PixoTopBarVariant.Camera -> {
                TopBarLeft(
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    PixoIconButton(
                        variant = PixoIconButtonVariant.FlashOffCamera,
                        contentDescription = stringResource(R.string.cd_flash_off),
                        onClick = onFlashClick
                    )
                }

                TopBarCenter(
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    PixoIconButton(
                        variant = PixoIconButtonVariant.ChevronUpCamera,
                        contentDescription = stringResource(R.string.cd_collapse),
                        onClick = onChevronClick
                    )
                }
            }

            PixoTopBarVariant.Tokens -> {
                TopBarLeft(
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    PixoTokenBadge(
                        variant = PixoTokenBadgeVariant.TokensBalance,
                        tokens = tokens,
                        modifier = Modifier.noRippleClick(onTokensClick)
                    )
                }
            }

            PixoTopBarVariant.SettingsWithBalance -> {
                TopBarLeft(
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    PixoTokenBadge(
                        variant = PixoTokenBadgeVariant.BalanceOutline,
                        tokens = tokens.orEmpty(),
                        modifier = Modifier.noRippleClick(onTokensClick)
                    )
                }

                TopBarCenter(
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    PixoLogoText()
                }

                TopBarRight(
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    PixoIconButton(
                        variant = PixoIconButtonVariant.CloseRight,
                        contentDescription = stringResource(R.string.common_close),
                        onClick = onCloseClick
                    )
                }
            }

            PixoTopBarVariant.SettingsSimple -> {
                TopBarCenter(
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    PixoLogoText()
                }

                TopBarRight(
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    PixoIconButton(
                        variant = PixoIconButtonVariant.CloseRight,
                        contentDescription = stringResource(R.string.common_close),
                        onClick = onCloseClick
                    )
                }
            }

            PixoTopBarVariant.Paywall -> {
                TopBarRight(
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    PixoIconButton(
                        variant = PixoIconButtonVariant.CloseTransparent,
                        contentDescription = stringResource(R.string.common_close),
                        onClick = onCloseClick
                    )
                }
            }
        }
    }
}

@Composable
private fun TopBarLeft(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier.height(dimensionResource(R.dimen._44)),
        contentAlignment = Alignment.CenterStart
    ) {
        content()
    }
}

@Composable
private fun TopBarCenter(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier.height(dimensionResource(R.dimen._44)),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
fun TopBarRight(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier.height(dimensionResource(R.dimen._44)),
        contentAlignment = Alignment.CenterEnd
    ) {
        content()
    }
}

@Composable
private fun PixoScreenTitle(
    @StringRes titleRes: Int?,
    onClick: () -> Unit = {}
) {
    if (titleRes == null) return

    Text(
        text = stringResource(titleRes),
        color = LabelPrimary,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onClick
        )
    )
}

@Composable
private fun PixoLogoText() {
    Icon(
        painter = painterResource(R.drawable.ic_pixo),
        contentDescription = stringResource(R.string.app_name),
        tint = Color.Unspecified,
        modifier = Modifier
            .width(dimensionResource(R.dimen._55))
            .height(dimensionResource(R.dimen._21))
    )
}

@Preview(name = "PixoTopBar / Main guest", showBackground = true)
@Composable
private fun PixoTopBarMainGuestPreview() {
    PixoTopBarPreviewContainer {
        PixoTopBar(
            variant = PixoTopBarVariant.MainGuest
        )
    }
}

@Preview(name = "PixoTopBar / Main guest no logo", showBackground = true)
@Composable
private fun PixoTopBarMainGuestNoLogoPreview() {
    PixoTopBarPreviewContainer {
        PixoTopBar(
            variant = PixoTopBarVariant.MainGuestNoLogo
        )
    }
}

@Preview(name = "PixoTopBar / Main subscribed", showBackground = true)
@Composable
private fun PixoTopBarMainSubscribedPreview() {
    PixoTopBarPreviewContainer {
        PixoTopBar(
            variant = PixoTopBarVariant.MainSubscribed,
            tokens = DEBUG_TOKENS_BALANCE
//            tokens = "240"
        )
    }
}

@Preview(name = "PixoTopBar / Detail", showBackground = true)
@Composable
private fun PixoTopBarDetailPreview() {
    PixoTopBarPreviewContainer {
        PixoTopBar(
            variant = PixoTopBarVariant.Detail,
            titleRes = R.string.tool_glam_makeup
        )
    }
}

@Preview(name = "PixoTopBar / Result", showBackground = true)
@Composable
private fun PixoTopBarResultPreview() {
    PixoTopBarPreviewContainer {
        PixoTopBar(
            variant = PixoTopBarVariant.Result,
            titleRes = R.string.tool_glam_makeup
        )
    }
}

@Preview(name = "PixoTopBar / Generation", showBackground = true)
@Composable
private fun PixoTopBarGenerationPreview() {
    PixoTopBarPreviewContainer {
        PixoTopBar(
            variant = PixoTopBarVariant.Generation
        )
    }
}

@Preview(name = "PixoTopBar / Camera", showBackground = true)
@Composable
private fun PixoTopBarCameraPreview() {
    PixoTopBarPreviewContainer {
        PixoTopBar(
            variant = PixoTopBarVariant.Camera
        )
    }
}

@Preview(name = "PixoTopBar / Tokens", showBackground = true)
@Composable
private fun PixoTopBarTokensPreview() {
    PixoTopBarPreviewContainer {
        PixoTopBar(
            variant = PixoTopBarVariant.Tokens,
            tokens = DEBUG_TOKENS_BALANCE
        )
    }
}

@Preview(name = "PixoTopBar / Settings balance", showBackground = true)
@Composable
private fun PixoTopBarSettingsWithBalancePreview() {
    PixoTopBarPreviewContainer {
        PixoTopBar(
            variant = PixoTopBarVariant.SettingsWithBalance,
            tokens = DEBUG_TOKENS_BALANCE
        )
    }
}

@Preview(name = "PixoTopBar / Settings simple", showBackground = true)
@Composable
private fun PixoTopBarSettingsSimplePreview() {
    PixoTopBarPreviewContainer {
        PixoTopBar(
            variant = PixoTopBarVariant.SettingsSimple
        )
    }
}

@Preview(name = "PixoTopBar / Paywall", showBackground = true)
@Composable
private fun PixoTopBarPaywallPreview() {
    PixoTopBarPreviewContainer {
        PixoTopBar(
            variant = PixoTopBarVariant.Paywall
        )
    }
}

@Composable
fun PixoTopBarPreviewContainer(
    content: @Composable () -> Unit
) {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(R.dimen._390))
                .height(dimensionResource(R.dimen._52))
                .background(BackgroundPrimary)
        ) {
            content()
        }
    }
}