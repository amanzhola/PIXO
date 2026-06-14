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

        val routeTemplateId = backStackEntry.arguments
            ?.getString("templateId")
            .orEmpty()

        val imageUri = backStackEntry.arguments
            ?.getString("imageUri")
            .orEmpty()

        val safeImageUri = imageUri
            .takeIf { it.isNotBlank() && it != "null" }
            .orEmpty()

        val templateIndex = routeTemplateId.toIntOrNull()

        val template = if (templateIndex != null) {
            pixoTemplateItems.getOrNull(templateIndex)
                ?: pixoTemplateItems.first()
        } else {
            pixoTemplateItems.firstOrNull { it.templateId == routeTemplateId }
                ?: pixoTemplateItems.first()
        }

        val serverTemplateId = template.templateId

        val titleRes = if (useGenericTemplateTitleState.value) {
            R.string.template_title
        } else {
            template.titleRes
        }

        PixoTemplateDetailsRoute(
            templateImage = template.image,
            templateTitleRes = titleRes,
            capturedImageUri = safeImageUri,
            onTitleClick = {
                println("TITLE CLICKED TemplateGenerate")
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
                println("TEMPLATE_GENERATE routeTemplateId=$routeTemplateId")
                println("TEMPLATE_GENERATE serverTemplateId=$serverTemplateId")
                println("TEMPLATE_GENERATE imageUri=$imageUri")
                println("TEMPLATE_GENERATE safeImageUri=$safeImageUri")

                val request = GenerationCreateRequest(
                    toolType = ToolType.TEMPLATE,
                    backendType = ToolBackendType.TEMPLATE,
                    serverAction = "template",
                    sourceImageUrl = null,
                    sourceImageUri = safeImageUri,
                    prompt = null,
                    templateId = serverTemplateId,
                    options = mapOf(
                        "templateId" to serverTemplateId,
                        "templateTitleRes" to template.titleRes.toString()
                    ),
                    tokenCost = 2,
                    outputCount = 2,
                    historyIdentity = "TEMPLATE_$serverTemplateId"
                )

                actions.createGenerationWithValidation(
                    request = request,
                    requiredFieldsValid = safeImageUri.isNotBlank()
                )
            }
        )
    }
}
