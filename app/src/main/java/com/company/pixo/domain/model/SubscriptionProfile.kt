package com.company.pixo.domain.model

data class SubscriptionProfile(
    val customerUserId: String?,
    val isPremiumActive: Boolean,
    val accessLevel: SubscriptionAccessLevel,
    val expiresAt: Long?
)