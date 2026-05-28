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
import com.company.pixo.feature.editor.PixoGhibliEditScreen

fun NavGraphBuilder.ghibliEditDestination(
    navController: NavHostController,
    actions: AppNavigationActions
) {
    composable(
        route = AppRoute.GhibliEdit.route,
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

        PixoGhibliEditScreen(
            imageUri = imageUri,
            onBackClick = {
                navController.popBackStack()
            },
            onGenerateClick = {
                val prompt =
                    "Transform the image into a Studio Ghibli-inspired look: Soft, dreamy hand-drawn textures with warm, pastel colors and delicate outlines."

                val request = GenerationCreateRequest(
                    toolType = ToolType.GHIBLI,
                    backendType = ToolBackendType.GHIBLI,
                    serverAction = "ghibli",
                    sourceImageUrl = null,
                    sourceImageUri = imageUri,
                    prompt = prompt,
                    templateId = null,
                    options = mapOf(
                        "style" to "ghibli",
                        "styleTitle" to "Ghibli style"
                    ),
                    tokenCost = 2,
                    outputCount = 2,
                    historyIdentity = ToolType.GHIBLI.name
                )

                actions.createGenerationWithValidation(
                    request = request
                )
            }
        )
    }
}
