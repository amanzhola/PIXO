package com.company.pixo.core.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.company.pixo.R
import com.company.pixo.core.theme.AccentBlack
import com.company.pixo.core.theme.AccentPrimary
import com.company.pixo.core.theme.AccentRed
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.BgSurface400
import com.company.pixo.core.theme.LabelPrimary
import com.company.pixo.core.theme.LabelTertiary
import com.company.pixo.core.theme.PaywallAccentBackground
import com.company.pixo.core.theme.PaywallStrokeInactive
import com.company.pixo.core.theme.PaywallTextMuted
import com.company.pixo.core.theme.PaywallTextSecondary
import com.company.pixo.core.theme.PixoTheme
import com.company.pixo.core.theme.TextIconSoft700
import com.company.pixo.core.theme.TokensRowBackground
import com.company.pixo.feature.paywall.PaywallProductUi

@Composable
fun PixoOnboardingBeforeAfterScreen(
    @DrawableRes beforeImageRes: Int,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    @DrawableRes afterImageRes: Int? = null,
    @StringRes buttonTextRes: Int = R.string.common_continue,
    onContinueClick: () -> Unit = {}
) {
    var sliderPosition by remember { mutableFloatStateOf(0.5f) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundPrimary)
    ) {
        PixoBeforeAfterSlider(
            beforeImageRes = beforeImageRes,
            afterImageRes = afterImageRes,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(
                    start = dimensionResource(id = R.dimen._16),
                    end = dimensionResource(id = R.dimen._16),
                    top = dimensionResource(id = R.dimen._36)
                )
                .fillMaxWidth()
                .fillMaxHeight(0.66f),
            sliderPosition = sliderPosition,
            onSliderPositionChange = { sliderPosition = it }
        )

        Box(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            PixoOnbTextBlock(
                title = title,
                subtitle = subtitle,
                buttonTextRes = buttonTextRes,
                onButtonClick = onContinueClick
            )
        }
    }
}

@Preview(name = "Pixo / Onboarding / Onb1", showBackground = true)
@Composable
private fun PixoOnboardingImageTextScreenOnb1Preview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._844))
                .background(BackgroundPrimary)
        ) {
            PixoOnboardingBeforeAfterScreen(
                beforeImageRes = R.drawable.onb1,
                title = stringResource(id = R.string.onboarding_ai_photo_enhancer_title),
                subtitle = stringResource(id = R.string.onboarding_ai_photo_enhancer_subtitle)
            )
        }
    }
}

@Preview(name = "Pixo / Onboarding / Onb2", showBackground = true)
@Composable
private fun PixoOnboardingImageTextScreenOnb2Preview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._844))
                .background(BackgroundPrimary)
        ) {
            PixoOnboardingBeforeAfterScreen(
                beforeImageRes = R.drawable.tools_glam_makeup1,
                afterImageRes = R.drawable.tools_glam_makeup2,
                title = stringResource(id = R.string.onboarding_glam_makeover_title),
                subtitle = stringResource(id = R.string.onboarding_glam_makeover_subtitle)
            )
        }
    }
}

@Preview(name = "Pixo / Onboarding / Onb3", showBackground = true)
@Composable
private fun PixoOnboardingImageTextScreenOnb3Preview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._844))
                .background(BackgroundPrimary)
        ) {
            PixoOnboardingBeforeAfterScreen(
                beforeImageRes = R.drawable.tools_remove_objects1,
                afterImageRes = R.drawable.tools_remove_objects2,
                title = stringResource(id = R.string.onboarding_remove_objects_title),
                subtitle = stringResource(id = R.string.onboarding_remove_objects_subtitle)
            )
        }
    }
}

@Preview(name = "Pixo / Onboarding / Onb4", showBackground = true)
@Composable
private fun PixoOnboardingImageTextScreenOnb4Preview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._844))
                .background(BackgroundPrimary)
        ) {
            PixoOnboardingBeforeAfterScreen(
                beforeImageRes = R.drawable.tools_smile_edit,
                title = stringResource(id = R.string.tool_smile_edit),
                subtitle = stringResource(id = R.string.onboarding_smile_edit_subtitle)
            )
        }
    }
}

@Preview(name = "Pixo / Onboarding / Onb5", showBackground = true)
@Composable
private fun PixoOnboardingImageTextScreenOnb5Preview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._844))
                .background(BackgroundPrimary)
        ) {
            PixoOnboardingBeforeAfterScreen(
                beforeImageRes = R.drawable.tools_ghost_style1,
                afterImageRes = R.drawable.tools_ghost_style2,
                title = stringResource(id = R.string.tool_ghost_style),
                subtitle = stringResource(id = R.string.onboarding_ghost_style_subtitle)
            )
        }
    }
}

@Preview(name = "Pixo / Onboarding / Onb6", showBackground = true)
@Composable
private fun PixoOnboardingImageTextScreenOnb6Preview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._844))
                .background(BackgroundPrimary)
        ) {
            PixoOnboardingBeforeAfterScreen(
                beforeImageRes = R.drawable.tools_hair_studio1,
                afterImageRes = R.drawable.tools_hair_studio2,
                title = stringResource(id = R.string.tool_hair_studio),
                subtitle = stringResource(id = R.string.onboarding_hair_studio_subtitle)
            )
        }
    }
}

@Preview(name = "Pixo / Onboarding / Onb7", showBackground = true)
@Composable
private fun PixoOnboardingImageTextScreenOnb7Preview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._844))
                .background(BackgroundPrimary)
        ) {
            PixoOnboardingBeforeAfterScreen(
                beforeImageRes = R.drawable.tools_ghibli_look1,
                afterImageRes = R.drawable.tools_ghibli_look2,
                title = stringResource(id = R.string.tool_ghibli_look),
                subtitle = stringResource(id = R.string.onboarding_ghibli_look_subtitle)
            )
        }
    }
}


@Preview(name = "Pixo / Onboarding / Onb8", showBackground = true)
@Composable
private fun PixoOnboardingImageTextScreenOnb8Preview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._844))
                .background(BackgroundPrimary)
        ) {
            PixoOnboardingBeforeAfterScreen(
                beforeImageRes = R.drawable.onb8,
                title = stringResource(id = R.string.tool_upscale_image),
                subtitle = stringResource(id = R.string.onboarding_upscale_image_subtitle)
            )
        }
    }
}

@Preview(name = "Pixo / Onboarding / Onb9", showBackground = true)
@Composable
private fun PixoOnboardingImageTextScreenOnb9Preview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._844))
                .background(BackgroundPrimary)
        ) {
            PixoOnboardingBeforeAfterScreen(
                beforeImageRes = R.drawable.onb9,
                title = stringResource(id = R.string.tool_remove_background),
                subtitle = stringResource(id = R.string.onboarding_remove_background_subtitle)
            )
        }
    }
}

@Preview(name = "Pixo / Onboarding / Onb10", showBackground = true)
@Composable
private fun PixoOnboardingImageTextScreenOnb10Preview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._844))
                .background(BackgroundPrimary)
        ) {
            PixoOnboardingBeforeAfterScreen(
                beforeImageRes = R.drawable.tools_face1,
                afterImageRes = R.drawable.tools_face2,
                title = stringResource(id = R.string.tool_skin_improve),
                subtitle = stringResource(id = R.string.onboarding_skin_improve_subtitle)
            )
        }
    }
}

@Preview(name = "Pixo / Onboarding / Onb11", showBackground = true)
@Composable
private fun PixoOnboardingImageTextScreenOnb11Preview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._844))
                .background(BackgroundPrimary)
        ) {
            PixoOnboardingBeforeAfterScreen(
                beforeImageRes = R.drawable.onb11_1,
                afterImageRes = R.drawable.onb11_2,
                title = stringResource(id = R.string.tool_change_scene),
                subtitle = stringResource(id = R.string.onboarding_change_scene_subtitle)
            )
        }
    }
}

@Composable
fun PixoProTemplatesBlockPreviewWithBackground(
    @StringRes titleRes: Int,
    onButtonClick: () -> Unit,
    background: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundPrimary)
    ) {
        background()

        Box(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            PixoProTemplatesBlock(
                title = stringResource(id = titleRes),
                onButtonClick = onButtonClick
            )
        }
    }
}

@Preview(name = "Pixo / Pro Templates / Rate RU", showBackground = true)
@Composable
private fun PixoProTemplatesBlockRateRuPreview() {
    PixoProTemplatesBlockPreviewWithBackground(
        titleRes = R.string.rate_ru_users_title,
        onButtonClick = {}
    ) {
        RateBackGround()
    }
}

@Preview(name = "Pixo / Pro Templates / Rate EN", showBackground = true)
@Composable
private fun PixoProTemplatesBlockRateEnPreview() {
    PixoProTemplatesBlockPreviewWithBackground(
        titleRes = R.string.rate_happy_users_title,
        onButtonClick = {}
    ) {
        RateBackGround()
    }
}

@Preview(name = "Pixo / Pro Templates / Onb15 EN", showBackground = true)
@Composable
private fun PixoProTemplatesBlockOnb15WithBgPreview() {
    PixoProTemplatesBlockPreviewWithBackground(
        titleRes = R.string.onboarding_edit_prompts_title,
        onButtonClick = {}
    ) {
        Onb15()
    }
}


@Preview(name = "Pixo / Pro Templates / Onb14 EN", showBackground = true)
@Composable
private fun PixoProTemplatesBlockOnb14WithBgPreview() {
    PixoProTemplatesBlockPreviewWithBackground(
        titleRes = R.string.onboarding_pro_templates_title,
        onButtonClick = {}
    ) {
        Onb14()
    }
}

@Preview(name = "Pixo / Pro Templates / Onb17 RU", showBackground = true)
@Composable
private fun PixoProTemplatesBlockOnb17WithBgPreview() {
    PixoProTemplatesBlockPreviewWithBackground(
        titleRes = R.string.onboarding_prompt_edit_ru_title,
        onButtonClick = {}
    ) {
        Onb15()
    }
}

@Composable
fun RateSuccessScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundPrimary),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.rate_frame), // + option with ic_frame
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._358))
                .height(dimensionResource(id = R.dimen._507_72))
        )
    }
}

@Preview(name = "Pixo / Rate Success Screen", showBackground = true)
@Composable
private fun RateSuccessScreenPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._844))
                .background(BackgroundPrimary)
        ) {
            RateSuccessScreen()
        }
    }
}

enum class RateSetupStepState {
    Loading,
    Done
}

data class RateSetupStep(
    @StringRes val textRes: Int,
    val state: RateSetupStepState
)

@Composable
fun RateSuccessSetupScreen(
    modifier: Modifier = Modifier,
    steps: List<RateSetupStep>
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundPrimary),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._358))
//                .height(dimensionResource(id = R.dimen._507_72)),
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.rate_golden_pixo),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen._250))
                    .height(dimensionResource(id = R.dimen._95_72))
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._40)))

            Column(
                modifier = Modifier.width(dimensionResource(id = R.dimen._326)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.rate_setup_title),
                    color = LabelPrimary,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineLarge
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._28)))

                Box(
                    modifier = Modifier
                        .width(dimensionResource(id = R.dimen._326))
                        .height(dimensionResource(id = R.dimen._4))
                        .clip(RoundedCornerShape(dimensionResource(id = R.dimen._24)))
                        .background(LabelPrimary)
                )
            }

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._28)))

//            Column(
//                modifier = Modifier
//                    .width(dimensionResource(id = R.dimen._358))
//                    .height(dimensionResource(id = R.dimen._256))
//                    .padding(horizontal = dimensionResource(id = R.dimen._16)),
//                verticalArrangement = Arrangement.spacedBy(
//                    dimensionResource(id = R.dimen._14)
//                )
//            ) {
            Column(
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen._358))
                    .wrapContentHeight()
                    .padding(horizontal = dimensionResource(id = R.dimen._16)),
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen._14)
                )
            ) {
                steps.forEach { step ->
                    RateSetupStepRow(step = step)
                }
            }
        }
    }
}

@Composable
fun RateSuccessSetupAnimatedScreen(
    modifier: Modifier = Modifier,
    onFinished: () -> Unit = {}
) {
    var currentLoadingIndex by remember { mutableIntStateOf(0) }
    var allDone by remember { mutableStateOf(false) }
    var finishedCalled by remember { mutableStateOf(false) }

    LaunchedEffect(currentLoadingIndex) {
        kotlinx.coroutines.delay(900)

        if (currentLoadingIndex < rateSetupStepTextResList.lastIndex) {
            currentLoadingIndex += 1
        } else {
            allDone = true
        }
    }

    LaunchedEffect(allDone) {
        if (allDone && !finishedCalled) {
            finishedCalled = true
            kotlinx.coroutines.delay(400)
            onFinished()
        }
    }

    RateSuccessSetupScreen(
        modifier = modifier,
        steps = buildRateSetupSteps(
            currentLoadingIndex = currentLoadingIndex,
            allDone = allDone
        )
    )
}

@Composable
private fun RateSetupStepRow(
    step: RateSetupStep
) {
    Row(
        modifier = Modifier
            .width(dimensionResource(id = R.dimen._326))
            .heightIn(min = dimensionResource(id = R.dimen._32)),
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen._8)
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(
                id = when (step.state) {
                    RateSetupStepState.Loading -> R.drawable.ic_history_loader
                    RateSetupStepState.Done -> R.drawable.ic_check_rate
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._32))
                .height(dimensionResource(id = R.dimen._32))
        )

        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(id = step.textRes),
            color = LabelTertiary,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 2
        )
    }
}

// static version

//@Preview(name = "Pixo / Rate Success Setup Screen", showBackground = true)
//@Composable
//private fun RateSuccessSetupScreenPreview() {
//    PixoTheme {
//        Box(
//            modifier = Modifier
//                .width(dimensionResource(id = R.dimen._390))
//                .height(dimensionResource(id = R.dimen._844))
//                .background(BackgroundPrimary)
//        ) {
//            RateSuccessSetupScreen()
//        }
//    }
//}


// static version -> animated version
@Preview(name = "Pixo / Rate Success Setup Animated Screen", showBackground = true)
@Composable
private fun RateSuccessSetupAnimatedScreenPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._844))
                .background(BackgroundPrimary)
        ) {
            RateSuccessSetupAnimatedScreen()
        }
    }
}

private val rateSetupStepTextResList = listOf(
    R.string.rate_setup_creative_space,
    R.string.rate_setup_style,
    R.string.rate_setup_tools,
    R.string.rate_setup_personalizing
)

private fun buildRateSetupSteps(
    currentLoadingIndex: Int,
    allDone: Boolean
): List<RateSetupStep> {
    return rateSetupStepTextResList
        .take(
            if (allDone) {
                rateSetupStepTextResList.size
            } else {
                currentLoadingIndex + 1
            }
        )
        .mapIndexed { index, textRes ->
            RateSetupStep(
                textRes = textRes,
                state = if (allDone || index < currentLoadingIndex) {
                    RateSetupStepState.Done
                } else {
                    RateSetupStepState.Loading
                }
            )
        }
}

@Composable
fun PixoPaywallScreen(
    modifier: Modifier = Modifier,
    yearlySelected: Boolean = true,
    weeklySelected: Boolean = false,
    yearlyProduct: PaywallProductUi? = null,
    weeklyProduct: PaywallProductUi? = null,
    isLoading: Boolean = false,
    @StringRes errorMessageRes: Int? = null,
    purchaseEnabled: Boolean = true,
    onYearlyClick: () -> Unit = {},
    onWeeklyClick: () -> Unit = {},
    onContinueClick: () -> Unit = {},
    onCloseClick: () -> Unit = {},
    onTermsClick: () -> Unit = {},
    onPrivacyClick: () -> Unit = {},
    onRestoreClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundPrimary)
    ) {
        PixoPaywallCardsBackground()

        PixoFloatingCloseButton(
            onClick = onCloseClick
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._541))
                .padding(horizontal = dimensionResource(id = R.dimen._16)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen._24))
        ) {
            PaywallTextFrame()

            errorMessageRes?.let { messageRes ->
                Text(
                    text = stringResource(messageRes),
                    color = AccentRed,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Column(
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen._358))
                    .height(dimensionResource(id = R.dimen._146)),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen._18))
            ) {
                PaywallPlanCard(
                    selected = yearlySelected,
                    showBadge = true,
                    title = yearlyProduct?.title ?: stringResource(R.string.paywall_yearly_title),
                    tokens = yearlyProduct?.let { product ->
                        stringResource(R.string.paywall_includes_tokens, product.tokensIncluded)
                    } ?: stringResource(R.string.paywall_yearly_tokens),
                    price = yearlyProduct?.priceText ?: stringResource(R.string.paywall_yearly_price),
                    period = yearlyProduct?.periodText ?: stringResource(R.string.paywall_yearly_period),
                    onClick = onYearlyClick
                )

                PaywallPlanCard(
                    selected = weeklySelected,
                    showBadge = false,
                    title = weeklyProduct?.title ?: stringResource(R.string.paywall_weekly_title),
                    tokens = weeklyProduct?.let { product ->
                        stringResource(R.string.paywall_includes_tokens, product.tokensIncluded)
                    } ?: stringResource(R.string.paywall_weekly_tokens),
                    price = weeklyProduct?.priceText ?: stringResource(R.string.paywall_weekly_price),
                    period = weeklyProduct?.periodText ?: stringResource(R.string.paywall_weekly_period),
                    onClick = onWeeklyClick
                )
            }
            PaywallBottomActions(
                isLoading = isLoading,
                purchaseEnabled = purchaseEnabled,
                onContinueClick = onContinueClick,
                onTermsClick = onTermsClick,
                onPrivacyClick = onPrivacyClick,
                onRestoreClick = onRestoreClick
            )
        }
    }
}

@Composable
private fun PaywallTextFrame() {
    Column(
        modifier = Modifier
            .width(dimensionResource(id = R.dimen._358))
            .height(dimensionResource(id = R.dimen._219)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen._12))
    ) {
        Text(
            text = stringResource(id = R.string.paywall_title),
            color = LabelPrimary,
            style = MaterialTheme.typography.displaySmall
        )

        PaywallFeatureRow(R.string.paywall_feature_portraits)
        PaywallFeatureRow(R.string.paywall_feature_objects)
        PaywallFeatureRow(R.string.paywall_feature_enhance)
        PaywallFeatureRow(R.string.paywall_feature_templates)
    }
}

@Composable
private fun PaywallFeatureRow(
    @StringRes textRes: Int
) {
    Row(
        modifier = Modifier
            .width(dimensionResource(id = R.dimen._358))
            .height(dimensionResource(id = R.dimen._31)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen._8)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_paywall_blur_bg),
            contentDescription = null,
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._31))
                .height(dimensionResource(id = R.dimen._31))
        )

        Text(
            text = stringResource(id = textRes),
            color = LabelPrimary,
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Composable
private fun PaywallPlanCard(
    selected: Boolean,
    showBadge: Boolean,
    title: String,
    tokens: String,
    price: String,
    period: String,
    onClick: () -> Unit
){
    Box {
        Row(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._358))
                .height(dimensionResource(id = R.dimen._64))
                .clip(RoundedCornerShape(dimensionResource(id = R.dimen._12)))
                .background(
                    if (selected) PaywallAccentBackground else BackgroundPrimary
                )
                .border(
                    width = dimensionResource(id = R.dimen._2),
                    color = if (selected) AccentPrimary else PaywallStrokeInactive,
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen._12))
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onClick
                )
                .padding(
                    horizontal = dimensionResource(id = R.dimen._18),
                    vertical = dimensionResource(id = R.dimen._12)
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = title,
                    color = LabelPrimary,
                    style = MaterialTheme.typography.titleSmall
                )

                Text(
                    text = tokens,
                    color = PaywallTextSecondary,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = price,
                    color = LabelPrimary,
                    style = MaterialTheme.typography.titleSmall
                )

                Text(
                    text = period,
                    color = PaywallTextSecondary,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        if (selected && showBadge) {
//            Box(
//                modifier = Modifier
//                    .align(Alignment.TopEnd)
//                    .padding(end = dimensionResource(id = R.dimen._110))
//                    .offset(y = -dimensionResource(id = R.dimen._12))
//                    .width(dimensionResource(id = R.dimen._91))
//                    .height(dimensionResource(id = R.dimen._24))
//                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen._150)))
//                    .background(AccentPrimary),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = stringResource(id = R.string.paywall_most_popular),
//                    color = AccentBlack,
//                    style = MaterialTheme.typography.bodySmall
//                )
//            }
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = -dimensionResource(id = R.dimen._12))
                    .height(dimensionResource(id = R.dimen._24))
                    .wrapContentWidth()
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen._150)))
                    .background(AccentPrimary)
                    .padding(horizontal = dimensionResource(id = R.dimen._12)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.paywall_most_popular),
                    color = AccentBlack,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                    softWrap = false
                )
            }
        }
    }
}

@Composable
private fun PaywallBottomActions(
    isLoading: Boolean,
    purchaseEnabled: Boolean,
    onContinueClick: () -> Unit,
    onTermsClick: () -> Unit,
    onPrivacyClick: () -> Unit,
    onRestoreClick: () -> Unit
){
    Column(
        modifier = Modifier
            .width(dimensionResource(id = R.dimen._358))
            .height(dimensionResource(id = R.dimen._116)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen._16)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.paywall_cancel_anytime),
            color = PaywallTextMuted,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall
        )

        PixoButton(
            textRes = R.string.common_continue,
            modifier = Modifier.width(dimensionResource(id = R.dimen._358)),
            enabled = !isLoading && purchaseEnabled,
            onClick = onContinueClick
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen._8)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PaywallFooterText(R.string.common_terms_of_use, onTermsClick)
            PaywallDot()
            PaywallFooterText(R.string.common_privacy_policy, onPrivacyClick)
            PaywallDot()
            PaywallFooterText(R.string.common_restore, onRestoreClick)
        }
    }
}

@Composable
private fun PaywallFooterText(
    @StringRes textRes: Int,
    onClick: () -> Unit
) {
    Text(
        text = stringResource(id = textRes),
        color = TextIconSoft700,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onClick
        )
    )
}

@Composable
private fun PaywallDot() {
    Image(
        painter = painterResource(id = R.drawable.ic_dot_paywall),
        contentDescription = null
    )
}

@Preview(name = "Pixo / Paywall Screen", showBackground = true)
@Composable
private fun PixoPaywallScreenPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._844))
                .background(BackgroundPrimary)
        ) {
            PixoPaywallScreen()
        }
    }
}

@Preview(name = "Pixo / Paywall Screen / Weekly Selected", showBackground = true)
@Composable
private fun PixoPaywallScreenWeeklySelectedPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._844))
                .background(BackgroundPrimary)
        ) {
            PixoPaywallScreen(
                yearlySelected = false,
                weeklySelected = true
            )
        }
    }
}

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
){
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
            imageRes = R.drawable.tokens_hostface
        )

        PixoTopBar(
            variant = PixoTopBarVariant.Tokens,
            tokens = tokens
        )

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = dimensionResource(id = R.dimen._390)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen._358))
                    .height(dimensionResource(id = R.dimen._424))
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
                    modifier = Modifier
                        .width(dimensionResource(id = R.dimen._318))
                        .height(dimensionResource(id = R.dimen._216)),
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
        }

        PixoTokensFooter(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = dimensionResource(id = R.dimen._37)),
            onTermsClick = onTermsClick,
            onPrivacyClick = onPrivacyClick,
            onRestoreClick = onRestoreClick
        )

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
        modifier = Modifier.width(dimensionResource(id = R.dimen._318)),
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
            .width(dimensionResource(id = R.dimen._318))
            .height(dimensionResource(id = R.dimen._48))
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

@Composable
private fun PixoTokensFooter(
    modifier: Modifier = Modifier,
    onTermsClick: () -> Unit,
    onPrivacyClick: () -> Unit,
    onRestoreClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen._8)
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        PaywallFooterText(R.string.common_terms_of_use, onTermsClick)
        PaywallDot()
        PaywallFooterText(R.string.common_privacy_policy, onPrivacyClick)
        PaywallDot()
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

@Composable
fun PixoProTemplatesBlockPreviewWithDialogBackground(
    @StringRes titleRes: Int,
    onButtonClick: () -> Unit,
    background: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundPrimary)
    ) {
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = maxHeight * 0.1f)
            ) {
                background()
            }

            PixoDialog(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = maxHeight * 0.2f)
            )
        }
        Box(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            PixoProTemplatesBlock(
                title = stringResource(id = titleRes),
                onButtonClick = onButtonClick
            )
        }
    }
}

@Preview(name = "Pixo / Pro Templates / Onb15 Dialog", showBackground = true)
@Composable
private fun PixoProTemplatesBlockOnb15DialogPreview() {
    PixoProTemplatesBlockPreviewWithDialogBackground(
        titleRes = R.string.onboarding_edit_prompts_title,
        onButtonClick = {}
    ) {
        Onb15()
    }
}

@Preview(name = "Pixo / Pro Templates / Onb17 Dialog", showBackground = true)
@Composable
private fun PixoProTemplatesBlockOnb17DialogPreview() {
    PixoProTemplatesBlockPreviewWithDialogBackground(
        titleRes = R.string.onboarding_prompt_edit_ru_title,
        onButtonClick = {}
    ) {
        Onb15()
    }
}

@Composable
fun PixoProRateScreen(
    onContinueClick: () -> Unit
) {
    PixoProTemplatesBlockPreviewWithBackground(
        titleRes = R.string.rate_happy_users_title,
        onButtonClick = onContinueClick
    ) {
        RateBackGround()
    }
}

@Composable
fun PixoTemplatesOnboardingScreen(
    onContinueClick: () -> Unit
) {
    PixoProTemplatesBlockPreviewWithBackground(
        titleRes = R.string.onboarding_pro_templates_title,
        onButtonClick = onContinueClick
    ) {
        Onb14()
    }
}

@Composable
fun PixoPromptsOnboardingScreen(
    onContinueClick: () -> Unit
) {
    PixoProTemplatesBlockPreviewWithDialogBackground(
        titleRes = R.string.onboarding_edit_prompts_title,
        onButtonClick = onContinueClick
    ) {
        Onb15()
    }
}