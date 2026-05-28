package com.company.pixo.feature.editor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.company.pixo.R
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.LabelPrimary
import com.company.pixo.core.ui.PixoOptionalDetailsBottomSheetContent

private enum class HairStyleField {
    Hairstyle,
    Length,
    Color
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PixoHairStyleEditRoute(
    imageUri: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onGenerateClick: (
        hairstyle: String,
        length: String,
        color: String
    ) -> Unit = { _, _, _ -> }
) {
    var hairstyle by remember {
        mutableStateOf("")
    }

    var length by remember {
        mutableStateOf("")
    }

    var color by remember {
        mutableStateOf("")
    }

    var activeField by remember {
        mutableStateOf<HairStyleField?>(null)
    }

    var bottomSheetValue by remember {
        mutableStateOf("")
    }

    val canGenerate =
        hairstyle.isNotBlank() &&
                length.isNotBlank() &&
                color.isNotBlank()

    PixoHairStyleEditScreen(
        imageUri = imageUri,
        hairstyle = hairstyle,
        length = length,
        color = color,
        canGenerate = canGenerate,
        modifier = modifier,
        onBackClick = onBackClick,
        onHairstyleClick = {
            activeField = HairStyleField.Hairstyle
            bottomSheetValue = hairstyle
        },
        onLengthClick = {
            activeField = HairStyleField.Length
            bottomSheetValue = length
        },
        onColorClick = {
            activeField = HairStyleField.Color
            bottomSheetValue = color
        },
        onGenerateClick = {
            onGenerateClick(
                hairstyle,
                length,
                color
            )
        }
    )

    if (activeField != null) {
        ModalBottomSheet(
            onDismissRequest = {
                activeField = null
            },
            containerColor = BackgroundPrimary,
            dragHandle = {
                Box(
                    modifier = Modifier
                        .padding(top = dimensionResource(R.dimen._12))
                        .width(dimensionResource(R.dimen._32))
                        .height(dimensionResource(R.dimen._4))
                        .clip(RoundedCornerShape(dimensionResource(R.dimen._8)))
                        .background(LabelPrimary)
                )
            }
        ) {
            PixoOptionalDetailsBottomSheetContent(
                title = when (activeField) {
                    HairStyleField.Hairstyle -> stringResource(R.string.hair_style_details_hint_1)
                    HairStyleField.Length -> stringResource(R.string.hair_style_details_hint_2)
                    HairStyleField.Color -> stringResource(R.string.hair_style_details_hint_3)
                    null -> ""
                },
                hint = when (activeField) {
                    HairStyleField.Hairstyle -> stringResource(R.string.hair_style_hint)
                    HairStyleField.Length -> stringResource(R.string.hair_style_details_hint_2)
                    HairStyleField.Color -> stringResource(R.string.hair_style_details_hint_3)
                    null -> ""
                },
                value = bottomSheetValue,
                onValueChange = {
                    bottomSheetValue = it
                },
                onCloseClick = {
                    activeField = null
                },
                onConfirmClick = {
                    when (activeField) {
                        HairStyleField.Hairstyle -> hairstyle = bottomSheetValue
                        HairStyleField.Length -> length = bottomSheetValue
                        HairStyleField.Color -> color = bottomSheetValue
                        null -> Unit
                    }

                    activeField = null
                }
            )
        }
    }
}
