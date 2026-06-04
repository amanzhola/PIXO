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
fun PixoSmileEditMainScreen(
    modifier: Modifier = Modifier,
    variant: ToolPhotoSourceScreenVariant = ToolPhotoSourceScreenVariant.Default,
    onBackClick: () -> Unit,
    onCameraClick: () -> Unit,
    onPhotoLibraryClick: () -> Unit
) {
   ToolPhotoSourceScreen(
        modifier = modifier,
        titleRes = R.string.tool_smile_edit,
       asset = BeforeAfterAsset.Separate(
           before = RemoteImageAsset.Remote(
               url = "${PixoRemoteAssets.SMILE_EDIT}/tools_smile_edit1.webp"
           ),
           after = RemoteImageAsset.Remote(
               url = "${PixoRemoteAssets.SMILE_EDIT}/tools_smile_edit2.webp"
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
    name = "Pixo / Smile Edit Main Screen",
    showBackground = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
private fun PixoSmileEditMainScreenPreview() {
    PixoTheme {
        PixoSmileEditMainScreen(
            onBackClick = {},
            onCameraClick = {},
            onPhotoLibraryClick = {}
        )
    }
}
