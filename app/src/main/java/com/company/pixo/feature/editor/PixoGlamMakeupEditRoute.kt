package com.company.pixo.feature.editor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.company.pixo.R
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.LabelPrimary
import com.company.pixo.core.ui.PixoMakeupStyle
import com.company.pixo.core.ui.PixoOptionalDetailsBottomSheetContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PixoGlamMakeupEditRoute(
    imageUri: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onGenerateClick: (
        selectedStyle: PixoMakeupStyle,
        optionalDetails: String
    ) -> Unit = { _, _ -> }
) {
    var selectedStyle by remember {
        mutableStateOf(PixoMakeupStyle.NaturalGlow)
    }

    var optionalDetails by remember {
        mutableStateOf("")
    }

    var bottomSheetValue by remember {
        mutableStateOf(optionalDetails)
    }

    var showOptionalDetailsSheet by remember {
        mutableStateOf(false)
    }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )

    PixoGlamMakeupEditScreen(
        imageUri = imageUri,
        selectedStyle = selectedStyle,
        optionalDetails = optionalDetails,
        modifier = modifier,
        onBackClick = onBackClick,
        onStyleClick = { style ->
            selectedStyle = style
        },
        onOptionalDetailsClick = {
            bottomSheetValue = optionalDetails
            showOptionalDetailsSheet = true
        },
        onGenerateClick = {
            onGenerateClick(
                selectedStyle,
                optionalDetails
            )
        }
    )

    if (showOptionalDetailsSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showOptionalDetailsSheet = false
            },
            sheetState = sheetState,
            containerColor = BackgroundPrimary,
            dragHandle = {
                Box(
                    modifier = Modifier
                        .padding(top = dimensionResource(R.dimen._12))
                        .width(dimensionResource(R.dimen._32))
                        .height(dimensionResource(R.dimen._4))
                        .background(
                            color = LabelPrimary,
                            shape = RoundedCornerShape(dimensionResource(R.dimen._8))
                        )
                )
            }
        ) {
            PixoOptionalDetailsBottomSheetContent(
                title = stringResource(id = R.string.glam_makeup_optional_details_title),
                hint = stringResource(id = R.string.glam_makeup_style_hint),
                value = bottomSheetValue,
                onValueChange = { text ->
                    bottomSheetValue = text
                },
                onCloseClick = {
                    showOptionalDetailsSheet = false
                },
                onConfirmClick = {
                    optionalDetails = bottomSheetValue
                    showOptionalDetailsSheet = false
                }
            )
        }
    }
}
