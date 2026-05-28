package com.company.pixo.feature.prompt

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.ui.PixoMediaLibraryPermissionDialog
import com.company.pixo.core.ui.photo.PixoPhotoRequirementsSheetHost
import com.company.pixo.feature.main.MainTab
import org.koin.androidx.compose.koinViewModel

@Composable
fun PromptFlowRoute(
    hasActiveSubscription: Boolean = false,
    tokens: String = "1000",
    onGetProClick: () -> Unit = {},
    onTokenBalanceClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onGenerateClick: (onDismiss: (() -> Unit)?) -> Unit = {},
    onOpenGalleryClick: () -> Unit = {},
    onTabClick: (MainTab) -> Unit = {},
    promptViewModel: PromptViewModel? = null
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val density = LocalDensity.current
    val imeVisible = WindowInsets.ime.getBottom(density) > 0

    var restoreKeyboardAfterRequirements by remember {
        mutableStateOf(false)
    }

    var showPhotoRequirementsSheet by remember {
        mutableStateOf(false)
    }

    val viewModel = promptViewModel ?: koinViewModel<PromptViewModel>()

    val uiState by viewModel
        .uiState
        .collectAsStateWithLifecycle()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        PixoPromptFlowScreen(
            promptText = uiState.promptText,
            imageUri = uiState.imageUri,
            onPromptTextChange = viewModel::onPromptTextChange,
            onPictureClick = {
                restoreKeyboardAfterRequirements = imeVisible

                keyboardController?.hide()

                showPhotoRequirementsSheet = true
            },
            onPictureRemoveClick = {
                viewModel.setImageUri(null)
            },
            hasActiveSubscription = hasActiveSubscription,
            tokens = tokens,
            onTokenBalanceClick = onTokenBalanceClick,
            onGetProClick = onGetProClick,
            onSettingsClick = onSettingsClick,
            onGenerateClick = {
                Log.d("AiSheetCancel", "Prompt Generate clicked, imeVisible=$imeVisible")

                keyboardController?.hide()

                onGenerateClick {
                    Log.d("AiSheetCancel", "Prompt onDismiss called")
                    // ничего не возвращаем после cancel
                }
            },
            onTabClick = onTabClick,
        )

        if (uiState.showMediaLibraryDialog) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BackgroundPrimary.copy(alpha = 0.72f)),
                contentAlignment = Alignment.Center
            ) {
                PixoMediaLibraryPermissionDialog(
                    onRestrictAccessClick = {
                        viewModel.hideMediaDialog()
                    },
                    onAllowFullAccessClick = {
                        viewModel.hideMediaDialog()
                        onOpenGalleryClick()
                    },
                    onAllowLimitedAccessClick = {
                        viewModel.hideMediaDialog()
                        onOpenGalleryClick()
                    }
                )
            }
        }

        PixoPhotoRequirementsSheetHost(
            show = showPhotoRequirementsSheet,

            onDismiss = {
                showPhotoRequirementsSheet = false

                if (restoreKeyboardAfterRequirements) {
                    keyboardController?.show()
                }

                restoreKeyboardAfterRequirements = false
            },

            onContinueClick = {
                showPhotoRequirementsSheet = false

                restoreKeyboardAfterRequirements = false

                viewModel.showMediaDialog()
            }
        )
    }
}
