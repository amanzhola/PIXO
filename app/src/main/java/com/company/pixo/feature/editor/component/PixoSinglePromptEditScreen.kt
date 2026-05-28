package com.company.pixo.feature.editor.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.company.pixo.R
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.LabelPrimary
import com.company.pixo.core.ui.PixoButton
import com.company.pixo.core.ui.PixoOptionalDetailsBottomSheetContent
import com.company.pixo.core.ui.PixoReusableTextFieldBlock
import com.company.pixo.core.ui.PixoTopBar
import com.company.pixo.core.ui.PixoTopBarVariant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PixoSinglePromptEditScreen(
    @StringRes titleRes: Int,
    fieldTitle: String,
    fieldHint: String,
    imageUri: String,
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onGenerateClick: () -> Unit,
    @DrawableRes placeholderImageRes: Int,
    sheetTitle: String = fieldTitle,
) {

    var showBottomSheet by remember {
        mutableStateOf(false)
    }

    var pendingValue by remember {
        mutableStateOf(value)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundPrimary)
    ) {

        PixoTopBar(
            variant = PixoTopBarVariant.Detail,
            titleRes = titleRes,
            onBackClick = onBackClick
        )

        AsyncImage(
            model = imageUri.takeIf { it.isNotBlank() },
            contentDescription = null,
            placeholder = if (imageUri.isBlank()) {
                painterResource(placeholderImageRes)
            } else {
                null
            },
            error = painterResource(placeholderImageRes),
            fallback = painterResource(placeholderImageRes),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(
                    top = dimensionResource(R.dimen._8),
                    start = dimensionResource(R.dimen._16),
                    end = dimensionResource(R.dimen._16),
                    bottom = dimensionResource(R.dimen._16)
                ),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            PixoReusableTextFieldBlock(
                title = fieldTitle,
                hint = fieldHint,
                value = value,
                onClick = {
                    pendingValue = value
                    showBottomSheet = true
                }
            )

            Spacer(
                modifier = Modifier.height(
                    dimensionResource(R.dimen._8)
                )
            )

            PixoButton(
                textRes = if (value.isNotEmpty()) {
                    R.string.common_generate_two
                } else {
                    R.string.common_generate
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(bottom = dimensionResource(R.dimen._8)),
                enabled = value.isNotEmpty(),
                trailingIconRes = if (value.isNotEmpty()) {
                    R.drawable.ic_sparkle
                } else {
                    null
                },
                onClick = onGenerateClick
            )
        }
    }

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
                title = fieldTitle,
                hint = fieldHint,
                value = pendingValue,
                onValueChange = {
                    pendingValue = it
                },
                onCloseClick = {
                    showBottomSheet = false
                },
                onConfirmClick = {
                    onValueChange(pendingValue)
                    showBottomSheet = false
                }
            )
        }
    }
}