package com.company.pixo.feature.editor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.company.pixo.R
import com.company.pixo.core.theme.PixoTheme
import com.company.pixo.core.ui.PixoGlamMakeupScreenContent
import com.company.pixo.core.ui.PixoMakeupStyle
import com.company.pixo.feature.editor.component.PixoToolEditorShell

@Composable
fun PixoGlamMakeupEditScreen(
    imageUri: String,
    selectedStyle: PixoMakeupStyle,
    optionalDetails: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onStyleClick: (PixoMakeupStyle) -> Unit,
    onOptionalDetailsClick: () -> Unit,
    onGenerateClick: () -> Unit
) {
    PixoToolEditorShell(
        imageUri = imageUri,
        titleRes = R.string.tool_glam_makeup,
        placeholderImageRes = R.drawable.tools_glam_makeup_camera_image,
        modifier = modifier,
        onBackClick = onBackClick
    ) {
        PixoGlamMakeupScreenContent(
            selectedStyle = selectedStyle,
            optionalDetails = optionalDetails,
            onStyleClick = onStyleClick,
            onOptionalDetailsClick = onOptionalDetailsClick,
            onGenerateClick = onGenerateClick
        )
    }
}

@Preview(
    name = "Pixo / Glam Makeup Edit Screen empty",
    showBackground = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
private fun PixoGlamMakeupEditScreenEmptyPreview() {
    PixoTheme {
        PixoGlamMakeupEditRoute(
            imageUri = "content://pixo/preview_image.jpg"
        )
    }
}

@Preview(
    name = "Pixo / Glam Makeup Edit Screen filled",
    showBackground = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
private fun PixoGlamMakeupEditScreenFilledPreview() {
    PixoTheme {
        var selectedStyle by remember {
            mutableStateOf(PixoMakeupStyle.NaturalGlow)
        }

        val optionalDetails = "Smooth skin, natural glow, soft lips"

        PixoGlamMakeupEditScreen(
            imageUri = "content://pixo/preview_image.jpg",
            selectedStyle = selectedStyle,
            optionalDetails = optionalDetails,
            onBackClick = {},
            onStyleClick = { style ->
                selectedStyle = style
            },
            onOptionalDetailsClick = {},
            onGenerateClick = {}
        )
    }
}
