package com.company.pixo.feature.editor.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.company.pixo.R
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.ui.PixoTopBar
import com.company.pixo.core.ui.PixoTopBarVariant

@Composable
fun PixoToolEditorShell(
    imageUri: String,
    @StringRes titleRes: Int,
    @DrawableRes placeholderImageRes: Int,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundPrimary)
    ) {
        PixoTopBar(
            variant = PixoTopBarVariant.Detail,
            titleRes = titleRes,
            onBackClick = onBackClick
        )
        val isPreview = LocalInspectionMode.current

        AsyncImage(
            model = imageUri.takeIf {
                it.isNotBlank() && !isPreview
            },
            contentDescription = null,
            placeholder = painterResource(placeholderImageRes),
            error = painterResource(placeholderImageRes),
            fallback = painterResource(placeholderImageRes),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(
                    top = dimensionResource(R.dimen._8),
                    start = dimensionResource(R.dimen._16),
                    end = dimensionResource(R.dimen._16),
                    bottom = dimensionResource(R.dimen._16)
                ),
            contentScale = ContentScale.Crop
        )

        content()
    }
}