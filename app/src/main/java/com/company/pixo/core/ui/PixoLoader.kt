package com.company.pixo.core.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.company.pixo.R
import com.company.pixo.core.theme.AccentPrimary
import com.company.pixo.core.theme.AccentWhite
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.LabelSecondary
import com.company.pixo.core.theme.PixoTheme
import com.company.pixo.core.theme.TextIconSoft700

@Composable
fun PixoLoader(
    progress: Float,
    modifier: Modifier = Modifier
) {
    val normalizedProgress = progress.coerceIn(0f, 1f)
    val percent = (normalizedProgress * 100).toInt()

    Column(
        modifier = modifier
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        PixoProgressRing(
            progress = normalizedProgress,
            percent = percent
        )

        Text(
            modifier = Modifier
                .padding(top = dimensionResource(R.dimen._8))
                .width(180.dp)
                .height(dimensionResource(R.dimen._25)),
            text = stringResource(R.string.generation_process_title),
            color = AccentWhite,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            maxLines = 1
        )

        Text(
            modifier = Modifier
                .padding(top = dimensionResource(R.dimen._8))
                .fillMaxWidth()
                .height(dimensionResource(R.dimen._36)),
            text = stringResource(R.string.generation_process_description),
            color = LabelSecondary,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            maxLines = 2
        )
    }
}

@Composable
private fun PixoProgressRing(
    progress: Float,
    percent: Int,
    modifier: Modifier = Modifier
) {
    val strokeWidth = 16.dp
    val safeProgress = progress.coerceIn(0f, 1f)

    Box(
        modifier = modifier
            .width(dimensionResource(R.dimen._110))
            .height(dimensionResource(R.dimen._110)),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val strokePx = strokeWidth.toPx()

            val arcSize = Size(
                width = size.width - strokePx,
                height = size.height - strokePx
            )

            val topLeft = Offset(
                x = strokePx / 2f,
                y = strokePx / 2f
            )

            val center = Offset(
                x = size.width / 2f,
                y = size.height / 2f
            )

            val ringRadius = size.minDimension / 2f - strokePx / 2f
            val outerRadius = ringRadius + strokePx / 2f
            val innerRadius = ringRadius - strokePx / 2f
            val thinStrokePx = 0.5.dp.toPx()

            val yellowSweep = -360f * safeProgress

            drawArc(
                color = TextIconSoft700,
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = topLeft,
                size = arcSize,
                style = Stroke(
                    width = strokePx,
                    cap = StrokeCap.Round
                )
            )

            drawCircle(
                color = AccentPrimary.copy(alpha = 0.45f),
                radius = outerRadius,
                center = center,
                style = Stroke(width = thinStrokePx)
            )

            drawCircle(
                color = AccentPrimary.copy(alpha = 0.45f),
                radius = innerRadius,
                center = center,
                style = Stroke(width = thinStrokePx)
            )

            drawArc(
                color = AccentPrimary,
                startAngle = -90f,
                sweepAngle = yellowSweep,
                useCenter = false,
                topLeft = topLeft,
                size = arcSize,
                style = Stroke(
                    width = strokePx,
                    cap = StrokeCap.Round
                )
            )

            // Вырез на конце жёлтой дуги
            drawArc(
                color = TextIconSoft700,
                startAngle = -90f + yellowSweep,
                sweepAngle = -8f,
                useCenter = false,
                topLeft = topLeft,
                size = arcSize,
                style = Stroke(
                    width = strokePx,
                    cap = StrokeCap.Round
                )
            )

            // Вырез в начале жёлтой дуги
            drawArc(
                color = TextIconSoft700,
                startAngle = -90f,
                sweepAngle = 8f,
                useCenter = false,
                topLeft = topLeft,
                size = arcSize,
                style = Stroke(
                    width = strokePx,
                    cap = StrokeCap.Round
                )
            )

            drawArc(
                color = TextIconSoft700,
                startAngle = -90f + yellowSweep,
                sweepAngle = -8f,
                useCenter = false,
                topLeft = topLeft,
                size = arcSize,
                style = Stroke(
                    width = strokePx,
                    cap = StrokeCap.Round
                )
            )
        }

        Text(
            text = stringResource(
                R.string.generation_process_percent,
                percent
            ),
            color = AccentWhite,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}

@Preview(
    name = "Pixo / Loader 28",
    showBackground = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
private fun PixoLoader28Preview() {
    PixoLoaderPreviewContainer {
        PixoLoader(progress = 0.28f)
    }
}

@Preview(
    name = "Pixo / Loader 50",
    showBackground = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
private fun PixoLoader50Preview() {
    PixoLoaderPreviewContainer {
        PixoLoader(progress = 0.50f)
    }
}

@Preview(
    name = "Pixo / Loader 75",
    showBackground = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
private fun PixoLoader75Preview() {
    PixoLoaderPreviewContainer {
        PixoLoader(progress = 0.75f)
    }
}

@Composable
private fun PixoLoaderPreviewContainer(
    content: @Composable () -> Unit
) {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary)
                .padding(horizontal = dimensionResource(R.dimen._16)),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}