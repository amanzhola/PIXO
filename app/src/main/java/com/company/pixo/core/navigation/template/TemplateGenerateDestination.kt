package com.company.pixo.core.navigation.template

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.company.pixo.R
import com.company.pixo.core.navigation.AppNavigationActions
import com.company.pixo.core.navigation.AppRoute
import com.company.pixo.core.navigation.GalleryPickTarget
import com.company.pixo.domain.model.GenerationCreateRequest
import com.company.pixo.domain.model.PixoToolConfigs
import com.company.pixo.feature.templates.PixoTemplateDetailsRoute

fun NavGraphBuilder.templateGenerateDestination(
    navController: NavHostController,
    actions: AppNavigationActions,
    useGenericTemplateTitleState: MutableState<Boolean>,
    openCameraFlow: (String) -> Unit,
    openGalleryPicker: (GalleryPickTarget.Template) -> Unit
) {
    composable(
        route = AppRoute.TemplateGenerate.route,
        arguments = listOf(
            navArgument("templateId") {
                type = NavType.StringType
            },
            navArgument("imageUri") {
                type = NavType.StringType
                nullable = true
                defaultValue = ""
            }
        )
    ) { backStackEntry ->

        val routeTemplateId = backStackEntry.arguments
            ?.getString("templateId")
            .orEmpty()

        val imageUri = backStackEntry.arguments
            ?.getString("imageUri")
            .orEmpty()

        val safeImageUri = imageUri
            .takeIf { it.isNotBlank() && it != "null" }
            .orEmpty()

        val template =
            PixoToolConfigs.findTemplateById(routeTemplateId)
                ?: return@composable

        val serverTemplateId = template.templateId ?: return@composable

        val titleRes = if (useGenericTemplateTitleState.value) {
            R.string.template_title
        } else {
            template.titleRes
        }

        PixoTemplateDetailsRoute(
            templateImage = template.previewBefore,
            templateTitleRes = titleRes,
            capturedImageUri = safeImageUri,
            onTitleClick = {
                useGenericTemplateTitleState.value = !useGenericTemplateTitleState.value
            },
            onBackClick = {
                navController.popBackStack()
            },
            onCameraClick = {
                openCameraFlow(routeTemplateId)
            },
            onPhotoLibraryClick = {
                openGalleryPicker(
                    GalleryPickTarget.Template(routeTemplateId)
                )
            },
            onPictureRemoveClick = {
                navController.popBackStack()
            },
            onGenerateClick = {

                val request = GenerationCreateRequest(
                    toolType = template.type,
                    backendType = template.backendType,
                    serverAction = template.serverAction,
                    sourceImageUrl = null,
                    sourceImageUri = safeImageUri,
                    prompt = template.defaultPrompt,
                    templateId = serverTemplateId,
                    options = mapOf(
                        "templateId" to serverTemplateId
                    ),
                    tokenCost = template.generation.tokenCost,
                    outputCount = template.generation.defaultOutputCount,
                    historyIdentity = template.historyIdentity
                )

                actions.createGenerationWithValidation(
                    request = request,
                    requiredFieldsValid = safeImageUri.isNotBlank()
                )
            }
        )
    }
}
