package com.company.pixo.ui.screens

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.company.pixo.domain.model.GenerationCreateRequest
import com.company.pixo.domain.model.GenerationStatus
import com.company.pixo.domain.model.PixoToolConfigs
import com.company.pixo.domain.model.ToolType
import com.company.pixo.domain.repository.GenerationRepository
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun MockGenerationRepositoryTestScreen() {
    val repository: GenerationRepository = koinInject()
    val scope = rememberCoroutineScope()

    var taskId by remember {
        mutableStateOf<String?>(null)
    }

    var statusText by remember {
        mutableStateOf("No generation yet")
    }

    var resultText by remember {
        mutableStateOf("")
    }

    LaunchedEffect(taskId) {
        val currentTaskId = taskId ?: return@LaunchedEffect

        repository.observeGeneration(currentTaskId).collect { status ->
            statusText = status.toReadableText()

            if (status is GenerationStatus.Success || status is GenerationStatus.Error) {
                val result = repository.getResult(currentTaskId)

                resultText = buildString {
                    append("generationId = ")
                    append(result.generationId)
                    append("\n")
                    append("resultImageUrl = ")
                    append(result.resultImageUrl)
                    append("\n")
                    append("errorMessage = ")
                    append(result.errorMessage)
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Mock Generation Repository Test",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "taskId = ${taskId.orEmpty()}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 16.dp)
        )

        Text(
            text = statusText,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 12.dp)
        )

        Text(
            text = resultText,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 12.dp)
        )

        Button(
            modifier = Modifier.padding(top = 24.dp),
            onClick = {
                scope.launch {
                    val config = PixoToolConfigs.findByType(ToolType.GHIBLI)
                        ?: return@launch

                    val uploadResult = repository.uploadImage(
                        localImageUri = "content://pixo/test_source_image.jpg"
                    )

                    val createResult = repository.createGeneration(
                        request = GenerationCreateRequest(
                            toolType = config.type,
                            backendType = config.backendType,
                            serverAction = config.serverAction,
                            sourceImageUrl = uploadResult.imageUrl,
                            sourceImageUri = "content://pixo/test_source_image.jpg",
                            prompt = config.defaultPrompt,
                            templateId = config.templateId,
                            options = emptyMap(),
                            tokenCost = config.generation.tokenCost,
                            outputCount = config.generation.defaultOutputCount,
                            historyIdentity = config.historyIdentity
                        )
                    )

                    taskId = createResult.taskId
                    resultText = ""
                }
            }
        ) {
            Text(text = "Generate Success")
        }

        Button(
            modifier = Modifier.padding(top = 12.dp),
            onClick = {
                scope.launch {
                    val config = PixoToolConfigs.findByType(ToolType.GHIBLI)
                        ?: return@launch

                    val uploadResult = repository.uploadImage(
                        localImageUri = "content://pixo/test_source_image.jpg"
                    )

                    val createResult = repository.createGeneration(
                        request = GenerationCreateRequest(
                            toolType = config.type,
                            backendType = config.backendType,
                            serverAction = config.serverAction,
                            sourceImageUrl = uploadResult.imageUrl,
                            sourceImageUri = "content://pixo/test_source_image.jpg",
                            prompt = config.defaultPrompt,
                            templateId = config.templateId,
                            options = mapOf(
                                "mockError" to "true"
                            ),
                            tokenCost = config.generation.tokenCost,
                            outputCount = config.generation.defaultOutputCount,
                            historyIdentity = config.historyIdentity
                        )
                    )

                    taskId = createResult.taskId
                    resultText = ""
                }
            }
        ) {
            Text(text = "Generate Error")
        }
    }
}

private fun GenerationStatus.toReadableText(): String {
    return when (this) {
        GenerationStatus.Idle -> {
            "status = idle"
        }

        is GenerationStatus.Processing -> {
            "status = processing, progress = $progressPercent%"
        }

        is GenerationStatus.Success -> {
            "status = success, generationId = $generationId"
        }

        is GenerationStatus.Error -> {
            "status = error, message = $message"
        }
    }
}