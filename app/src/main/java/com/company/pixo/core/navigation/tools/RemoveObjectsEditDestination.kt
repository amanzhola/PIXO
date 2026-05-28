package com.company.pixo.core.navigation.tools

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.company.pixo.R
import com.company.pixo.core.navigation.AppNavigationActions
import com.company.pixo.core.navigation.AppRoute
import com.company.pixo.domain.model.GenerationCreateRequest
import com.company.pixo.domain.model.ToolBackendType
import com.company.pixo.domain.model.ToolType
import com.company.pixo.feature.editor.component.PixoSinglePromptEditScreen

fun NavGraphBuilder.removeObjectsEditDestination(
    navController: NavHostController,
    actions: AppNavigationActions
) {

    composable(
        route = AppRoute.RemoveObjectsEdit.route,
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

        var value by remember {
            mutableStateOf("")
        }

        PixoSinglePromptEditScreen(
            titleRes = R.string.tool_remove_objects,
            fieldTitle = stringResource(
                R.string.remove_objects_style_title
            ),
            fieldHint = stringResource(
                R.string.remove_objects_style_hint
            ),
            imageUri = imageUri,
            placeholderImageRes = R.drawable.tools_remove_objects_camera_image,
            value = value,
            onValueChange = {
                value = it
            },
            onBackClick = {
                navController.popBackStack()
            },
            onGenerateClick = {
                val request = GenerationCreateRequest(
                    toolType = ToolType.REMOVE_OBJECTS,
                    backendType = ToolBackendType.REMOVE_OBJECTS,
                    serverAction = "remove_objects",
                    sourceImageUrl = null,
                    sourceImageUri = imageUri,
                    prompt = value,
                    templateId = null,
                    options = mapOf(
                        "removeTarget" to value
                    ),
                    tokenCost = 2,
                    outputCount = 2,
                    historyIdentity = ToolType.REMOVE_OBJECTS.name
                )

                actions.createGenerationWithValidation(
                    request = request
                )
            }
        )
    }
}
