package com.company.pixo.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.company.pixo.R
import com.company.pixo.core.theme.BackgroundPrimary

private object ImagesCollageDimens {
    val GapX = 14.dp
    val GapY = 14.dp
    val Gap = 16.dp
    val GridOffsetY = (-24).dp
    val Collage2StartY = 8.dp
    val CornerRadius = 15.53.dp

    const val HeightFraction = 0.75f
    const val CardWidthFraction = 0.40f
    const val CardAspectRatio = 1.225f

    const val GridOffsetXFraction = 0.60f
    const val GridRotationDegrees = 2f

    const val Collage2StartXFraction = 0.72f
    const val Collage2StepYFraction = 0.68f
    const val Collage2RotationDegrees = 6f

    const val GradientMiddleStop = 0.7531f

    const val PreviewBackgroundColor = 0xFF09090B
    const val PreviewWidth = 390
    const val PreviewHeight = 844
}

private object ImagesCollageConstants {
    val Images = listOf(
        R.drawable.template_cherry,
        R.drawable.tools_glam,
        R.drawable.template_preview_image_lab,
        R.drawable.template_doublepicture,
        R.drawable.template_cherry,

        R.drawable.tools_glam,
        R.drawable.template_preview_image_lab,
        R.drawable.template_doublepicture,
        R.drawable.template_cherry,
        R.drawable.tools_glam,

        R.drawable.template_preview_image_lab,
        R.drawable.template_doublepicture,
        R.drawable.template_cherry,
        R.drawable.tools_glam,
        R.drawable.template_preview_image_lab
    )

    const val Columns = 5
}

@Composable
fun ImagesCollage(
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(ImagesCollageDimens.HeightFraction)
            .background(BackgroundPrimary)
            .clipToBounds()
    ) {
        val cardWidth = maxWidth * ImagesCollageDimens.CardWidthFraction
        val cardHeight = cardWidth * ImagesCollageDimens.CardAspectRatio

        val stepX = cardWidth + ImagesCollageDimens.GapX
        val stepY = cardHeight + ImagesCollageDimens.GapY

        Box(
            modifier = Modifier
                .offset(
                    x = -cardWidth * ImagesCollageDimens.GridOffsetXFraction,
                    y = ImagesCollageDimens.GridOffsetY
                )
                .rotate(ImagesCollageDimens.GridRotationDegrees)
        ) {
            ImagesCollageConstants.Images.forEachIndexed { index, image ->
                val row = index / ImagesCollageConstants.Columns
                val column = index % ImagesCollageConstants.Columns

                Image(
                    painter = painterResource(id = image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .offset(
                            x = stepX * column,
                            y = stepY * row
                        )
                        .size(
                            width = cardWidth,
                            height = cardHeight
                        )
                        .clip(RoundedCornerShape(ImagesCollageDimens.CornerRadius))
                )
            }
        }

        ImagesCollageGradient()
    }
}

@Composable
fun ImagesCollage2(
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(ImagesCollageDimens.HeightFraction)
            .background(BackgroundPrimary)
            .clipToBounds()
    ) {
        val cardWidth = maxWidth * ImagesCollageDimens.CardWidthFraction
        val cardHeight = cardWidth * ImagesCollageDimens.CardAspectRatio

        val stepX = cardWidth + ImagesCollageDimens.Gap
        val stepY = cardHeight * ImagesCollageDimens.Collage2StepYFraction

        ImagesCollageConstants.Images.forEachIndexed { index, image ->
            val row = index / ImagesCollageConstants.Columns
            val column = index % ImagesCollageConstants.Columns

            val startX = -cardWidth * ImagesCollageDimens.Collage2StartXFraction
            val startY = ImagesCollageDimens.Collage2StartY

            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .offset(
                        x = startX + stepX * column,
                        y = startY + stepY * row
                    )
                    .size(
                        width = cardWidth,
                        height = cardHeight
                    )
                    .rotate(ImagesCollageDimens.Collage2RotationDegrees)
                    .clip(RoundedCornerShape(ImagesCollageDimens.CornerRadius))
                    .background(BackgroundPrimary)
            )
        }

        ImagesCollageGradient()
    }
}

@Composable
private fun BoxScope.ImagesCollageGradient() {
    Box(
        modifier = Modifier
            .matchParentSize()
            .background(
                Brush.verticalGradient(
                    colorStops = arrayOf(
                        0f to BackgroundPrimary.copy(alpha = 0f),
                        ImagesCollageDimens.GradientMiddleStop to BackgroundPrimary,
                        1f to BackgroundPrimary
                    )
                )
            )
    )
}

@Preview(
    showBackground = true,
    backgroundColor = ImagesCollageDimens.PreviewBackgroundColor,
    widthDp = ImagesCollageDimens.PreviewWidth,
    heightDp = ImagesCollageDimens.PreviewHeight
)
@Composable
fun ImagesCollagePreview() {
    ImagesCollage()
}

@Preview(
    showBackground = true,
    backgroundColor = ImagesCollageDimens.PreviewBackgroundColor,
    widthDp = ImagesCollageDimens.PreviewWidth,
    heightDp = ImagesCollageDimens.PreviewHeight
)
@Composable
fun ImagesCollagePreview2() {
    ImagesCollage2()
}