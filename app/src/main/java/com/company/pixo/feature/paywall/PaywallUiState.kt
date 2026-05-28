package com.company.pixo.feature.paywall

import androidx.annotation.StringRes

data class PaywallUiState(
    val isLoading: Boolean = true,
    @StringRes val errorMessageRes: Int? = null,
    val weekly: PaywallProductUi? = null,
    val yearly: PaywallProductUi? = null,
    val selectedProductId: String? = null,
    val purchaseEnabled: Boolean = true
)