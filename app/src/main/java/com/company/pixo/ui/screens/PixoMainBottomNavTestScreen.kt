package com.company.pixo.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.company.pixo.core.navigation.DEBUG_TOKENS_BALANCE
import com.company.pixo.feature.history.HistoryRoute
import com.company.pixo.feature.main.MainTab
import com.company.pixo.feature.prompt.PromptFlowRoute
import com.company.pixo.feature.templates.PixoTemplatesScreen
import com.company.pixo.feature.tools.PixoToolsScreen

// testing screen
@Composable
fun PixoMainBottomNavTestScreen() {
    var selectedTab by remember { mutableStateOf(MainTab.Tools) }

    when (selectedTab) {
        MainTab.Tools -> {
            PixoToolsScreen(
                tokens = DEBUG_TOKENS_BALANCE,
                onGetProClick = {},
                onSettingsClick = {},
                onImageLabClick = {},
                onToolClick = { _, _ -> },
                onTabClick = { selectedTab = it }
            )
        }

        MainTab.Templates -> {
            PixoTemplatesScreen(
                selectedTab = MainTab.Templates,
                onTemplateClick = {},
                onTabClick = { selectedTab = it }
            )
        }

        MainTab.Prompts -> {
            PromptFlowRoute(
                onTabClick = { selectedTab = it }
            )
        }

        MainTab.History -> {
            HistoryRoute(
                onTabClick = { selectedTab = it },
                onGetProClick = {},
                onSettingsClick = {}
            )
        }
    }
}
