package com.company.pixo.feature.prompt

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.company.pixo.R
import com.company.pixo.core.theme.AccentWhite
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.DialogBorder
import com.company.pixo.core.theme.PixoTheme
import com.company.pixo.core.ui.PixoBottomNavigation
import com.company.pixo.core.ui.PixoButton
import com.company.pixo.core.ui.PixoDialog
import com.company.pixo.core.ui.PixoPromptPicture
import com.company.pixo.core.ui.PixoTopBar
import com.company.pixo.core.ui.PixoTopBarVariant
import com.company.pixo.feature.main.MainTab

private fun getPromptGenerateTextRes(promptText: String): Int {
    val hasPrompt = promptText.isNotBlank()

    return when {
        !hasPrompt -> R.string.common_generate
        promptText.contains("cartoon", ignoreCase = true) -> R.string.common_generate_four
        else -> R.string.common_generate_two
    }
}

@Composable
fun PixoPromptFlowScreen(
    modifier: Modifier = Modifier,
    promptText: String,
    imageUri: String?,
    onPromptTextChange: (String) -> Unit,
    onPictureClick: () -> Unit,
    onPictureRemoveClick: () -> Unit,
    hasActiveSubscription: Boolean = false,
    tokens: String = "1000",
    onTokenBalanceClick: () -> Unit = {},
    onGetProClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onGenerateClick: () -> Unit,
    onTabClick: (MainTab) -> Unit,
) {
    val hasPrompt = promptText.isNotBlank()
    val hasPicture = !imageUri.isNullOrBlank()
    val canGenerate = hasPrompt && hasPicture

    val generateTextRes = getPromptGenerateTextRes(promptText)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundPrimary)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            PixoTopBar(
                variant = if (hasActiveSubscription) {
                    PixoTopBarVariant.MainSubscribed
                } else {
                    PixoTopBarVariant.MainGuestNoLogo
                },
                tokens = tokens,
                onGetProClick = onGetProClick,
                onTokensClick = onTokenBalanceClick,
                onSettingsClick = onSettingsClick
            )

            PixoPromptFlowScreenContent(
                promptText = promptText,
                imageUri = imageUri,
                onPromptTextChange = onPromptTextChange,
                onPictureClick = onPictureClick,
                onPictureRemoveClick = onPictureRemoveClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(
                        bottom = dimensionResource(R.dimen._111) +
                                dimensionResource(R.dimen._68)
                    ),
            )
        }
        val imeVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .imePadding()
                .padding(
                    start = dimensionResource(R.dimen._16),
                    end = dimensionResource(R.dimen._16),
                    bottom = if (imeVisible) {
                        dimensionResource(R.dimen._12)
                    } else {
                        dimensionResource(R.dimen._111) + dimensionResource(R.dimen._12)
                    }
                )
        ) {
            PixoButton(
                textRes = generateTextRes,
                modifier = Modifier.fillMaxWidth(),
                enabled = canGenerate,
                trailingIconRes = if (hasPrompt) R.drawable.ic_sparkle else null,
                onClick = onGenerateClick
            )
        }

        PixoBottomNavigation(
            selectedTab = MainTab.Prompts,
            onTabClick = onTabClick,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun PixoPromptFlowScreenContent(
    promptText: String,
    imageUri: String?,
    modifier: Modifier = Modifier,
    onPromptTextChange: (String) -> Unit,
    onPictureClick: () -> Unit,
    onPictureRemoveClick: () -> Unit,
) {
    Column(
        modifier
            .fillMaxWidth()
            .background(BackgroundPrimary)
            .padding(horizontal = dimensionResource(id = R.dimen._16)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._8)))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.prompt_flow_title),
            color = AccentWhite,
            style = MaterialTheme.typography.headlineMedium,
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._8)))

        PixoDialog(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = dimensionResource(id = R.dimen._206)),
            inputText = promptText,
            borderColor = DialogBorder,
            borderWidth = 0.5.dp,
            glowEnabled = false,
            onTrashClick = {
                onPromptTextChange("")
            },
            onValueChange = onPromptTextChange,
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._8)))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = dimensionResource(id = R.dimen._8))
        ) {
            PixoPromptPicture(
                imageUri = imageUri,
                onClick = onPictureClick,
                onRemoveClick = onPictureRemoveClick
            )
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(
    name = "Pixo / Prompt Flow Screen Text Generate 4",
    showBackground = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
private fun PixoPromptFlowScreenGenerateFourPreview() {
    PixoTheme {
        PixoPromptFlowScreen(
            promptText = stringResource(id = R.string.prompt_dialog_cartoon),
            imageUri = "preview://prompt_image",
            onPromptTextChange = {},
            onPictureClick = {},
            onPictureRemoveClick = {},
            onGetProClick = {},
            onSettingsClick = {},
            onGenerateClick = {},
            onTabClick = {}
        )
    }
}

@Preview(
    name = "Pixo / Prompt Flow Screen Text Generate 2",
    showBackground = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
private fun PixoPromptFlowScreenGenerateTwoPreview() {
    PixoTheme {
        PixoPromptFlowScreen(
            promptText = stringResource(id = R.string.prompt_dialog_headshot),
            imageUri = "preview://prompt_image",
            onPromptTextChange = {},
            onPictureClick = {},
            onPictureRemoveClick = {},
            onGetProClick = {},
            onSettingsClick = {},
            onGenerateClick = {},
            onTabClick = {}
        )
    }
}

@Preview(
    name = "Pixo / Prompt Flow Screen Empty",
    showBackground = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
private fun PixoPromptFlowScreenEmptyPreview() {
    PixoPromptFlowScreen(
        promptText = "",
        imageUri = "preview://prompt_image",
        onPromptTextChange = {},
        onPictureClick = {},
        onPictureRemoveClick = {},
        onGetProClick = {},
        onSettingsClick = {},
        onGenerateClick = {},
        onTabClick = {}
    )
}
