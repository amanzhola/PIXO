package com.company.pixo.feature.history

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.company.pixo.R
import com.company.pixo.feature.main.MainTab
import com.company.pixo.domain.model.ToolType
import com.company.pixo.domain.repository.HistoryRepository
import kotlinx.coroutines.delay
import org.koin.compose.koinInject

@StringRes
private fun historyTitleRes(
    toolType: String
): Int {
    return when (runCatching { ToolType.valueOf(toolType) }.getOrNull()) {
        ToolType.AI_ENHANCER -> R.string.ai_enhancer_title
        ToolType.GLAM_MAKEUP -> R.string.tool_glam_makeup
        ToolType.REMOVE_OBJECTS -> R.string.tool_remove_objects
        ToolType.REMOVE_BACKGROUND -> R.string.tool_remove_background
        ToolType.SKIN_IMPROVE -> R.string.tool_skin_improve
        ToolType.UPSCALE_IMAGE -> R.string.tool_upscale_image
        ToolType.CHANGE_SCENE -> R.string.tool_change_scene
        ToolType.HAIR_STUDIO -> R.string.tool_hair_studio
        ToolType.SMILE_EDIT -> R.string.tool_smile_edit
        ToolType.GHOSTFACE -> R.string.tool_ghost_face
        ToolType.GHIBLI -> R.string.ghibli_style
        ToolType.PROMPT -> R.string.prompt_flow_title
        ToolType.TEMPLATE -> R.string.template_title
        null -> R.string.prompt_flow_title
    }
}

private enum class PixoHistoryLoadingSlotState {
    Idle,
    Loading,
}

val emptyPreviewImages: List<PixoHistoryItem> = listOf(
    PixoHistoryItem.Image(
        id = "empty_preview_1",
        toolType = ToolType.HAIR_STUDIO,
        titleRes = R.string.tool_hair_studio,
        imageUrl = "android.resource://com.company.pixo/${R.drawable.tools_hair_studio4}"
    ),
    PixoHistoryItem.Image(
        id = "empty_preview_2",
        toolType = ToolType.GHIBLI,
        titleRes = R.string.tool_ghibli_look,
        imageUrl = "android.resource://com.company.pixo/${R.drawable.tools_ghibli_look}"
    ),
    PixoHistoryItem.Image(
        id = "empty_preview_3",
        toolType = ToolType.GHOSTFACE,
        titleRes = R.string.history_ghostface_style,
        imageUrl = "android.resource://com.company.pixo/${R.drawable.tools_ghostface_style}"
    ),
    PixoHistoryItem.Image(
        id = "empty_preview_4",
        toolType = ToolType.HAIR_STUDIO,
        titleRes = R.string.tool_hair_studio,
        imageUrl = "android.resource://com.company.pixo/${R.drawable.tools_hair_studio3}"
    )
)

@Composable
fun HistoryRoute(
    onTabClick: (MainTab) -> Unit = {},
    hasActiveSubscription: Boolean = false,
    tokens: String = "1000",
    onGetProClick: () -> Unit,
    onTokenBalanceClick: () -> Unit = {},
    onSettingsClick: () -> Unit,
    historyRepository: HistoryRepository = koinInject(),
    onImageClick: (String, ToolType?) -> Unit = { _, _ -> },
    onLoadingClick: (String, ToolType?) -> Unit = { _, _ -> },
    onErrorRetryClick: (String, ToolType?) -> Unit = { _, _ -> },
    onErrorDeleteClick: (String) -> Unit = {},
) {
    val historyEntities by historyRepository
        .observeHistory()
        .collectAsState(initial = emptyList())

    val realItems = historyEntities
        .mapNotNull { item ->
            Log.d(
                "HistoryDelete",
                "History item from DB: id=${item.id}, status=${item.status}, resultImageUrl=${item.resultImageUrl}"
            )

            val toolType = runCatching {
                ToolType.valueOf(item.toolType)
            }.getOrNull()

            when (item.status) {
                "success" -> {
                    if (item.resultImageUrl.isNullOrBlank()) {
                        null
                    } else {
                        PixoHistoryItem.Image(
                            id = item.id,
                            toolType = toolType,
                            titleRes = historyTitleRes(item.toolType),
                            imageUrl = item.resultImageUrl
                        )
                    }
                }

                "loading" -> {
                    Log.d(
                        "HistoryLoadingClick",
                        "map loading item id=${item.id}, toolType=$toolType"
                    )

                    PixoHistoryItem.Loading(
                        id = item.id,
                        toolType = toolType
                    )
                }

                "error" -> {
                    PixoHistoryItem.Error(
                        id = item.id,
                        toolType = toolType
                    )
                }

                else -> null
            }
        }

    val hasRealHistory = realItems.isNotEmpty()

    var loadedCount by remember {
        mutableIntStateOf(0)
    }

    var loadingSlotState by remember {
        mutableStateOf(PixoHistoryLoadingSlotState.Idle)
    }

    var hasPlayedEmptyLoadingFlow by remember {
        mutableStateOf(false)
    }

    var previousHasRealHistory by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(hasRealHistory) {
        if (previousHasRealHistory && !hasRealHistory) {
            loadingSlotState = PixoHistoryLoadingSlotState.Idle
            hasPlayedEmptyLoadingFlow = false
        }

        previousHasRealHistory = hasRealHistory
    }

    LaunchedEffect(
        hasRealHistory,
        loadedCount,
        loadingSlotState,
        hasPlayedEmptyLoadingFlow
    ) {
        if (hasRealHistory || hasPlayedEmptyLoadingFlow) {
            return@LaunchedEffect
        }

        delay(1200)

        when {
            loadedCount == 0 &&
                    loadingSlotState == PixoHistoryLoadingSlotState.Idle -> {
                loadingSlotState = PixoHistoryLoadingSlotState.Loading
            }

            loadingSlotState == PixoHistoryLoadingSlotState.Loading -> {
                loadedCount += 1
                loadingSlotState = PixoHistoryLoadingSlotState.Idle
            }

            loadedCount < emptyPreviewImages.size -> {
                loadingSlotState = PixoHistoryLoadingSlotState.Loading
            }

            else -> {
                delay(1200)
                loadedCount = 0
                loadingSlotState = PixoHistoryLoadingSlotState.Idle
                hasPlayedEmptyLoadingFlow = true
            }
        }
    }

    val emptyAnimatedItems: List<PixoHistoryItem> = buildList {
        if (loadingSlotState == PixoHistoryLoadingSlotState.Loading) {
            add(
                PixoHistoryItem.Loading(
                    id = "empty_loading",
                    toolType = null
                )
            )
        }

        addAll(emptyPreviewImages.take(loadedCount))

        if (loadedCount == 0 &&
            loadingSlotState == PixoHistoryLoadingSlotState.Idle
        ) {
            addAll(List(4) { PixoHistoryItem.Empty })
        }
    }

    val items = if (hasRealHistory) {
        realItems
    } else {
        emptyAnimatedItems
    }

    val showEmptyMessage =
        !hasRealHistory &&
                hasPlayedEmptyLoadingFlow &&
                loadingSlotState == PixoHistoryLoadingSlotState.Idle

    PixoHistoryScreen(
        hasActiveSubscription = hasActiveSubscription,
        tokens = tokens,
        items = items,
        showEmptyMessage = showEmptyMessage,
        onGetProClick = onGetProClick,
        onTokenBalanceClick = onTokenBalanceClick,
        onSettingsClick = onSettingsClick,
        onImageClick = onImageClick,
        onTabClick = onTabClick,
        onLoadingClick = { taskId, toolType ->
            Log.d(
                "HistoryLoadingClick",
                "HistoryRoute received loading click taskId=$taskId, toolType=$toolType"
            )

            onLoadingClick(taskId, toolType)
        },
        onErrorRetryClick = onErrorRetryClick,
        onErrorDeleteClick = onErrorDeleteClick,
    )
}
