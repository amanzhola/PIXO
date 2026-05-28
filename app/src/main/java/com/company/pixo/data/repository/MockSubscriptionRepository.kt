package com.company.pixo.data.repository

import com.company.pixo.domain.model.PaywallInfo
import com.company.pixo.domain.model.PaywallOffer
import com.company.pixo.domain.model.PurchaseResult
import com.company.pixo.domain.model.SubscriptionAccessLevel
import com.company.pixo.domain.model.SubscriptionProfile
import com.company.pixo.domain.repository.SubscriptionRepository
import kotlinx.coroutines.delay

class MockSubscriptionRepository : SubscriptionRepository {

    private var premiumActive: Boolean = false

    override suspend fun initialize() {
        delay(300)
    }

    override suspend fun getProfile(): SubscriptionProfile {
        delay(300)

        return SubscriptionProfile(
            customerUserId = "mock_user",
            isPremiumActive = premiumActive,
            accessLevel = if (premiumActive) {
                SubscriptionAccessLevel.Pro
            } else {
                SubscriptionAccessLevel.Free
            },
            expiresAt = null
        )
    }

    override suspend fun isPremiumActive(): Boolean {
        delay(150)
        return premiumActive
    }

    override suspend fun getPaywall(): PaywallInfo {
        delay(300)

        return PaywallInfo(
            id = "mock_main_paywall",
            title = "Unlock Your Best Photos",
            offers = listOf(
                PaywallOffer(
                    id = "yearly",
                    title = "Yearly Access",
                    priceText = "$39.99",
                    periodText = "per year",
                    tokensIncluded = 100
                ),
                PaywallOffer(
                    id = "weekly",
                    title = "Weekly Access",
                    priceText = "$4.99",
                    periodText = "per week",
                    tokensIncluded = 25
                )
            )
        )
    }

    override suspend fun purchase(
        offerId: String
    ): PurchaseResult {
        delay(700)

        premiumActive = true

        return PurchaseResult.Success(
            profile = getProfile()
        )
    }

    override suspend fun restore(): PurchaseResult {
        delay(500)

        return if (premiumActive) {
            PurchaseResult.Success(
                profile = getProfile()
            )
        } else {
            PurchaseResult.Error(
                message = "No purchases to restore"
            )
        }
    }
}