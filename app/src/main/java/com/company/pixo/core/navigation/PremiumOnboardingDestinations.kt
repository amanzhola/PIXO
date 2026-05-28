package com.company.pixo.core.navigation

import com.company.pixo.data.datastore.AppPreferencesDataSource
import com.company.pixo.domain.model.ToolType
import com.company.pixo.feature.onboarding.premiumOnboardingNavGraph
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.navigation.NavGraphBuilder

fun NavGraphBuilder.premiumOnboardingDestinations(
    preferences: AppPreferencesDataSource,
    coroutineScope: CoroutineScope,
    actions: AppNavigationActions
) {
    premiumOnboardingNavGraph(
        onToolOnboardingContinue = { toolType ->
            coroutineScope.launch {
                preferences.setPremiumOnboardingShown(toolType.name)
                actions.openProFlow()
            }
        },
        onTemplatesOnboardingContinue = {
            coroutineScope.launch {
                preferences.setPremiumOnboardingShown(ToolType.TEMPLATE.name)
                actions.openProFlow()
            }
        },
        onPromptsOnboardingContinue = {
            coroutineScope.launch {
                preferences.setPremiumOnboardingShown(ToolType.PROMPT.name)
                actions.openProFlow()
            }
        }
    )
}