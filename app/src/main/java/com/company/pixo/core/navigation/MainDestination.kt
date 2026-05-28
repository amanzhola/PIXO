package com.company.pixo.core.navigation

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.company.pixo.data.datastore.AppPreferencesDataSource
import com.company.pixo.domain.model.GenerationCreateRequest
import com.company.pixo.domain.model.ToolBackendType
import com.company.pixo.domain.model.ToolType
import com.company.pixo.domain.repository.GenerationRepository
import com.company.pixo.domain.repository.HistoryRepository
import com.company.pixo.domain.repository.TokenRepository
import com.company.pixo.feature.main.MainScreen
import com.company.pixo.feature.main.MainTab
import com.company.pixo.feature.prompt.PromptViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

fun NavGraphBuilder.mainDestination(
    navController: NavHostController,
    preferences: AppPreferencesDataSource,
    tokenRepository: TokenRepository,
    coroutineScope: CoroutineScope,
    actions: AppNavigationActions,
    hasActiveSubscription: Boolean,
    showTokenBalanceInMainTabs: Boolean,
    repeatPaywallShown: Boolean,
    onRepeatPaywallShownChange: (Boolean) -> Unit,
    openGalleryPicker: (GalleryPickTarget) -> Unit,
    promptViewModel: PromptViewModel,
    generationRepository: GenerationRepository,
    historyRepository: HistoryRepository,
) {
    composable(
        route = AppRoute.Main.route,
        arguments = listOf(
            navArgument("tab") {
                type = NavType.StringType
                nullable = true
                defaultValue = MainTab.Tools.name
            }
        )
    ) { backStackEntry ->

        val initialTab = backStackEntry.arguments
            ?.getString("tab")
            ?.let { value ->
                runCatching {
                    MainTab.valueOf(value)
                }.getOrDefault(MainTab.Tools)
            }
            ?: MainTab.Tools

        LaunchedEffect(Unit) {
            val firstLaunchCompleted = preferences
                .isFirstLaunchCompleted()
                .first()

            val lastPaywallShownAt = preferences
                .getLastPaywallShownAt()
                .first()

            if (!firstLaunchCompleted) {
                preferences.setFirstLaunchCompleted(true)
                return@LaunchedEffect
            }

            val shouldShowRepeatPaywall =
                !hasActiveSubscription &&
                        DEBUG_SHOW_REPEAT_PAYWALL_AFTER_10_SEC &&
                        lastPaywallShownAt == 0L

            if (shouldShowRepeatPaywall) {
                preferences.setLastPaywallShownAt(
                    System.currentTimeMillis()
                )

                delay(10_000)

                navController.navigate(AppRoute.Paywall.route)
            }
        }

        val tokenBalance by tokenRepository
            .getBalance()
            .collectAsState(initial = 0)

        MainScreen(
            initialTab = initialTab,
            hasActiveSubscription = hasActiveSubscription,
            showTokenBalanceBadge = showTokenBalanceInMainTabs,
            tokens = tokenBalance.toString(),
            promptViewModel = promptViewModel,

            onSettingsClick = {
                navController.navigate(AppRoute.Settings.route)
            },

            onPaywallClick = {
                actions.openProFlow()
            },

            onTokenPaywallClick = {
                navController.navigate(AppRoute.TokenPaywall.route)
            },

            onGenerateClick = { onDismiss ->
            val state = promptViewModel.uiState.value

                val isCartoonPrompt = state.promptText.contains(
                    other = "cartoon",
                    ignoreCase = true
                )

                val request = GenerationCreateRequest(
                    toolType = ToolType.PROMPT,
                    backendType = ToolBackendType.PROMPT,
                    serverAction = "prompt",
                    sourceImageUrl = null,
                    sourceImageUri = state.imageUri,
                    prompt = state.promptText,
                    templateId = null,
                    options = emptyMap(),
                    tokenCost = if (isCartoonPrompt) {
                        4
                    } else {
                        2
                    },
                    outputCount = if (isCartoonPrompt) {
                        4
                    } else {
                        2
                    },
                    historyIdentity = ToolType.PROMPT.name
                )

                actions.createGenerationWithValidation(
                    request = request,
                    requiredFieldsValid = state.promptText.isNotBlank() &&
                            !state.imageUri.isNullOrBlank(),
                    onConsentDismiss = onDismiss,
                )
            },

            onTemplatesLockedClick = {
                actions.openPremiumFeature(
                    toolType = ToolType.TEMPLATE,
                    realRoute = AppRoute.Main.route,
                    onboardingRoute = AppRoute.TemplatesOnboarding.route
                )
            },

            onPromptsLockedClick = {
                actions.openPremiumFeature(
                    toolType = ToolType.PROMPT,
                    realRoute = AppRoute.Main.route,
                    onboardingRoute = AppRoute.PromptsOnboarding.route
                )
            },

            onTemplateClick = { templateIndex ->
                navController.navigate(
                    AppRoute.TemplateDetails.createRoute(
                        templateId = templateIndex.toString()
                    )
                )
            },

            onAiEnhancherClick = { sourceVariant ->
                actions.openPremiumFeature(
                    toolType = ToolType.AI_ENHANCER,
                    realRoute = AppRoute.ToolMain.createRoute(ToolType.AI_ENHANCER, variant = sourceVariant),
                    onboardingRoute = AppRoute.AiEnhancherOnboarding.route
                )
            },

            onGlamMakeupClick = { sourceVariant ->
                actions.openPremiumFeature(
                    toolType = ToolType.GLAM_MAKEUP,
                    realRoute = AppRoute.ToolMain.createRoute(ToolType.GLAM_MAKEUP, variant = sourceVariant),
                    onboardingRoute = AppRoute.GlamMakeupOnboarding.route
                )
            },

            onRemoveObjectsClick = { sourceVariant ->
                actions.openPremiumFeature(
                    toolType = ToolType.REMOVE_OBJECTS,
                    realRoute = AppRoute.ToolMain.createRoute(ToolType.REMOVE_OBJECTS, variant = sourceVariant),
                    onboardingRoute = AppRoute.RemoveObjectsOnboarding.route
                )
            },

            onRemoveBackgroundClick = { sourceVariant ->
                actions.openPremiumFeature(
                    toolType = ToolType.REMOVE_BACKGROUND,
                    realRoute = AppRoute.ToolMain.createRoute(ToolType.REMOVE_BACKGROUND, variant = sourceVariant),
                    onboardingRoute = AppRoute.RemoveBackgroundOnboarding.route
                )
            },

            onSkinImproveClick = { sourceVariant ->
                actions.openPremiumFeature(
                    toolType = ToolType.SKIN_IMPROVE,
                    realRoute = AppRoute.ToolMain.createRoute(ToolType.SKIN_IMPROVE, variant = sourceVariant),
                    onboardingRoute = AppRoute.SkinImproveOnboarding.route
                )
            },

            onUpscaleImageClick = { sourceVariant ->
                actions.openPremiumFeature(
                    toolType = ToolType.UPSCALE_IMAGE,
                    realRoute = AppRoute.ToolMain.createRoute(ToolType.UPSCALE_IMAGE, variant = sourceVariant),
                    onboardingRoute = AppRoute.UpscaleImageOnboarding.route
                )
            },

            onChangeSceneClick = { sourceVariant ->
                actions.openPremiumFeature(
                    toolType = ToolType.CHANGE_SCENE,
                    realRoute = AppRoute.ToolMain.createRoute(ToolType.CHANGE_SCENE, variant = sourceVariant),
                    onboardingRoute = AppRoute.ChangeSceneOnboarding.route
                )
            },

            onHairStudioClick = { sourceVariant ->
                actions.openPremiumFeature(
                    toolType = ToolType.HAIR_STUDIO,
                    realRoute = AppRoute.ToolMain.createRoute(ToolType.HAIR_STUDIO, variant = sourceVariant),
                    onboardingRoute = AppRoute.HairStudioOnboarding.route
                )
            },

            onSmileEditClick = { sourceVariant ->
                actions.openPremiumFeature(
                    toolType = ToolType.SMILE_EDIT,
                    realRoute = AppRoute.ToolMain.createRoute(ToolType.SMILE_EDIT, variant = sourceVariant),
                    onboardingRoute = AppRoute.SmileEditOnboarding.route
                )
            },

            onGhostFaceClick = { sourceVariant ->
                actions.openPremiumFeature(
                    toolType = ToolType.GHOSTFACE,
                    realRoute = AppRoute.ToolMain.createRoute(ToolType.GHOSTFACE, variant = sourceVariant),
                    onboardingRoute = AppRoute.GhostFaceOnboarding.route
                )
            },

            onGhibliLookClick = { sourceVariant ->
                actions.openPremiumFeature(
                    toolType = ToolType.GHIBLI,
                    realRoute = AppRoute.ToolMain.createRoute(ToolType.GHIBLI, variant = sourceVariant),
                    onboardingRoute = AppRoute.GhibliLookOnboarding.route
                )
            },

            onPromptPhotoClick = {
                openGalleryPicker(
                    GalleryPickTarget.Prompt
                )
            },

            onHistoryImageClick = { taskId, toolType ->
                Log.d("HistoryDelete", "Navigate History -> Result taskId=$taskId, toolType=$toolType")
                navController.navigate(
                    AppRoute.Result.createRoute(
                        generationId = taskId,
                        toolType = toolType ?: ToolType.PROMPT
                    )
                )
            },

            onHistoryLoadingClick = { taskId, toolType ->
                navController.navigate(
                    AppRoute.Generation.createRoute(
                        taskId = taskId,
                        toolType = toolType ?: ToolType.PROMPT
                    )
                ) {
                    launchSingleTop = true
                }
            },

            onHistoryErrorRetryClick = { taskId, _ ->
                coroutineScope.launch {
                    val request = generationRepository.getRequest(taskId)

                    if (request != null) {
                        historyRepository.deleteById(taskId)

                        actions.createGenerationWithValidation(
                            request = request
                        )
                    }
                }
            },

            onHistoryErrorDeleteClick = { taskId ->
                coroutineScope.launch {
                    historyRepository.deleteById(taskId)
                }
            },
        )
    }
}
