package com.company.pixo.core.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.company.pixo.R
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.PixoTheme
import com.company.pixo.core.theme.ToastBackground
import com.company.pixo.core.theme.ToastBorder

@Composable
fun PixoToast(
    @DrawableRes iconRes: Int,
    @StringRes textRes: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .width(dimensionResource(R.dimen._165))
            .height(dimensionResource(R.dimen._40))
            .background(
                color = ToastBackground,
                shape = RoundedCornerShape(dimensionResource(R.dimen._8))
            )
            .border(
                width = dimensionResource(R.dimen._1),
                color = ToastBorder,
                shape = RoundedCornerShape(dimensionResource(R.dimen._8))
            )
            .padding(dimensionResource(R.dimen._8)),
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen._4)
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .width(dimensionResource(R.dimen._24))
                .height(dimensionResource(R.dimen._24)),
            painter = painterResource(iconRes),
            contentDescription = null,
            tint = Color.Unspecified
        )

        Text(
            text = stringResource(textRes),
            color = Color(0xFF14131B),
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1
        )
    }
}

@Preview(
    name = "Pixo / Toast Saved",
    showBackground = true,
    widthDp = 390,
    heightDp = 200
)
@Composable
private fun PixoToastSavedPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary)
                .padding(top = dimensionResource(R.dimen._121)),
            contentAlignment = Alignment.TopCenter
        ) {
            PixoToast(
                iconRes = R.drawable.ic_check_rate,
                textRes = R.string.toast_saved_successfully
            )
        }
    }
}

@Preview(
    name = "Pixo / Toast Error",
    showBackground = true,
    widthDp = 390,
    heightDp = 200
)
@Composable
private fun PixoToastErrorPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary)
                .padding(top = dimensionResource(R.dimen._121)),
            contentAlignment = Alignment.TopCenter
        ) {
            PixoToast(
                iconRes = R.drawable.ic_error,
                textRes = R.string.toast_couldnt_save
            )
        }
    }
}