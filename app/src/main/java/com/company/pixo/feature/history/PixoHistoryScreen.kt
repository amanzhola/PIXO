package com.company.pixo.feature.history

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.company.pixo.R
import com.company.pixo.core.theme.AccentWhite
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.LabelPrimary
import com.company.pixo.core.theme.LabelQuaternary
import com.company.pixo.core.theme.PixoTheme
import com.company.pixo.core.ui.PixoBottomNavigation
import com.company.pixo.core.ui.PixoCard
import com.company.pixo.core.ui.PixoHistoryCard
import com.company.pixo.core.ui.PixoHistoryCardState
import com.company.pixo.core.ui.PixoTopBar
import com.company.pixo.core.ui.PixoTopBarVariant
import com.company.pixo.domain.model.ToolType
import com.company.pixo.feature.main.MainTab
import com.company.pixo.feature.result.PixoResultPlaceholder

sealed interface PixoHistoryItem {
    data object Empty : PixoHistoryItem

    data class Loading(
        val id: String,
        val toolType: ToolType?
    ) : PixoHistoryItem

    data class Error(
        val id: String,
        val toolType: ToolType?
    ) : PixoHistoryItem

    data class Image(
        val id: String,
        @StringRes val titleRes: Int,
        val imageUrl: String,
        val toolType: ToolType?
    ) : PixoHistoryItem
}

@Composable
fun PixoHistoryScreen(
    modifier: Modifier = Modifier,
    hasActiveSubscription: Boolean = false,
    tokens: String = "1000",
    items: List<PixoHistoryItem>,
    showEmptyMessage: Boolean,
    onGetProClick: () -> Unit,
    onTokenBalanceClick: () -> Unit = {},
    onSettingsClick: () -> Unit,
    onImageClick: (String, ToolType?) -> Unit = { _, _ -> },
    onTabClick: (MainTab) -> Unit,
    onLoadingClick: (String, ToolType?) -> Unit = { _, _ -> },
    onErrorRetryClick: (String, ToolType?) -> Unit = { _, _ -> },
    onErrorDeleteClick: (String) -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundPrimary)
    ) {
        PixoTopBar(
            variant = if (hasActiveSubscription) {
                PixoTopBarVariant.MainSubscribed
            } else {
                PixoTopBarVariant.MainGuestNoLogo
            },
            tokens = tokens,
            onGetProClick = onGetProClick,
            onTokensClick = onTokenBalanceClick,
            onSettingsClick = onSettingsClick
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(
                    start = dimensionResource(R.dimen._16),
                    top = dimensionResource(R.dimen._8),
                    end = dimensionResource(R.dimen._16),
                    bottom = dimensionResource(R.dimen._16)
                ),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._8)),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._8))
            ) {
                items(items) { item ->
                    PixoHistoryGridItem(
                        item = item,
                        onImageClick = onImageClick,
                        onLoadingClick = onLoadingClick,
                        onErrorRetryClick = onErrorRetryClick,
                        onErrorDeleteClick = onErrorDeleteClick
                    )
                }
            }

            if (showEmptyMessage) {
                BoxWithConstraints(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = maxHeight * 0.33f)
                            .widthIn(max = dimensionResource(R.dimen._242)),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.history_empty_title),
                            color = LabelPrimary,
                            style = MaterialTheme.typography.headlineMedium,
                            textAlign = TextAlign.Center,
                            maxLines = 1
                        )

                        Text(
                            text = stringResource(R.string.history_empty_subtitle),
                            color = LabelQuaternary,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center,
                            maxLines = 1
                        )
                    }
                }
            }
        }

        PixoBottomNavigation(
            selectedTab = MainTab.History,
            onTabClick = onTabClick
        )
    }
}

@Composable
private fun PixoHistoryGridItem(
    item: PixoHistoryItem,
    modifier: Modifier = Modifier,
    onImageClick: (String, ToolType?) -> Unit = { _, _ -> },
    onLoadingClick: (String, ToolType?) -> Unit = { _, _ -> },
    onErrorRetryClick: (String, ToolType?) -> Unit = { _, _ -> },
    onErrorDeleteClick: (String) -> Unit = {}
) {
    when (item) {
        PixoHistoryItem.Empty -> {
            PixoHistoryCard(
                modifier = modifier.fillMaxWidth(),
                state = PixoHistoryCardState.Empty
            )
        }

        is PixoHistoryItem.Loading -> {
            PixoHistoryCard(
                modifier = modifier
                    .fillMaxWidth()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {
                            onLoadingClick(
                                item.id,
                                item.toolType
                            )
                        }
                    ),
                state = PixoHistoryCardState.Loading
            )
        }

        is PixoHistoryItem.Error -> {
            PixoHistoryCard(
                modifier = modifier.fillMaxWidth(),
                state = PixoHistoryCardState.Error,
                onCloseClick = {
                    onErrorDeleteClick(item.id)
                },
                onTryAgainClick = {
                    onErrorRetryClick(item.id, item.toolType)
                }
            )
        }

        is PixoHistoryItem.Image -> {
            PixoHistoryImageCard(
                titleRes = item.titleRes,
                imageUrl = item.imageUrl,
                modifier = modifier.fillMaxWidth(),
                onClick = {
                    Log.d(
                        "HistoryDelete",
                        "History card click id=${item.id}, toolType=${item.toolType}"
                    )
                    onImageClick(item.id, item.toolType)
                }
            )
        }
    }
}

@Composable
private fun PixoHistoryImageCard(
    @StringRes titleRes: Int,
    imageUrl: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    PixoCard(
        modifier = modifier.aspectRatio(175f / 311f),
        shape = RoundedCornerShape(dimensionResource(R.dimen._16)),
        onClick = onClick
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (imageUrl.isBlank()) {
                PixoResultPlaceholder(url = imageUrl)
            } else {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colorStops = arrayOf(
                                0f to Color.Transparent,
                                1f to BackgroundPrimary.copy(alpha = 0.616f)
                            )
                        )
                    )
            )

            Text(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(
                        start = dimensionResource(R.dimen._16),
                        bottom = dimensionResource(R.dimen._12),
                        end = dimensionResource(R.dimen._16)
                    ),
                text = stringResource(titleRes),
                color = AccentWhite,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(
    name = "Pixo / History Empty Screen",
    showBackground = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
private fun PixoHistoryEmptyScreenPreview() {
    PixoTheme {
        PixoHistoryScreen(
            items = listOf(
                PixoHistoryItem.Empty,
                PixoHistoryItem.Empty,
                PixoHistoryItem.Empty,
                PixoHistoryItem.Empty
            ),
            showEmptyMessage = true,
            onGetProClick = {},
            onSettingsClick = {},
            onTabClick = {}
        )
    }
}
