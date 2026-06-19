package com.company.pixo.feature.editor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.company.pixo.domain.model.PixoToolConfigs
import com.company.pixo.domain.model.ToolType

@Composable
fun PixoSmileEditRoute(
    imageUri: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onGenerateClick: (String) -> Unit = {}
) {
    val config = remember {
        PixoToolConfigs.findByType(ToolType.SMILE_EDIT)
    }

    val levels = remember(config) {
        config?.optionConfig?.samples.orEmpty()
    }

    val defaultLevel = levels.firstOrNull()?.serverValue ?: "1"

    var smileLevel by rememberSaveable {
        mutableStateOf(defaultLevel)
    }

    var showSmileSample by rememberSaveable {
        mutableStateOf(false)
    }

    PixoSmileEditEditScreen(
        imageUri = imageUri,
        smileLevel = smileLevel.toIntOrNull() ?: 0,
        showSmileSample = showSmileSample,
        modifier = modifier,
        onBackClick = onBackClick,
        onSmileLevelChange = { level ->
            smileLevel = level.toString()
        },
        onSmileSampleClick = {
            showSmileSample = !showSmileSample
        },
        onGenerateClick = {
            onGenerateClick(smileLevel)
        }
    )
}