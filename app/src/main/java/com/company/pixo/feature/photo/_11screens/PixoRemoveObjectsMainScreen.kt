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
fun PixoRemoveObjectsMainScreen(
    modifier: Modifier = Modifier,
    variant: ToolPhotoSourceScreenVariant = ToolPhotoSourceScreenVariant.Default,
    onBackClick: () -> Unit,
    onCameraClick: () -> Unit,
    onPhotoLibraryClick: () -> Unit
) {
    ToolPhotoSourceScreen(
        modifier = modifier,
        titleRes = R.string.tool_remove_objects,
        asset = BeforeAfterAsset.Separate(
            before = RemoteImageAsset.Remote(
                url = "$TOOL_ASSETS_BASE_URL/tools_remove_objects1.webp"
            ),
            after = RemoteImageAsset.Remote(
                url = "$TOOL_ASSETS_BASE_URL/tools_remove_objects2.webp"
            )
        ),
        highlightSliderOnCameraDialog = false,
        onBackClick = onBackClick,
        onCameraClick = onCameraClick,
        onPhotoLibraryClick = onPhotoLibraryClick,
        variant = variant,
    )
}

@Preview(
    name = "Pixo / Remove Objects Main Screen",
    showBackground = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
private fun PixoRemoveObjectsMainScreenPreview() {
    PixoTheme {
        PixoRemoveObjectsMainScreen(
            onBackClick = {},
            onCameraClick = {},
            onPhotoLibraryClick = {}
        )
    }
}
