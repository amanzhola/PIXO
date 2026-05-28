package com.company.pixo.core.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.company.pixo.R
import com.company.pixo.core.theme.AccentPrimary
import com.company.pixo.core.theme.AccentWhite
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.LabelPrimary
import com.company.pixo.core.theme.LabelTertiary
import com.company.pixo.core.theme.PhotoSourceButtonBackground
import com.company.pixo.core.theme.PixoTheme

@Composable
fun PixoOnbTextBlock(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    @StringRes buttonTextRes: Int = R.string.common_continue,
    onButtonClick: () -> Unit
) {
    Column(
        modifier = modifier
//            .width(dimensionResource(id = R.dimen._390))
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen._204))
            .background(BackgroundPrimary)
            .padding(horizontal = dimensionResource(id = R.dimen._16)),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen._32)
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
//                .width(dimensionResource(id = R.dimen._358)),
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                color = LabelPrimary,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge
            )

            Text(
                text = subtitle,
                color = LabelTertiary,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall
            )
        }

        PixoButton(
            textRes = buttonTextRes,
//            modifier = Modifier.width(dimensionResource(id = R.dimen._358)),
            modifier = Modifier.fillMaxWidth(),
            onClick = onButtonClick
        )
    }
}

@Preview(name = "Pixo / Onboarding Text Block", showBackground = true)
@Composable
private fun PixoOnbTextBlockPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .background(BackgroundPrimary),
            contentAlignment = Alignment.Center
        ) {
            PixoOnbTextBlock(
                title = stringResource(id = R.string.onboarding_ai_photo_enhancer_title),
                subtitle = stringResource(id = R.string.onboarding_ai_photo_enhancer_subtitle),
                onButtonClick = {}
            )
        }
    }
}

@Preview(name = "Pixo / Onboarding Text Block / Onb2", showBackground = true)
@Composable
private fun PixoOnbTextBlockOnb2Preview() {
    PixoOnbTextBlockPreviewContent(
        titleRes = R.string.onboarding_glam_makeover_title,
        subtitleRes = R.string.onboarding_glam_makeover_subtitle
    )
}

@Preview(name = "Pixo / Onboarding Text Block / Onb8", showBackground = true)
@Composable
private fun PixoOnbTextBlockOnb8Preview() {
    PixoOnbTextBlockPreviewContent(
        titleRes = R.string.tool_upscale_image,
        subtitleRes = R.string.onboarding_upscale_image_subtitle
    )
}

@Preview(name = "Pixo / Onboarding Text Block / Onb9", showBackground = true)
@Composable
private fun PixoOnbTextBlockOnb9Preview() {
    PixoOnbTextBlockPreviewContent(
        titleRes = R.string.tool_remove_background,
        subtitleRes = R.string.onboarding_remove_background_subtitle
    )
}

@Preview(name = "Pixo / Onboarding Text Block / Onb10", showBackground = true)
@Composable
private fun PixoOnbTextBlockOnb10Preview() {
    PixoOnbTextBlockPreviewContent(
        titleRes = R.string.tool_skin_improve,
        subtitleRes = R.string.onboarding_skin_improve_subtitle
    )
}

@Preview(name = "Pixo / Onboarding Text Block / Onb11", showBackground = true)
@Composable
private fun PixoOnbTextBlockOnb11Preview() {
    PixoOnbTextBlockPreviewContent(
        titleRes = R.string.tool_change_scene,
        subtitleRes = R.string.onboarding_change_scene_subtitle
    )
}

@Preview(name = "Pixo / Onboarding Text Block / Onb23", showBackground = true)
@Composable
private fun PixoOnbTextBlockOnb23Preview() {
    PixoOnbTextBlockPreviewContent(
        titleRes = R.string.onboarding_ai_quality_ru_title,
        subtitleRes = R.string.onboarding_ai_quality_ru_subtitle
    )
}

@Preview(name = "Pixo / Onboarding Text Block / Onb18", showBackground = true)
@Composable
private fun PixoOnbTextBlockOnb18Preview() {
    PixoOnbTextBlockPreviewContent(
        titleRes = R.string.onboarding_sharp_quality_ru_title,
        subtitleRes = R.string.onboarding_sharp_quality_ru_subtitle
    )
}

@Preview(name = "Pixo / Onboarding Text Block / Onb20", showBackground = true)
@Composable
private fun PixoOnbTextBlockOnb20Preview() {
    PixoOnbTextBlockPreviewContent(
        titleRes = R.string.onboarding_remove_bg_ru_title,
        subtitleRes = R.string.onboarding_remove_bg_ru_subtitle
    )
}

@Preview(name = "Pixo / Onboarding Text Block / Onb21", showBackground = true)
@Composable
private fun PixoOnbTextBlockOnb21Preview() {
    PixoOnbTextBlockPreviewContent(
        titleRes = R.string.onboarding_skin_improve_ru_title,
        subtitleRes = R.string.onboarding_skin_improve_ru_subtitle
    )
}

@Preview(name = "Pixo / Onboarding Text Block / Onb22", showBackground = true)
@Composable
private fun PixoOnbTextBlockOnb22Preview() {
    PixoOnbTextBlockPreviewContent(
        titleRes = R.string.onboarding_change_bg_ru_title,
        subtitleRes = R.string.onboarding_change_bg_ru_subtitle
    )
}

@Composable
private fun PixoOnbTextBlockPreviewContent(
    @StringRes titleRes: Int,
    @StringRes subtitleRes: Int
) {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .background(BackgroundPrimary),
            contentAlignment = Alignment.Center
        ) {
            PixoOnbTextBlock(
                title = stringResource(id = titleRes),
                subtitle = stringResource(id = subtitleRes),
                onButtonClick = {}
            )
        }
    }
}

@Composable
fun PixoProTemplatesBlock(
    title: String,
    modifier: Modifier = Modifier,
    @StringRes buttonTextRes: Int = R.string.common_continue,
    onButtonClick: () -> Unit
) {
    Column(
        modifier = modifier
            .width(dimensionResource(id = R.dimen._390))
            .wrapContentHeight()
            .navigationBarsPadding()
            .padding(
                start = dimensionResource(id = R.dimen._16),
                end = dimensionResource(id = R.dimen._16),
                bottom = dimensionResource(id = R.dimen._16)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.width(dimensionResource(id = R.dimen._322)),
            text = title,
            color = LabelPrimary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(
            modifier = Modifier.height(dimensionResource(id = R.dimen._32))
        )

        PixoButton(
            textRes = buttonTextRes,
            modifier = Modifier.fillMaxWidth(),
            onClick = onButtonClick
        )
    }
}

@Preview(name = "Pixo / Pro Templates Block / Onb14", showBackground = true)
@Composable
private fun PixoProTemplatesBlockOnb14Preview() {
    PixoProTemplatesBlockPreviewContent(
        titleRes = R.string.onboarding_pro_templates_title
    )
}

@Preview(name = "Pixo / Pro Templates Block / Onb15", showBackground = true)
@Composable
private fun PixoProTemplatesBlockOnb15Preview() {
    PixoProTemplatesBlockPreviewContent(
        titleRes = R.string.onboarding_edit_prompts_title
    )
}

@Preview(name = "Pixo / Pro Templates Block / Rate", showBackground = true)
@Composable
private fun PixoProTemplatesBlockRatePreview() {
    PixoProTemplatesBlockPreviewContent(
        titleRes = R.string.rate_happy_users_title
    )
}

@Composable
private fun PixoProTemplatesBlockPreviewContent(
    @StringRes titleRes: Int
) {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .background(BackgroundPrimary),
            contentAlignment = Alignment.Center
        ) {
            PixoProTemplatesBlock(
                title = stringResource(id = titleRes),
                onButtonClick = {}
            )
        }
    }
}

@Preview(name = "Pixo / Pro Templates Block / Onb17", showBackground = true)
@Composable
private fun PixoProTemplatesBlockOnb17Preview() {
    PixoProTemplatesBlockPreviewContent(
        titleRes = R.string.onboarding_prompt_edit_ru_title
    )
}

@Composable
fun PixoGlamMakeupScreenContent(
    selectedStyle: PixoMakeupStyle,
    optionalDetails: String,
    modifier: Modifier = Modifier,
    onStyleClick: (PixoMakeupStyle) -> Unit,
    onGenerateClick: () -> Unit,
    onOptionalDetailsClick: () -> Unit,
) {
    Column(
        modifier = modifier
//            .width(dimensionResource(R.dimen._390))
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen._16))
            .background(BackgroundPrimary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PixoMakeupStyleSelector(
            modifier = Modifier.fillMaxWidth(),
            selectedStyle = selectedStyle,
            onStyleClick = onStyleClick
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen._16)))

        PixoGlamMakeupStyleTextField(
            value = optionalDetails,
            onClick = onOptionalDetailsClick
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen._9)))

        PixoButton(
            textRes = if (optionalDetails.isNotEmpty()) {
                R.string.common_generate_two
            } else {
                R.string.common_generate
            },
            modifier = Modifier
//                .width(dimensionResource(R.dimen._358))
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(bottom = dimensionResource(R.dimen._8)),
            enabled = optionalDetails.isNotEmpty(),
            trailingIconRes = if (optionalDetails.isNotEmpty()) {
                R.drawable.ic_sparkle
            } else {
                null
            },
            onClick = onGenerateClick
        )
    }
}

@Preview(
    name = "Pixo / Glam Makeup Screen inactive",
    showBackground = true,
    widthDp = 390,
    heightDp = 230
)
@Composable
private fun PixoGlamMakeupScreenContentInactivePreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary),
            contentAlignment = Alignment.TopCenter
        ) {
            PixoGlamMakeupScreenContent(
                selectedStyle = PixoMakeupStyle.NaturalGlow,
                optionalDetails = "",
                onStyleClick = {},
                onGenerateClick = {},
                onOptionalDetailsClick = {}
            )
        }
    }
}

@Preview(
    name = "Pixo / Glam Makeup Screen active",
    showBackground = true,
    widthDp = 390,
    heightDp = 230
)
@Composable
private fun PixoGlamMakeupScreenContentActivePreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary),
            contentAlignment = Alignment.TopCenter
        ) {
            PixoGlamMakeupScreenContent(
                selectedStyle = PixoMakeupStyle.NaturalGlow,
                optionalDetails = stringResource(R.string.glam_makeup_style_preview_text),
                onStyleClick = {},
                onGenerateClick = {},
                onOptionalDetailsClick = {}
            )
        }
    }
}

enum class PixoRemoveBackgroundType {
    White,
    Transparent
}

@Composable
fun PixoRemoveBackgroundScreenContent(
    selectedType: PixoRemoveBackgroundType?,
    modifier: Modifier = Modifier,
    onTypeClick: (PixoRemoveBackgroundType) -> Unit,
    onGenerateClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen._16))
            .background(BackgroundPrimary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PixoRemoveBackgroundTypeSelector(
            selectedType = selectedType,
            onTypeClick = onTypeClick
        )

        PixoRemoveBackgroundExamples(
            selected = selectedType != null,
            firstImageRes = if (selectedType == null) {
                R.drawable.tools_item_selector_image1_1
            } else {
                R.drawable.tools_item_selector_image2_1
            },
            secondImageRes = if (selectedType == null) {
                R.drawable.tools_item_selector_image1_2
            } else {
                R.drawable.tools_item_selector_image2_2
            }
        )

        Spacer(
            modifier = Modifier.height(dimensionResource(R.dimen._8))
        )

        PixoButton(
            textRes = R.string.common_generate_two,
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(bottom = dimensionResource(R.dimen._8)),
            enabled = true,
            trailingIconRes = R.drawable.ic_sparkle,
            onClick = onGenerateClick
        )
    }
}

@Composable
private fun PixoRemoveBackgroundTypeSelector(
    selectedType: PixoRemoveBackgroundType?,
    modifier: Modifier = Modifier,
    onTypeClick: (PixoRemoveBackgroundType) -> Unit
) {
    Column(
        modifier = modifier
            .width(dimensionResource(R.dimen._358))
            .height(dimensionResource(R.dimen._86))
            .padding(
                top = dimensionResource(R.dimen._12),
                bottom = dimensionResource(R.dimen._12)
            ),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._8))
    ) {
        Text(
            modifier = Modifier
                .width(dimensionResource(R.dimen._358))
                .height(dimensionResource(R.dimen._25)),
            text = stringResource(R.string.remove_background_choose_type),
            color = AccentWhite,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1
        )

        Row(
            modifier = Modifier
                .width(dimensionResource(R.dimen._358))
                .height(41.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PixoRemoveBackgroundTypeButton(
                textRes = R.string.photo_mode_white,
                selected = selectedType == PixoRemoveBackgroundType.White,
                modifier = Modifier
                    .width(179.dp)
                    .height(41.dp),
                onClick = { onTypeClick(PixoRemoveBackgroundType.White) }
            )

            PixoRemoveBackgroundTypeButton(
                textRes = R.string.photo_mode_transparent,
                selected = selectedType == PixoRemoveBackgroundType.Transparent,
                modifier = Modifier
                    .width(179.dp)
                    .height(41.dp),
                onClick = { onTypeClick(PixoRemoveBackgroundType.Transparent) }
            )
        }
    }
}

@Composable
private fun PixoRemoveBackgroundTypeButton(
    @StringRes textRes: Int,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(dimensionResource(R.dimen._8))

    Surface(
        modifier = modifier
            .then(
                if (selected) {
                    Modifier.border(
                        width = 1.5.dp,
                        color = AccentPrimary,
                        shape = shape
                    )
                } else {
                    Modifier
                }
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        shape = shape,
        color = if (selected) PhotoSourceButtonBackground else BackgroundPrimary
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen._8)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(textRes),
                color = AccentWhite,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
    }
}

@Composable
private fun PixoRemoveBackgroundExamples(
    selected: Boolean,
    @DrawableRes firstImageRes: Int,
    @DrawableRes secondImageRes: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.width(dimensionResource(R.dimen._358))
    ) {
        Text(
            modifier = Modifier.height(dimensionResource(R.dimen._22)),
            text = stringResource(R.string.remove_background_examples_title),
            color = AccentWhite,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1
        )

        if (selected) {
            Row(
                modifier = Modifier
                    .padding(top = dimensionResource(R.dimen._8))
                    .width(dimensionResource(R.dimen._358)),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._8)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                PixoRemoveBackgroundExampleImage(
                    imageRes = firstImageRes,
                    modifier = Modifier
                        .width(dimensionResource(R.dimen._175))
                        .height(dimensionResource(R.dimen._91))
                )

                PixoRemoveBackgroundExampleImage(
                    imageRes = secondImageRes,
                    modifier = Modifier
                        .width(dimensionResource(R.dimen._175))
                        .height(dimensionResource(R.dimen._91))
                )
            }
        } else {
            Row(
                modifier = Modifier
                    .padding(top = dimensionResource(R.dimen._8))
                    .width(dimensionResource(R.dimen._358)),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._8)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                PixoRemoveBackgroundExampleImage(
                    imageRes = R.drawable.tools_item_selector_image1_1,
                    modifier = Modifier
                        .width(dimensionResource(R.dimen._175))
                        .height(dimensionResource(R.dimen._28))
                )

                PixoRemoveBackgroundExampleImage(
                    imageRes = R.drawable.tools_item_selector_image1_2,
                    modifier = Modifier
                        .width(dimensionResource(R.dimen._175))
                        .height(dimensionResource(R.dimen._28))
                )
            }
        }
    }
}

@Composable
private fun PixoRemoveBackgroundExampleImage(
    @DrawableRes imageRes: Int,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier,
        painter = painterResource(imageRes),
        contentDescription = null,
        contentScale = ContentScale.FillBounds
    )
}

@Preview(
    name = "Pixo / Remove Background Screen none",
    showBackground = true,
    widthDp = 390,
    heightDp = 300
)
@Composable
private fun PixoRemoveBackgroundScreenContentNonePreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary),
            contentAlignment = Alignment.TopCenter
        ) {
            PixoRemoveBackgroundScreenContent(
                selectedType = null,
                onTypeClick = {},
                onGenerateClick = {}
            )
        }
    }
}

@Preview(
    name = "Pixo / Remove Background Screen white active",
    showBackground = true,
    widthDp = 390,
    heightDp = 300
)
@Composable
private fun PixoRemoveBackgroundScreenContentWhitePreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary),
            contentAlignment = Alignment.TopCenter
        ) {
            PixoRemoveBackgroundScreenContent(
                selectedType = PixoRemoveBackgroundType.White,
                onTypeClick = {},
                onGenerateClick = {}
            )
        }
    }
}

@Preview(
    name = "Pixo / Remove Background Screen transparent active",
    showBackground = true,
    widthDp = 390,
    heightDp = 300
)
@Composable
private fun PixoRemoveBackgroundScreenContentTransparentPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary),
            contentAlignment = Alignment.TopCenter
        ) {
            PixoRemoveBackgroundScreenContent(
                selectedType = PixoRemoveBackgroundType.Transparent,
                onTypeClick = {},
                onGenerateClick = {}
            )
        }
    }
}
