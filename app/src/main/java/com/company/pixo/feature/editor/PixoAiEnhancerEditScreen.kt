package com.company.pixo.feature.editor

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.company.pixo.R
import com.company.pixo.core.ui.PixoAiEnhancerScreenContent
import com.company.pixo.core.ui.PixoProcessingOption
import com.company.pixo.feature.editor.component.PixoToolEditorShell

@Composable
fun PixoAiEnhancerEditScreen(
    imageUri: String,
    selectedOption: PixoProcessingOption,
    showPromptSwitch: Boolean,
    promptSwitchChecked: Boolean,
    customPrompt: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onOptionClick: (PixoProcessingOption) -> Unit,
    onPromptInfoClick: () -> Unit,
    onPromptCheckedChange: (Boolean) -> Unit,
    onGenerateClick: () -> Unit
) {
    PixoToolEditorShell(
        imageUri = imageUri,
        titleRes = R.string.ai_enhancer_title,
        placeholderImageRes = R.drawable.tools_placeholder_ai_enhancher_camera_image,
        onBackClick = onBackClick
    ) {
        PixoAiEnhancerScreenContent(
            selectedOption = selectedOption,
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
