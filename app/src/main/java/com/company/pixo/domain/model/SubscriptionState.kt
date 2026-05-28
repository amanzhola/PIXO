package com.company.pixo.domain.model

data class SubscriptionState(
    val isActive: Boolean,
    val accessLevel: SubscriptionAccessLevel,
    val expiresAt: Long?
)

enum class SubscriptionAccessLevel {
    Free,
    Pro,
    Lifetime
}