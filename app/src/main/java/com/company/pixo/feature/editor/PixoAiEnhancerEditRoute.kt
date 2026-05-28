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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.company.pixo.R
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.LabelPrimary
import com.company.pixo.core.ui.PixoOptionalDetailsBottomSheetContent
import com.company.pixo.core.ui.PixoProcessingOption
import com.company.pixo.core.ui.firstBulletRes
import com.company.pixo.core.ui.toServerName

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PixoAiEnhancerEditRoute(
    imageUri: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onGenerateClick: (
        selectedOption: PixoProcessingOption,
        customPrompt: String
    ) -> Unit = { _, _ -> }
) {
    var selectedOption by remember {
        mutableStateOf(PixoProcessingOption.HdEnhance)
    }

    var showPromptSwitch by remember {
        mutableStateOf(false)
    }

    var customPrompt by remember {
        mutableStateOf("")
    }

    var bottomSheetValue by remember {
        mutableStateOf("")
    }

    var showBottomSheet by remember {
        mutableStateOf(false)
    }

    var promptSwitchChecked by remember {
        mutableStateOf(true)
    }

    PixoAiEnhancerEditScreen(
        imageUri = imageUri,
        selectedOption = selectedOption,
        showPromptSwitch = showPromptSwitch,
        promptSwitchChecked = promptSwitchChecked,
        customPrompt = customPrompt,
        modifier = modifier,
        onBackClick = onBackClick,
        onOptionClick = { option ->
            selectedOption = option
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
                title = selectedOption.toServerName(),
                hint = stringResource(selectedOption.firstBulletRes()),
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
