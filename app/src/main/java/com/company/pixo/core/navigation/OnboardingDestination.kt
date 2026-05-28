package com.company.pixo.core.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.company.pixo.data.datastore.AppPreferencesDataSource
import com.company.pixo.domain.model.ToolType
import com.company.pixo.feature.onboarding.GeneralOnboardingRoute
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun NavGraphBuilder.onboardingDestination(
    navController: NavHostController,
    preferences: AppPreferencesDataSource,
    coroutineScope: CoroutineScope
) {
    composable(AppRoute.Onboarding.route) {
        GeneralOnboardingRoute(
            onFinished = {
                coroutineScope.launch {
                    preferences.setOnboardingCompleted(true)

                    preferences.setAllPremiumOnboardingsShown(
                        listOf(
                            ToolType.AI_ENHANCER.name,
                            ToolType.GLAM_MAKEUP.name,
                            ToolType.REMOVE_OBJECTS.name,
                            ToolType.REMOVE_BACKGROUND.name,
                            ToolType.SKIN_IMPROVE.name,
                            ToolType.UPSCALE_IMAGE.name,
                            ToolType.CHANGE_SCENE.name,
                            ToolType.HAIR_STUDIO.name,
                            ToolType.SMILE_EDIT.name,
                            ToolType.GHOSTFACE.name,
                            ToolType.GHIBLI.name,
                            ToolType.TEMPLATE.name,
                            ToolType.PROMPT.name
                        )
                    )

                    navController.navigate(AppRoute.ProRate.route) {
                        popUpTo(AppRoute.Onboarding.route) {
                            inclusive = true
                        }
                    }
                }
            }
        )
    }
}