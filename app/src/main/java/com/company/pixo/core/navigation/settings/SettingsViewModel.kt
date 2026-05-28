package com.company.pixo.core.navigation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SettingsViewModel : ViewModel() {

    private val _effect = Channel<SettingsEffect>(
        Channel.BUFFERED
    )

    val effect = _effect.receiveAsFlow()

    fun onIntent(intent: SettingsIntent) {
        when (intent) {

            SettingsIntent.CloseClick -> {
                sendEffect(SettingsEffect.Close)
            }

            SettingsIntent.UpgradeClick -> {
                sendEffect(SettingsEffect.OpenUpgrade)
            }

            SettingsIntent.ContactUsClick -> {
                sendEffect(SettingsEffect.OpenContactEmail)
            }

            SettingsIntent.ShareWithFriendsClick -> {
                sendEffect(SettingsEffect.ShareWithFriends)
            }

            SettingsIntent.PrivacyPolicyClick -> {
                sendEffect(SettingsEffect.OpenPrivacyPolicy)
            }

            SettingsIntent.TermsOfUseClick -> {
                sendEffect(SettingsEffect.OpenTermsOfUse)
            }
        }
    }

    private fun sendEffect(
        effect: SettingsEffect
    ) {
        viewModelScope.launch {
            _effect.send(effect)
        }
    }
}