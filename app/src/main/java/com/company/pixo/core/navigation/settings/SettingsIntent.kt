package com.company.pixo.core.navigation.settings

sealed interface SettingsIntent {

    data object CloseClick : SettingsIntent

    data object UpgradeClick : SettingsIntent

    data object ContactUsClick : SettingsIntent

    data object ShareWithFriendsClick : SettingsIntent

    data object PrivacyPolicyClick : SettingsIntent

    data object TermsOfUseClick : SettingsIntent
}