package com.company.pixo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.res.stringResource
import com.company.pixo.core.navigation.AppNavHost
import com.company.pixo.core.navigation.DEBUG_TOKENS_BALANCE
import com.company.pixo.core.theme.PixoTheme
import com.company.pixo.core.ui.PixoOnboardingBeforeAfterScreen
import com.company.pixo.core.ui.PixoPaywallScreen
import com.company.pixo.core.ui.RateSuccessSetupAnimatedScreen
import com.company.pixo.domain.model.ToolType
import com.company.pixo.feature.photo._11screens.PixoAiEnhancherMainScreen
import com.company.pixo.feature.history.PixoHistoryErrorRetryTestScreen
import com.company.pixo.feature.history.PixoHistoryItem
import com.company.pixo.feature.history.PixoHistoryScreen
import com.company.pixo.feature.history.HistoryRoute
import com.company.pixo.ui.screens.PixoMainBottomNavTestScreen
import com.company.pixo.feature.main.MainScreen
import com.company.pixo.feature.templates.PixoTemplatesScreen
import com.company.pixo.feature.tools.PixoToolsScreen
import com.company.pixo.feature.main.MainTab
import com.company.pixo.feature.prompt.PromptFlowRoute
import com.company.pixo.ui.screens.DataStoreTestScreen
import com.company.pixo.ui.screens.MockGenerationRepositoryTestScreen
import com.company.pixo.ui.screens.PixoComponentsTestScreen
import com.company.pixo.ui.screens.RoomHistoryTestScreen
import com.company.pixo.ui.screens.ToolConfigTestScreen
import com.company.pixo.ui.screens.StateModelsTestScreen
import com.company.pixo.ui.screens.SubscriptionRepositoryTestScreen
import com.company.pixo.ui.screens.TokenConfigTestScreen
import com.company.pixo.ui.screens.TokenRepositoryTestScreen

private const val ACTIVE_TEST = 0

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            PixoTheme {
                when (ACTIVE_TEST) {
                    0 -> AppNavHost()

                    1 -> PixoComponentsTestScreen()

                    2 -> PixoAiEnhancherMainScreen(
                        onBackClick = {},
                        onCameraClick = {},
                        onPhotoLibraryClick = {}
                    )

                    3 -> PixoOnboardingBeforeAfterScreen(
                        beforeImageRes = R.drawable.onb1,
                        title = stringResource(id = R.string.onboarding_ai_photo_enhancer_title),
                        subtitle = stringResource(id = R.string.onboarding_ai_photo_enhancer_subtitle),
                        onContinueClick = {}
                    )

                    4 -> PixoPaywallScreen(
                        yearlySelected = true,
                        weeklySelected = false,
                        onYearlyClick = {},
                        onWeeklyClick = {},
                        onContinueClick = {},
                        onCloseClick = {},
                        onTermsClick = {},
                        onPrivacyClick = {},
                        onRestoreClick = {}
                    )

                    5 -> PixoPaywallScreen(
                            yearlySelected = false,
                            weeklySelected = true
                        )

                    6 -> PixoTheme {
                        RateSuccessSetupAnimatedScreen(
                            onFinished = {}
                        )
                    }

                    7 -> PixoToolsScreen(
                        tokens = DEBUG_TOKENS_BALANCE,
                        onGetProClick = {},
                        onSettingsClick = {},
                        onImageLabClick = {},
                        onToolClick = { _, _ -> },
                        onTabClick = {}
                    )

                    8 -> PixoTemplatesScreen(
                        selectedTab = MainTab.Templates,
                        onTemplateClick = {},
                        onTabClick = {}
                    )

                    9 -> PixoTemplatesScreen(
                        tokens = "240",
                        selectedTab = MainTab.Templates,
                        onTemplateClick = {},
                        onTabClick = {}
                    )

                    10 -> PromptFlowRoute()

                    11 -> PixoHistoryScreen(
                        items = listOf(
                            PixoHistoryItem.Loading(
                                id = "loading_1",
                                toolType = null
                            ),

                            PixoHistoryItem.Image(
                                id = "1",
                                titleRes = R.string.tool_ghibli_look,
                                imageUrl = "android.resource://com.company.pixo/${R.drawable.tools_ghibli_look}",
                                toolType = ToolType.GHIBLI
                            ),

                            PixoHistoryItem.Image(
                                id = "2",
                                titleRes = R.string.history_ghostface_style,
                                imageUrl = "android.resource://com.company.pixo/${R.drawable.tools_ghostface_style}",
                                toolType = ToolType.GHOSTFACE
                            ),

                            PixoHistoryItem.Image(
                                id = "3",
                                titleRes = R.string.tool_hair_studio,
                                imageUrl = "android.resource://com.company.pixo/${R.drawable.tools_hair_studio3}",
                                toolType = ToolType.HAIR_STUDIO
                            )
                        ),
                        showEmptyMessage = false,
                        onGetProClick = {},
                        onSettingsClick = {},
                        onTabClick = {}
                    )

                    12 -> HistoryRoute(
                        onGetProClick = {},
                        onSettingsClick = {},
                    )

                    13 -> PixoHistoryErrorRetryTestScreen()

                    14 -> PixoMainBottomNavTestScreen()

                    15 -> MainScreen()

                    16 -> PixoToolsScreen(
                            tokens = DEBUG_TOKENS_BALANCE,
                            hasActiveSubscription = false,
                            onGetProClick = {},
                            onTokenBalanceClick = {},
                            onSettingsClick = {},
                            onImageLabClick = {},
                            onToolClick = { _, _ -> },
                            onTabClick = {}
                        )

                    17 -> PixoTemplatesScreen(
                            hasActiveSubscription = false,
                            tokens = DEBUG_TOKENS_BALANCE,
                            selectedTab = MainTab.Templates,
                            onGetProClick = {},
                            onTokenBalanceClick = {},
                            onSettingsClick = {},
                            onTemplateClick = {},
                            onTabClick = {}
                        )
                    
                    18 -> PromptFlowRoute(
                            onGetProClick = {},
                            onSettingsClick = {},
                            onGenerateClick = {},
                            onTabClick = {}
                        )

                    19 -> DataStoreTestScreen()

                    20 -> RoomHistoryTestScreen()

                    21 -> ToolConfigTestScreen()

                    22 -> StateModelsTestScreen()

                    23 -> MockGenerationRepositoryTestScreen()

                    24 -> SubscriptionRepositoryTestScreen()

                    25 -> TokenRepositoryTestScreen()

                    26 -> TokenConfigTestScreen()

                    27 -> TokenRepositoryTestScreen()

                }
            }
        }
    }
}