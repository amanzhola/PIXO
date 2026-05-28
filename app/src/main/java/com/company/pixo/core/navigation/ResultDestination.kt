package com.company.pixo.core.navigation

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.company.pixo.R
import com.company.pixo.domain.model.ToolType
import com.company.pixo.domain.repository.GenerationRepository
import com.company.pixo.domain.repository.HistoryRepository
import com.company.pixo.domain.repository.MediaRepository
import com.company.pixo.feature.main.MainTab
import com.company.pixo.feature.result.PixoPromptFlowResultScreen
import com.company.pixo.feature.templates.pixoTemplateItems
import kotlinx.coroutines.launch

private fun resultTitleRes(
    toolType: ToolType?,
    templateId: String,
    useGenericTemplateTitle: Boolean
): Int {
    return when (toolType) {
        ToolType.TEMPLATE -> {
            if (useGenericTemplateTitle) {
                R.string.template_title
            } else {
                val index = templateId.toIntOrNull() ?: 0

                pixoTemplateItems
                    .getOrNull(index)
                    ?.titleRes
                    ?: R.string.template_title
            }
        }

        ToolType.AI_ENHANCER -> R.string.ai_enhancer_title
        ToolType.GLAM_MAKEUP -> R.string.tool_glam_makeup
        ToolType.REMOVE_OBJECTS -> R.string.tool_remove_objects
        ToolType.REMOVE_BACKGROUND -> R.string.tool_remove_background
        ToolType.SKIN_IMPROVE -> R.string.tool_skin_improve
        ToolType.UPSCALE_IMAGE -> R.string.tool_upscale_image
        ToolType.CHANGE_SCENE -> R.string.tool_change_scene
        ToolType.HAIR_STUDIO -> R.string.tool_hair_studio
        ToolType.SMILE_EDIT -> R.string.tool_smile_edit
        ToolType.GHOSTFACE -> R.string.tool_ghost_face
        ToolType.GHIBLI -> R.string.ghibli_style
        ToolType.PROMPT -> R.string.prompt_flow_title
        null -> R.string.prompt_flow_title
    }
}

fun NavGraphBuilder.resultDestination(
    navController: NavHostController,
    generationRepository: GenerationRepository,
    mediaRepository: MediaRepository,
    historyRepository: HistoryRepository,
    actions: AppNavigationActions,
    useGenericTemplateTitleState: MutableState<Boolean>
) {
    composable(
        route = AppRoute.Result.route,
        arguments = listOf(
            navArgument("generationId") {
                type = NavType.StringType
            },
            navArgument("toolType") {
                type = NavType.StringType
                nullable = true
                defaultValue = ""
            },
            navArgument("templateId") {
                type = NavType.StringType
                nullable = true
                defaultValue = ""
            }
        )
    ) { backStackEntry ->

        val toolTypeName = backStackEntry.arguments
            ?.getString("toolType")
            .orEmpty()

        val toolType = runCatching {
            ToolType.valueOf(toolTypeName)
        }.getOrNull()

        val templateId = backStackEntry.arguments
            ?.getString("templateId")
            .orEmpty()

        BackHandler {
            navController.navigate(
                AppRoute.Main.createRoute(MainTab.Prompts)
            ) {
                popUpTo(AppRoute.Main.route) {
                    inclusive = false
                }
                launchSingleTop = true
            }
        }

        val taskId = backStackEntry.arguments
            ?.getString("generationId")
            .orEmpty()
        Log.d("HistoryDelete", "Result opened taskId=$taskId, toolType=$toolType")

        var resultImageUrl by remember {
            mutableStateOf<String?>(null)
        }

        val scope = rememberCoroutineScope()

        LaunchedEffect(taskId) {
            val result = generationRepository.getResult(taskId)
            resultImageUrl = result.resultImageUrl
        }

        PixoPromptFlowResultScreen(
            resultImageUrl = resultImageUrl,
            titleRes = resultTitleRes(
                toolType = toolType,
                templateId = templateId,
                useGenericTemplateTitle = useGenericTemplateTitleState.value
            ),
            onTitleClick = {
                println("TITLE CLICKED Result")

                if (toolType == ToolType.TEMPLATE) {
                    useGenericTemplateTitleState.value =
                        !useGenericTemplateTitleState.value
                }
            },
            onCloseClick = {
                when (toolType) {
                    ToolType.TEMPLATE -> {
                        navController.navigate(AppRoute.Main.createRoute(MainTab.Templates)) {
                            popUpTo(AppRoute.Main.route) { inclusive = false }
                            launchSingleTop = true
                        }
                    }

                    ToolType.PROMPT -> {
                        navController.navigate(AppRoute.Main.createRoute(MainTab.Prompts)) {
                            popUpTo(AppRoute.Main.route) { inclusive = false }
                            launchSingleTop = true
                        }
                    }

                    null -> {
                        navController.navigate(AppRoute.Main.createRoute(MainTab.Tools)) {
                            popUpTo(AppRoute.Main.route) { inclusive = false }
                            launchSingleTop = true
                        }
                    }

                    else -> {
                        navController.navigate(AppRoute.ToolMain.createRoute(toolType)) {
                            popUpTo(AppRoute.Main.route) { inclusive = false }
                            launchSingleTop = true
                        }
                    }
                }
            },
            onSaveClick = {
                val imageUrl = resultImageUrl

                if (imageUrl.isNullOrBlank()) {
                    false
                } else {
                    mediaRepository.saveImageToGallery(
                        imageUri = imageUrl
                    )
                }
            },
            onShareClick = {
                val imageUrl = resultImageUrl

                if (imageUrl.isNullOrBlank()) {
                    false
                } else {
                    mediaRepository.shareImage(
                        imageUri = imageUrl
                    )
                }
            },
            onRegenerateClick = {
                scope.launch {
                    val request = generationRepository.getRequest(taskId)

                    if (request != null) {
                        actions.createGenerationWithValidation(request)
                    }
                }
            },
            onDeleteClick = {
                scope.launch {
                    Log.d("HistoryDelete", "Delete clicked taskId=$taskId")
                    historyRepository.deleteById(taskId)
                    Log.d("HistoryDelete", "Delete finished taskId=$taskId")

                    navController.navigate(
                        AppRoute.Main.createRoute(MainTab.History)
                    ) {
                        popUpTo(AppRoute.Result.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            }
        )
    }
}
