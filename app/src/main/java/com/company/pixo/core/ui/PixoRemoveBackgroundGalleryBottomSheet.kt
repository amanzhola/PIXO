package com.company.pixo.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import com.company.pixo.core.theme.AccentWhite
import com.company.pixo.core.theme.GallerySheetBackground
import com.company.pixo.core.theme.GallerySheetSearchIcon
import com.company.pixo.core.theme.GallerySheetSegmentBackground
import com.company.pixo.core.theme.GallerySheetSeparator
import com.company.pixo.core.theme.GallerySheetSystemBlue
import com.company.pixo.core.theme.GallerySheetTextPrimary
import com.company.pixo.core.theme.PixoTheme

@Composable
fun PixoRemoveBackgroundGalleryBottomSheet(
    modifier: Modifier = Modifier,
    onCancelClick: () -> Unit,
    onAddClick: () -> Unit,
    onShowSelectedClick: () -> Unit
) {
    Column(
        modifier = modifier
            .width(dimensionResource(R.dimen._391))
            .background(GallerySheetBackground)
    ) {
        PixoGalleryTopFrame(
            onCancelClick = onCancelClick,
            onAddClick = onAddClick
        )

        PixoGallerySearchField()

        LazyVerticalGrid(
            modifier = Modifier
                .width(dimensionResource(R.dimen._391))
                .weight(1f),
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(1.5.dp),
            verticalArrangement = Arrangement.spacedBy(1.5.dp),
            userScrollEnabled = true
        ) {
            itemsIndexed(galleryImages) { index, imageRes ->
                PixoBottomSheetImageCard(
                    modifier = Modifier.size(129.dp),
                    imageRes = imageRes,
                    isSelected = index == 3
                )
            }
        }

        PixoGalleryBottomBar(
            onShowSelectedClick = onShowSelectedClick
        )
    }
}

private val galleryImages = listOf(
    R.drawable.bottomsheet_albums1,
    R.drawable.bottomsheet_albums2,
    R.drawable.bottomsheet_albums3,
    R.drawable.tools_glam,
    R.drawable.bottomsheet_albums5,
    R.drawable.bottomsheet_albums6,
    R.drawable.bottomsheet_albums7,
    R.drawable.bottomsheet_albums8,
    R.drawable.bottomsheet_albums9,
    R.drawable.bottomsheet_albums10,
    R.drawable.bottomsheet_albums11,
    R.drawable.bottomsheet_albums12,
    R.drawable.bottomsheet_albums13,
    R.drawable.bottomsheet_albums14,
    R.drawable.bottomsheet_albums15
)

@Composable
private fun PixoGalleryTopFrame(
    onCancelClick: () -> Unit,
    onAddClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(dimensionResource(R.dimen._391))
            .height(dimensionResource(R.dimen._133)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.gallery_select_limit),
                color = GallerySheetTextPrimary,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }

        Box(
            modifier = Modifier
                .width(dimensionResource(R.dimen._391))
                .height(dimensionResource(R.dimen._97))
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = dimensionResource(R.dimen._16))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onCancelClick
                    ),
                text = stringResource(R.string.common_cancel),
                color = GallerySheetSystemBlue,
                style = MaterialTheme.typography.bodyLarge
            )

            PixoGallerySegmentedControl(
                modifier = Modifier.align(Alignment.TopCenter)
            )

            Text(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = dimensionResource(R.dimen._16))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onAddClick
                    ),
                text = stringResource(R.string.common_add),
                color = GallerySheetSystemBlue,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
private fun PixoGallerySegmentedControl(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .width(131.dp)
            .height(32.dp)
            .background(
                color = GallerySheetSegmentBackground,
                shape = RoundedCornerShape(dimensionResource(R.dimen._8))
            )
            .padding(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .width(63.5.dp)
                .height(28.dp)
                .background(
                    color = AccentWhite,
                    shape = RoundedCornerShape(7.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.gallery_photos),
                color = GallerySheetTextPrimary,
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }

        Box(
            modifier = Modifier
                .width(63.5.dp)
                .height(28.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.gallery_albums),
                color = GallerySheetTextPrimary,
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
    }
}

@Composable
private fun PixoGallerySearchField() {
    Box(
        modifier = Modifier
            .width(dimensionResource(R.dimen._391))
            .height(dimensionResource(R.dimen._51))
            .padding(
                start = dimensionResource(R.dimen._16),
                end = dimensionResource(R.dimen._16),
                bottom = dimensionResource(R.dimen._15)
            )
    ) {
        Row(
            modifier = Modifier
                .width(dimensionResource(R.dimen._359))
                .height(dimensionResource(R.dimen._36))
                .background(
                    color = GallerySheetSegmentBackground,
                    shape = RoundedCornerShape(dimensionResource(R.dimen._10))
                )
                .padding(
                    horizontal = dimensionResource(R.dimen._8),
                    vertical = dimensionResource(R.dimen._7)
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .width(15.63.dp)
                    .height(15.78.dp),
                painter = painterResource(R.drawable.ic_search),
                contentDescription = null,
                tint = GallerySheetSearchIcon
            )

            Text(
                modifier = Modifier
                    .padding(start = 6.dp)
                    .weight(1f),
                text = stringResource(R.string.gallery_search_hint),
                color = GallerySheetSearchIcon,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1
            )

            Icon(
                modifier = Modifier
                    .width(11.88.dp)
                    .height(17.68.dp),
                painter = painterResource(R.drawable.ic_microphone),
                contentDescription = null,
                tint = GallerySheetSearchIcon
            )
        }
    }
}

@Composable
private fun PixoGalleryBottomBar(
    onShowSelectedClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(393.dp)
            .height(83.dp)
            .background(GallerySheetBackground)
            .border(
                width = 0.5.dp,
                color = GallerySheetSeparator
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onShowSelectedClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.gallery_show_selected),
            color = GallerySheetSystemBlue,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}

@Preview(
    name = "Pixo / Remove Background Gallery Bottom Sheet",
    showBackground = true,
    widthDp = 393,
    heightDp = 844
)
@Composable
private fun PixoRemoveBackgroundGalleryBottomSheetPreview() {
    PixoTheme {
        PixoRemoveBackgroundGalleryBottomSheet(
            onCancelClick = {},
            onAddClick = {},
            onShowSelectedClick = {}
        )
    }
}