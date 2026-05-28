package com.company.pixo.core.navigation.tools

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.company.pixo.core.navigation.AppNavigationActions
import com.company.pixo.core.navigation.AppRoute
import com.company.pixo.domain.model.ToolType
import com.company.pixo.feature.editor.PixoGhostfaceEditScreen
import com.company.pixo.domain.model.GenerationCreateRequest
import com.company.pixo.domain.model.ToolBackendType

fun NavGraphBuilder.ghostfaceEditDestination(
    navController: NavHostController,
    actions: AppNavigationActions
) {
    composable(
        route = AppRoute.GhostfaceEdit.route,
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

        PixoGhostfaceEditScreen(
            imageUri = imageUri,
            onBackClick = {
                navController.popBackStack()
            },
            onGenerateClick = {
                val prompt = "Ghost face"

                val request = GenerationCreateRequest(
                    toolType = ToolType.GHOSTFACE,
                    backendType = ToolBackendType.GHOSTFACE,
                    serverAction = "ghostface",
                    sourceImageUrl = null,
                    sourceImageUri = imageUri,
                    prompt = prompt,
                    templateId = null,
                    options = mapOf(
                        "style" to "ghostface",
                        "prompt" to prompt
                    ),
                    tokenCost = 2,
                    outputCount = 2,
                    historyIdentity = ToolType.GHOSTFACE.name
                )

                actions.createGenerationWithValidation(
                    request = request
                )
            }
        )
    }
}
