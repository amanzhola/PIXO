package com.company.pixo.feature.editor

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.company.pixo.R
import com.company.pixo.core.theme.AccentPrimary
import com.company.pixo.core.theme.AccentWhite
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.ui.PixoTopBar
import com.company.pixo.core.ui.PixoTopBarVariant

@Composable
fun PixoRemoveObjectsRefineScreen(
    imageUrl: String,
    brushEnabled: Boolean,
    scale: Float,
    offset: Offset,
    strokes: List<List<OffsetPoint>>,
    modifier: Modifier = Modifier,
    onBrushClick: () -> Unit,
    onZoomInClick: () -> Unit,
    onZoomOutClick: () -> Unit,
    onMove: (Offset) -> Unit,
    onStrokeStart: (OffsetPoint) -> Unit,
    onStrokeMove: (OffsetPoint) -> Unit,
    onUndoClick: () -> Unit,
    onBackClick: () -> Unit,
    onGenerateClick: () -> Unit,
    onCanvasSizeChanged: (Int, Int) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundPrimary)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    translationX = offset.x
                    translationY = offset.y
                }
                .pointerInput(brushEnabled, scale) {
                    if (brushEnabled) {
                        detectDragGestures(
                            onDragStart = { point ->
                                onStrokeStart(
                                    OffsetPoint(
                                        x = point.x,
                                        y = point.y
                                    )
                                )
                            },
                            onDrag = { change, _ ->
                                onStrokeMove(
                                    OffsetPoint(
                                        x = change.position.x,
                                        y = change.position.y
                                    )
                                )
                            }
                        )
                    } else {
                        detectDragGestures(
                            onDrag = { _, dragAmount ->
                                onMove(dragAmount)
                            }
                        )
                    }
                }
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .onSizeChanged { size ->
                        onCanvasSizeChanged(
                            size.width,
                            size.height
                        )
                    }
            ) {
                strokes.forEach { stroke ->
                    if (stroke.size > 1) {
                        val path = Path().apply {
                            moveTo(
                                stroke.first().x,
                                stroke.first().y
                            )

                            stroke.drop(1).forEach { point ->
                                lineTo(
                                    point.x,
                                    point.y
                                )
                            }
                        }

                        drawPath(
                            path = path,
                            color = AccentPrimary.copy(alpha = 0.72f),
                            style = Stroke(
                                width = 32.dp.toPx(),
                                cap = StrokeCap.Round
                            )
                        )
                    }
                }
            }
        }

        PixoTopBar(
            variant = PixoTopBarVariant.Detail,
            titleRes = R.string.tool_remove_objects,
            onBackClick = onBackClick
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .navigationBarsPadding()
                .padding(
                    start = dimensionResource(R.dimen._16),
                    bottom = dimensionResource(R.dimen._24)
                ),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._8))
        ) {
            PixoZoomFloatingButton(
                text = "+",
                onClick = onZoomInClick
            )

            PixoZoomFloatingButton(
                text = "−",
                onClick = onZoomOutClick
            )
        }

        PixoRefineFloatingIconButton(
            iconRes = R.drawable.ic_baseline_add_task_24,
            selected = false,
            enabled = strokes.isNotEmpty(),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(bottom = dimensionResource(R.dimen._24)),
            onClick = onGenerateClick
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .navigationBarsPadding()
                .padding(
                    end = dimensionResource(R.dimen._16),
                    bottom = dimensionResource(R.dimen._24)
                ),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._12))
        ) {
            PixoRefineFloatingIconButton(
                iconRes = R.drawable.ic_arrow_back,
                selected = false,
                enabled = strokes.isNotEmpty(),
                onClick = onUndoClick
            )

            PixoRefineFloatingIconButton(
                iconRes = R.drawable.ic_baseline_brush_24,
                selected = brushEnabled,
                enabled = true,
                onClick = onBrushClick
            )
        }
    }
}

@Composable
private fun PixoRefineFloatingIconButton(
    @DrawableRes iconRes: Int,
    selected: Boolean,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .size(dimensionResource(R.dimen._56))
            .clickable(
                enabled = enabled,
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        shape = RoundedCornerShape(dimensionResource(R.dimen._100)),
        color = when {
            selected -> AccentPrimary.copy(alpha = 0.72f)
            enabled -> BackgroundPrimary.copy(alpha = 0.42f)
            else -> BackgroundPrimary.copy(alpha = 0.22f)
        },
        border = BorderStroke(
            width = 1.dp,
            color = AccentWhite.copy(
                alpha = if (enabled) 0.32f else 0.14f
            )
        )
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = null,
                tint = AccentWhite.copy(
                    alpha = if (enabled) 1f else 0.36f
                ),
                modifier = Modifier.size(dimensionResource(R.dimen._24))
            )
        }
    }
}

@Composable
private fun PixoZoomFloatingButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .size(dimensionResource(R.dimen._48))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        shape = RoundedCornerShape(dimensionResource(R.dimen._100)),
        color = BackgroundPrimary.copy(alpha = 0.42f),
        border = BorderStroke(
            width = 1.dp,
            color = AccentWhite.copy(alpha = 0.32f)
        )
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = text,
                color = AccentWhite,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

