package com.company.pixo.core.navigation.tools

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.company.pixo.core.navigation.AppNavigationActions
import com.company.pixo.core.navigation.AppRoute
import com.company.pixo.domain.model.GenerationCreateRequest
import com.company.pixo.domain.model.ToolBackendType
import com.company.pixo.domain.model.ToolType
import com.company.pixo.feature.editor.PixoSkinImproveEditScreen

fun NavGraphBuilder.skinImproveEditDestination(
    navController: NavHostController,
    actions: AppNavigationActions
) {
    composable(
        route = AppRoute.SkinImproveEdit.route,
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

        PixoSkinImproveEditScreen(
            imageUri = imageUri,
            onBackClick = {
                navController.popBackStack()
            },
            onGenerateClick = {
                val prompt = "Smooth skin, reduce blemishes, and keep natural texture."

                val request = GenerationCreateRequest(
                    toolType = ToolType.SKIN_IMPROVE,
                    backendType = ToolBackendType.SKIN_IMPROVE,
                    serverAction = "skin_improve",
                    sourceImageUrl = null,
                    sourceImageUri = imageUri,
                    prompt = prompt,
                    templateId = null,
                    options = mapOf(
                        "mode" to "skin_improve",
                        "prompt" to prompt
                    ),
                    tokenCost = 2,
                    outputCount = 2,
                    historyIdentity = ToolType.SKIN_IMPROVE.name
                )

                actions.createGenerationWithValidation(
                    request = request
                )
            }
        )
    }
}
