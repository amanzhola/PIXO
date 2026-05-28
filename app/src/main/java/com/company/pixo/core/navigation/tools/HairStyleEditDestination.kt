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
import com.company.pixo.feature.editor.PixoHairStyleEditRoute

fun NavGraphBuilder.hairStyleEditDestination(
    navController: NavHostController,
    actions: AppNavigationActions
) {
    composable(
        route = AppRoute.HairStyleEdit.route,
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

        PixoHairStyleEditRoute(
            imageUri = imageUri,
            onBackClick = {
                navController.popBackStack()
            },
            onGenerateClick = { hairstyle, length, color ->
                val request = GenerationCreateRequest(
                    toolType = ToolType.HAIR_STUDIO,
                    backendType = ToolBackendType.HAIR_STUDIO,
                    serverAction = "hair_studio",
                    sourceImageUrl = null,
                    sourceImageUri = imageUri,
                    prompt = null,
                    templateId = null,
                    options = mapOf(
                        "hairstyle" to hairstyle,
                        "length" to length,
                        "color" to color
                    ),
                    tokenCost = 2,
                    outputCount = 2,
                    historyIdentity = ToolType.HAIR_STUDIO.name
                )

                actions.createGenerationWithValidation(
                    request = request
                )
            }
        )
    }
}
