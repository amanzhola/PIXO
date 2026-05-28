package com.company.pixo.feature.photo._11screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.company.pixo.R
import com.company.pixo.core.theme.PixoTheme
import com.company.pixo.feature.photo.ToolPhotoSourceScreen
import com.company.pixo.feature.photo.ToolPhotoSourceScreenVariant

@Composable
fun PixoUpscaleImageMainScreen(
    modifier: Modifier = Modifier,
    variant: ToolPhotoSourceScreenVariant = ToolPhotoSourceScreenVariant.Default,
    onBackClick: () -> Unit,
    onCameraClick: () -> Unit,
    onPhotoLibraryClick: () -> Unit
) {
   ToolPhotoSourceScreen(
        modifier = modifier,
        titleRes = R.string.tool_upscale_image,
        beforeImageRes = R.drawable.tools_upscale_image1,
        afterImageRes = R.drawable.tools_upscale_image2,
        highlightSliderOnCameraDialog = false,
        onBackClick = onBackClick,
        onCameraClick = onCameraClick,
        onPhotoLibraryClick = onPhotoLibraryClick,
        variant = variant,
    )
}

@Preview(
    name = "Pixo / Upscale Image Main Screen",
    showBackground = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
private fun PixoUpscaleImageMainScreenPreview() {
    PixoTheme {
        PixoUpscaleImageMainScreen(
            onBackClick = {},
            onCameraClick = {},
            onPhotoLibraryClick = {}
        )
    }
}
