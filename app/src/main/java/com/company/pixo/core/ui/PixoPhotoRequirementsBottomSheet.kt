package com.company.pixo.core.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.company.pixo.R
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.LabelPrimary
import com.company.pixo.core.theme.LabelQuaternary
import com.company.pixo.core.theme.PixoTheme
import com.company.pixo.core.theme.SeparatorSecondary

@Composable
fun PixoPhotoRequirementsBottomSheet(
    modifier: Modifier = Modifier,
    onCloseClick: () -> Unit,
    onContinueClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen._16))
            .background(BackgroundPrimary)
            .padding(bottom = dimensionResource(R.dimen._16)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .width(dimensionResource(R.dimen._32))
                .height(dimensionResource(R.dimen._4))
                .background(
                    color = LabelPrimary,
                    shape = RoundedCornerShape(dimensionResource(R.dimen._8))
                )
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen._16)))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(R.dimen._32))
                .padding(horizontal = dimensionResource(R.dimen._16))
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(R.string.photo_requirements_title),
                color = LabelPrimary,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                maxLines = 1
            )

            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(dimensionResource(R.dimen._32))
                    .border(
                        width = 0.33.dp,
                        color = SeparatorSecondary,
                        shape = RoundedCornerShape(dimensionResource(R.dimen._40))
                    )
                    .background(
                        color = BackgroundPrimary,
                        shape = RoundedCornerShape(dimensionResource(R.dimen._40))
                    )
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onCloseClick
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(R.drawable.ic_close),
                    contentDescription = stringResource(R.string.common_close),
                    tint = LabelQuaternary
                )
            }
        }

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen._20)))

        Column(
            modifier = Modifier
                .width(dimensionResource(R.dimen._358)),
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(R.dimen._16)
            )
        ) {
            PixoPhotoRequirementSection(
                titleRes = R.string.photo_good_photos_title
            ) {
                PixoBottomSheetFaceCard(
                    imageRes = R.drawable.bottomsheetimage,
                    textRes = R.string.photo_face_is_visible
                )

                PixoBottomSheetFaceCard(
                    imageRes = R.drawable.bottomsheetimage2,
                    textRes = R.string.photo_face_is_visible
                )

                PixoBottomSheetFaceCard(
                    imageRes = R.drawable.bottomsheetimage3,
                    textRes = R.string.photo_good_lighting
                )
            }

            PixoPhotoRequirementSection(
                titleRes = R.string.photo_bad_photos_title
            ) {
                PixoBottomSheetFaceCard2(
                    imageRes = R.drawable.bottomsheet_bad_image,
                    textRes = R.string.photo_face_is_hidden
                )

                PixoBottomSheetFaceCard2(
                    imageRes = R.drawable.bottomsheet_bad_image2,
                    textRes = R.string.photo_poor_lighting
                )

                PixoBottomSheetFaceCard2(
                    imageRes = R.drawable.bottomsheet_bad_image3,
                    textRes = R.string.photo_bad_angle
                )
            }
        }

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen._20)))

        PixoButton(
            textRes = R.string.common_continue,
            modifier = Modifier
                .width(dimensionResource(R.dimen._358))
                .height(dimensionResource(R.dimen._56)),
            enabled = true,
            onClick = onContinueClick
        )
    }
}

@Composable
private fun PixoPhotoRequirementSection(
    @StringRes titleRes: Int,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen._16)
        )
    ) {
        Text(
            modifier = Modifier.height(dimensionResource(R.dimen._28)),
            text = stringResource(titleRes),
            color = LabelPrimary,
            style = MaterialTheme.typography.headlineMedium,
            maxLines = 1
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                dimensionResource(R.dimen._12)
            ),
            verticalAlignment = Alignment.CenterVertically,
            content = content
        )
    }
}

@Preview(
    name = "Pixo / Photo Requirements Bottom Sheet",
    showBackground = true,
    widthDp = 390,
    heightDp = 540
)
@Composable
private fun PixoPhotoRequirementsBottomSheetPreview() {
    PixoTheme {
        PixoPhotoRequirementsBottomSheet(
            onCloseClick = {},
            onContinueClick = {}
        )
    }
}