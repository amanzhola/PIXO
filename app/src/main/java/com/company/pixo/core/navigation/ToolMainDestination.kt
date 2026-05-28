package com.company.pixo.core.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.company.pixo.domain.model.ToolType
import com.company.pixo.feature.photo.ToolPhotoSourceScreenVariant
import com.company.pixo.feature.photo._11screens.PixoAiEnhancherMainScreen
import com.company.pixo.feature.photo._11screens.PixoChangeSceneMainScreen
import com.company.pixo.feature.photo._11screens.PixoGhibliLookMainScreen
import com.company.pixo.feature.photo._11screens.PixoGhostFaceMainScreen
import com.company.pixo.feature.photo._11screens.PixoGlamMakeupMainScreen
import com.company.pixo.feature.photo._11screens.PixoHairStudioMainScreen
import com.company.pixo.feature.photo._11screens.PixoRemoveBackgroundMainScreen
import com.company.pixo.feature.photo._11screens.PixoRemoveObjectsMainScreen
import com.company.pixo.feature.photo._11screens.PixoSkinImproveMainScreen
import com.company.pixo.feature.photo._11screens.PixoSmileEditMainScreen
import com.company.pixo.feature.photo._11screens.PixoUpscaleImageMainScreen

fun NavGraphBuilder.toolMainDestination(
    navController: NavHostController,
    openCameraFlow: (ToolType) -> Unit,
    openGalleryPicker: (ToolType) -> Unit
) {
    composable(
        route = AppRoute.ToolMain.route,
        arguments = listOf(
            navArgument("toolType") {
                type = NavType.StringType
            },
            navArgument("variant") {
                type = NavType.StringType
                defaultValue = ToolPhotoSourceScreenVariant.Default.name
            }
        )
    ) { backStackEntry ->
        val toolType = backStackEntry.arguments
            ?.getString("toolType")
            .orEmpty()

        val variant = backStackEntry.arguments
            ?.getString("variant")
            ?.let { value ->
                runCatching {
                    ToolPhotoSourceScreenVariant.valueOf(value)
                }.getOrDefault(ToolPhotoSourceScreenVariant.Default)
            }
            ?: ToolPhotoSourceScreenVariant.Default

        when (toolType) {
            ToolType.AI_ENHANCER.name -> {
                PixoAiEnhancherMainScreen(
                    variant = variant,
                    onBackClick = { navController.popBackStack() },
                    onCameraClick = { openCameraFlow(ToolType.AI_ENHANCER) },
                    onPhotoLibraryClick = { openGalleryPicker(ToolType.AI_ENHANCER) }
                )
            }

            ToolType.GLAM_MAKEUP.name -> {
                PixoGlamMakeupMainScreen(
                    variant = variant,
                    onBackClick = { navController.popBackStack() },
                    onCameraClick = { openCameraFlow(ToolType.GLAM_MAKEUP) },
                    onPhotoLibraryClick = { openGalleryPicker(ToolType.GLAM_MAKEUP) }
                )
            }

            ToolType.REMOVE_OBJECTS.name -> {
                PixoRemoveObjectsMainScreen(
                    variant = variant,
                    onBackClick = { navController.popBackStack() },
                    onCameraClick = { openCameraFlow(ToolType.REMOVE_OBJECTS) },
                    onPhotoLibraryClick = { openGalleryPicker(ToolType.REMOVE_OBJECTS) }
                )
            }

            ToolType.REMOVE_BACKGROUND.name -> {
                PixoRemoveBackgroundMainScreen(
                    variant = variant,
                    onBackClick = { navController.popBackStack() },
                    onCameraClick = { openCameraFlow(ToolType.REMOVE_BACKGROUND) },
                    onPhotoLibraryClick = { openGalleryPicker(ToolType.REMOVE_BACKGROUND) }
                )
            }

            ToolType.SKIN_IMPROVE.name -> {
                PixoSkinImproveMainScreen(
                    variant = variant,
                    onBackClick = { navController.popBackStack() },
                    onCameraClick = { openCameraFlow(ToolType.SKIN_IMPROVE) },
                    onPhotoLibraryClick = { openGalleryPicker(ToolType.SKIN_IMPROVE) }
                )
            }

            ToolType.UPSCALE_IMAGE.name -> {
                PixoUpscaleImageMainScreen(
                    variant = variant,
                    onBackClick = { navController.popBackStack() },
                    onCameraClick = { openCameraFlow(ToolType.UPSCALE_IMAGE) },
                    onPhotoLibraryClick = { openGalleryPicker(ToolType.UPSCALE_IMAGE) }
                )
            }

            ToolType.CHANGE_SCENE.name -> {
                PixoChangeSceneMainScreen(
                    variant = variant,
                    onBackClick = { navController.popBackStack() },
                    onCameraClick = { openCameraFlow(ToolType.CHANGE_SCENE) },
                    onPhotoLibraryClick = { openGalleryPicker(ToolType.CHANGE_SCENE) }
                )
            }

            ToolType.HAIR_STUDIO.name -> {
                PixoHairStudioMainScreen(
                    variant = variant,
                    onBackClick = { navController.popBackStack() },
                    onCameraClick = { openCameraFlow(ToolType.HAIR_STUDIO) },
                    onPhotoLibraryClick = { openGalleryPicker(ToolType.HAIR_STUDIO) }
                )
            }

            ToolType.SMILE_EDIT.name -> {
                PixoSmileEditMainScreen(
                    variant = variant,
                    onBackClick = { navController.popBackStack() },
                    onCameraClick = { openCameraFlow(ToolType.SMILE_EDIT) },
                    onPhotoLibraryClick = { openGalleryPicker(ToolType.SMILE_EDIT) }
                )
            }

            ToolType.GHOSTFACE.name -> {
                PixoGhostFaceMainScreen(
                    variant = variant,
                    onBackClick = { navController.popBackStack() },
                    onCameraClick = { openCameraFlow(ToolType.GHOSTFACE) },
                    onPhotoLibraryClick = { openGalleryPicker(ToolType.GHOSTFACE) }
                )
            }

            ToolType.GHIBLI.name -> {
                PixoGhibliLookMainScreen(
                    variant = variant,
                    onBackClick = { navController.popBackStack() },
                    onCameraClick = { openCameraFlow(ToolType.GHIBLI) },
                    onPhotoLibraryClick = { openGalleryPicker(ToolType.GHIBLI) }
                )
            }

            else -> {
                ArgumentDebugScreen(
                    title = "Unknown tool",
                    lines = listOf("toolType = $toolType"),
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}