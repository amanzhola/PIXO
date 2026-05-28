package com.company.pixo.feature.main

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import com.company.pixo.R
import com.company.pixo.domain.model.ToolType
import com.company.pixo.feature.history.HistoryRoute
import com.company.pixo.feature.history.PixoHistoryItem
import com.company.pixo.feature.history.PixoHistoryScreen
import com.company.pixo.feature.photo.ToolPhotoSourceScreenVariant
import com.company.pixo.feature.prompt.PromptFlowRoute
import com.company.pixo.feature.prompt.PromptViewModel
import com.company.pixo.feature.templates.PixoTemplatesScreen
import com.company.pixo.feature.tools.PixoToolsScreen

private const val USE_HISTORY_ROUTE = true

@Composable
fun MainScreen(
    initialTab: MainTab = MainTab.Tools,
    hasActiveSubscription: Boolean = false,
    showTokenBalanceBadge: Boolean = false,
    tokens: String = "1000",
    onSettingsClick: () -> Unit = {},
    onPaywallClick: () -> Unit = {},
    onTokenPaywallClick: () -> Unit = {},
    onGenerateClick: (onDismiss: (() -> Unit)?) -> Unit = { _ -> },
    onAiEnhancherClick: (ToolPhotoSourceScreenVariant) -> Unit = { _ -> },
    onGlamMakeupClick: (ToolPhotoSourceScreenVariant) -> Unit = { _ -> },
    onRemoveObjectsClick: (ToolPhotoSourceScreenVariant) -> Unit = { _ -> },
    onRemoveBackgroundClick: (ToolPhotoSourceScreenVariant) -> Unit = { _ -> },
    onSkinImproveClick: (ToolPhotoSourceScreenVariant) -> Unit = { _ -> },
    onUpscaleImageClick: (ToolPhotoSourceScreenVariant) -> Unit = { _ -> },
    onChangeSceneClick: (ToolPhotoSourceScreenVariant) -> Unit = { _ -> },
    onHairStudioClick: (ToolPhotoSourceScreenVariant) -> Unit = { _ -> },
    onSmileEditClick: (ToolPhotoSourceScreenVariant) -> Unit = { _ -> },
    onGhostFaceClick: (ToolPhotoSourceScreenVariant) -> Unit = { _ -> },
    onGhibliLookClick: (ToolPhotoSourceScreenVariant) -> Unit = { _ -> },
    onTemplatesLockedClick: () -> Unit = {},
    onPromptsLockedClick: () -> Unit = {},
    onTemplateClick: (Int) -> Unit = {},
    onPromptPhotoClick: () -> Unit = {},
    promptViewModel: PromptViewModel? = null,
    onHistoryImageClick: (String, ToolType?) -> Unit = { _, _ -> },
    onHistoryLoadingClick: (String, ToolType?) -> Unit = { _, _ -> },
    onHistoryErrorRetryClick: (String, ToolType?) -> Unit = { _, _ -> },
    onHistoryErrorDeleteClick: (String) -> Unit = {},
) {
    var selectedTab by remember(initialTab) {
        mutableStateOf(initialTab)
    }

    var toolsSourceVariant by rememberSaveable {
        mutableStateOf(ToolPhotoSourceScreenVariant.Default)
    }

    fun handleTabClick(tab: MainTab) {
        when (tab) {
            MainTab.Templates -> {
                if (hasActiveSubscription) {
                    selectedTab = tab
                } else {
                    onTemplatesLockedClick()
                }
            }

            MainTab.Prompts -> {
                if (hasActiveSubscription) {
                    selectedTab = tab
                } else {
                    onPromptsLockedClick()
                }
            }

            else -> {
                selectedTab = tab
            }
        }
    }

    when (selectedTab) {
        MainTab.Tools -> {
            PixoToolsScreen(
                tokens = tokens,
                hasActiveSubscription = showTokenBalanceBadge,
                onGetProClick = onPaywallClick,
                onTokenBalanceClick = onTokenPaywallClick,
                onSettingsClick = onSettingsClick,
                onImageLabClick = {},
                onToolClick = { tool, variant ->
                    when (tool.titleRes) {
                        R.string.tool_ai_enhancer -> onAiEnhancherClick(variant)
                        R.string.tool_glam_makeup -> onGlamMakeupClick(variant)
                        R.string.tool_remove_objects -> onRemoveObjectsClick(variant)
                        R.string.tool_remove_background -> onRemoveBackgroundClick(variant)
                        R.string.tool_skin_improve -> onSkinImproveClick(variant)
                        R.string.tool_upscale_image -> onUpscaleImageClick(variant)
                        R.string.tool_change_scene -> onChangeSceneClick(variant)
                        R.string.tool_hair_studio -> onHairStudioClick(variant)
                        R.string.tool_smile_edit -> onSmileEditClick(variant)
                        R.string.tool_ghost_style -> onGhostFaceClick(variant)
                        R.string.tool_ghibli_look -> onGhibliLookClick(variant)
                    }
                },
                onTabClick = { tab -> handleTabClick(tab) },
                toolSourceVariant = toolsSourceVariant,
                onToolSourceVariantChange = {
                    toolsSourceVariant = it
                },
            )
        }

        MainTab.Templates -> {
            PixoTemplatesScreen(
                hasActiveSubscription = showTokenBalanceBadge,
                tokens = tokens,
                selectedTab = MainTab.Templates,
                onGetProClick = onPaywallClick,
                onTokenBalanceClick = onTokenPaywallClick,
                onSettingsClick = onSettingsClick,
                onTemplateClick = onTemplateClick,
                onTabClick = { tab ->
                    handleTabClick(tab)
                }
            )
        }

        MainTab.Prompts -> {
                PromptFlowRoute(
                    hasActiveSubscription = showTokenBalanceBadge,
                    tokens = tokens,
                    onGetProClick = onPaywallClick,
                    onTokenBalanceClick = onTokenPaywallClick,
                    onSettingsClick = onSettingsClick,
                    onGenerateClick = onGenerateClick,
                    onOpenGalleryClick = onPromptPhotoClick,
                    onTabClick = { tab -> handleTabClick(tab) },
                    promptViewModel = promptViewModel
                )
        }

        MainTab.History -> {
            if (USE_HISTORY_ROUTE) {
                HistoryRoute(
                    hasActiveSubscription = showTokenBalanceBadge,
                    tokens = tokens,
                    onGetProClick = onPaywallClick,
                    onTokenBalanceClick = onTokenPaywallClick,
                    onSettingsClick = onSettingsClick,
                    onImageClick = onHistoryImageClick,
                    onTabClick = { tab ->
                        handleTabClick(tab)
                    },
                    onLoadingClick = { taskId, toolType ->
                        Log.d(
                            "HistoryLoadingClick",
                            "MainScreen received loading click taskId=$taskId, toolType=$toolType"
                        )

                        onHistoryLoadingClick(taskId, toolType)
                    },
                    onErrorRetryClick = onHistoryErrorRetryClick,
                    onErrorDeleteClick = onHistoryErrorDeleteClick,
                )
            } else {
                PixoHistoryScreen(
                    hasActiveSubscription = showTokenBalanceBadge,
                    tokens = tokens,
                    items = List(4) { PixoHistoryItem.Empty },
                    showEmptyMessage = true,
                    onGetProClick = onPaywallClick,
                    onTokenBalanceClick = onTokenPaywallClick,
                    onSettingsClick = onSettingsClick,
                    onTabClick = { tab -> handleTabClick(tab) }
                )
            }
        }
    }
}
