package com.company.pixo.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.company.pixo.R
import com.company.pixo.core.theme.AccentWhite
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.PixoTheme

@Composable
fun PixoCardSplash(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundPrimary),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_pixo),
            contentDescription = stringResource(id = R.string.cd_pixo_logo),
            colorFilter = ColorFilter.tint(AccentWhite),
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._242_8))
                .height(dimensionResource(id = R.dimen._85_4))
        )
    }
}

@Preview(name = "Pixo / Splash", showBackground = true)
@Composable
private fun PixoCardSplashPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._844))
                .background(BackgroundPrimary)
        ) {
            PixoCardSplash()
        }
    }
}
