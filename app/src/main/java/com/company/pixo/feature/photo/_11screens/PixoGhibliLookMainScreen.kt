package com.company.pixo.feature.photo._11screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.company.pixo.R
import com.company.pixo.core.config.PixoRemoteAssets
import com.company.pixo.core.theme.PixoTheme
import com.company.pixo.domain.model.BeforeAfterAsset
import com.company.pixo.domain.model.RemoteImageAsset
import com.company.pixo.feature.photo.ToolPhotoSourceScreen
import com.company.pixo.feature.photo.ToolPhotoSourceScreenVariant

@Composable
fun PixoGhibliLookMainScreen(
    modifier: Modifier = Modifier,
    variant: ToolPhotoSourceScreenVariant = ToolPhotoSourceScreenVariant.Default,
    onBackClick: () -> Unit,
    onCameraClick: () -> Unit,
    onPhotoLibraryClick: () -> Unit
) {
    ToolPhotoSourceScreen(
        modifier = modifier,
        titleRes = R.string.tool_ghibli_look,
        asset = BeforeAfterAsset.Separate(
            before = RemoteImageAsset.Remote(
                url = "${PixoRemoteAssets.TOOLS}/tools_ghibli_look1.webp"
            ),
            after = RemoteImageAsset.Remote(
                url = "${PixoRemoteAssets.TOOLS}/tools_ghibli_look2.webp"
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
    name = "Pixo / Ghibli Look Main Screen",
    showBackground = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
private fun PixoGhibliLookMainScreenPreview() {
    PixoTheme {
        PixoGhibliLookMainScreen(
            onBackClick = {},
            onCameraClick = {},
            onPhotoLibraryClick = {}
        )
    }
}
