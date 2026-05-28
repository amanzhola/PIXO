package com.company.pixo.core.navigation.tools

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.company.pixo.core.navigation.AppNavigationActions
import com.company.pixo.core.navigation.AppRoute
import com.company.pixo.core.ui.toServerName
import com.company.pixo.domain.model.GenerationCreateRequest
import com.company.pixo.domain.model.ToolBackendType
import com.company.pixo.domain.model.ToolType
import com.company.pixo.feature.editor.PixoAiEnhancerEditRoute

fun NavGraphBuilder.aiEnhancerEditDestination(
    navController: NavHostController,
    actions: AppNavigationActions
) {
    composable(
        route = AppRoute.AiEnhancerEdit.route,
        arguments = listOf(
            navArgument("imageUri") {
                type = NavType.StringType
                nullable = true
                defaultValue = ""
            }
        )
    ) { backStackEntry ->

        val imageUri = backStackEntry.arguments
            ?.getString("imageUri")
            .orEmpty()

        PixoAiEnhancerEditRoute(
            imageUri = imageUri,
            onBackClick = {
                navController.popBackStack()
            },
            onGenerateClick = { selectedOption, customPrompt ->
                val request = GenerationCreateRequest(
                    toolType = ToolType.AI_ENHANCER,
                    backendType = ToolBackendType.AI_ENHANCER,
                    serverAction = "ai_enhancer",
                    sourceImageUrl = null,
                    sourceImageUri = imageUri,
                    prompt = customPrompt.ifBlank {
                        selectedOption.toServerName()
                    },
                    templateId = null,
                    options = mapOf(
                        "enhancerMode" to selectedOption.name,
                        "enhancerModeTitle" to selectedOption.toServerName(),
                        "customPrompt" to customPrompt
                    ),
                    tokenCost = 2,
                    outputCount = 2,
                    historyIdentity = ToolType.AI_ENHANCER.name
                )

                actions.createGenerationWithValidation(
                    request = request
                )
            }
        )
    }
}
