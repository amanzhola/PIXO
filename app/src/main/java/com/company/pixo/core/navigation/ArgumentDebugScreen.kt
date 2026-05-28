package com.company.pixo.core.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ArgumentDebugScreen(
    title: String,
    lines: List<String>,
    actionText: String? = null,
    onActionClick: (() -> Unit)? = null,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall
        )

        lines.forEach { line ->
            Text(
                text = line,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        if (actionText != null && onActionClick != null) {
            Button(
                onClick = onActionClick,
                modifier = Modifier.padding(top = 24.dp)
            ) {
                Text(text = actionText)
            }
        }

        Button(
            onClick = onBackClick,
            modifier = Modifier.padding(top = 12.dp)
        ) {
            Text(text = "Back")
        }
    }
}