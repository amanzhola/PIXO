package com.company.pixo.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.company.pixo.core.theme.BackgroundPrimary
import kotlin.math.roundToInt

@Composable
fun PixoAiProcessingBottomSheetHost(
    show: Boolean,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onCancelClick: () -> Unit,
    onContinueClick: () -> Unit,
) {
    val windowInfo = LocalWindowInfo.current
    val density = LocalDensity.current

    val maxSheetHeight = with(density) {
        windowInfo.containerSize.height.toDp() - 48.dp
    }

    fun requestDismiss() {
        onCancelClick()
    }

    if (show) {
        var offsetY by remember {
            mutableFloatStateOf(0f)
        }

        val closeThreshold = with(density) {
            120.dp.toPx()
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    BackgroundPrimary.copy(alpha = 0.72f)
                ),
            contentAlignment = Alignment.BottomCenter
        ) {
            Box(
                modifier = Modifier
                    .offset {
                        IntOffset(
                            x = 0,
                            y = offsetY.roundToInt().coerceAtLeast(0)
                        )
                    }
                    .draggable(
                        orientation = Orientation.Vertical,
                        state = rememberDraggableState { delta ->
                            offsetY = (offsetY + delta).coerceAtLeast(0f)
                        },
                        onDragStopped = {
                            if (offsetY > closeThreshold) {
                                requestDismiss()
                            } else {
                                offsetY = 0f
                            }
                        }
                    )
            ) {
                PixoAiProcessingBottomSheetContent(
                    checked = checked,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = maxSheetHeight)
                        .wrapContentHeight(),
                    onCheckedChange = onCheckedChange,
                    onCancelClick = {
                        requestDismiss()
                    },
                    onContinueClick = onContinueClick
                )
            }
        }
    }
}
