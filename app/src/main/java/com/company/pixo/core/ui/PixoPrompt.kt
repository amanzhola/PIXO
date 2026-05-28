package com.company.pixo.core.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.company.pixo.R
import com.company.pixo.core.theme.AccentWhite
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.ColorActionsGhost
import com.company.pixo.core.theme.GlamMakeupTextFieldBorder
import com.company.pixo.core.theme.LabelSecondary
import com.company.pixo.core.theme.LabelTertiary
import com.company.pixo.core.theme.PixoTheme
import com.company.pixo.core.theme.TextIconWhite24

data class PixoTextFieldItem(
    val hint: String,
    val value: String,
    val onClick: () -> Unit
)

@Composable
fun PixoReusableTextFieldBlock(
    title: String,
    hint: String,
    value: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    PixoReusableTextFieldBlock(
        title = title,
        fields = listOf(
            PixoTextFieldItem(
                hint = hint,
                value = value,
                onClick = onClick
            )
        ),
        modifier = modifier
    )
}

@Composable
fun PixoReusableTextFieldBlock(
    title: String,
    fields: List<PixoTextFieldItem>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(BackgroundPrimary),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen._12)
        )
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen._25)),
            text = title,
            color = AccentWhite,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1
        )

        fields.forEach { field ->
            PixoReusableTextFieldItem(
                hint = field.hint,
                value = field.value,
                onClick = field.onClick
            )
        }
    }
}

@Composable
private fun PixoReusableTextFieldItem(
    hint: String,
    value: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen._40))
            .border(
                width = dimensionResource(id = R.dimen._1),
                color = GlamMakeupTextFieldBorder,
                shape = RoundedCornerShape(dimensionResource(id = R.dimen._8))
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(horizontal = dimensionResource(id = R.dimen._12)),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = value.ifEmpty { hint },
            color = if (value.isEmpty()) TextIconWhite24 else AccentWhite,
            style = MaterialTheme.typography.labelMedium,
            maxLines = 1
        )
    }
}

@Composable
fun PixoGlamMakeupStyleTextField(
    value: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    PixoReusableTextFieldBlock(
        title = stringResource(id = R.string.glam_makeup_style_title),
        hint = stringResource(id = R.string.glam_makeup_style_hint),
        value = value,
        onClick = onClick,
        modifier = modifier
    )
}

@Preview(
    name = "Pixo / Glam Makeup Style Text Field empty",
    showBackground = true,
    widthDp = 390,
    heightDp = 120
)
@Composable
private fun PixoGlamMakeupStyleTextFieldEmptyPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._120))
                .background(BackgroundPrimary)
                .padding(horizontal = dimensionResource(id = R.dimen._16)),
            contentAlignment = Alignment.Center
        ) {
            PixoReusableTextFieldBlock(
                title = stringResource(id = R.string.glam_makeup_style_title),
                hint = stringResource(id = R.string.glam_makeup_style_hint),
                value = "",
                onClick = {},
            )
        }
    }
}

@Preview(
    name = "Pixo / Glam Makeup Style Text Field filled",
    showBackground = true,
    widthDp = 390,
    heightDp = 120
)
@Composable
private fun PixoGlamMakeupStyleTextFieldFilledPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._120))
                .background(BackgroundPrimary)
                .padding(horizontal = dimensionResource(id = R.dimen._16)),
            contentAlignment = Alignment.Center
        ) {
            PixoReusableTextFieldBlock(
                title = stringResource(id = R.string.glam_makeup_style_title),
                hint = stringResource(id = R.string.glam_makeup_style_hint),
                value = stringResource(id = R.string.glam_makeup_style_preview_text),
                onClick = {},
            )
        }
    }
}

@Preview(
    name = "Pixo / Glam Makeup Style Text Field empty",
    showBackground = true,
    widthDp = 390,
    heightDp = 120
)
@Composable
private fun PixoRemoveObjectsStyleTextFieldEmptyPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._120))
                .background(BackgroundPrimary)
                .padding(horizontal = dimensionResource(id = R.dimen._16)),
            contentAlignment = Alignment.Center
        ) {
            PixoReusableTextFieldBlock(
                title = stringResource(id = R.string.remove_objects_style_title),
                hint = stringResource(id = R.string.remove_objects_style_hint),
                value = "",
                onClick = {},
            )
        }
    }
}

@Preview(
    name = "Pixo / Glam Makeup Style Text Field filled",
    showBackground = true,
    widthDp = 390,
    heightDp = 120
)
@Composable
private fun PixoRemoveObjectsStyleTextFieldFilledPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._120))
                .background(BackgroundPrimary)
                .padding(horizontal = dimensionResource(id = R.dimen._16)),
            contentAlignment = Alignment.Center
        ) {
            PixoReusableTextFieldBlock(
                title = stringResource(id = R.string.remove_objects_style_title),
                hint = "",
                value = stringResource(id = R.string.remove_objects_style_preview_text),
                onClick = {},
            )
        }
    }
}

@Composable
fun PixoHireStyleDetailsStyleTextField(
    modifier: Modifier = Modifier,
    firstValue: String,
    secondValue: String,
    thirdValue: String,
    @StringRes titleRes: Int = R.string.hair_style_details_title,
    onFirstClick: () -> Unit,
    onSecondClick: () -> Unit,
    onThirdClick: () -> Unit,
) {
    PixoReusableTextFieldBlock(
        title = stringResource(id = titleRes),
        fields = listOf(
            PixoTextFieldItem(
                hint = stringResource(id = R.string.hair_style_details_hint_1),
                value = firstValue,
                onClick = onFirstClick
            ),
            PixoTextFieldItem(
                hint = stringResource(id = R.string.hair_style_details_hint_2),
                value = secondValue,
                onClick = onSecondClick
            ),
            PixoTextFieldItem(
                hint = stringResource(id = R.string.hair_style_details_hint_3),
                value = thirdValue,
                onClick = onThirdClick
            )
        ),
        modifier = modifier
    )
}

@Preview(
    name = "Pixo / Hire Style Details Text Field empty",
    showBackground = true,
    widthDp = 390,
    heightDp = 220
)
@Composable
private fun PixoHireStyleDetailsStyleTextFieldEmptyPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(220.dp)
                .background(BackgroundPrimary)
                .padding(horizontal = dimensionResource(id = R.dimen._16)),
            contentAlignment = Alignment.Center
        ) {
            PixoHireStyleDetailsStyleTextField(
                firstValue = "",
                secondValue = "",
                thirdValue = "",
                onFirstClick = {},
                onSecondClick = {},
                onThirdClick = {}
            )
        }
    }
}

@Preview(
    name = "Pixo / Hire Style Details Text Field filled",
    showBackground = true,
    widthDp = 390,
    heightDp = 220
)
@Composable
private fun PixoHireStyleDetailsStyleTextFieldFilledPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(220.dp)
                .background(BackgroundPrimary)
                .padding(horizontal = dimensionResource(id = R.dimen._16)),
            contentAlignment = Alignment.Center
        ) {
            PixoHireStyleDetailsStyleTextField(
                firstValue = stringResource(id = R.string.hair_style_details_preview_text_1),
                secondValue = stringResource(id = R.string.hair_style_details_preview_text_2),
                thirdValue = stringResource(id = R.string.hair_style_details_preview_text_3),
                onFirstClick = {},
                onSecondClick = {},
                onThirdClick = {}
            )
        }
    }
}

@Composable
fun PixoPromptTextFieldContainer(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = ColorActionsGhost,
                shape = RoundedCornerShape(dimensionResource(R.dimen._16))
            )
            .padding(dimensionResource(R.dimen._16)),
        verticalArrangement = Arrangement.Center,
        content = content
    )
}

@Composable
fun PixoPromptTextField(
    value: String,
    modifier: Modifier = Modifier
) {
    PixoPromptTextFieldContainer(modifier = modifier) {
        Text(
            text = value,
            color = LabelTertiary,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 3
        )
    }
}

@Composable
fun PixoPromptBulletTextField(
    modifier: Modifier = Modifier
) {
    PixoPromptTextFieldContainer(modifier = modifier) {
        PixoPromptBulletText(
            textRes = R.string.upscale_image_prompt_blurry_image
        )

        PixoPromptBulletText(
            textRes = R.string.upscale_image_prompt_restore_sharpness
        )
    }
}

@Composable
private fun PixoPromptBulletText(
    @StringRes textRes: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._8)),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            modifier = Modifier
                .padding(top = dimensionResource(R.dimen._7))
                .size(dimensionResource(R.dimen._4)),
            painter = painterResource(R.drawable.ic_dot_paywall),
            contentDescription = null,
            tint = Color.Unspecified
        )

        Text(
            text = stringResource(textRes),
            color = LabelTertiary,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 2
        )
    }
}

@Preview(
    name = "Pixo / Prompt Text Field",
    showBackground = true,
    widthDp = 390,
    heightDp = 100
)
@Composable
private fun PixoPromptTextFieldPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary),
            contentAlignment = Alignment.Center
        ) {
            PixoPromptTextField(
                value = stringResource(R.string.skin_improve_prompt_smooth_skin)
            )
        }
    }
}

@Preview(
    name = "Pixo / Prompt Bullet Text Field",
    showBackground = true,
    widthDp = 390,
    heightDp = 100
)
@Composable
private fun PixoPromptBulletTextFieldPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary),
            contentAlignment = Alignment.Center
        ) {
            PixoPromptBulletTextField()
        }
    }
}

@Preview(
    name = "Pixo / Prompt Text Field",
    showBackground = true,
    widthDp = 390,
    heightDp = 100
)
@Composable
private fun PixoPromptTextFieldSmileEditPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary),
            contentAlignment = Alignment.Center
        ) {
            PixoPromptTextField(
                value = stringResource(R.string.smile_edit_prompt)
            )
        }
    }
}

@Preview(
    name = "Pixo / Change Scene Text Field empty",
    showBackground = true,
    widthDp = 390,
    heightDp = 120
)
@Composable
private fun PixoChangeSceneTextFieldEmptyPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._120))
                .background(BackgroundPrimary)
                .padding(horizontal = dimensionResource(id = R.dimen._16)),
            contentAlignment = Alignment.Center
        ) {
            PixoReusableTextFieldBlock(
                title = stringResource(id = R.string.change_scene_describe_title),
                hint = stringResource(id = R.string.change_scene_hint),
                value = "",
                onClick = {},
            )
        }
    }
}

@Preview(
    name = "Pixo / Change Scene Text Field filled",
    showBackground = true,
    widthDp = 390,
    heightDp = 120
)
@Composable
private fun ChangeSceneTextFieldFilledPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._120))
                .background(BackgroundPrimary)
                .padding(horizontal = dimensionResource(id = R.dimen._16)),
            contentAlignment = Alignment.Center
        ) {
            PixoReusableTextFieldBlock(
                title = stringResource(id = R.string.change_scene_describe_title),
                hint = "",
                value = stringResource(id = R.string.change_scene_preview_text),
                onClick = {},
            )
        }
    }
}

@Preview(
    name = "Pixo / Prompt Text Field",
    showBackground = true,
    widthDp = 390,
    heightDp = 200
)
@Composable
private fun PixoPromptTextFieldGhibliStylePreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary),
            contentAlignment = Alignment.Center
        ) {
            PixoPromptTextField(
                value = stringResource(R.string.ghibli_prompt)
            )
        }
    }
}

@Preview(
    name = "Pixo / Prompt Text Field",
    showBackground = true,
    widthDp = 390,
    heightDp = 100
)
@Composable
private fun PixoPromptTextFieldGhostFacePreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary),
            contentAlignment = Alignment.Center
        ) {
            PixoPromptTextField(
                value = stringResource(R.string.ghost_face_prompt)
            )
        }
    }
}

@Composable
fun PixoAiEnhancerScreenContent(
    selectedOption: PixoProcessingOption,
    showPromptSwitch: Boolean,
    customPrompt: String,
    onPromptInfoClick: () -> Unit,
    modifier: Modifier = Modifier,
    onOptionClick: (PixoProcessingOption) -> Unit,
    onPromptCheckedChange: (Boolean) -> Unit,
    onGenerateClick: () -> Unit,
    promptSwitchChecked: Boolean,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen._280))
            .background(BackgroundPrimary)
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PixoProcessingOptionsSelector(
                modifier = Modifier.fillMaxWidth(),
                selectedOption = selectedOption,
                onOptionClick = onOptionClick
            )

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen._16)))

            if (showPromptSwitch) {
                PixoAiEnhancerPromptSwitch(
                    checked = promptSwitchChecked,
                    onCheckedChange = onPromptCheckedChange
                )

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen._16)))
            }
            PixoHdEnhancePromptInfo(
                firstBulletRes = selectedOption.firstBulletRes(),
                secondBulletRes = selectedOption.secondBulletRes(),
                customPrompt = customPrompt,
                onClick = onPromptInfoClick
            )
        }

        PixoButton(
            textRes = R.string.common_generate_two,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen._16))
                .navigationBarsPadding()
                .padding(bottom = dimensionResource(R.dimen._8)),
            enabled = true,
            trailingIconRes = R.drawable.ic_sparkle,
            onClick = onGenerateClick
        )
    }
}

@Composable
private fun PixoHdEnhancePromptInfo(
    @StringRes firstBulletRes: Int,
    @StringRes secondBulletRes: Int,
    customPrompt: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen._90))
            .background(
                color = ColorActionsGhost,
                shape = RoundedCornerShape(dimensionResource(R.dimen._16))
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(dimensionResource(R.dimen._16)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._8)),
        horizontalAlignment = Alignment.Start
    ) {
        if (customPrompt.isNotBlank()) {
            Text(
                text = customPrompt,
                color = LabelSecondary,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2
            )
        } else {
            PixoHdEnhanceBulletText(textRes = firstBulletRes)
            PixoHdEnhanceBulletText(textRes = secondBulletRes)
        }
    }
}

@Composable
private fun PixoHdEnhanceBulletText(
    @StringRes textRes: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._4)),
        verticalAlignment = Alignment.Top
    ) {
        Image(
            modifier = Modifier.padding(top = dimensionResource(R.dimen._7)),
            painter = painterResource(R.drawable.ic_dot_paywall),
            contentDescription = null
        )

        Text(
            text = stringResource(textRes),
            color = LabelSecondary,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 2
        )
    }
}

@Preview(
    name = "Pixo / AI Enhancer Screen collapsed",
    showBackground = true,
    widthDp = 390,
    heightDp = 280
)
@Composable
private fun PixoAiEnhancerScreenContentCollapsedPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary),
            contentAlignment = Alignment.TopCenter
        ) {
            PixoAiEnhancerScreenContent(
                modifier = Modifier.height(dimensionResource(R.dimen._280)),
                selectedOption = PixoProcessingOption.HdEnhance,
                showPromptSwitch = false,
                customPrompt = "",
                onPromptInfoClick = {},
                onOptionClick = {},
                onPromptCheckedChange = {},
                onGenerateClick = {},
                promptSwitchChecked = false,
            )
        }
    }
}

@Preview(
    name = "Pixo / AI Enhancer Screen expanded",
    showBackground = true,
    widthDp = 390,
    heightDp = 280
)
@Composable
private fun PixoAiEnhancerScreenContentExpandedPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary),
            contentAlignment = Alignment.TopCenter
        ) {
            PixoAiEnhancerScreenContent(
                modifier = Modifier.height(dimensionResource(R.dimen._280)),
                selectedOption = PixoProcessingOption.HdEnhance,
                showPromptSwitch = true,
                customPrompt = "",
                onPromptInfoClick = {},
                onOptionClick = {},
                onPromptCheckedChange = {},
                onGenerateClick = {},
                promptSwitchChecked = false,
            )
        }
    }
}
