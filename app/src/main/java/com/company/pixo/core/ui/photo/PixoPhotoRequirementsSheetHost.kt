package com.company.pixo.core.ui.photo

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import com.company.pixo.R
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.LabelPrimary
import com.company.pixo.core.ui.PixoPhotoRequirementsBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PixoPhotoRequirementsSheetHost(
    show: Boolean,
    onDismiss: () -> Unit,
    onContinueClick: () -> Unit
) {
    if (show) {
        val sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true
        )

        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = sheetState,
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
            PixoPhotoRequirementsBottomSheet(
                onCloseClick = onDismiss,
                onContinueClick = onContinueClick
            )
        }
    }
}