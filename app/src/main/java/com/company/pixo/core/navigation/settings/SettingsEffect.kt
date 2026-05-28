package com.company.pixo.core.navigation.settings

sealed interface SettingsEffect {

    data object Close : SettingsEffect

    data object OpenUpgrade : SettingsEffect

    data object OpenContactEmail : SettingsEffect

    data object ShareWithFriends : SettingsEffect

    data object OpenPrivacyPolicy : SettingsEffect

    data object OpenTermsOfUse : SettingsEffect
}