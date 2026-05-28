package com.company.pixo.domain.model

data class PaywallInfo(
    val id: String,
    val title: String,
    val offers: List<PaywallOffer>
)