package com.company.pixo.feature.generation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.company.pixo.R
import com.company.pixo.core.ui.PixoLoader
import com.company.pixo.core.ui.PixoTopBar
import com.company.pixo.core.ui.PixoTopBarVariant
import com.company.pixo.core.ui.PixoButton
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.PixoTheme

@Composable
fun PixoGenerationScreen(
    progress: Float,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
    onCloseClick: () -> Unit,
    onGoToHistoryClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundPrimary)
    ) {

        PixoTopBar(
            variant = PixoTopBarVariant.Generation,
            onCloseClick = onCloseClick
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            if (errorMessage == null) {
                PixoLoader(
                    progress = progress
                )
            } else {
                PixoGenerationErrorContent(
                    message = errorMessage
                )
            }
        }

        PixoButton(
            textRes = R.string.common_go_to_history,
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(
                    start = dimensionResource(R.dimen._16),
                    end = dimensionResource(R.dimen._16),
                    bottom = dimensionResource(R.dimen._8)
                ),
            enabled = true,
            onClick = onGoToHistoryClick
        )
    }
}

@Preview(
    name = "Pixo / Generation Screen",
    showBackground = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
private fun PixoGenerationScreenPreview() {
    PixoTheme {
        PixoGenerationScreen(
            progress = 0.72f,
            onCloseClick = {},
            onGoToHistoryClick = {}
        )
    }
}