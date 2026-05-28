package com.company.pixo.core.ui

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.company.pixo.R
import com.company.pixo.core.theme.AccentWhite
import com.company.pixo.core.theme.AiProcessingContinueDisabled
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.BackgroundTertiary
import com.company.pixo.core.theme.PixoTheme
import com.company.pixo.core.theme.TextIconBlack

@Composable
fun PixoAiProcessingBottomActions(
    continueEnabled: Boolean,
    modifier: Modifier = Modifier,
    onCancelClick: () -> Unit,
    onContinueClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(BackgroundPrimary),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._8)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        PixoAiProcessingActionButton(
            textRes = R.string.common_cancel,
            backgroundColor = BackgroundTertiary,
            textColor = AccentWhite,
            modifier = Modifier.weight(1f),
            enabled = true,
            onClick = onCancelClick
        )

        PixoAiProcessingActionButton(
            textRes = R.string.common_continue,
            backgroundColor = if (continueEnabled) AccentWhite else AiProcessingContinueDisabled,
            textColor = TextIconBlack,
            modifier = Modifier.weight(1f),
            enabled = continueEnabled,
            onClick = onContinueClick
        )
    }
}

@Composable
private fun PixoAiProcessingActionButton(
    @StringRes textRes: Int,
    backgroundColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .height(dimensionResource(R.dimen._46))
            .clickable(
                enabled = enabled,
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        shape = RoundedCornerShape(dimensionResource(R.dimen._12)),
        color = backgroundColor
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = dimensionResource(R.dimen._12),
                    vertical = dimensionResource(R.dimen._8)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(textRes),
                color = textColor,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
    }
}

@Preview(
    name = "Pixo / AI Processing Bottom Actions inactive",
    showBackground = true,
    widthDp = 390,
    heightDp = 80
)
@Composable
private fun PixoAiProcessingBottomActionsInactivePreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary),
            contentAlignment = Alignment.Center
        ) {
            PixoAiProcessingBottomActions(
                continueEnabled = false,
                onCancelClick = {},
                onContinueClick = {}
            )
        }
    }
}

@Preview(
    name = "Pixo / AI Processing Bottom Actions active",
    showBackground = true,
    widthDp = 390,
    heightDp = 80
)
@Composable
private fun PixoAiProcessingBottomActionsActivePreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary),
            contentAlignment = Alignment.Center
        ) {
            PixoAiProcessingBottomActions(
                continueEnabled = true,
                onCancelClick = {},
                onContinueClick = {}
            )
        }
    }
}

@Composable
fun PixoAiProcessingBottomSheetContent(
    checked: Boolean,
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean) -> Unit,
    onCancelClick: () -> Unit,
    onContinueClick: () -> Unit
) {
    val scrollState = rememberScrollState()
    val density = LocalDensity.current
    val windowInfo = LocalWindowInfo.current

    val maxSheetHeight = with(density) {
        windowInfo.containerSize.height.toDp() * 0.9f
    }

    val bottomSheetShape = RoundedCornerShape(
        topStart = dimensionResource(R.dimen._50),
        topEnd = dimensionResource(R.dimen._50),
        bottomStart = dimensionResource(R.dimen._40),
        bottomEnd = dimensionResource(R.dimen._40)
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(max = maxSheetHeight)
            .clip(bottomSheetShape)
            .background(BackgroundPrimary)
    ) {
        PixoAiProcessingTopBorder(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(R.dimen._54))
                .align(Alignment.TopCenter)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(R.dimen._12),
                    start = dimensionResource(R.dimen._16),
                    end = dimensionResource(R.dimen._16),
                    bottom = dimensionResource(R.dimen._32)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .width(dimensionResource(R.dimen._72))
                    .height(dimensionResource(R.dimen._5))
                    .clip(RoundedCornerShape(dimensionResource(R.dimen._100)))
                    .background(AccentWhite)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onCancelClick
                    )
            )

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen._24)))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, fill = false)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PixoAiProcessingNote(
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen._24)))

                PixoDataProcessingConsentSwitch(
                    checked = checked,
                    modifier = Modifier.fillMaxWidth(),
                    onCheckedChange = onCheckedChange
                )

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen._24)))

                PixoAiProcessingBottomActions(
                    continueEnabled = checked,
                    onCancelClick = {
                        Log.d("AiSheetCancel", "Content Cancel clicked")
                        onCancelClick()
                    },
                    onContinueClick = onContinueClick
                )
            }
        }
    }
}

@Composable
private fun PixoAiProcessingTopBorder(
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val outerRadius = 50.dp.toPx()
        val thickness = 2.dp.toPx()

        val endOffset = 3.dp.toPx()
        val cut = 3.dp.toPx()
        val tipInset = 0.35.dp.toPx()

        val outerEndY = outerRadius - endOffset
        val innerEndY = outerEndY - cut

        val leftOuterX = 0f
        val rightOuterX = size.width

        val leftInnerTipX = leftOuterX + tipInset
        val rightInnerTipX = rightOuterX - tipInset

        val path = Path().apply {
            // OUTER LEFT
            moveTo(leftOuterX, outerEndY)

            cubicTo(
                leftOuterX, outerRadius * 0.45f,
                outerRadius * 0.45f, 0f,
                outerRadius, 0f
            )

            // OUTER TOP
            lineTo(size.width - outerRadius, 0f)

            // OUTER RIGHT
            cubicTo(
                size.width - outerRadius * 0.45f, 0f,
                rightOuterX, outerRadius * 0.45f,
                rightOuterX, outerEndY
            )

            // RIGHT SHARP CUT
            lineTo(rightInnerTipX, innerEndY)

            // INNER RIGHT
            cubicTo(
                size.width - thickness, outerRadius * 0.45f + thickness,
                size.width - outerRadius * 0.45f - thickness, thickness,
                size.width - outerRadius - thickness, thickness
            )

            // INNER TOP
            lineTo(outerRadius + thickness, thickness)

            // INNER LEFT
            cubicTo(
                outerRadius * 0.45f + thickness, thickness,
                thickness, outerRadius * 0.45f + thickness,
                leftInnerTipX, innerEndY
            )

            // LEFT SHARP CUT
            lineTo(leftOuterX, outerEndY)

            close()
        }

        drawPath(
            path = path,
            color = AccentWhite
        )
    }
}

@Preview(
    name = "Pixo / AI Processing Bottom Sheet inactive",
    showBackground = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
private fun PixoAiProcessingBottomSheetInactivePreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary),
            contentAlignment = Alignment.BottomCenter
        ) {
            PixoAiProcessingBottomSheetContent(
                checked = false,
                onCheckedChange = {},
                onCancelClick = {},
                onContinueClick = {}
            )
        }
    }
}

@Preview(
    name = "Pixo / AI Processing Bottom Sheet active",
    showBackground = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
private fun PixoAiProcessingBottomSheetActivePreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary),
            contentAlignment = Alignment.BottomCenter
        ) {
            PixoAiProcessingBottomSheetContent(
                checked = true,
                onCheckedChange = {},
                onCancelClick = {},
                onContinueClick = {}
            )
        }
    }
}

