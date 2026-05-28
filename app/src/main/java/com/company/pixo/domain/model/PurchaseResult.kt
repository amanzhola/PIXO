package com.company.pixo.domain.model

sealed class PurchaseResult {

    data class Success(
        val profile: SubscriptionProfile
    ) : PurchaseResult()

    data object Cancelled : PurchaseResult()

    data class Error(
        val message: String
    ) : PurchaseResult()
}