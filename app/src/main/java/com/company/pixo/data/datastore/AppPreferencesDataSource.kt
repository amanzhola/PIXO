package com.company.pixo.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val PIXO_PREFERENCES = "pixo_preferences"
private val Context.dataStore by preferencesDataStore(
    name = PIXO_PREFERENCES
)

class AppPreferencesDataSource(
    private val context: Context
) {

    private object Keys {

        val ONBOARDING_COMPLETED =
            booleanPreferencesKey("onboarding_completed")

        val FIRST_LAUNCH_COMPLETED =
            booleanPreferencesKey("first_launch_completed")

        val CACHED_SUBSCRIPTION_ACTIVE =
            booleanPreferencesKey("cached_subscription_active")

        val TOKEN_BALANCE =
            stringPreferencesKey("token_balance")

        val LAST_PAYWALL_SHOWN_AT =
            longPreferencesKey("last_paywall_shown_at")

        /*
        // as option for setting language via setting, currently used Android system

        val LANGUAGE =
            stringPreferencesKey("language")
        */

        val SHOWN_PREMIUM_ONBOARDINGS =
            stringPreferencesKey("shown_premium_onboardings")
    }

    suspend fun setOnboardingCompleted(
        completed: Boolean
    ) {
        context.dataStore.edit { preferences ->
            preferences[Keys.ONBOARDING_COMPLETED] = completed
        }
    }

    fun isOnboardingCompleted(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[Keys.ONBOARDING_COMPLETED] ?: false
        }
    }

    suspend fun setFirstLaunchCompleted(
        completed: Boolean
    ) {
        context.dataStore.edit { preferences ->
            preferences[Keys.FIRST_LAUNCH_COMPLETED] = completed
        }
    }

    fun isFirstLaunchCompleted(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[Keys.FIRST_LAUNCH_COMPLETED] ?: false
        }
    }

    suspend fun setSubscriptionCached(
        active: Boolean
    ) {
        context.dataStore.edit { preferences ->
            preferences[Keys.CACHED_SUBSCRIPTION_ACTIVE] = active
        }
    }

    fun getSubscriptionCached(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[Keys.CACHED_SUBSCRIPTION_ACTIVE] ?: false
        }
    }

    suspend fun setTokenBalance(
        balance: String
    ) {
        context.dataStore.edit { preferences ->
            preferences[Keys.TOKEN_BALANCE] = balance
        }
    }

    fun getTokenBalance(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[Keys.TOKEN_BALANCE] ?: "0"
        }
    }

    suspend fun setLastPaywallShownAt(
        timestamp: Long
    ) {
        context.dataStore.edit { preferences ->
            preferences[Keys.LAST_PAYWALL_SHOWN_AT] = timestamp
        }
    }

    fun getLastPaywallShownAt(): Flow<Long> {
        return context.dataStore.data.map { preferences ->
            preferences[Keys.LAST_PAYWALL_SHOWN_AT] ?: 0L
        }
    }

    /*
    // here used Android system language set up -> as an option for settings
    suspend fun setLanguage(
        language: String
    ) {
        context.dataStore.edit { preferences ->
            preferences[Keys.LANGUAGE] = language
        }
    }

    fun getLanguage(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[Keys.LANGUAGE] ?: "en"
        }
    }
    */
    suspend fun setPremiumOnboardingShown(
        toolTypeName: String
    ) {
        context.dataStore.edit { preferences ->
            val currentValue = preferences[Keys.SHOWN_PREMIUM_ONBOARDINGS].orEmpty()

            val shownItems = currentValue
                .split(",")
                .filter { it.isNotBlank() }
                .toMutableSet()

            shownItems.add(toolTypeName)

            preferences[Keys.SHOWN_PREMIUM_ONBOARDINGS] = shownItems.joinToString(",")
        }
    }

    fun isPremiumOnboardingShown(
        toolTypeName: String
    ): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            val currentValue = preferences[Keys.SHOWN_PREMIUM_ONBOARDINGS].orEmpty()

            currentValue
                .split(",")
                .filter { it.isNotBlank() }
                .contains(toolTypeName)
        }
    }

    suspend fun setAllPremiumOnboardingsShown(
        toolTypeNames: List<String>
    ) {
        context.dataStore.edit { preferences ->
            val currentValue = preferences[Keys.SHOWN_PREMIUM_ONBOARDINGS].orEmpty()

            val shownItems = currentValue
                .split(",")
                .filter { it.isNotBlank() }
                .toMutableSet()

            shownItems.addAll(toolTypeNames)

            preferences[Keys.SHOWN_PREMIUM_ONBOARDINGS] = shownItems.joinToString(",")
        }
    }

    suspend fun clearPremiumOnboardingsShown() {
        context.dataStore.edit { preferences ->
            preferences.remove(Keys.SHOWN_PREMIUM_ONBOARDINGS)
        }
    }
}