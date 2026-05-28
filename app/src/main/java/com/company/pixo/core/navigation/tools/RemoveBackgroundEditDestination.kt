package com.company.pixo.core.navigation.tools

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.company.pixo.core.navigation.AppNavigationActions
import com.company.pixo.core.navigation.AppRoute
import com.company.pixo.core.ui.PixoRemoveBackgroundType
import com.company.pixo.domain.model.GenerationCreateRequest
import com.company.pixo.domain.model.ToolBackendType
import com.company.pixo.domain.model.ToolType
import com.company.pixo.feature.editor.PixoRemoveBackgroundEditScreen

fun NavGraphBuilder.removeBackgroundEditDestination(
    navController: NavHostController,
    actions: AppNavigationActions
) {
    composable(
        route = AppRoute.RemoveBackgroundEdit.route,
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

        var selectedType by remember {
            mutableStateOf<PixoRemoveBackgroundType?>(null)
        }

        PixoRemoveBackgroundEditScreen(
            imageUri = imageUri,
            selectedType = selectedType,
            onBackClick = {
                navController.popBackStack()
            },
            onTypeClick = { type ->
                selectedType = if (selectedType == type) {
                    null
                } else {
                    type
                }
            },
            onGenerateClick = {
                    val backgroundType = when (selectedType) {
                        PixoRemoveBackgroundType.White -> "white"
                        PixoRemoveBackgroundType.Transparent -> "transparent"
                        null -> "default"
                    }

                    val request = GenerationCreateRequest(
                        toolType = ToolType.REMOVE_BACKGROUND,
                        backendType = ToolBackendType.REMOVE_BACKGROUND,
                        serverAction = "remove_background",
                        sourceImageUrl = null,
                        sourceImageUri = imageUri,
                        prompt = null,
                        templateId = null,
                        options = mapOf(
                            "backgroundType" to backgroundType
                        ),
                        tokenCost = 2,
                        outputCount = 2,
                        historyIdentity = ToolType.REMOVE_BACKGROUND.name
                    )

                actions.createGenerationWithValidation(
                    request = request
                )
            }
        )
    }
}
