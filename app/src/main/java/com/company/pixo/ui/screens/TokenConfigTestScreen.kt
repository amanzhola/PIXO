package com.company.pixo.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.company.pixo.domain.model.PixoToolConfigs
import com.company.pixo.domain.model.TokenConfig

@Composable
fun TokenConfigTestScreen() {
    val configs = PixoToolConfigs.generationConfigs

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        Text(
            text = "Token Config Test",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "initialBalance = ${TokenConfig.INITIAL_BALANCE}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 16.dp)
        )

        Text(
            text = "generationConfigs = ${configs.size}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = "Tool costs",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 24.dp)
        )

        configs.forEach { config ->
            val normalOutputCount = TokenConfig.resolveOutputCount(
                config = config,
                prompt = "Create a professional headshot"
            )

            val cartoonOutputCount = TokenConfig.resolveOutputCount(
                config = config,
                prompt = "Make this into a cartoon style"
            )

            Text(
                text = buildString {
                    append(stringResource(config.titleRes))
                    append(" | type=")
                    append(config.type.name)
                    append(" | cost=")
                    append(TokenConfig.resolveTokenCost(config))
                    append(" | normalOutput=")
                    append(normalOutputCount)
                    append(" | cartoonOutput=")
                    append(cartoonOutputCount)

                    if (config.templateId != null) {
                        append(" | templateId=")
                        append(config.templateId)
                    }
                },
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Text(
            text = "Packages",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 24.dp)
        )

        TokenConfig.PACKAGES.forEach { tokenPackage ->
            Text(
                text = "${tokenPackage.id}: ${tokenPackage.amount} tokens, ${tokenPackage.priceText}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}