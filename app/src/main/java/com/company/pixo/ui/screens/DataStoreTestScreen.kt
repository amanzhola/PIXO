package com.company.pixo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.company.pixo.data.datastore.AppPreferencesDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun DataStoreTestScreen() {

    val preferences: AppPreferencesDataSource = koinInject()

    val onboardingCompleted by preferences
        .isOnboardingCompleted()
        .collectAsState(initial = false)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        verticalArrangement = Arrangement.Center,

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "onboardingCompleted = $onboardingCompleted",
            style = MaterialTheme.typography.headlineSmall
        )

        Button(
            modifier = Modifier.padding(top = 24.dp),

            onClick = {
                CoroutineScope(Dispatchers.IO).launch {

                    preferences.setOnboardingCompleted(
                        completed = !onboardingCompleted
                    )
                }
            }
        ) {
            Text("Toggle Flag")
        }
    }
}