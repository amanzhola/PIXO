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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.company.pixo.data.db.dao.HistoryDao
import com.company.pixo.data.db.entity.HistoryEntity
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun RoomHistoryTestScreen() {
    val historyDao: HistoryDao = koinInject()
    val scope = rememberCoroutineScope()

    val historyItems by historyDao
        .observeHistory()
        .collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "History items count = ${historyItems.size}",
            style = MaterialTheme.typography.headlineSmall
        )

        historyItems.forEach { item ->
            Text(
                text = "${item.toolType} / ${item.status} / ${item.id}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Button(
            modifier = Modifier.padding(top = 24.dp),
            onClick = {
                scope.launch {
                    historyDao.insert(
                        HistoryEntity(
                            id = "fake_${System.currentTimeMillis()}",
                            toolType = "GhibliLook",
                            sourceImageUri = "content://pixo/source_fake_image.jpg",
                            resultImageUrl = "https://pixo.fake/result_fake_image.jpg",
                            status = "success",
                            prompt = "Fake test prompt",
                            createdAt = System.currentTimeMillis(),
                            errorMessage = null
                        )
                    )
                }
            }
        ) {
            Text(text = "Add Fake History Item")
        }

        Button(
            modifier = Modifier.padding(top = 12.dp),
            onClick = {
                scope.launch {
                    historyDao.clear()
                }
            }
        ) {
            Text(text = "Clear History")
        }
    }
}