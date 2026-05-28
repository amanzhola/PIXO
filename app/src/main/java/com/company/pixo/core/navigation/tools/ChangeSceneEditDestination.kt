package com.company.pixo.core.navigation.tools

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.company.pixo.R
import com.company.pixo.core.navigation.AppRoute
import com.company.pixo.domain.model.GenerationCreateRequest
import com.company.pixo.domain.model.ToolBackendType
import com.company.pixo.domain.model.ToolType
import com.company.pixo.domain.repository.GenerationRepository
import com.company.pixo.feature.editor.component.PixoSinglePromptEditScreen
import kotlinx.coroutines.launch

fun NavGraphBuilder.changeSceneEditDestination(
    navController: NavHostController,
    generationRepository: GenerationRepository
) {
    composable(
        route = AppRoute.ChangeSceneEdit.route,
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

        val scope = rememberCoroutineScope()

        PixoSinglePromptEditScreen(
            titleRes = R.string.tool_change_scene,
            fieldTitle = stringResource(R.string.change_scene_describe_title),
            fieldHint = stringResource(R.string.change_scene_hint),
            imageUri = imageUri,
            placeholderImageRes = R.drawable.tools_placeholder_change_scene_camera_image,
            value = value,
            onValueChange = {
                value = it
            },
            onBackClick = {
                navController.popBackStack()
            },
            onGenerateClick = {
                scope.launch {
                    val request = GenerationCreateRequest(
                        toolType = ToolType.CHANGE_SCENE,
                        backendType = ToolBackendType.CHANGE_SCENE,
                        serverAction = "change_scene",
                        sourceImageUrl = null,
                        sourceImageUri = imageUri,
                        prompt = value,
                        templateId = null,
                        options = mapOf(
                            "sceneDescription" to value
                        ),
                        tokenCost = 2,
                        outputCount = 2,
                        historyIdentity = ToolType.CHANGE_SCENE.name
                    )

                    val result = generationRepository.createGeneration(request)

                    navController.navigate(
                        AppRoute.Generation.createRoute(
                            taskId = result.taskId,
                            toolType = ToolType.CHANGE_SCENE
                        )
                    )
                }
            }
        )
    }
}