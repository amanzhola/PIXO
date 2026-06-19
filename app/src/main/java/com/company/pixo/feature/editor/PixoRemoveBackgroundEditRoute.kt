package com.company.pixo.feature.editor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.company.pixo.core.ui.PixoRemoveBackgroundType
import com.company.pixo.domain.model.PixoToolConfigs
import com.company.pixo.domain.model.ToolType

@Composable
fun PixoRemoveBackgroundEditRoute(
    imageUri: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onGenerateClick: (backgroundType: String) -> Unit = {}
) {
    val config = remember {
        PixoToolConfigs.findByType(ToolType.REMOVE_BACKGROUND)
    }

    val samples = remember(config) {
        config?.optionConfig?.samples.orEmpty()
    }

    val whiteId = samples
        .firstOrNull { it.id == "white" }
        ?.id
        ?: "white"

    val transparentId = samples
        .firstOrNull { it.id == "transparent" }
        ?.id
        ?: "transparent"

    var selectedTypeId by rememberSaveable {
        mutableStateOf<String?>(null)
    }

    val selectedType = when (selectedTypeId) {
        whiteId -> PixoRemoveBackgroundType.White
        transparentId -> PixoRemoveBackgroundType.Transparent
        else -> null
    }

    PixoRemoveBackgroundEditScreen(
        imageUri = imageUri,
        selectedType = selectedType,
        modifier = modifier,
        onBackClick = onBackClick,
        onTypeClick = { type ->
            val clickedId = when (type) {
                PixoRemoveBackgroundType.White -> whiteId
                PixoRemoveBackgroundType.Transparent -> transparentId
            }

            selectedTypeId = if (selectedTypeId == clickedId) {
                null
            } else {
                clickedId
            }
        },
        onGenerateClick = {
            onGenerateClick(selectedTypeId ?: "default")
        }
    )
}