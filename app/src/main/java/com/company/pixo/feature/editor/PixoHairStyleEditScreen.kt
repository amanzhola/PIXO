package com.company.pixo.feature.editor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.company.pixo.R
import com.company.pixo.core.ui.PixoButton
import com.company.pixo.core.ui.PixoHireStyleDetailsStyleTextField
import com.company.pixo.feature.editor.component.PixoToolEditorShell

@Composable
fun PixoHairStyleEditScreen(
    imageUri: String,
    hairstyle: String,
    length: String,
    color: String,
    canGenerate: Boolean,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onHairstyleClick: () -> Unit,
    onLengthClick: () -> Unit,
    onColorClick: () -> Unit,
    onGenerateClick: () -> Unit
) {
    PixoToolEditorShell(
        imageUri = imageUri,
        titleRes = R.string.tool_hair_studio,
        placeholderImageRes = R.drawable.tools_hair_styler_camera_image,
        modifier = modifier,
        onBackClick = onBackClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen._16)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PixoHireStyleDetailsStyleTextField(
                titleRes = if (canGenerate) {
                    R.string.glam_makeup_optional_details_title
                } else {
                    R.string.hair_style_details_title
                },
                firstValue = hairstyle,
                secondValue = length,
                thirdValue = color,
                onFirstClick = onHairstyleClick,
                onSecondClick = onLengthClick,
                onThirdClick = onColorClick
            )

            Spacer(
                modifier = Modifier.height(
                    dimensionResource(R.dimen._8)
                )
            )

            PixoButton(
                textRes = if (canGenerate) {
                    R.string.common_generate_two
                } else {
                    R.string.common_generate
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(bottom = dimensionResource(R.dimen._8)),
                enabled = canGenerate,
                trailingIconRes = if (canGenerate) {
                    R.drawable.ic_sparkle
                } else {
                    null
                },
                onClick = onGenerateClick
            )
        }
    }
}
