package com.company.pixo.core.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.company.pixo.R
import com.company.pixo.core.theme.AccentWhite
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.CameraCenterCircle
import com.company.pixo.core.theme.ColorActionsGhost
import com.company.pixo.core.theme.ColorOpacityLight
import com.company.pixo.core.theme.PixoTheme
import com.company.pixo.core.theme.TopBarRightCircle

enum class PixoIconButtonVariant {
    BackLeft,
    CloseLeft,
    FlashOffCamera,
    ChevronUpCamera,
    SettingsRight,
    CloseRight,
    CloseTransparent
}

private enum class PixoGlassGlowStyle {
    Back,
    Close
}

enum class PixoGlassBackgroundVariant {
    Default,
    Soft
}

@Composable
fun PixoIconButton(
    variant: PixoIconButtonVariant,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    glassBackgroundVariant: PixoGlassBackgroundVariant = PixoGlassBackgroundVariant.Default,
    onClick: () -> Unit
) {
    when (variant) {
        PixoIconButtonVariant.FlashOffCamera -> {
            PixoRawIconButton(
                iconRes = R.drawable.ic_flash_off,
                sizeRes = R.dimen._24,
                contentDescription = contentDescription,
                modifier = modifier,
                onClick = onClick
            )
        }

        PixoIconButtonVariant.BackLeft -> {
            PixoGlassIconButton(
                iconRes = R.drawable.ic_arrow_back,
                sizeRes = R.dimen._44,
                iconSizeRes = R.dimen._24,
                backgroundColor = ColorOpacityLight,
                padding = PaddingValues(
                    horizontal = dimensionResource(R.dimen._8),
                    vertical = dimensionResource(R.dimen._4)
                ),
                glowStyle = PixoGlassGlowStyle.Back,
                glassBackgroundVariant = glassBackgroundVariant,
                contentDescription = contentDescription,
                modifier = modifier,
                onClick = onClick
            )
        }

        PixoIconButtonVariant.CloseLeft -> {
            PixoGlassIconButton(
                iconRes = R.drawable.ic_close,
                sizeRes = R.dimen._40,
                iconSizeRes = R.dimen._20,
                backgroundColor = ColorActionsGhost,
                padding = PaddingValues(
                    dimensionResource(R.dimen._10)
                ),
                glowStyle = PixoGlassGlowStyle.Close,
                glassBackgroundVariant = glassBackgroundVariant,
                contentDescription = contentDescription,
                modifier = modifier,
                onClick = onClick
            )
        }

        PixoIconButtonVariant.ChevronUpCamera -> {
            PixoCircleIconButton(
                iconRes = R.drawable.ic_chevron_up,
                sizeRes = R.dimen._24,
                iconSizeRes = R.dimen._14,
                backgroundColor = CameraCenterCircle,
                contentDescription = contentDescription,
                modifier = modifier,
                onClick = onClick
            )
        }

        PixoIconButtonVariant.SettingsRight -> {
            PixoCircleIconButton(
                iconRes = R.drawable.ic_settings,
                sizeRes = R.dimen._32,
                iconSizeRes = R.dimen._20,
                backgroundColor = TopBarRightCircle,
                contentDescription = contentDescription,
                modifier = modifier,
                onClick = onClick
            )
        }

        PixoIconButtonVariant.CloseRight -> {
            PixoCircleIconButton(
                iconRes = R.drawable.ic_close,
                sizeRes = R.dimen._32,
                iconSizeRes = R.dimen._20,
                backgroundColor = TopBarRightCircle,
                contentDescription = contentDescription,
                modifier = modifier,
                onClick = onClick
            )
        }

        PixoIconButtonVariant.CloseTransparent -> {
            PixoRawIconButton(
                iconRes = R.drawable.ic_close,
                sizeRes = R.dimen._20,
                contentDescription = contentDescription,
                modifier = modifier,
                onClick = onClick
            )
        }
    }
}

@Composable
private fun PixoRawIconButton(
    iconRes: Int,
    sizeRes: Int,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(dimensionResource(sizeRes))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = contentDescription,
            tint = Color.Unspecified,
            modifier = Modifier.size(dimensionResource(sizeRes))
        )
    }
}

@Composable
private fun PixoCircleIconButton(
    iconRes: Int,
    sizeRes: Int,
    iconSizeRes: Int,
    backgroundColor: Color,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(dimensionResource(sizeRes))
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = contentDescription,
            tint = AccentWhite,
            modifier = Modifier.size(dimensionResource(iconSizeRes))
        )
    }
}

@Composable
private fun PixoGlassIconButton(
    iconRes: Int,
    sizeRes: Int,
    iconSizeRes: Int,
    backgroundColor: Color,
    padding: PaddingValues,
    glowStyle: PixoGlassGlowStyle,
    glassBackgroundVariant: PixoGlassBackgroundVariant,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(dimensionResource(sizeRes))
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        when (glassBackgroundVariant) {
            PixoGlassBackgroundVariant.Default -> {
                GlassIconButtonBackground(
                    topLeftGlow = glowStyle == PixoGlassGlowStyle.Back,
                    bottomRightGlow = glowStyle == PixoGlassGlowStyle.Back,
                    topRightGlow = glowStyle == PixoGlassGlowStyle.Close,
                    bottomLeftGlow = glowStyle == PixoGlassGlowStyle.Close
                )
            }

            PixoGlassBackgroundVariant.Soft -> {
                GlassIconButtonBackground1(glowStyle)
            }
        }

        Icon(
            painter = painterResource(iconRes),
            contentDescription = contentDescription,
            tint = AccentWhite,
            modifier = Modifier
                .padding(padding)
                .size(dimensionResource(iconSizeRes))
        )
    }
}

@Composable
private fun GlassIconButtonBackground1(
    style: PixoGlassGlowStyle
) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val firstCenter = when (style) {
            PixoGlassGlowStyle.Back -> Offset(size.width * 0.24f, size.height * 0.10f)
            PixoGlassGlowStyle.Close -> Offset(size.width * 0.78f, size.height * 0.10f)
        }

        val secondCenter = when (style) {
            PixoGlassGlowStyle.Back -> Offset(size.width * 0.80f, size.height * 0.88f)
            PixoGlassGlowStyle.Close -> Offset(size.width * 0.18f, size.height * 0.86f)
        }

        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    AccentWhite.copy(alpha = 0.32f),
                    AccentWhite.copy(alpha = 0.12f),
                    Color.Transparent
                ),
                center = firstCenter,
                radius = size.minDimension * 0.52f
            )
        )

        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    AccentWhite.copy(alpha = 0.22f),
                    AccentWhite.copy(alpha = 0.08f),
                    Color.Transparent
                ),
                center = secondCenter,
                radius = size.minDimension * 0.48f
            )
        )

        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    AccentWhite.copy(alpha = 0.06f),
                    Color.Transparent
                ),
                center = Offset(size.width * 0.50f, size.height * 0.45f),
                radius = size.minDimension * 0.85f
            )
        )
    }
}

@Composable
private fun GlassIconButtonBackground(
    topLeftGlow: Boolean = false,
    topRightGlow: Boolean = false,
    bottomLeftGlow: Boolean = false,
    bottomRightGlow: Boolean = false
) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val strokeWidth = size.minDimension * 0.018f

        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    AccentWhite.copy(alpha = 0.10f),
                    AccentWhite.copy(alpha = 0.035f),
                    Color.Transparent
                ),
                center = Offset(size.width * 0.5f, size.height * 0.5f),
                radius = size.minDimension * 0.85f
            )
        )

        fun softArc(
            startAngle: Float,
            sweepAngle: Float
        ) {
            drawArc(
                brush = Brush.sweepGradient(
                    colors = listOf(
                        Color.Transparent,
                        AccentWhite.copy(alpha = 0.42f),
                        AccentWhite.copy(alpha = 0.18f),
                        Color.Transparent
                    )
                ),
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(
                    width = strokeWidth,
                    cap = StrokeCap.Round
                )
            )
        }

        if (topLeftGlow) softArc(startAngle = 195f, sweepAngle = 58f)
        if (topRightGlow) softArc(startAngle = 288f, sweepAngle = 58f)
        if (bottomLeftGlow) softArc(startAngle = 105f, sweepAngle = 58f)
        if (bottomRightGlow) softArc(startAngle = 15f, sweepAngle = 58f)
    }
}

@Preview(name = "PixoIconButton / Back left", showBackground = true)
@Composable
private fun PixoIconButtonBackLeftPreview() {
    PixoIconButtonPreviewContainer {
        PixoIconButton(
            variant = PixoIconButtonVariant.BackLeft,
            contentDescription = "Back",
            onClick = {}
        )
    }
}

@Preview(name = "PixoIconButton / Close left", showBackground = true)
@Composable
private fun PixoIconButtonCloseLeftPreview() {
    PixoIconButtonPreviewContainer {
        PixoIconButton(
            variant = PixoIconButtonVariant.CloseLeft,
            contentDescription = "Close",
            onClick = {}
        )
    }
}

// better version for customers of Broad App
@Preview(name = "PixoIconButton / Back left soft", showBackground = true)
@Composable
private fun PixoIconButtonBackLeftSoftPreview() {
    PixoIconButtonPreviewContainer {
        PixoIconButton(
            variant = PixoIconButtonVariant.BackLeft,
            glassBackgroundVariant = PixoGlassBackgroundVariant.Soft,
            contentDescription = "Back",
            onClick = {}
        )
    }
}

// better version for customers of Broad App
@Preview(name = "PixoIconButton / Close left soft", showBackground = true)
@Composable
private fun PixoIconButtonCloseLeftSoftPreview() {
    PixoIconButtonPreviewContainer {
        PixoIconButton(
            variant = PixoIconButtonVariant.CloseLeft,
            glassBackgroundVariant = PixoGlassBackgroundVariant.Soft,
            contentDescription = "Close",
            onClick = {}
        )
    }
}

@Preview(name = "PixoIconButton / Settings right", showBackground = true)
@Composable
private fun PixoIconButtonSettingsRightPreview() {
    PixoIconButtonPreviewContainer {
        PixoIconButton(
            variant = PixoIconButtonVariant.SettingsRight,
            contentDescription = "Settings",
            onClick = {}
        )
    }
}

@Preview(name = "PixoIconButton / Close right", showBackground = true)
@Composable
private fun PixoIconButtonCloseRightPreview() {
    PixoIconButtonPreviewContainer {
        PixoIconButton(
            variant = PixoIconButtonVariant.CloseRight,
            contentDescription = "Close",
            onClick = {}
        )
    }
}

@Preview(name = "PixoIconButton / Flash off camera", showBackground = true)
@Composable
private fun PixoIconButtonFlashOffCameraPreview() {
    PixoIconButtonPreviewContainer {
        PixoIconButton(
            variant = PixoIconButtonVariant.FlashOffCamera,
            contentDescription = "Flash off",
            onClick = {}
        )
    }
}

@Preview(name = "PixoIconButton / Chevron up camera", showBackground = true)
@Composable
private fun PixoIconButtonChevronUpCameraPreview() {
    PixoIconButtonPreviewContainer {
        PixoIconButton(
            variant = PixoIconButtonVariant.ChevronUpCamera,
            contentDescription = "Collapse",
            onClick = {}
        )
    }
}

@Composable
private fun PixoIconButtonPreviewContainer(
    content: @Composable () -> Unit
) {
    PixoTheme {
        Box(
            modifier = Modifier
                .size(dimensionResource(R.dimen._390))
                .background(BackgroundPrimary),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}