package com.company.pixo.core.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.company.pixo.domain.model.ToolType

fun NavGraphBuilder.editorDestination(
    navController: NavHostController
) {
    composable(
        route = AppRoute.Editor.route,
        arguments = listOf(
            navArgument("toolType") {
                type = NavType.StringType
            },
            navArgument("imageUri") {
                type = NavType.StringType
                nullable = true
                defaultValue = ""
            }
        )
    ) { backStackEntry ->
        val toolType = backStackEntry.arguments
            ?.getString("toolType")
            .orEmpty()

        val imageUri = backStackEntry.arguments
            ?.getString("imageUri")
            .orEmpty()

        ArgumentDebugScreen(
            title = "Editor test route",
            lines = listOf(
                "toolType = $toolType",
                "imageUri = $imageUri"
            ),
            actionText = "Go to Generation",
            onActionClick = {
                val parsedToolType = runCatching {
                    ToolType.valueOf(toolType)
                }.getOrNull()

                if (parsedToolType != null) {
                    navController.navigate(
                        AppRoute.Generation.createRoute(
                            taskId = "task_from_editor_123",
                            toolType = parsedToolType
                        )
                    )
                }
            },
            onBackClick = {
                navController.popBackStack()
            }
        )
    }
}