package com.company.pixo.core.navigation

import com.company.pixo.domain.model.SubscriptionPlanType

enum class PixoDebugScenario {
    NoSubscriptionPremiumOnboarding,
    NoSubscriptionGeneralOnboarding,
    NoSubscriptionGeneralFirstThenPremium,
    WeeklySubscription,
    YearlySubscription
}

object PixoDebugConfig {

    val scenario = PixoDebugScenario.WeeklySubscription

    val mockPremiumPlan: SubscriptionPlanType
        get() = when (scenario) {
            PixoDebugScenario.NoSubscriptionPremiumOnboarding -> SubscriptionPlanType.None
            PixoDebugScenario.NoSubscriptionGeneralOnboarding -> SubscriptionPlanType.None
            PixoDebugScenario.NoSubscriptionGeneralFirstThenPremium -> SubscriptionPlanType.None
            PixoDebugScenario.WeeklySubscription -> SubscriptionPlanType.Weekly
            PixoDebugScenario.YearlySubscription -> SubscriptionPlanType.Yearly
        }

    val enableGeneralFirstLaunchOnboarding: Boolean
        get() = when (scenario) {
            PixoDebugScenario.NoSubscriptionPremiumOnboarding -> false
            PixoDebugScenario.NoSubscriptionGeneralOnboarding -> true
            PixoDebugScenario.NoSubscriptionGeneralFirstThenPremium -> true
            PixoDebugScenario.WeeklySubscription -> true
            PixoDebugScenario.YearlySubscription -> true
        }

    const val enablePremiumFeatureOnboarding: Boolean = true
    const val fallbackToPremiumFlowIfGeneralNotCompleted: Boolean = true
    const val showRepeatPaywallAfter10Sec: Boolean = true

    const val initTokensIfEmpty: Boolean = true
    const val mockTokens: Int = 200

    const val mockGenerationDelayMs: Long = 10_000L
    const val mockGenerationError: Boolean = false

    const val useGenericTemplateTitle: Boolean = true
}

/**
 * Backward-compatible aliases.
 * Можно постепенно заменить их на PixoDebugConfig.xxx.
 */
const val DEBUG_TOKENS_BALANCE = "4321"

val DEBUG_SUBSCRIPTION_PLAN: SubscriptionPlanType
    get() = PixoDebugConfig.mockPremiumPlan

const val DEBUG_IS_FIRST_LAUNCH = true

val DEBUG_SHOW_REPEAT_PAYWALL_AFTER_10_SEC: Boolean
    get() = PixoDebugConfig.showRepeatPaywallAfter10Sec

val ENABLE_GENERAL_FIRST_LAUNCH_ONBOARDING: Boolean
    get() = PixoDebugConfig.enableGeneralFirstLaunchOnboarding

val ENABLE_PREMIUM_FEATURE_ONBOARDING: Boolean
    get() = PixoDebugConfig.enablePremiumFeatureOnboarding

val DEBUG_INIT_TOKENS_IF_EMPTY: Boolean
    get() = PixoDebugConfig.initTokensIfEmpty

val DEBUG_INITIAL_TOKENS: Int
    get() = PixoDebugConfig.mockTokens

val USE_GENERIC_TEMPLATE_TITLE: Boolean
    get() = PixoDebugConfig.useGenericTemplateTitle