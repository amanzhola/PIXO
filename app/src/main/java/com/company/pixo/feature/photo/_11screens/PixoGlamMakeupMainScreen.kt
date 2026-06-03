package com.company.pixo.feature.photo._11screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.company.pixo.R
import com.company.pixo.core.theme.PixoTheme
import com.company.pixo.domain.model.BeforeAfterAsset
import com.company.pixo.domain.model.PixoToolConfigs.TOOL_ASSETS_BASE_URL
import com.company.pixo.domain.model.RemoteImageAsset
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
        asset = BeforeAfterAsset.Separate(
            before = RemoteImageAsset.Remote(
                url = "$TOOL_ASSETS_BASE_URL/tools_glam_makeup1.webp"
            ),
            after = RemoteImageAsset.Remote(
                url = "$TOOL_ASSETS_BASE_URL/tools_glam_makeup2.webp"
            )
        ),
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
