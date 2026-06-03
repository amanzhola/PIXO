package com.company.pixo.core.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.company.pixo.R
import com.company.pixo.core.theme.AccentWhite
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.LabelSecondary
import com.company.pixo.core.theme.PixoTheme


@Composable
fun PixoAiProcessingNote(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(BackgroundPrimary)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.ai_processing_title),
            color = AccentWhite,
            style = MaterialTheme.typography.headlineMedium
        )

        Column(
            modifier = Modifier
                .padding(top = dimensionResource(R.dimen._16))
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._12))
        ) {
            PixoAiProcessingConfirmBlock()
            PixoAiProcessingDataBlock()
            PixoAiProcessingPurposeBlock()
        }
    }
}

@Composable
private fun PixoAiProcessingConfirmBlock() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._8))
    ) {
        PixoAiProcessingBodyText(
            textRes = R.string.ai_processing_confirm_title,
            color = AccentWhite
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._4))
        ) {
            PixoAiProcessingBulletText(R.string.ai_processing_own_input)
            PixoAiProcessingBulletText(R.string.ai_processing_permission)
        }
    }
}

@Composable
private fun PixoAiProcessingDataBlock() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._8))
    ) {
        PixoAiProcessingBodyText(
            textRes = R.string.ai_processing_data_title,
            color = AccentWhite
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._4))
        ) {
            PixoAiProcessingBulletText(R.string.ai_processing_data_prompt)
            PixoAiProcessingBulletText(R.string.ai_processing_data_photos)
            PixoAiProcessingBulletText(R.string.ai_processing_data_identifiers)
        }
    }
}

@Composable
private fun PixoAiProcessingPurposeBlock() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._12))
    ) {
        PixoAiProcessingBodyText(
            textRes = R.string.ai_processing_purpose,
            color = AccentWhite
        )

        PixoAiProcessingBodyText(
            textRes = R.string.ai_processing_consent_required,
            color = AccentWhite
        )

        PixoAiProcessingBodyText(
            textRes = R.string.ai_processing_no_third_party,
            color = AccentWhite
        )

        PixoAiProcessingPrivacyText()
    }
}

@Composable
private fun PixoAiProcessingBodyText(
    @StringRes textRes: Int,
    modifier: Modifier = Modifier,
    color: androidx.compose.ui.graphics.Color,
    maxLines: Int = Int.MAX_VALUE
) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = stringResource(textRes),
        color = color,
        style = MaterialTheme.typography.labelMedium,
        maxLines = maxLines
    )
}

@Composable
private fun PixoAiProcessingBulletText(
    @StringRes textRes: Int,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._4)),
        verticalAlignment = Alignment.Top
    ) {
        Image(
            painter = painterResource(R.drawable.ic_dot_paywall),
            contentDescription = null,
            modifier = Modifier.padding(top = dimensionResource(R.dimen._7))
        )

        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(textRes),
            color = LabelSecondary,
            style = MaterialTheme.typography.labelMedium,
            maxLines = maxLines
        )
    }
}

@Composable
private fun PixoAiProcessingPrivacyText() {
    val prefix = stringResource(R.string.ai_processing_privacy_prefix)
    val policy = stringResource(R.string.common_privacy_policy)

    Text(
        modifier = Modifier.fillMaxWidth(),
        text = buildAnnotatedString {
            append(prefix)
            pushStyle(SpanStyle(textDecoration = TextDecoration.Underline))
            append(policy)
            pop()
        },
        color = LabelSecondary,
        style = MaterialTheme.typography.bodySmall
    )
}

@Preview(
    name = "Pixo / AI Processing Note",
    showBackground = true,
    widthDp = 390,
    heightDp = 550
)
@Composable
private fun PixoAiProcessingNotePreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary),
            contentAlignment = Alignment.TopCenter
        ) {
            PixoAiProcessingNote()
        }
    }
}