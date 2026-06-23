package com.company.pixo.core.navigation.tools

import android.net.Uri
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.company.pixo.core.navigation.AppNavigationActions
import com.company.pixo.core.navigation.AppRoute
import com.company.pixo.domain.model.GenerationCreateRequest
import com.company.pixo.domain.model.PixoToolConfigs
import com.company.pixo.domain.model.ToolType
import com.company.pixo.domain.repository.GenerationRepository
import com.company.pixo.feature.editor.PixoRemoveObjectsRefineRoute
import kotlinx.coroutines.launch

fun NavGraphBuilder.removeObjectsRefineDestination(
    navController: NavHostController,
    actions: AppNavigationActions,
    generationRepository: GenerationRepository
) {
    composable(
        route = AppRoute.RemoveObjectsRefine.route,
        arguments = listOf(
            navArgument("taskId") {
                type = NavType.StringType
            },
            navArgument("imageUrl") {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->

        val taskId = backStackEntry.arguments
            ?.getString("taskId")
            .orEmpty()

        val imageUrl = backStackEntry.arguments
            ?.getString("imageUrl")
            ?.let(Uri::decode)
            .orEmpty()

        val scope = rememberCoroutineScope()

        PixoRemoveObjectsRefineRoute(
            imageUrl = imageUrl,
            onBackClick = {
                navController.popBackStack()
            },
            onGenerateClick = { maskImageUri ->
                val config = PixoToolConfigs.findByType(ToolType.REMOVE_OBJECTS)

                if (config != null) {
                    scope.launch {
                        val uploadedMask = generationRepository.uploadImage(maskImageUri)

                        val request = GenerationCreateRequest(
                            toolType = config.type,
                            backendType = config.backendType,
                            serverAction = "remove_objects_cleanup",
                            sourceImageUrl = imageUrl,
                            sourceImageUri = null,
                            prompt = "remove selected leftovers, reconstruct background naturally",
                            templateId = null,
                            options = mapOf(
                                "sourceTaskId" to taskId,
                                "maskImageUrl" to uploadedMask.imageUrl,
                                "mode" to "manual_cleanup"
                            ),
                            tokenCost = config.generation.tokenCost,
                            outputCount = config.generation.defaultOutputCount,
                            historyIdentity = config.historyIdentity
                        )

                        actions.createGenerationWithValidation(
                            request = request,
                            requiredFieldsValid = imageUrl.isNotBlank() &&
                                    uploadedMask.imageUrl.isNotBlank()
                        )
                    }
                }
            }
        )
    }
}