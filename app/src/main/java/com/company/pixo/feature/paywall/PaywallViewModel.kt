package com.company.pixo.feature.paywall

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.pixo.R
import com.company.pixo.domain.model.PurchaseResult
import com.company.pixo.domain.repository.SubscriptionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PaywallViewModel(
    private val subscriptionRepository: SubscriptionRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PaywallUiState())
    val uiState: StateFlow<PaywallUiState> = _uiState

    fun loadProducts() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true, errorMessageRes = null
            )

            runCatching {
                subscriptionRepository.getPaywall()
            }.onSuccess { paywall ->
                val yearly = paywall.offers.firstOrNull { offer ->
                    offer.id == "yearly"
                }

                val weekly = paywall.offers.firstOrNull { offer ->
                    offer.id == "weekly"
                }

                _uiState.value = PaywallUiState(
                    isLoading = false,
                    errorMessageRes = null,
                    yearly = yearly?.let { offer ->
                        PaywallProductUi(
                            id = offer.id,
                            title = offer.title,
                            tokensIncluded = offer.tokensIncluded,
                            priceText = offer.priceText,
                            periodText = offer.periodText
                        )
                    },
                    weekly = weekly?.let { offer ->
                        PaywallProductUi(
                            id = offer.id,
                            title = offer.title,
                            tokensIncluded = offer.tokensIncluded,
                            priceText = offer.priceText,
                            periodText = offer.periodText
                        )
                    },
                    selectedProductId = yearly?.id ?: weekly?.id,
                    purchaseEnabled = true
                )
            }.onFailure { throwable ->
                _uiState.value = PaywallUiState(
                    isLoading = false,
                    errorMessageRes = R.string.error_paywall_loading_failed,
                    yearly = PaywallProductUi(
                        id = "yearly",
                        title = "Yearly Access",
                        tokensIncluded = 100,
                        priceText = "$39.99",
                        periodText = "per year"
                    ),
                    weekly = PaywallProductUi(
                        id = "weekly",
                        title = "Weekly Access",
                        tokensIncluded = 25,
                        priceText = "$4.99",
                        periodText = "per week"
                    ),
                    selectedProductId = "yearly",
                    purchaseEnabled = false
                )
            }
        }
    }

    fun selectYearly() {
        _uiState.value = _uiState.value.copy(
            selectedProductId = _uiState.value.yearly?.id
        )
    }

    fun selectWeekly() {
        _uiState.value = _uiState.value.copy(
            selectedProductId = _uiState.value.weekly?.id
        )
    }

    fun purchaseSelected(
        onSuccess: () -> Unit
    ) {
        val productId = _uiState.value.selectedProductId ?: return

        viewModelScope.launch {
            when (subscriptionRepository.purchase(productId)) {
                is PurchaseResult.Success -> {
                    onSuccess()
                }

                PurchaseResult.Cancelled -> Unit

                is PurchaseResult.Error -> {
                    _uiState.value = _uiState.value.copy(
                        errorMessageRes = R.string.error_purchase_failed
                    )
                }
            }
        }
    }

    fun restore(
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            when (subscriptionRepository.restore()) {
                is PurchaseResult.Success -> {
                    onSuccess()
                }

                PurchaseResult.Cancelled -> Unit

                is PurchaseResult.Error -> {
                    _uiState.value = _uiState.value.copy(
                        errorMessageRes = R.string.error_restore_failed
                    )
                }
            }
        }
    }
}