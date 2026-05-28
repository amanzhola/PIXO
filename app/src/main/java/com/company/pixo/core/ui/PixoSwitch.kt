package com.company.pixo.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.company.pixo.R
import com.company.pixo.core.theme.AccentWhite
import com.company.pixo.core.theme.AccentBlack
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.PixoTheme
import com.company.pixo.core.theme.SeparatorSecondary
import com.company.pixo.core.theme.TextIconWeak100
import com.company.pixo.core.theme.TopBarRightCircle
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.clip

@Composable
fun PixoAiEnhancerPromptSwitch(
    checked: Boolean,
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        modifier = modifier
            .width(dimensionResource(R.dimen._390))
            .background(BackgroundPrimary)
            .padding(horizontal = dimensionResource(R.dimen._16))
    ) {
        Row(
            modifier = Modifier
                .width(dimensionResource(R.dimen._358))
                .height(dimensionResource(R.dimen._47))
                .border(
                    width = dimensionResource(R.dimen._1),
                    color = SeparatorSecondary,
                    shape = RoundedCornerShape(dimensionResource(R.dimen._16))
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { onCheckedChange(!checked) }
                )
                .padding(
                    horizontal = dimensionResource(R.dimen._16),
                    vertical = dimensionResource(R.dimen._8)
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.prompt_dialog_title),
                color = TextIconWeak100,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1
            )

            PixoAiEnhancerSwitch(
                checked = checked
            )
        }
    }
}

@Composable
private fun PixoAiEnhancerSwitch(
    checked: Boolean,
    modifier: Modifier = Modifier
) {
    val trackWidth = dimensionResource(R.dimen._51)
    val trackHeight = dimensionResource(R.dimen._31)
    val trackPadding = dimensionResource(R.dimen._2)
    val thumbSize = dimensionResource(R.dimen._27)

    Box(
        modifier = modifier
            .width(trackWidth)
            .height(trackHeight)
            .clip(RoundedCornerShape(100.dp))
            .background(
                color = TopBarRightCircle,
                shape = RoundedCornerShape(100.dp)
            )
            .padding(trackPadding),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .offset(
                    x = if (checked) {
                        trackWidth - thumbSize - trackPadding * 2
                    } else {
                        0.dp
                    }
                )
                .size(thumbSize)
                .clip(RoundedCornerShape(100.dp))
                .shadow(
                    elevation = dimensionResource(R.dimen._3),
                    shape = RoundedCornerShape(100.dp),
                    clip = false
                )
                .background(
                    color = AccentWhite,
                    shape = RoundedCornerShape(100.dp)
                )
        )
    }
}

@Preview(
    name = "Pixo / AI Enhancer Prompt Switch",
    showBackground = true,
    widthDp = 390,
    heightDp = 47
)
@Composable
private fun PixoAiEnhancerPromptSwitchPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary),
            contentAlignment = Alignment.Center
        ) {
            PixoAiEnhancerPromptSwitch(
                checked = false,
                onCheckedChange = {}
            )
        }
    }
}

@Composable
fun PixoDataProcessingConsentSwitch(
    checked: Boolean,
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(BackgroundPrimary)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { onCheckedChange(!checked) }
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(end = dimensionResource(R.dimen._16)),
            text = stringResource(R.string.data_processing_consent),
            color = AccentWhite,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 2
        )

        PixoDataProcessingSwitch(
            checked = checked
        )
    }
}

@Composable
private fun PixoDataProcessingSwitch(
    checked: Boolean,
    modifier: Modifier = Modifier
) {
    val switchWidth = dimensionResource(R.dimen._51)
    val switchHeight = dimensionResource(R.dimen._31)
    val padding = dimensionResource(R.dimen._2)
    val thumbSize = switchHeight - padding * 2

    val offsetX = if (checked) {
        switchWidth - thumbSize - padding * 2
    } else {
        0.dp
    }

    Box(
        modifier = modifier
            .width(switchWidth)
            .height(switchHeight)
            .clip(RoundedCornerShape(percent = 50))
            .background(
                color = AccentWhite.copy(alpha = if (checked) 1f else 0.25f)
            )
            .padding(padding),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .offset(x = offsetX)
                .size(thumbSize)
                .shadow(
                    elevation = dimensionResource(R.dimen._3),
                    shape = CircleShape
                )
                .background(
                    color = AccentBlack,
                    shape = CircleShape
                )
        )
    }
}

@Preview(
    name = "Pixo / Data Processing Consent Switch inactive",
    showBackground = true,
    widthDp = 390,
    heightDp = 80
)
@Composable
private fun PixoDataProcessingConsentSwitchInactivePreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary),
            contentAlignment = Alignment.Center
        ) {
            PixoDataProcessingConsentSwitch(
                checked = false,
                onCheckedChange = {}
            )
        }
    }
}

@Preview(
    name = "Pixo / Data Processing Consent Switch active",
    showBackground = true,
    widthDp = 390,
    heightDp = 80
)
@Composable
private fun PixoDataProcessingConsentSwitchActivePreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary),
            contentAlignment = Alignment.Center
        ) {
            PixoDataProcessingConsentSwitch(
                checked = true,
                onCheckedChange = {}
            )
        }
    }
}