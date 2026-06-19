package com.company.pixo.feature.editor

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.company.pixo.R
import com.company.pixo.core.ui.PixoAiEnhancerScreenContent
import com.company.pixo.domain.model.ToolOptionSample
import com.company.pixo.feature.editor.component.PixoToolEditorShell

@Composable
fun PixoAiEnhancerEditScreen(
    imageUri: String,
    selectedOption: ToolOptionSample,
    options: List<ToolOptionSample>,
    showPromptSwitch: Boolean,
    promptSwitchChecked: Boolean,
    customPrompt: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onOptionClick: (ToolOptionSample) -> Unit,
    onPromptInfoClick: () -> Unit,
    onPromptCheckedChange: (Boolean) -> Unit,
    onGenerateClick: () -> Unit
) {
    PixoToolEditorShell(
        imageUri = imageUri,
        titleRes = R.string.ai_enhancer_title,
        modifier = modifier,
        onBackClick = onBackClick
    ) {
        PixoAiEnhancerScreenContent(
            selectedOption = selectedOption,
            options = options,
            showPromptSwitch = showPromptSwitch,
            promptSwitchChecked = promptSwitchChecked,
            customPrompt = customPrompt,
            onOptionClick = onOptionClick,
            onPromptInfoClick = onPromptInfoClick,
            onPromptCheckedChange = onPromptCheckedChange,
            onGenerateClick = onGenerateClick
        )
    }
}
