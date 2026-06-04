package com.company.pixo.feature.editor.component

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import coil.compose.AsyncImage
import com.company.pixo.R
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.BgSurface400
import com.company.pixo.core.ui.PixoTopBar
import com.company.pixo.core.ui.PixoTopBarVariant

@Composable
fun PixoToolEditorShell(
    imageUri: String,
    @StringRes titleRes: Int,
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
        val shouldShowImage = imageUri.isNotBlank() && !isPreview

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(
                    top = dimensionResource(R.dimen._8),
                    start = dimensionResource(R.dimen._16),
                    end = dimensionResource(R.dimen._16),
                    bottom = dimensionResource(R.dimen._16)
                )
        ) {
            if (shouldShowImage) {
                AsyncImage(
                    model = imageUri,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else {
                PixoImagePlaceholder()
            }
        }

        content()
    }
}

@Composable
fun PixoImagePlaceholder(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BgSurface400)
    )
}
