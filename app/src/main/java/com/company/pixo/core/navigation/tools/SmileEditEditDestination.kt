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
import com.company.pixo.feature.editor.PixoSmileEditEditScreen

fun NavGraphBuilder.smileEditEditDestination(
    navController: NavHostController,
    actions: AppNavigationActions
) {
    composable(
        route = AppRoute.SmileEditEdit.route,
        arguments = listOf(
            navArgument("imageUri") {
                type = NavType.StringType
                nullable = true
                defaultValue = ""
            }
        )
    ) {
        val imageUri = it.arguments
            ?.getString("imageUri")
            .orEmpty()

        PixoSmileEditEditScreen(
            imageUri = imageUri,
            onBackClick = {
                navController.popBackStack()
            },
            onGenerateClick = { smileLevel ->
                    val request = GenerationCreateRequest(
                        toolType = ToolType.SMILE_EDIT,
                        backendType = ToolBackendType.SMILE_EDIT,
                        serverAction = "smile_edit",
                        sourceImageUrl = null,
                        sourceImageUri = imageUri,
                        prompt = null,
                        templateId = null,
                        options = mapOf(
                            "smileLevel" to smileLevel.toString()
                        ),
                        tokenCost = 2,
                        outputCount = 2,
                        historyIdentity = ToolType.SMILE_EDIT.name
                    )

                actions.createGenerationWithValidation(
                    request = request
                )
            }
        )
    }
}
