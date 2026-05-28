package com.company.pixo.domain.model

data class TokenState(
    val balance: Int,
    val packages: List<TokenPackage>,
    val isLoading: Boolean
)

data class TokenPackage(
    val id: String,
    val amount: Int,
    val priceText: String
)