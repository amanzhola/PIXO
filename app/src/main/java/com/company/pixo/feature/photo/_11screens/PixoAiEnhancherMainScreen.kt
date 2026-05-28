package com.company.pixo.feature.photo._11screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.company.pixo.R
import com.company.pixo.core.theme.PixoTheme
import com.company.pixo.feature.photo.ToolPhotoSourceScreen
import com.company.pixo.feature.photo.ToolPhotoSourceScreenVariant

@Composable
fun PixoAiEnhancherMainScreen(
    modifier: Modifier = Modifier,
    variant: ToolPhotoSourceScreenVariant = ToolPhotoSourceScreenVariant.Default,
    onBackClick: () -> Unit,
    onCameraClick: () -> Unit,
    onPhotoLibraryClick: () -> Unit,
) {

    ToolPhotoSourceScreen(
        modifier = modifier,
        titleRes = R.string.ai_enhancer_title,
        beforeImageRes = R.drawable.tools_ai_enhancher1_1,
        afterImageRes = R.drawable.tools_ai_enhancher1_2,
        variant = variant,
        onBackClick = onBackClick,
        onCameraClick = onCameraClick,
        onPhotoLibraryClick = onPhotoLibraryClick,
        highlightSliderOnCameraDialog = false,
    )
}

@Preview(
    name = "Pixo / AI Enhancher Main Screen",
    showBackground = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
private fun PixoAiEnhancherMainScreenPreview() {
    PixoTheme {
        PixoAiEnhancherMainScreen(
            onBackClick = {},
            onCameraClick = {},
            onPhotoLibraryClick = {}
        )
    }
}
