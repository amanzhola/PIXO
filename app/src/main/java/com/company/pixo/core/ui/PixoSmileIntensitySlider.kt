package com.company.pixo.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.company.pixo.R
import com.company.pixo.core.theme.AccentPrimary
import com.company.pixo.core.theme.AccentWhite
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.ColorActionsGhost
import com.company.pixo.core.theme.PixoTheme
import kotlin.math.roundToInt

@Composable
fun PixoSmileIntensitySlider(
    level: Int,
    onLevelChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val safeLevel = level.coerceIn(0, 4)
    val progress = safeLevel / 4f

    Column(
        modifier = modifier
            .width(358.dp)
            .height(98.dp)
            .background(BackgroundPrimary),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            modifier = Modifier
                .width(358.dp)
                .height(25.dp),
            text = stringResource(R.string.smile_edit_intensity_title),
            color = AccentWhite,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1
        )

        Column(
            modifier = Modifier
                .width(358.dp)
                .height(68.dp)
        ) {
            Row(
                modifier = Modifier
                    .width(358.dp)
                    .height(20.dp)
                    .padding(horizontal = 14.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(5) { index ->
                    Box(
                        modifier = Modifier
                            .size(4.dp)
                            .background(
                                color = if (index <= safeLevel) {
                                    AccentPrimary
                                } else {
                                    AccentWhite
                                },
                                shape = RoundedCornerShape(100.dp)
                            )
                    )
                }
            }

            BoxWithConstraints(
                modifier = Modifier
                    .width(358.dp)
                    .height(24.dp)
                    .pointerInput(Unit) {
                        detectDragGestures { change, _ ->
                            change.consume()

                            val x = change.position.x
                            val width = size.width.toFloat()
                            val nextLevel = ((x / width) * 4f)
                                .roundToInt()
                                .coerceIn(0, 4)

                            onLevelChange(nextLevel)
                        }
                    }
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {}
                    ),
                contentAlignment = Alignment.CenterStart
            ) {
                val trackWidth = maxWidth
                val thumbWidth = 32.dp
                val thumbCenterX = 14.dp + ((trackWidth - 28.dp) * progress)
                val thumbOffsetX = thumbCenterX - thumbWidth / 2

                Box(
                    modifier = Modifier
                        .width(trackWidth)
                        .height(4.dp)
                        .align(Alignment.CenterStart)
                        .background(
                            color = ColorActionsGhost,
                            shape = RoundedCornerShape(100.dp)
                        )
                )

                Box(
                    modifier = Modifier
                        .width(thumbCenterX)
                        .height(4.dp)
                        .align(Alignment.CenterStart)
                        .background(
                            color = AccentPrimary,
                            shape = RoundedCornerShape(100.dp)
                        )
                )

                Box(
                    modifier = Modifier
                        .offset(x = thumbOffsetX)
                        .width(32.dp)
                        .height(20.dp)
                        .shadow(
                            elevation = 3.dp,
                            shape = RoundedCornerShape(100.dp)
                        )
                        .background(
                            color = AccentWhite,
                            shape = RoundedCornerShape(100.dp)
                        )
                )
            }

            Row(
                modifier = Modifier
                    .width(358.dp)
                    .height(24.dp)
                    .padding(top = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = stringResource(R.string.smile_edit_percent_zero),
                    color = AccentWhite,
                    style = MaterialTheme.typography.labelSmall,
                    maxLines = 1
                )

                Text(
                    text = stringResource(R.string.smile_edit_percent_hundred),
                    color = AccentWhite,
                    style = MaterialTheme.typography.labelSmall,
                    maxLines = 1
                )
            }
        }
    }
}

@Preview(
    name = "Pixo / Smile Intensity Slider",
    showBackground = true,
    widthDp = 390,
    heightDp = 130
)
@Composable
private fun PixoSmileIntensitySliderPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary)
                .padding(horizontal = dimensionResource(R.dimen._16)),
            contentAlignment = Alignment.Center
        ) {
            PixoSmileIntensitySlider(
                level = 1,
                onLevelChange = {}
            )
        }
    }
}
