package com.company.pixo.feature.templates

import androidx.annotation.DrawableRes
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

data class PixoTemplateItem(
    @StringRes val titleRes: Int,
    @DrawableRes val imageRes: Int
)

val pixoTemplateItems = listOf(
    PixoTemplateItem(R.string.template_gloria_model, R.drawable.tools_gloria_model),
    PixoTemplateItem(R.string.template_cherry, R.drawable.template_cherry),
    PixoTemplateItem(R.string.template_travel_style, R.drawable.template_travel_style),
    PixoTemplateItem(R.string.template_one_love, R.drawable.template_one_love),
    PixoTemplateItem(R.string.template_warm_day, R.drawable.template_warm_day),
    PixoTemplateItem(R.string.template_pink_captivity, R.drawable.template_pink_captivity),
    PixoTemplateItem(R.string.template_80s_gloss, R.drawable.template_e80s_gloss),
    PixoTemplateItem(R.string.template_match_point, R.drawable.template_match_point),
    PixoTemplateItem(R.string.template_japan_breathe, R.drawable.template_japan_breathe),
    PixoTemplateItem(R.string.template_easter_morning, R.drawable.template_easter_morning),
    PixoTemplateItem(R.string.template_sea_breathe, R.drawable.template_sea_breathe),
    PixoTemplateItem(R.string.template_blossom, R.drawable.template_blossom),
    PixoTemplateItem(R.string.template_darning_noir, R.drawable.template_darning_noir),
    PixoTemplateItem(R.string.template_love_in_paris, R.drawable.template_love_in_paris),
    PixoTemplateItem(R.string.template_queen_of_the_day, R.drawable.template_queen_of_the_day),
    PixoTemplateItem(R.string.template_old_money_muse, R.drawable.template_old_money_muse),
    PixoTemplateItem(R.string.template_sport_and_healthy, R.drawable.template_sport_and_healthy),
    PixoTemplateItem(R.string.template_rapunzel_glow, R.drawable.template_rapunzel_glow),
    PixoTemplateItem(R.string.template_safari, R.drawable.template_safary),
    PixoTemplateItem(R.string.template_housewives, R.drawable.template_housewives),
    PixoTemplateItem(R.string.template_morning_routine, R.drawable.template_morning_routine),
    PixoTemplateItem(R.string.template_oscar, R.drawable.template_oscar),
    PixoTemplateItem(R.string.template_retro_style, R.drawable.template_retro_style),
    PixoTemplateItem(R.string.template_metro_style, R.drawable.template_metro_style)
)

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
                    imageRes = item.imageRes,
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
