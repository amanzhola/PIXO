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
fun PixoHairStudioMainScreen(
    modifier: Modifier = Modifier,
    variant: ToolPhotoSourceScreenVariant = ToolPhotoSourceScreenVariant.Default,
    onBackClick: () -> Unit,
    onCameraClick: () -> Unit,
    onPhotoLibraryClick: () -> Unit
) {
    ToolPhotoSourceScreen(
        modifier = modifier,
        titleRes = R.string.tool_hair_studio,
        beforeImageRes = R.drawable.tools_hair_studio1,
        afterImageRes = R.drawable.tools_hair_studio2,
        highlightSliderOnCameraDialog = false,
        onBackClick = onBackClick,
        onCameraClick = onCameraClick,
        onPhotoLibraryClick = onPhotoLibraryClick,
        variant = variant,
    )
}

@Preview(
    name = "Pixo / Hair Studio Main Screen",
    showBackground = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
private fun PixoHairStudioMainScreenPreview() {
    PixoTheme {
        PixoHairStudioMainScreen(
            onBackClick = {},
            onCameraClick = {},
            onPhotoLibraryClick = {}
        )
    }
}
