package com.company.pixo.feature.editor

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Paint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import java.io.File
import kotlinx.coroutines.launch
import androidx.core.graphics.createBitmap

data class OffsetPoint(
    val x: Float,
    val y: Float
)

@Composable
fun PixoRemoveObjectsRefineRoute(
    imageUrl: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onGenerateClick: (maskImageUri: String) -> Unit = {}
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var brushEnabled by rememberSaveable {
        mutableStateOf(false)
    }

    var scale by rememberSaveable {
        mutableFloatStateOf(1f)
    }

    var offsetX by rememberSaveable {
        mutableFloatStateOf(0f)
    }

    var offsetY by rememberSaveable {
        mutableFloatStateOf(0f)
    }

    val strokes = rememberSaveable {
        mutableStateListOf<List<OffsetPoint>>()
    }

    var maskWidth by rememberSaveable {
        mutableIntStateOf(0)
    }

    var maskHeight by rememberSaveable {
        mutableIntStateOf(0)
    }

    PixoRemoveObjectsRefineScreen(
        imageUrl = imageUrl,
        modifier = modifier,
        brushEnabled = brushEnabled,
        scale = scale,
        offset = Offset(offsetX, offsetY),
        strokes = strokes,
        onBrushClick = {
            brushEnabled = !brushEnabled
        },
        onZoomInClick = {
            scale = (scale + 0.25f).coerceAtMost(3f)
        },
        onZoomOutClick = {
            scale = (scale - 0.25f).coerceAtLeast(1f)

            if (scale == 1f) {
                offsetX = 0f
                offsetY = 0f
            }
        },
        onMove = { delta ->
            if (scale > 1f) {
                offsetX += delta.x
                offsetY += delta.y
            }
        },
        onStrokeStart = { point ->
            strokes.add(listOf(point))
        },
        onStrokeMove = { point ->
            val lastIndex = strokes.lastIndex

            if (lastIndex >= 0) {
                strokes[lastIndex] = strokes[lastIndex] + point
            }
        },
        onBackClick = onBackClick,
        onUndoClick = {
            if (strokes.isNotEmpty()) {
                strokes.removeAt(strokes.lastIndex)
            }
        },
        onGenerateClick = {
            if (maskWidth > 0 && maskHeight > 0 && strokes.isNotEmpty()) {
                scope.launch {
                    val maskImageUri = saveRemoveObjectsMaskToCache(
                        context = context,
                        strokes = strokes,
                        width = maskWidth,
                        height = maskHeight
                    )

                    onGenerateClick(maskImageUri)
                }
            }
        },
        onCanvasSizeChanged = { width, height ->
            maskWidth = width
            maskHeight = height
        },
    )
}

private fun saveRemoveObjectsMaskToCache(
    context: Context,
    strokes: List<List<OffsetPoint>>,
    width: Int,
    height: Int
): String {
    val bitmap = createBitmap(width, height)

    val canvas = android.graphics.Canvas(bitmap)

    canvas.drawColor(android.graphics.Color.BLACK)

    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = android.graphics.Color.WHITE
        style = Paint.Style.STROKE
        strokeWidth = 42f
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
    }

    strokes.forEach { stroke ->
        if (stroke.isNotEmpty()) {
            val path = android.graphics.Path().apply {
                moveTo(stroke.first().x, stroke.first().y)

                stroke.drop(1).forEach { point ->
                    lineTo(point.x, point.y)
                }
            }

            canvas.drawPath(path, paint)
        }
    }

    val dir = File(
        context.cacheDir,
        "remove_objects_masks"
    ).apply {
        mkdirs()
    }

    val file = File(
        dir,
        "mask_${System.currentTimeMillis()}.png"
    )

    file.outputStream().use { output ->
        bitmap.compress(
            Bitmap.CompressFormat.PNG,
            100,
            output
        )
    }

    return file.toUri().toString()
}