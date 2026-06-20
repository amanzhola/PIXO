package com.company.pixo.core.navigation.template

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.company.pixo.R
import com.company.pixo.core.navigation.AppRoute
import com.company.pixo.core.navigation.GalleryPickTarget
import com.company.pixo.domain.model.PixoToolConfigs
import com.company.pixo.feature.templates.PixoTemplateDetailsRoute

fun NavGraphBuilder.templateDetailsDestination(
    navController: NavHostController,
    useGenericTemplateTitleState: MutableState<Boolean>,
    openCameraFlow: (String) -> Unit,
    openGalleryPicker: (GalleryPickTarget.Template) -> Unit
) {
    composable(
        route = AppRoute.TemplateDetails.route,
        arguments = listOf(
            navArgument("templateId") {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->

        val templateId = backStackEntry.arguments
            ?.getString("templateId")
            .orEmpty()

        val template =
            PixoToolConfigs.findTemplateById(templateId)
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
            capturedImageUri = null,
            onBackClick = {
                navController.popBackStack()
            },
            onCameraClick = {
                openCameraFlow(serverTemplateId)
            },
            onPhotoLibraryClick = {
                openGalleryPicker(
                    GalleryPickTarget.Template(serverTemplateId)
                )
            },
            onPictureRemoveClick = {},
            onGenerateClick = {
                // На этом экране фото ещё нет,
                // поэтому Generate ничего не делает.
                // Реальный generate идёт в templateGenerateDestination.
            },
            onTitleClick = {
                println("TITLE CLICKED TemplateDetails")
                useGenericTemplateTitleState.value = !useGenericTemplateTitleState.value
            }
        )
    }
}
