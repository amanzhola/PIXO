package com.company.pixo.core.navigation.tools

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.company.pixo.core.navigation.AppNavigationActions
import com.company.pixo.core.navigation.AppRoute
import com.company.pixo.core.ui.toServerTitle
import com.company.pixo.domain.model.GenerationCreateRequest
import com.company.pixo.domain.model.ToolBackendType
import com.company.pixo.domain.model.ToolType
import com.company.pixo.feature.editor.PixoGlamMakeupEditRoute

fun NavGraphBuilder.glamMakeupEditDestination(
    navController: NavHostController,
    actions: AppNavigationActions
) {
    composable(
        route = AppRoute.GlamMakeupEdit.route,
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

        PixoGlamMakeupEditRoute(
            imageUri = imageUri,
            onBackClick = {
                navController.popBackStack()
            },
            onGenerateClick = { selectedStyle, optionalDetails ->
                val request = GenerationCreateRequest(
                    toolType = ToolType.GLAM_MAKEUP,
                    backendType = ToolBackendType.GLAM_MAKEUP,
                    serverAction = "glam_makeup",
                    sourceImageUrl = null,
                    sourceImageUri = imageUri,
                    prompt = null,
                    templateId = null,
                    options = mapOf(
                        "makeupStyle" to selectedStyle.name,
                        "makeupStyleTitle" to selectedStyle.toServerTitle(),
                        "optionalDetails" to optionalDetails
                    ),
                    tokenCost = 2,
                    outputCount = 2,
                    historyIdentity = ToolType.GLAM_MAKEUP.name
                )

                actions.createGenerationWithValidation(
                    request = request
                )
            }
        )
    }
}
