package com.company.pixo.feature.editor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.company.pixo.R
import com.company.pixo.core.ui.PixoButton
import com.company.pixo.core.ui.PixoPromptTextField
import com.company.pixo.feature.editor.component.PixoToolEditorShell

@Composable
fun PixoGhibliEditScreen(
    imageUri: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onGenerateClick: () -> Unit
) {
    PixoToolEditorShell(
        imageUri = imageUri,
        titleRes = R.string.ghibli_style,
        placeholderImageRes = R.drawable.tools_ghibli_style_camera_image,
        modifier = modifier,
        onBackClick = onBackClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen._16)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PixoPromptTextField(
                value = stringResource(R.string.ghibli_prompt)
            )

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen._16)))

            PixoButton(
                textRes = R.string.common_generate_two,
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(bottom = dimensionResource(R.dimen._8)),
                enabled = true,
                trailingIconRes = R.drawable.ic_sparkle,
                onClick = onGenerateClick
            )
        }
    }
}
