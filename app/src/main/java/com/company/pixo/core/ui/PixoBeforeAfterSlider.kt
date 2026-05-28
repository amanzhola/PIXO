package com.company.pixo.core.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material.icons.outlined.PhotoLibrary
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.platform.LocalDensity
import com.company.pixo.R
import com.company.pixo.core.theme.AccentSecondYellow
import com.company.pixo.core.theme.AccentWhite
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.BgWhite200
import com.company.pixo.core.theme.PixoTheme
import kotlin.math.roundToInt

enum class PixoBeforeAfterSliderVariant {
    Default,
    Preview
}

@Composable
fun PixoBeforeAfterSlider(
    @DrawableRes beforeImageRes: Int,
    modifier: Modifier = Modifier,
    @DrawableRes afterImageRes: Int? = null,
    variant: PixoBeforeAfterSliderVariant = PixoBeforeAfterSliderVariant.Default,
    sliderPosition: Float = 0.5f,
    onSliderPositionChange: ((Float) -> Unit)? = null,
    handleBackgroundBrush: Brush? = null,
    labelsAsIcons: Boolean = false,
    onBeforeLabelClick: (() -> Unit)? = null,
    onAfterLabelClick: (() -> Unit)? = null,
) {
    val normalizedPosition = sliderPosition.coerceIn(0f, 1f)
    val previewWidth = dimensionResource(R.dimen._175)

    val height = when (variant) {
        PixoBeforeAfterSliderVariant.Default -> dimensionResource(R.dimen._290)
        PixoBeforeAfterSliderVariant.Preview -> dimensionResource(R.dimen._311)
    }

    val radius = dimensionResource(R.dimen._16)
    val padding = dimensionResource(R.dimen._12)
    val dividerWidth = dimensionResource(R.dimen._2)
    val density = LocalDensity.current

    BoxWithConstraints(
        modifier = modifier
            .then(
                if (modifier == Modifier) {
                    when (variant) {
                        PixoBeforeAfterSliderVariant.Default -> {
                            Modifier
                                .fillMaxWidth()
                                .height(height)
                        }

                        PixoBeforeAfterSliderVariant.Preview -> {
                            Modifier
                                .width(previewWidth)
                                .height(height)
                        }
                    }
                } else {
                    Modifier
                }
            )
            .clip(RoundedCornerShape(radius))
            .background(BackgroundPrimary)

    ) {
        val sliderOffsetX = maxWidth * (normalizedPosition - 0.5f)

        val dragModifier = if (onSliderPositionChange != null) {
            Modifier.pointerInput(maxWidth, normalizedPosition) {
                val widthPx = with(density) { maxWidth.toPx() }

                // Tools Screen update for small screens sliders
                detectDragGestures(
                    orientationLock = Orientation.Horizontal,
                    onDrag = { change, dragAmount ->
                        change.consume()

                        if (widthPx > 0f) {
                            val nextPosition = (normalizedPosition + dragAmount.x / widthPx)
                                .coerceIn(0f, 1f)

                            onSliderPositionChange(nextPosition)
                        }
                    }
                )

            }
        } else {
            Modifier
        }

        Box(
            modifier = Modifier
                .matchParentSize()
                .then(dragModifier)
        ) {
            if (afterImageRes == null) {
                PixoCombinedBeforeAfterImage(
                    imageRes = beforeImageRes,
                    sliderPosition = normalizedPosition
                )
            } else {
                PixoSeparateBeforeAfterImages(
                    beforeImageRes = beforeImageRes,
                    afterImageRes = afterImageRes,
                    sliderPosition = normalizedPosition
                )
            }

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            colorStops = arrayOf(
                                0f to Color.Transparent,
                                1f to BackgroundPrimary.copy(alpha = 0.616f)
                            )
                        )
                    )
            )

            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(x = sliderOffsetX)
                    .width(dividerWidth)
                    .fillMaxHeight()
                    .background(AccentWhite)
            )

            PixoBeforeAfterLabel(
                text = if (labelsAsIcons) null else stringResource(R.string.slider_before),
                icon = if (labelsAsIcons) Icons.Outlined.PhotoLibrary else null,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = padding, bottom = padding),
                onClick = onBeforeLabelClick
            )

            PixoBeforeAfterLabel(
                text = if (labelsAsIcons) null else stringResource(R.string.slider_after),
                icon = if (labelsAsIcons) Icons.Outlined.PhotoCamera else null,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = padding, bottom = padding),
                onClick = onAfterLabelClick
            )

            PixoBeforeAfterHandle(
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(x = sliderOffsetX),
                backgroundBrush = handleBackgroundBrush
            )
        }
    }
}

@Composable
private fun PixoSeparateBeforeAfterImages(
    @DrawableRes beforeImageRes: Int,
    @DrawableRes afterImageRes: Int,
    sliderPosition: Float
) {
    Image(
        painter = painterResource(beforeImageRes),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        alignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .drawWithContent {
                clipRect(
                    left = 0f,
                    top = 0f,
                    right = size.width * sliderPosition,
                    bottom = size.height
                ) {
                    this@drawWithContent.drawContent()
                }
            }
    )

    Image(
        painter = painterResource(afterImageRes),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        alignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .drawWithContent {
                clipRect(
                    left = size.width * sliderPosition,
                    top = 0f,
                    right = size.width,
                    bottom = size.height
                ) {
                    this@drawWithContent.drawContent()
                }
            }
    )
}

@Composable
private fun PixoCombinedBeforeAfterImage(
    @DrawableRes imageRes: Int,
    sliderPosition: Float
) {
    val imageBitmap = ImageBitmap.imageResource(id = imageRes)

    Canvas(modifier = Modifier.fillMaxSize()) {
        val sourceWidth = imageBitmap.width / 2
        val sourceHeight = imageBitmap.height

        clipRect(
            left = 0f,
            top = 0f,
            right = size.width * sliderPosition,
            bottom = size.height
        ) {
            drawImage(
                image = imageBitmap,
                srcOffset = IntOffset(0, 0),
                srcSize = IntSize(sourceWidth, sourceHeight),
                dstOffset = IntOffset.Zero,
                dstSize = IntSize(size.width.roundToInt(), size.height.roundToInt())
            )
        }

        clipRect(
            left = size.width * sliderPosition,
            top = 0f,
            right = size.width,
            bottom = size.height
        ) {
            drawImage(
                image = imageBitmap,
                srcOffset = IntOffset(sourceWidth, 0),
                srcSize = IntSize(sourceWidth, sourceHeight),
                dstOffset = IntOffset.Zero,
                dstSize = IntSize(size.width.roundToInt(), size.height.roundToInt())
            )
        }
    }
}

@Composable
fun PixoBeforeAfterPreview(
    @StringRes titleRes: Int,
    imageResList: List<Int>,
    modifier: Modifier = Modifier,
    sliderPosition: Float = 0.5f,
    onSliderPositionChange: ((Float) -> Unit)? = null
) {
    var internalSliderPosition by remember { mutableFloatStateOf(sliderPosition) }

    val currentSliderPosition = onSliderPositionChange
        ?.let { sliderPosition }
        ?: internalSliderPosition

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._8))
    ) {
        Text(
            modifier = Modifier.fillMaxSize(),
            text = stringResource(titleRes),
            color = AccentWhite,
            style = MaterialTheme.typography.titleSmall
        )

        PixoBeforeAfterSlider(
            beforeImageRes = imageResList.first(),
            afterImageRes = imageResList.getOrNull(1),
            variant = PixoBeforeAfterSliderVariant.Preview,
            sliderPosition = currentSliderPosition,
            onSliderPositionChange = { nextPosition ->
                if (onSliderPositionChange != null) {
                    onSliderPositionChange(nextPosition)
                } else {
                    internalSliderPosition = nextPosition
                }
            }
        )
    }
}

@Composable
private fun PixoBeforeAfterLabel(
    modifier: Modifier = Modifier,
    text: String? = null,
    icon: ImageVector? = null,
    onClick: (() -> Unit)? = null
) {
    Surface(
        modifier = modifier
            .width(dimensionResource(R.dimen._64))
            .height(dimensionResource(R.dimen._28))
            .then(
                if (onClick != null) {
                    Modifier.clickable(onClick = onClick)
                } else {
                    Modifier
                }
            ),
        shape = RoundedCornerShape(dimensionResource(R.dimen._8)),
        color = BgWhite200
    ) {
        Box(
            modifier = Modifier.padding(
                PaddingValues(
                    horizontal = dimensionResource(R.dimen._12),
                    vertical = dimensionResource(R.dimen._4)
                )
            ),
            contentAlignment = Alignment.Center
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = AccentWhite,
                    modifier = Modifier.size(dimensionResource(R.dimen._16))
                )
            } else {
                Text(
                    text = text.orEmpty(),
                    color = AccentWhite,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

@Composable
private fun PixoBeforeAfterHandle(
    modifier: Modifier = Modifier,
    backgroundBrush: Brush? = null
) {
    Box(
        modifier = modifier
            .size(dimensionResource(R.dimen._33))
            .clip(CircleShape)
            .then(
                if (backgroundBrush != null) {
                    Modifier.background(backgroundBrush)
                } else {
                    Modifier.background(AccentSecondYellow)
                }
            )
            .border(
                width = dimensionResource(R.dimen._1_04),
                color = AccentWhite,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._8)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_left),
                contentDescription = null,
                tint = AccentWhite,
                modifier = Modifier
                    .width(dimensionResource(R.dimen._4_34))
                    .height(dimensionResource(R.dimen._8_68))
            )

            Icon(
                painter = painterResource(R.drawable.ic_arrow_right),
                contentDescription = null,
                tint = AccentWhite,
                modifier = Modifier
                    .width(dimensionResource(R.dimen._4_34))
                    .height(dimensionResource(R.dimen._8_68))
            )
        }
    }
}

@Preview(name = "PixoBeforeAfterSlider", showBackground = true)
@Composable
private fun PixoBeforeAfterSliderPreview() {
    PixoTheme {
        var sliderPosition by remember { mutableFloatStateOf(0.5f) }

        Box(
            modifier = Modifier
                .width(dimensionResource(R.dimen._390))
                .background(BackgroundPrimary)
                .padding(dimensionResource(R.dimen._16)),
            contentAlignment = Alignment.Center
        ) {
            PixoBeforeAfterSlider(
                beforeImageRes = R.drawable.tools_face1,
                afterImageRes = R.drawable.tools_face2,
                sliderPosition = sliderPosition,
                onSliderPositionChange = { sliderPosition = it }
            )
        }
    }
}

@Preview(name = "PixoBeforeAfterPreview", showBackground = true)
@Composable
private fun PixoBeforeAfterSmallPreview() {
    PixoTheme {
        var sliderPosition by remember { mutableFloatStateOf(0.5f) }

        Box(
            modifier = Modifier
                .width(dimensionResource(R.dimen._390))
                .background(BackgroundPrimary)
                .padding(dimensionResource(R.dimen._16)),
            contentAlignment = Alignment.Center
        ) {
            PixoBeforeAfterPreview(
                titleRes = R.string.tool_ai_enhancer,
                imageResList = listOf(
                    R.drawable.tools_cute1,
                    R.drawable.tools_cute2
                ),
                sliderPosition = sliderPosition,
                onSliderPositionChange = { sliderPosition = it }
            )
        }
    }
}

@Preview(name = "PixoBeforeAfterPreview / 25 and 75", showBackground = true)
@Composable
private fun PixoBeforeAfterPreviewTwoPositionsPreview() {
    PixoTheme {
        var firstSliderPosition by remember { mutableFloatStateOf(0.25f) }
        var secondSliderPosition by remember { mutableFloatStateOf(0.75f) }

        Column(
            modifier = Modifier
                .width(dimensionResource(R.dimen._390))
                .background(BackgroundPrimary)
                .padding(dimensionResource(R.dimen._16)),
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(R.dimen._8)
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PixoBeforeAfterPreview(
                titleRes = R.string.tool_ai_enhancer,
                imageResList = listOf(
                    R.drawable.tools_cute1,
                    R.drawable.tools_cute2
                ),
                sliderPosition = firstSliderPosition,
                onSliderPositionChange = { firstSliderPosition = it }
            )

            PixoBeforeAfterPreview(
                titleRes = R.string.tool_ai_enhancer,
                imageResList = listOf(
                    R.drawable.tools_cute1,
                    R.drawable.tools_cute2
                ),
                sliderPosition = secondSliderPosition,
                onSliderPositionChange = { secondSliderPosition = it }
            )
        }
    }
}

@Preview(name = "PixoBeforeAfterSlider / 25 and 75", showBackground = true)
@Composable
private fun PixoBeforeAfterSliderTwoPositionsPreview() {
    PixoTheme {
        var firstSliderPosition by remember { mutableFloatStateOf(0.25f) }
        var secondSliderPosition by remember { mutableFloatStateOf(0.75f) }

        Column(
            modifier = Modifier
                .width(dimensionResource(R.dimen._390))
                .background(BackgroundPrimary)
                .padding(dimensionResource(R.dimen._16)),
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(R.dimen._16)
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PixoBeforeAfterSlider(
                beforeImageRes = R.drawable.tools_face1,
                afterImageRes = R.drawable.tools_face2,
                sliderPosition = firstSliderPosition,
                onSliderPositionChange = { firstSliderPosition = it }
            )

            PixoBeforeAfterSlider(
                beforeImageRes = R.drawable.tools_face1,
                afterImageRes = R.drawable.tools_face2,
                sliderPosition = secondSliderPosition,
                onSliderPositionChange = { secondSliderPosition = it }
            )
        }
    }
}

@Preview(name = "PixoBeforeAfterSlider / Single image hair studio", showBackground = true)
@Composable
private fun PixoBeforeAfterSliderSingleImagePreview() {
    PixoTheme {
        var sliderPosition by remember { mutableFloatStateOf(0.5f) }

        Box(
            modifier = Modifier
                .width(dimensionResource(R.dimen._390))
                .background(BackgroundPrimary)
                .padding(dimensionResource(R.dimen._16)),
            contentAlignment = Alignment.Center
        ) {
            PixoBeforeAfterSlider(
                beforeImageRes = R.drawable.tools_hair_studio,
                variant = PixoBeforeAfterSliderVariant.Preview,
                sliderPosition = sliderPosition,
                onSliderPositionChange = { sliderPosition = it }
            )
        }
    }
}

@Preview(name = "PixoBeforeAfterSlider / Single image hair studio large", showBackground = true)
@Composable
private fun PixoBeforeAfterSliderSingleImageLargePreview() {
    PixoTheme {
        var sliderPosition by remember { mutableFloatStateOf(0.5f) }

        Box(
            modifier = Modifier
                .width(dimensionResource(R.dimen._390))
                .background(BackgroundPrimary)
                .padding(dimensionResource(R.dimen._16)),
            contentAlignment = Alignment.Center
        ) {
            PixoBeforeAfterSlider(
                beforeImageRes = R.drawable.tools_hair_studio,
                variant = PixoBeforeAfterSliderVariant.Default,
                sliderPosition = sliderPosition,
                onSliderPositionChange = { sliderPosition = it }
            )
        }
    }
}

@Preview(name = "PixoBeforeAfterPreview / Glam makeup", showBackground = true)
@Composable
private fun PixoBeforeAfterGlamMakeupSmallPreview() {
    PixoTheme {
        var sliderPosition by remember { mutableFloatStateOf(0.5f) }

        Box(
            modifier = Modifier
                .width(dimensionResource(R.dimen._390))
                .background(BackgroundPrimary)
                .padding(dimensionResource(R.dimen._16)),
            contentAlignment = Alignment.Center
        ) {
            PixoBeforeAfterPreview(
                titleRes = R.string.tool_ai_enhancer,
                imageResList = listOf(
                    R.drawable.tools_glam_makeup1,
                    R.drawable.tools_glam_makeup2
                ),
                sliderPosition = sliderPosition,
                onSliderPositionChange = { sliderPosition = it }
            )
        }
    }
}

@Preview(name = "PixoBeforeAfterSlider / Single image Smile Edit", showBackground = true)
@Composable
private fun PixoBeforeAfterSliderSingleSmilePreview() {
    PixoTheme {
        var sliderPosition by remember { mutableFloatStateOf(0.5f) }

        Box(
            modifier = Modifier
                .width(dimensionResource(R.dimen._390))
                .background(BackgroundPrimary)
                .padding(dimensionResource(R.dimen._16)),
            contentAlignment = Alignment.Center
        ) {
            PixoBeforeAfterSlider(
                beforeImageRes = R.drawable.tools_smile_edit,
                variant = PixoBeforeAfterSliderVariant.Preview,
                sliderPosition = sliderPosition,
                onSliderPositionChange = { sliderPosition = it }
            )
        }
    }
}

@Preview(name = "PixoBeforeAfterSlider / Single image hair studio large", showBackground = true)
@Composable
private fun PixoBeforeAfterSliderInsideImageLargePreview() {
    PixoTheme {
        var sliderPosition by remember { mutableFloatStateOf(0.5f) }

        Box(
            modifier = Modifier
                .width(dimensionResource(R.dimen._390))
                .background(BackgroundPrimary)
                .padding(dimensionResource(R.dimen._16)),
            contentAlignment = Alignment.Center
        ) {
            PixoBeforeAfterSlider(
                beforeImageRes = R.drawable.tools_glam_makeup_2_1,
                afterImageRes = R.drawable.tools_glam_makeup_2_2,
                variant = PixoBeforeAfterSliderVariant.Default,
                sliderPosition = sliderPosition,
                onSliderPositionChange = { sliderPosition = it },
                handleBackgroundBrush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFE07A8E),
                        Color(0xFFB24CCF),
                        Color(0xFF5B4DFF)
                    )
                )
            )
        }
    }
}

@Preview(name = "PixoBeforeAfterSlider / Single image Glam Makeup large", showBackground = true)
@Composable
private fun PixoBeforeAfterSliderSingleImageLargePreview2() {
    PixoTheme {
        var sliderPosition by remember { mutableFloatStateOf(0.5f) }

        Box(
            modifier = Modifier
                .width(dimensionResource(R.dimen._390))
                .background(BackgroundPrimary)
                .padding(dimensionResource(R.dimen._16)),
            contentAlignment = Alignment.Center
        ) {
            PixoBeforeAfterSlider(
                beforeImageRes = R.drawable.tools_glam_makeup_2_1,
                afterImageRes = R.drawable.tools_glam_makeup_2_2,
                sliderPosition = sliderPosition,
                onSliderPositionChange = { sliderPosition = it }
            )
        }
    }
}
