package com.company.pixo.feature.editor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.company.pixo.R
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.LabelPrimary
import com.company.pixo.core.ui.PixoOptionalDetailsBottomSheetContent
import com.company.pixo.domain.model.PixoToolConfigs
import com.company.pixo.domain.model.ToolOptionSample
import com.company.pixo.domain.model.ToolType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PixoAiEnhancerEditRoute(
    imageUri: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onGenerateClick: (
        selectedOption: ToolOptionSample,
        customPrompt: String
    ) -> Unit = { _, _ -> }
) {
    val config = remember { PixoToolConfigs.findByType(ToolType.AI_ENHANCER) }
    val options = remember(config) { config?.optionConfig?.samples.orEmpty() }
    if (options.isEmpty()) {
        return
    }
    var selectedOptionId by rememberSaveable {
        mutableStateOf(options.first().id)
    }
    val selectedOption = options.firstOrNull { option ->
        option.id == selectedOptionId
    } ?: options.first()

    var showPromptSwitch by rememberSaveable { mutableStateOf(false) }
    var customPrompt by rememberSaveable { mutableStateOf("") }
    var bottomSheetValue by rememberSaveable { mutableStateOf("") }
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    var promptSwitchChecked by rememberSaveable { mutableStateOf(true) }

    PixoAiEnhancerEditScreen(
        imageUri = imageUri,
        selectedOption = selectedOption,
        options = options,
        showPromptSwitch = showPromptSwitch,
        promptSwitchChecked = promptSwitchChecked,
        customPrompt = customPrompt,
        modifier = modifier,
        onBackClick = onBackClick,
        onOptionClick = { option ->
            selectedOptionId = option.id
        },
        onPromptInfoClick = {
            showPromptSwitch = true
            promptSwitchChecked = true
            bottomSheetValue = customPrompt
            showBottomSheet = true
        },
        onPromptCheckedChange = { checked ->
            promptSwitchChecked = checked

            if (!checked) {
                customPrompt = ""
                showPromptSwitch = false
            }
        },
        onGenerateClick = {
            onGenerateClick(selectedOption, customPrompt)
        }
    )

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            containerColor = BackgroundPrimary,
            dragHandle = {
                Box(
                    modifier = Modifier
                        .padding(top = dimensionResource(R.dimen._12))
                        .width(dimensionResource(R.dimen._32))
                        .height(dimensionResource(R.dimen._4))
                        .clip(RoundedCornerShape(dimensionResource(R.dimen._8)))
                        .background(LabelPrimary)
                )
            }
        ) {
            PixoOptionalDetailsBottomSheetContent(
                title = stringResource(selectedOption.titleRes ?: R.string.tool_ai_enhancer),
                hint = stringResource(selectedOption.firstBulletRes ?: R.string.ai_enhancer_hd_quality),
                value = bottomSheetValue,
                onValueChange = {
                    bottomSheetValue = it
                },
                onCloseClick = {
                    showBottomSheet = false
                },
                onConfirmClick = {
                    customPrompt = bottomSheetValue
                    showPromptSwitch = false
                    showBottomSheet = false
                    promptSwitchChecked = false
                }
            )
        }
    }
}
