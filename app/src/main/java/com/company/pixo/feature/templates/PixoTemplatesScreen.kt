package com.company.pixo.feature.templates

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.company.pixo.R
import com.company.pixo.core.navigation.DEBUG_TOKENS_BALANCE
import com.company.pixo.core.theme.AccentWhite
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.PixoTheme
import com.company.pixo.core.ui.PixoBottomNavigation
import com.company.pixo.core.ui.PixoTemplatePreviewCard
import com.company.pixo.core.ui.PixoTopBar
import com.company.pixo.core.ui.PixoTopBarVariant
import com.company.pixo.feature.main.MainTab
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import com.company.pixo.core.theme.LabelPrimary
import com.company.pixo.core.ui.PixoPhotoRequirementsBottomSheet
import com.company.pixo.domain.model.PixoToolConfigs
import com.company.pixo.domain.model.RemoteImageAsset

data class PixoTemplateItem(
    val templateId: String,
    @StringRes val titleRes: Int,
    val image: RemoteImageAsset
)

val pixoTemplateItems: List<PixoTemplateItem> =
    PixoToolConfigs.templateTools.map { config ->
        PixoTemplateItem(
            templateId = config.templateId.orEmpty(),
            titleRes = config.titleRes,
            image = config.previewBefore
        )
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PixoTemplatesScreen(
    modifier: Modifier = Modifier,
    hasActiveSubscription: Boolean = false,
    tokens: String? = null,
    selectedTab: MainTab = MainTab.Templates,
    onGetProClick: () -> Unit = {},
    onTokenBalanceClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onTemplateClick: (Int) -> Unit,
    onTabClick: (MainTab) -> Unit,
) {
    var selectedTemplateIndex by remember {
        mutableStateOf<Int?>(null)
    }

    var showRequirementsSheet by remember {
        mutableStateOf(false)
    }

    val requirementsSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

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
            tokens = tokens.orEmpty(),
            onGetProClick = onGetProClick,
            onTokensClick = onTokenBalanceClick,
            onSettingsClick = onSettingsClick
        )

        LazyVerticalGrid(
            modifier = Modifier
                .weight(1f)
                .padding(top = dimensionResource(R.dimen._8)),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                start = dimensionResource(R.dimen._16),
                end = dimensionResource(R.dimen._16),
                bottom = dimensionResource(R.dimen._16)
            ),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._8)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._16))
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Text(
                    text = stringResource(R.string.templates_title),
                    color = AccentWhite,
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            itemsIndexed(pixoTemplateItems) { index, item ->
                PixoTemplatePreviewCard(
                    titleRes = item.titleRes,
                    image = item.image,
                    onClick = {
                        selectedTemplateIndex = index
                        showRequirementsSheet = true
                    }
                )
            }
        }

        PixoBottomNavigation(
            selectedTab = selectedTab,
            onTabClick = onTabClick
        )
    }

    if (showRequirementsSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showRequirementsSheet = false
            },
            sheetState = requirementsSheetState,
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
                onCloseClick = {
                    showRequirementsSheet = false
                },
                onContinueClick = {
                    showRequirementsSheet = false
                    selectedTemplateIndex?.let { index ->
                        onTemplateClick(index)
                    }
                }
            )
        }
    }
}

@Preview(
    name = "Pixo / Templates Screen",
    showBackground = true,
    widthDp = 390,
    heightDp = 2000
)
@Composable
private fun PixoTemplatesScreenPreview() {
    PixoTheme {
        PixoTemplatesScreen(
            tokens = DEBUG_TOKENS_BALANCE,
//            tokens = "240",
            selectedTab = MainTab.Templates,
            onTemplateClick = {},
            onTabClick = {}
        )
    }
}

@Preview(
    name = "Pixo / Templates Screen Guest",
    showBackground = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
private fun PixoTemplatesScreenGuestPreview() {
    PixoTheme {
        PixoTemplatesScreen(
            selectedTab = MainTab.Templates,
            onTemplateClick = {},
            onTabClick = {}
        )
    }
}
