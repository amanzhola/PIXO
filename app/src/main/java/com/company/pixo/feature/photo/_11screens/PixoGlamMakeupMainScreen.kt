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
fun PixoGlamMakeupMainScreen(
    modifier: Modifier = Modifier,
    variant: ToolPhotoSourceScreenVariant = ToolPhotoSourceScreenVariant.Default,

    onBackClick: () -> Unit,
    onCameraClick: () -> Unit,
    onPhotoLibraryClick: () -> Unit
) {
    ToolPhotoSourceScreen(
        modifier = modifier,
        titleRes = R.string.tool_glam_makeup,
        beforeImageRes = R.drawable.tools_glam_makeup_2_1,
        afterImageRes = R.drawable.tools_glam_makeup_2_2,
        highlightSliderOnCameraDialog = true,
        onBackClick = onBackClick,
        onCameraClick = onCameraClick,
        onPhotoLibraryClick = onPhotoLibraryClick,
        variant = variant,
    )
}

@Preview(
    name = "PixoGlamMakeupMainScreen",
    showBackground = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
private fun PixoGlamMakeupMainScreenPreview() {
    PixoTheme {
        PixoGlamMakeupMainScreen(
            onBackClick = {},
            onCameraClick = {},
            onPhotoLibraryClick = {}
        )
    }
}
