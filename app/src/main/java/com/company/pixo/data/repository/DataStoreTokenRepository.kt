package com.company.pixo.data.repository

import com.company.pixo.data.datastore.AppPreferencesDataSource
import com.company.pixo.domain.model.TokenConfig
import com.company.pixo.domain.model.TokenPackage
import com.company.pixo.domain.repository.TokenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DataStoreTokenRepository(
    private val preferences: AppPreferencesDataSource
) : TokenRepository {

    override fun getBalance(): Flow<Int> {
        return preferences.getTokenBalance().map { balance ->
            balance.toIntOrNull() ?: 0
        }
    }

    override suspend fun setBalance(balance: Int) {
        preferences.setTokenBalance(
            balance = balance.coerceAtLeast(0).toString()
        )
    }

    override suspend fun hasEnoughTokens(cost: Int): Boolean {
        val currentBalance = getBalance().first()
        return currentBalance >= cost
    }

    override suspend fun consumeTokens(cost: Int): Boolean {
        val safeCost = cost.coerceAtLeast(0)
        val currentBalance = getBalance().first()

        if (currentBalance < safeCost) {
            return false
        }

        setBalance(currentBalance - safeCost)
        return true
    }

    override suspend fun addTokens(amount: Int) {
        val safeAmount = amount.coerceAtLeast(0)
        val currentBalance = getBalance().first()

        setBalance(currentBalance + safeAmount)
    }

    override fun getTokenPackages(): List<TokenPackage> {
        return TokenConfig.PACKAGES
    }
}