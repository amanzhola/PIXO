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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.company.pixo.R
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.LabelPrimary
import com.company.pixo.core.ui.PixoOptionalDetailsBottomSheetContent
import com.company.pixo.domain.model.PixoToolConfigs
import com.company.pixo.domain.model.ToolOptionSample
import com.company.pixo.domain.model.ToolType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PixoGlamMakeupEditRoute(
    imageUri: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onGenerateClick: (
        selectedStyle: ToolOptionSample,
        optionalDetails: String
    ) -> Unit = { _, _ -> }
) {
    val config = remember { PixoToolConfigs.findByType(ToolType.GLAM_MAKEUP) }
    val styles = remember(config) { config?.optionConfig?.samples.orEmpty() }

    if (styles.isEmpty()) {
        return
    }

    var selectedStyleId by rememberSaveable { mutableStateOf(styles.first().id) }
    val selectedStyle = styles.firstOrNull { style ->
        style.id == selectedStyleId
    } ?: styles.first()

    var optionalDetails by rememberSaveable { mutableStateOf("") }
    var bottomSheetValue by rememberSaveable { mutableStateOf("") }
    var showOptionalDetailsSheet by rememberSaveable { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState( skipPartiallyExpanded = false)

    PixoGlamMakeupEditScreen(
        imageUri = imageUri,
        selectedStyle = selectedStyle,
        styles = styles,
        optionalDetails = optionalDetails,
        modifier = modifier,
        onBackClick = onBackClick,
        onStyleClick = { style ->
            selectedStyleId = style.id
        },
        onOptionalDetailsClick = {
            bottomSheetValue = optionalDetails
            showOptionalDetailsSheet = true
        },
        onGenerateClick = {
            onGenerateClick(selectedStyle, optionalDetails)
        }
    )

    if (showOptionalDetailsSheet) {
        ModalBottomSheet(
            onDismissRequest = { showOptionalDetailsSheet = false },
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
                onValueChange = { text -> bottomSheetValue = text },
                onCloseClick = { showOptionalDetailsSheet = false },
                onConfirmClick = {
                    optionalDetails = bottomSheetValue
                    showOptionalDetailsSheet = false
                }
            )
        }
    }
}