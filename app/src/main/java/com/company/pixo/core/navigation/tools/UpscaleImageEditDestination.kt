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
import com.company.pixo.feature.editor.PixoUpscaleImageEditScreen

fun NavGraphBuilder.upscaleImageEditDestination(
    navController: NavHostController,
    actions: AppNavigationActions
) {
    composable(
        route = AppRoute.UpscaleImageEdit.route,
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

        PixoUpscaleImageEditScreen(
            imageUri = imageUri,
            onBackClick = {
                navController.popBackStack()
            },
            onGenerateClick = {
                    val request = GenerationCreateRequest(
                        toolType = ToolType.UPSCALE_IMAGE,
                        backendType = ToolBackendType.UPSCALE_IMAGE,
                        serverAction = "upscale_image",

                        sourceImageUrl = null,
                        sourceImageUri = imageUri,

                        prompt = "Upscale image",
                        templateId = null,

                        options = emptyMap(),

                        tokenCost = 2,
                        outputCount = 2,

                        historyIdentity = ToolType.UPSCALE_IMAGE.name
                    )

                actions.createGenerationWithValidation(
                    request = request
                )
            }
        )
    }
}
