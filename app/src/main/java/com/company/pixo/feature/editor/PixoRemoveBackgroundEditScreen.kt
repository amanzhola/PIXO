package com.company.pixo.feature.editor

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.company.pixo.R
import com.company.pixo.core.ui.PixoRemoveBackgroundScreenContent
import com.company.pixo.core.ui.PixoRemoveBackgroundType
import com.company.pixo.feature.editor.component.PixoToolEditorShell

@Composable
fun PixoRemoveBackgroundEditScreen(
    imageUri: String,
    selectedType: PixoRemoveBackgroundType?,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onTypeClick: (PixoRemoveBackgroundType) -> Unit,
    onGenerateClick: () -> Unit
) {
    PixoToolEditorShell(
        imageUri = imageUri,
        titleRes = R.string.tool_remove_background,
        placeholderImageRes = R.drawable.tools_remove_background_camera_image,
        modifier = modifier,
        onBackClick = onBackClick
    ) {
        PixoRemoveBackgroundScreenContent(
            selectedType = selectedType,
            onTypeClick = onTypeClick,
            onGenerateClick = onGenerateClick
        )

        Spacer(
            modifier = Modifier.height(
                dimensionResource(R.dimen._8)
            )
        )
    }
}
