package com.company.pixo.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.company.pixo.domain.repository.TokenRepository
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun TokenRepositoryTestScreen() {
    val repository: TokenRepository = koinInject()
    val scope = rememberCoroutineScope()

    val balance by repository
        .getBalance()
        .collectAsState(initial = 0)

    val packages = repository.getTokenPackages()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        Text(
            text = "Token Repository Test",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "balance = $balance",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 16.dp)
        )

        Button(
            modifier = Modifier.padding(top = 24.dp),
            onClick = {
                scope.launch {
                    repository.setBalance(10)
                }
            }
        ) {
            Text(text = "Set Balance = 10")
        }

        Button(
            modifier = Modifier.padding(top = 12.dp),
            onClick = {
                scope.launch {
                    repository.consumeTokens(2)
                }
            }
        ) {
            Text(text = "Consume 2 Tokens")
        }

        Button(
            modifier = Modifier.padding(top = 12.dp),
            onClick = {
                scope.launch {
                    repository.addTokens(5)
                }
            }
        ) {
            Text(text = "Add 5 Tokens")
        }

        Text(
            text = "Packages",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 24.dp)
        )

        packages.forEach { tokenPackage ->
            Text(
                text = "${tokenPackage.id}: ${tokenPackage.amount} tokens, ${tokenPackage.priceText}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}