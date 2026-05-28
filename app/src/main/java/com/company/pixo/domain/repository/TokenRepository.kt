package com.company.pixo.domain.repository

import com.company.pixo.domain.model.TokenPackage
import kotlinx.coroutines.flow.Flow

interface TokenRepository {

    fun getBalance(): Flow<Int>

    suspend fun setBalance(balance: Int)

    suspend fun hasEnoughTokens(cost: Int): Boolean

    suspend fun consumeTokens(cost: Int): Boolean

    suspend fun addTokens(amount: Int)

    fun getTokenPackages(): List<TokenPackage>
}