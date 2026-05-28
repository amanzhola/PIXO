package com.company.pixo.feature.paywall

data class PaywallProductUi(
    val id: String,
    val title: String,
    val tokensIncluded: Int,
    val priceText: String,
    val periodText: String
)