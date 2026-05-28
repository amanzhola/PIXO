package com.company.pixo.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.company.pixo.R
import com.company.pixo.core.theme.AccentPrimary
import com.company.pixo.core.theme.AccentWhite
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.PixoTheme
import com.company.pixo.core.ui.extensions.noRippleClick

@Composable
fun PixoCameraScreen(
    modifier: Modifier = Modifier,
    onCancelClick: () -> Unit,
    onShutterClick: () -> Unit,
    onFlipClick: () -> Unit,
    onFlashClick: () -> Unit = {},
    onChevronClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundPrimary)
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            PixoTopBar(
                variant = PixoTopBarVariant.Camera,
                onFlashClick = onFlashClick,
                onChevronClick = onChevronClick
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(
                        R.drawable.tools_glam_makeup_camera_image
                    ),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                PixoCameraZoomSelector(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = dimensionResource(R.dimen._24))
                )
            }

            PixoCameraBottomControls(
                onCancelClick = onCancelClick,
                onShutterClick = onShutterClick,
                onFlipClick = onFlipClick
            )
        }
    }
}

@Composable
private fun PixoCameraZoomSelector(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .width(dimensionResource(R.dimen._81))
            .height(dimensionResource(R.dimen._30))
            .clip(RoundedCornerShape(dimensionResource(R.dimen._75)))
            .background(Color(0x26D9D9D9)),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PixoCameraZoomItem(
            text = ".5",
            selected = false
        )

        PixoCameraZoomItem(
            text = "1x",
            selected = true
        )

        PixoCameraZoomItem(
            text = "3",
            selected = false
        )
    }
}

@Composable
private fun PixoCameraZoomItem(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(
                if (selected) {
                    dimensionResource(R.dimen._25)
                } else {
                    dimensionResource(R.dimen._17)
                }
            )
            .clip(RoundedCornerShape(dimensionResource(R.dimen._100)))
            .background(Color(0x59000000)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (selected) AccentPrimary else AccentWhite,
            style = if (selected) {
                MaterialTheme.typography.labelMedium
            } else {
                MaterialTheme.typography.labelSmall
            },
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}

@Composable
private fun PixoCameraBottomControls(
    modifier: Modifier = Modifier,
    onCancelClick: () -> Unit,
    onShutterClick: () -> Unit,
    onFlipClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen._120))
            .background(BackgroundPrimary)
            .navigationBarsPadding()
            .padding(horizontal = dimensionResource(R.dimen._16)),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(R.dimen._72))
        ) {

            Text(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .noRippleClick(onCancelClick),
                text = stringResource(R.string.common_cancel),
                color = AccentWhite,
                style = MaterialTheme.typography.bodyLarge
            )

            Image(
                painter = painterResource(R.drawable.ic_shutter),
                contentDescription = stringResource(
                    R.string.cd_camera_shutter
                ),
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(dimensionResource(R.dimen._72))
                    .noRippleClick(onShutterClick)
            )

            Image(
                painter = painterResource(R.drawable.ic_camera_flip),
                contentDescription = stringResource(
                    R.string.cd_camera_flip
                ),
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(dimensionResource(R.dimen._48))
                    .noRippleClick(onFlipClick)
            )
        }
    }
}

@Preview(
    name = "Pixo / Camera Screen",
    showBackground = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
private fun PixoCameraScreenPreview() {
    PixoTheme {
        PixoCameraScreen(
            onCancelClick = {},
            onShutterClick = {},
            onFlipClick = {}
        )
    }
}