package com.company.pixo.domain.model

data class PaywallOffer(
    val id: String,
    val title: String,
    val priceText: String,
    val periodText: String,
    val tokensIncluded: Int
)