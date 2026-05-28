package com.company.pixo.core.navigation

import androidx.navigation.NavHostController
import com.company.pixo.data.datastore.AppPreferencesDataSource
import com.company.pixo.domain.model.GenerationCreateRequest
import com.company.pixo.domain.model.ToolType
import com.company.pixo.domain.repository.GenerationRepository
import com.company.pixo.domain.repository.TokenRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

private var generationConsentCancelled = false

class AppNavigationActions(
    private val navController: NavHostController,
    private val coroutineScope: CoroutineScope,
    private val preferences: AppPreferencesDataSource,
    private val tokenRepository: TokenRepository,
    private val generationRepository: GenerationRepository,
    private val hasActiveSubscription: Boolean,
    private val onShowGenerationConsent: (GenerationCreateRequest, (() -> Unit)?) -> Unit = { _, _ -> },
) {
    fun openProFlow() {
        if (hasActiveSubscription) {
            navController.navigate(AppRoute.TokenPaywall.route)
        } else {
            navController.navigate(AppRoute.ProRate.route)
        }
    }

    fun openPremiumFeature(
        toolType: ToolType,
        realRoute: String,
        onboardingRoute: String
    ) {
        if (hasActiveSubscription) {
            navController.navigate(realRoute)
            return
        }

        if (!ENABLE_PREMIUM_FEATURE_ONBOARDING) {
            navController.navigate(AppRoute.Paywall.route)
            return
        }

        coroutineScope.launch {
            val onboardingAlreadyShown = preferences
                .isPremiumOnboardingShown(toolType.name)
                .first()

            if (onboardingAlreadyShown) {
                navController.navigate(AppRoute.Paywall.route)
            } else {
                navController.navigate(onboardingRoute)
            }
        }
    }
    private var lastConsentClosedAt: Long = 0L

    fun createGenerationWithValidation(
        request: GenerationCreateRequest,
        onRequiredFieldsError: (() -> Unit)? = null,
        requiredFieldsValid: Boolean = true,
        onConsentDismiss: (() -> Unit)? = null,
    ) {
        if (generationConsentCancelled) {

            generationConsentCancelled = false
            return
        }

        val now = android.os.SystemClock.elapsedRealtime()
        if (now - lastConsentClosedAt < 2500L) {
            return
        }

        coroutineScope.launch {
            if (!hasActiveSubscription) {
                openProFlow()
                return@launch
            }

            if (!requiredFieldsValid) {
                onRequiredFieldsError?.invoke()
                return@launch
            }

            val hasEnoughTokens = tokenRepository.hasEnoughTokens(
                request.tokenCost
            )

            if (!hasEnoughTokens) {
                navController.navigate(AppRoute.TokenPaywall.route)
                return@launch
            }

            onShowGenerationConsent(
                request,
                onConsentDismiss,
            )
        }
    }

    fun markGenerationConsentCancelled() {
        generationConsentCancelled = true
    }

    fun createGenerationAfterConsent(
        request: GenerationCreateRequest
    ) {
        coroutineScope.launch {
            val result = generationRepository.createGeneration(request)

            tokenRepository.consumeTokens(request.tokenCost)

            navController.navigate(
                AppRoute.Generation.createRoute(
                    taskId = result.taskId,
                    toolType = request.toolType,
                    templateId = request.templateId
                )
            )
        }
    }

    fun openPickedImage(
        target: GalleryPickTarget,
        imageUri: String
    ) {
        when (target) {
            is GalleryPickTarget.Tool -> {
                openToolEditScreen(
                    toolType = target.toolType,
                    imageUri = imageUri
                )
            }

            is GalleryPickTarget.Template -> {
                navController.navigate(
                    AppRoute.TemplateGenerate.createRoute(
                        templateId = target.templateId,
                        imageUri = imageUri
                    )
                )
            }

            GalleryPickTarget.Prompt -> {
                navController.currentBackStackEntry
                    ?.savedStateHandle
                    ?.set(
                        "promptPickedImageUri",
                        imageUri
                    )
            }
        }
    }

    fun openToolEditScreen(
        toolType: ToolType,
        imageUri: String
    ) {
        when (toolType) {
            ToolType.AI_ENHANCER -> {
                navController.navigate(
                    AppRoute.AiEnhancerEdit.createRoute(imageUri)
                )
            }

            ToolType.GLAM_MAKEUP -> {
                navController.navigate(
                    AppRoute.GlamMakeupEdit.createRoute(imageUri)
                )
            }

            ToolType.REMOVE_OBJECTS -> {
                navController.navigate(
                    AppRoute.RemoveObjectsEdit.createRoute(imageUri)
                )
            }

            ToolType.REMOVE_BACKGROUND -> {
                navController.navigate(
                    AppRoute.RemoveBackgroundEdit.createRoute(imageUri)
                )
            }

            ToolType.SKIN_IMPROVE -> {
                navController.navigate(
                    AppRoute.SkinImproveEdit.createRoute(imageUri)
                )
            }

            ToolType.UPSCALE_IMAGE -> {
                navController.navigate(
                    AppRoute.UpscaleImageEdit.createRoute(imageUri)
                )
            }

            ToolType.CHANGE_SCENE -> {
                navController.navigate(
                    AppRoute.ChangeSceneEdit.createRoute(imageUri)
                )
            }

            ToolType.HAIR_STUDIO -> {
                navController.navigate(
                    AppRoute.HairStyleEdit.createRoute(imageUri)
                )
            }

            ToolType.SMILE_EDIT -> {
                navController.navigate(
                    AppRoute.SmileEditEdit.createRoute(imageUri)
                )
            }

            ToolType.GHOSTFACE -> {
                navController.navigate(
                    AppRoute.GhostfaceEdit.createRoute(imageUri)
                )
            }

            ToolType.GHIBLI -> {
                navController.navigate(
                    AppRoute.GhibliEdit.createRoute(imageUri)
                )
            }

            ToolType.PROMPT,
            ToolType.TEMPLATE -> {
                // handled separately
            }
        }
    }
}
