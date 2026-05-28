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
import com.company.pixo.domain.model.ToolBackendType
import com.company.pixo.domain.model.ToolType
import com.company.pixo.feature.templates.PixoTemplateDetailsRoute
import com.company.pixo.feature.templates.pixoTemplateItems

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

        val templateId = backStackEntry.arguments
            ?.getString("templateId")
            .orEmpty()

        val imageUri = backStackEntry.arguments
            ?.getString("imageUri")
            .orEmpty()

        val index = templateId.toIntOrNull() ?: 0
        val template = pixoTemplateItems.getOrNull(index) ?: pixoTemplateItems.first()

        val titleRes = if (useGenericTemplateTitleState.value) {
            R.string.template_title
        } else {
            template.titleRes
        }

        PixoTemplateDetailsRoute(
            templateImageRes = template.imageRes,
            templateTitleRes = titleRes,
            capturedImageUri = imageUri,
            onTitleClick = {
                println("TITLE CLICKED TemplateGenerate")
                useGenericTemplateTitleState.value = !useGenericTemplateTitleState.value
            },
            onBackClick = {
                navController.popBackStack()
            },
            onCameraClick = {
                openCameraFlow(templateId)
            },
            onPhotoLibraryClick = {
                openGalleryPicker(
                    GalleryPickTarget.Template(templateId)
                )
            },
            onPictureRemoveClick = {
                navController.popBackStack()
            },
            onGenerateClick = {
                val request = GenerationCreateRequest(
                    toolType = ToolType.TEMPLATE,
                    backendType = ToolBackendType.TEMPLATE,
                    serverAction = "template",
                    sourceImageUrl = null,
                    sourceImageUri = imageUri,
                    prompt = null,
                    templateId = templateId,
                    options = mapOf(
                        "templateId" to templateId,
                        "templateTitleRes" to template.titleRes.toString()
                    ),
                    tokenCost = 2,
                    outputCount = 2,
                    historyIdentity = "TEMPLATE_$templateId"
                )

                actions.createGenerationWithValidation(
                    request = request,
                    requiredFieldsValid = imageUri.isNotBlank()
                )
            }
        )
    }
}
