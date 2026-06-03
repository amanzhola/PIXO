package com.company.pixo.core.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.company.pixo.R
import com.company.pixo.core.theme.AccentGreen
import com.company.pixo.core.theme.AccentRed
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.LabelPrimary
import com.company.pixo.core.theme.PixoTheme
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.remember
import com.company.pixo.domain.model.RemoteImageAsset

@Composable
fun PixoBottomSheetFaceCard(
    image: RemoteImageAsset,
    @StringRes textRes: Int,
    modifier: Modifier = Modifier,
    isSelected: Boolean = true
) {
    Column(
        modifier = modifier
            .width(dimensionResource(R.dimen._110))
            .height(dimensionResource(R.dimen._144))
            .background(BackgroundPrimary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen._16)
        )
    ) {
        Box(
            modifier = Modifier
                .width(dimensionResource(R.dimen._110))
                .height(dimensionResource(R.dimen._110)),
            contentAlignment = Alignment.BottomCenter
        ) {
            when (image) {
                is RemoteImageAsset.Local -> {
                    Image(
                        painter = painterResource(image.res),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(dimensionResource(R.dimen._110))
                            .height(dimensionResource(R.dimen._110))
                            .clip(
                                bottomSheetFaceImageShape(
                                    radius = dimensionResource(R.dimen._16).value,
                                    cutoutRadius = dimensionResource(R.dimen._24).value
                                )
                            )
                    )
                }

                is RemoteImageAsset.Remote -> {
                    coil.compose.AsyncImage(
                        model = image.url,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(dimensionResource(R.dimen._110))
                            .height(dimensionResource(R.dimen._110))
                            .clip(
                                bottomSheetFaceImageShape(
                                    radius = dimensionResource(R.dimen._16).value,
                                    cutoutRadius = dimensionResource(R.dimen._24).value
                                )
                            )
                    )
                }
            }

            if (isSelected) {
                Box(
                    modifier = Modifier
                        .size(dimensionResource(R.dimen._32))
                        .offset(y = dimensionResource(R.dimen._16))
                        .clip(RoundedCornerShape(dimensionResource(R.dimen._100)))
                        .background(BackgroundPrimary),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_check),
                        contentDescription = null,
                        tint = AccentGreen,
                        modifier = Modifier.size(
                            dimensionResource(R.dimen._17)
                        )
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .width(dimensionResource(R.dimen._85))
                .height(dimensionResource(R.dimen._18)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(textRes),
                color = LabelPrimary,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1
            )
        }
    }
}

private fun bottomSheetFaceImageShape(
    radius: Float,
    cutoutRadius: Float
) = GenericShape { size: Size, _ ->
    val path = Path().apply {
        fillType = PathFillType.EvenOdd

        addRoundRect(
            androidx.compose.ui.geometry.RoundRect(
                rect = Rect(
                    left = 0f,
                    top = 0f,
                    right = size.width,
                    bottom = size.height
                ),
                radiusX = radius,
                radiusY = radius
            )
        )

        addOval(
            Rect(
                left = size.width / 2f - cutoutRadius,
                top = size.height - cutoutRadius,
                right = size.width / 2f + cutoutRadius,
                bottom = size.height + cutoutRadius
            )
        )
    }

    addPath(path)
}

@Composable
fun PixoBottomSheetFaceCard2(
    image: RemoteImageAsset,
    @StringRes textRes: Int,
    modifier: Modifier = Modifier,
    isSelected: Boolean = true
) {
    Column(
        modifier = modifier
            .width(dimensionResource(R.dimen._110))
            .height(dimensionResource(R.dimen._144))
            .background(BackgroundPrimary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen._16)
        )
    ) {
        Box(
            modifier = Modifier
                .width(dimensionResource(R.dimen._110))
                .height(dimensionResource(R.dimen._110)),
            contentAlignment = Alignment.BottomCenter
        ) {
            when (image) {
                is RemoteImageAsset.Local -> {
                    Image(
                        painter = painterResource(image.res),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(dimensionResource(R.dimen._110))
                            .height(dimensionResource(R.dimen._110))
                            .clip(
                                bottomSheetFaceImageShape(
                                    radius = dimensionResource(R.dimen._16).value,
                                    cutoutRadius = dimensionResource(R.dimen._24).value
                                )
                            )
                    )
                }

                is RemoteImageAsset.Remote -> {
                    coil.compose.AsyncImage(
                        model = image.url,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(dimensionResource(R.dimen._110))
                            .height(dimensionResource(R.dimen._110))
                            .clip(
                                bottomSheetFaceImageShape(
                                    radius = dimensionResource(R.dimen._16).value,
                                    cutoutRadius = dimensionResource(R.dimen._24).value
                                )
                            )
                    )
                }
            }

            if (isSelected) {
                Box(
                    modifier = Modifier
                        .size(dimensionResource(R.dimen._32))
                        .offset(y = dimensionResource(R.dimen._16))
                        .clip(RoundedCornerShape(dimensionResource(R.dimen._100)))
                        .background(BackgroundPrimary),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_badcheck),
                        contentDescription = null,
                        tint = AccentRed,
                        modifier = Modifier.size(
                            dimensionResource(R.dimen._17)
                        )
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .width(dimensionResource(R.dimen._85))
                .height(dimensionResource(R.dimen._18)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(textRes),
                color = LabelPrimary,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1
            )
        }
    }
}

@Composable
fun PixoBottomSheetImageCard(
    image: RemoteImageAsset,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    Box(
        modifier = modifier
            .width(dimensionResource(R.dimen._129))
            .height(dimensionResource(R.dimen._129))
            .then(
                if (onClick != null) {
                    Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onClick
                    )
                } else {
                    Modifier
                }
            )
    ) {
        when (image) {
            is RemoteImageAsset.Local -> {
                Image(
                    painter = painterResource(image.res),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )
            }

            is RemoteImageAsset.Remote -> {
                coil.compose.AsyncImage(
                    model = image.url,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )
            }
        }

        if (isSelected) {
            Image(
                painter = painterResource(R.drawable.ic_bottomsheet_selected),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(dimensionResource(R.dimen._6))
            )
        }
    }
}