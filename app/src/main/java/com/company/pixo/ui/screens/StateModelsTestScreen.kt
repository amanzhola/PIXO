package com.company.pixo.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.company.pixo.domain.model.GenerationStatus
import com.company.pixo.domain.model.SubscriptionAccessLevel
import com.company.pixo.domain.model.SubscriptionState
import com.company.pixo.domain.model.TokenPackage
import com.company.pixo.domain.model.TokenState

@Composable
fun StateModelsTestScreen() {
    val generationStatus: GenerationStatus = GenerationStatus.Processing(
        taskId = "task_123",
        progressPercent = 45
    )

    val subscriptionState = SubscriptionState(
        isActive = true,
        accessLevel = SubscriptionAccessLevel.Pro,
        expiresAt = 1_735_689_600_000L
    )

    val tokenState = TokenState(
        balance = 4321,
        isLoading = false,
        packages = listOf(
            TokenPackage(
                id = "tokens_100",
                amount = 100,
                priceText = "$9.99"
            ),
            TokenPackage(
                id = "tokens_500",
                amount = 500,
                priceText = "$34.99"
            ),
            TokenPackage(
                id = "tokens_1000",
                amount = 1000,
                priceText = "$59.99"
            ),
            TokenPackage(
                id = "tokens_2000",
                amount = 2000,
                priceText = "$99.99"
            )
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = "State Models Test",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = generationStatus.toReadableText(),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 16.dp)
        )

        Text(
            text = "Subscription: active=${subscriptionState.isActive}, access=${subscriptionState.accessLevel}, expiresAt=${subscriptionState.expiresAt}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 12.dp)
        )

        Text(
            text = "Tokens: balance=${tokenState.balance}, loading=${tokenState.isLoading}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 12.dp)
        )

        tokenState.packages.forEach { tokenPackage ->
            Text(
                text = "Package: id=${tokenPackage.id}, amount=${tokenPackage.amount}, price=${tokenPackage.priceText}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

private fun GenerationStatus.toReadableText(): String {
    return when (this) {
        GenerationStatus.Idle -> {
            "Generation: idle"
        }

        is GenerationStatus.Processing -> {
            "Generation: processing, taskId=$taskId, progress=$progressPercent%"
        }

        is GenerationStatus.Success -> {
            "Generation: success, generationId=$generationId, result=$resultImageUrl"
        }

        is GenerationStatus.Error -> {
            "Generation: error, taskId=$taskId, message=$message"
        }
    }
}