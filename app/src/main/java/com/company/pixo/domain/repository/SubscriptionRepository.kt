package com.company.pixo.domain.repository

import com.company.pixo.domain.model.PaywallInfo
import com.company.pixo.domain.model.PurchaseResult
import com.company.pixo.domain.model.SubscriptionProfile

interface SubscriptionRepository {

    suspend fun initialize()

    suspend fun getProfile(): SubscriptionProfile

    suspend fun isPremiumActive(): Boolean

    suspend fun getPaywall(): PaywallInfo

    suspend fun purchase(
        offerId: String
    ): PurchaseResult

    suspend fun restore(): PurchaseResult
}