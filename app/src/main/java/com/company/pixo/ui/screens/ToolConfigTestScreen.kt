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

@Composable
fun ToolConfigTestScreen() {
    val imageTools = PixoToolConfigs.imageTools
    val templateTools = PixoToolConfigs.templateTools
    val promptFlow = PixoToolConfigs.promptFlow
    val generationConfigs = PixoToolConfigs.generationConfigs

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        Text(
            text = "Tool Config Test",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "imageTools = ${imageTools.size}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 16.dp)
        )

        Text(
            text = "templateTools = ${templateTools.size}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = "promptFlow = ${promptFlow.type.name}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = "generationConfigs = ${generationConfigs.size}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = "Expected: 11 + 1 + 24 = 36",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = "Image tools",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 24.dp)
        )

        imageTools.forEach { config ->
            Text(
                text = buildString {
                    append(stringResource(config.titleRes))
                    append(" | type=")
                    append(config.type.name)
                    append(" | backend=")
                    append(config.backendType.name)
                    append(" | flow=")
                    append(config.flowType.name)
                    append(" | options=")
                    append(config.optionsType.name)
                    append(" | tokens=")
                    append(config.generation.tokenCost)
                    append(" | photo=")
                    append(config.requiresUserPhoto)
                    append(" | prompt=")
                    append(config.requiresPrompt)
                    append(" | serverAction=")
                    append(config.serverAction)
                },
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Text(
            text = "Prompt flow",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 24.dp)
        )

        Text(
            text = buildString {
                append(stringResource(promptFlow.titleRes))
                append(" | type=")
                append(promptFlow.type.name)
                append(" | backend=")
                append(promptFlow.backendType.name)
                append(" | flow=")
                append(promptFlow.flowType.name)
                append(" | options=")
                append(promptFlow.optionsType.name)
                append(" | tokens=")
                append(promptFlow.generation.tokenCost)
                append(" | output=")
                append(promptFlow.generation.defaultOutputCount)
                append(" | cartoonOutput=")
                append(promptFlow.generation.cartoonOutputCount)
            },
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = "Templates",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 24.dp)
        )

        templateTools.forEach { config ->
            Text(
                text = buildString {
                    append(stringResource(config.titleRes))
                    append(" | type=")
                    append(config.type.name)
                    append(" | templateId=")
                    append(config.templateId)
                    append(" | backend=")
                    append(config.backendType.name)
                    append(" | flow=")
                    append(config.flowType.name)
                    append(" | tokens=")
                    append(config.generation.tokenCost)
                    append(" | serverAction=")
                    append(config.serverAction)
                    append(" | historyIdentity=")
                    append(config.historyIdentity)
                },
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}