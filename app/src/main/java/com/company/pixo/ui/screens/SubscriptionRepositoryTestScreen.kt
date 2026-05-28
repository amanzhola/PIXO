package com.company.pixo.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.company.pixo.domain.model.PurchaseResult
import com.company.pixo.domain.model.SubscriptionProfile
import com.company.pixo.domain.repository.SubscriptionRepository
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun SubscriptionRepositoryTestScreen() {
    val repository: SubscriptionRepository = koinInject()
    val scope = rememberCoroutineScope()

    var profile by remember {
        mutableStateOf<SubscriptionProfile?>(null)
    }

    var paywallText by remember {
        mutableStateOf("Paywall not loaded")
    }

    var resultText by remember {
        mutableStateOf("No action yet")
    }

    LaunchedEffect(Unit) {
        repository.initialize()
        profile = repository.getProfile()

        val paywall = repository.getPaywall()

        paywallText = buildString {
            append("paywallId = ")
            append(paywall.id)
            append("\n")
            append("title = ")
            append(paywall.title)
            append("\n")
            append("offers = ")
            append(paywall.offers.size)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = "Subscription Repository Test",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "premium = ${profile?.isPremiumActive}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 16.dp)
        )

        Text(
            text = "accessLevel = ${profile?.accessLevel}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = paywallText,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 16.dp)
        )

        Text(
            text = resultText,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 16.dp)
        )

        Button(
            modifier = Modifier.padding(top = 24.dp),
            onClick = {
                scope.launch {
                    val result = repository.purchase(
                        offerId = "yearly"
                    )

                    resultText = result.toReadableText()
                    profile = repository.getProfile()
                }
            }
        ) {
            Text(text = "Mock Purchase")
        }

        Button(
            modifier = Modifier.padding(top = 12.dp),
            onClick = {
                scope.launch {
                    val result = repository.restore()

                    resultText = result.toReadableText()
                    profile = repository.getProfile()
                }
            }
        ) {
            Text(text = "Mock Restore")
        }
    }
}

private fun PurchaseResult.toReadableText(): String {
    return when (this) {
        is PurchaseResult.Success -> {
            "Purchase success, premium=${profile.isPremiumActive}"
        }

        PurchaseResult.Cancelled -> {
            "Purchase cancelled"
        }

        is PurchaseResult.Error -> {
            "Purchase error: $message"
        }
    }
}