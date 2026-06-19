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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.company.pixo.R
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.LabelPrimary
import com.company.pixo.core.ui.PixoOptionalDetailsBottomSheetContent
import com.company.pixo.domain.model.PixoToolConfigs
import com.company.pixo.domain.model.ToolType

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
    val config = remember {
        PixoToolConfigs.findByType(ToolType.HAIR_STUDIO)
    }

    val samples = remember(config) {
        config?.optionConfig?.samples.orEmpty()
    }

    val hairstyleId = samples.firstOrNull { it.id == "hairstyle" }?.id ?: "hairstyle"
    val lengthId = samples.firstOrNull { it.id == "length" }?.id ?: "length"
    val colorId = samples.firstOrNull { it.id == "color" }?.id ?: "color"

    var hairstyle by rememberSaveable { mutableStateOf("") }
    var length by rememberSaveable { mutableStateOf("") }
    var color by rememberSaveable { mutableStateOf("") }

    var activeFieldId by rememberSaveable { mutableStateOf<String?>(null) }
    var bottomSheetValue by rememberSaveable { mutableStateOf("") }
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }

    PixoHairStyleEditScreen(
        imageUri = imageUri,
        hairstyle = hairstyle,
        length = length,
        color = color,
        canGenerate = hairstyle.isNotBlank() ||
                length.isNotBlank() ||
                color.isNotBlank(),
        modifier = modifier,
        onBackClick = onBackClick,
        onHairstyleClick = {
            activeFieldId = hairstyleId
            bottomSheetValue = hairstyle
            showBottomSheet = true
        },
        onLengthClick = {
            activeFieldId = lengthId
            bottomSheetValue = length
            showBottomSheet = true
        },
        onColorClick = {
            activeFieldId = colorId
            bottomSheetValue = color
            showBottomSheet = true
        },
        onGenerateClick = {
            onGenerateClick(
                hairstyle,
                length,
                color
            )
        }
    )

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
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
                title = when (activeFieldId) {
                    hairstyleId -> stringResource(R.string.hair_style_details_hint_1)
                    lengthId -> stringResource(R.string.hair_style_details_hint_2)
                    colorId -> stringResource(R.string.hair_style_details_hint_3)
                    else -> stringResource(R.string.hair_style_details_title)
                },
                hint = when (activeFieldId) {
                    hairstyleId -> samples
                        .firstOrNull { it.id == hairstyleId }
                        ?.example
                        .orEmpty()
                        .ifBlank {
                            stringResource(R.string.hair_style_details_hint_1)
                        }

                    lengthId -> samples
                        .firstOrNull { it.id == lengthId }
                        ?.example
                        .orEmpty()
                        .ifBlank {
                            stringResource(R.string.hair_style_details_hint_2)
                        }

                    colorId -> samples
                        .firstOrNull { it.id == colorId }
                        ?.example
                        .orEmpty()
                        .ifBlank {
                            stringResource(R.string.hair_style_details_hint_3)
                        }

                    else -> ""
                },
                value = bottomSheetValue,
                onValueChange = { value ->
                    bottomSheetValue = value
                },
                onCloseClick = {
                    showBottomSheet = false
                },
                onConfirmClick = {
                    when (activeFieldId) {
                        hairstyleId -> hairstyle = bottomSheetValue
                        lengthId -> length = bottomSheetValue
                        colorId -> color = bottomSheetValue
                    }

                    showBottomSheet = false
                }
            )
        }
    }
}
