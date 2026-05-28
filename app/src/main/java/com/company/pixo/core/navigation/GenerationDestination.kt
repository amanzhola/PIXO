package com.company.pixo.core.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.company.pixo.domain.model.ToolType
import com.company.pixo.domain.model.GenerationStatus
import com.company.pixo.domain.repository.GenerationRepository
import com.company.pixo.feature.generation.PixoGenerationScreen
import com.company.pixo.feature.main.MainTab

import android.util.Log

fun NavGraphBuilder.generationDestination(
    navController: NavHostController,
    generationRepository: GenerationRepository
) {
    composable(
        route = AppRoute.Generation.route,
        arguments = listOf(
            navArgument("taskId") {
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

        val taskId = backStackEntry.arguments
            ?.getString("taskId")
            .orEmpty()

        val toolTypeName = backStackEntry.arguments
            ?.getString("toolType")
            .orEmpty()

        val toolType = runCatching {
            ToolType.valueOf(toolTypeName)
        }.getOrNull()

        Log.d(
            "HistoryLoadingClick",
            "GenerationDestination opened taskId=$taskId, toolType=$toolType"
        )

        val templateId = backStackEntry.arguments
            ?.getString("templateId")
            .orEmpty()

        var progress by remember {
            mutableStateOf(0f)
        }

        var errorMessage by remember {
            mutableStateOf<String?>(null)
        }

        LaunchedEffect(taskId) {
            generationRepository
                .observeGeneration(taskId)
                .collect { status ->
                    when (status) {
                        GenerationStatus.Idle -> {
                            progress = 0f
                            errorMessage = null
                        }

                        is GenerationStatus.Processing -> {
                            progress = status.progressPercent / 100f
                            errorMessage = null
                        }

                        is GenerationStatus.Success -> {
                            errorMessage = null

                            if (toolType != null) {
                                navController.navigate(
                                    AppRoute.Result.createRoute(
                                        generationId = taskId,
                                        toolType = toolType,
                                        templateId = templateId
                                    )
                                ) {
                                    popUpTo(AppRoute.Generation.route) {
                                        inclusive = true
                                    }
                                }
                            }
                        }

                        is GenerationStatus.Error -> {
                            errorMessage = status.message
                        }
                    }
                }
        }

        PixoGenerationScreen(
            progress = progress,
            errorMessage = errorMessage,
            onCloseClick = {
                navController.popBackStack()
            },
            onGoToHistoryClick = {
                navController.navigate(
                    AppRoute.Main.createRoute(MainTab.History)
                ) {
                    popUpTo(AppRoute.Main.route) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
        )
    }
}
