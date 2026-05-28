package com.company.pixo.feature.tools

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.company.pixo.R
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.LabelPrimary
import com.company.pixo.core.theme.PixoTheme
import com.company.pixo.core.ui.PixoBeforeAfterPreview
import com.company.pixo.core.ui.PixoBottomNavigation
import com.company.pixo.core.ui.PixoImageLabCard
import com.company.pixo.core.ui.PixoTopBar
import com.company.pixo.core.ui.PixoTopBarVariant
import com.company.pixo.core.ui.extensions.noRippleClick
import com.company.pixo.feature.main.MainTab
import com.company.pixo.feature.photo.ToolPhotoSourceScreenVariant

data class PixoToolItem(
    @StringRes val titleRes: Int,
    @DrawableRes val imageResList: List<Int>
)

@Composable
fun PixoToolsScreen(
    modifier: Modifier = Modifier,
    tokens: String = "1000",
    hasActiveSubscription: Boolean = false,
    onGetProClick: () -> Unit = {},
    onTokenBalanceClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onImageLabClick: () -> Unit = {},
    onToolClick: (PixoToolItem, ToolPhotoSourceScreenVariant) -> Unit = { _, _ -> },
    toolSourceVariant: ToolPhotoSourceScreenVariant = ToolPhotoSourceScreenVariant.Default,
    onToolSourceVariantChange: (ToolPhotoSourceScreenVariant) -> Unit = {},
    onTabClick: (MainTab) -> Unit = {}
) {
    val tools = rememberPixoToolItems()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundPrimary)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            PixoTopBar(
                variant = if (hasActiveSubscription) {
                    PixoTopBarVariant.MainSubscribed
                } else {
                    PixoTopBarVariant.MainGuest
                },
                tokens = tokens,
                onGetProClick = onGetProClick,
                onTokensClick = onTokenBalanceClick,
                onSettingsClick = onSettingsClick
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentPadding = PaddingValues(
                    start = dimensionResource(id = R.dimen._16),
                    end = dimensionResource(id = R.dimen._16),
                    bottom = dimensionResource(id = R.dimen._111)
                ),
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen._16)
                )
            ) {
                item {
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._8)))

                    Text(
                        text = stringResource(id = R.string.tools_title),
                        color = LabelPrimary,
                        style = MaterialTheme.typography.headlineMedium
                    )
                }

                item {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        PixoImageLabCard(
                            imageRes = R.drawable.template_preview_image_lab,
                            onClick = {
                                val nextVariant = when (toolSourceVariant) {
                                    ToolPhotoSourceScreenVariant.Default ->
                                        ToolPhotoSourceScreenVariant.FullImageActions

                                    ToolPhotoSourceScreenVariant.FullImageActions ->
                                        ToolPhotoSourceScreenVariant.Default
                                }

                                onToolSourceVariantChange(nextVariant)
                            },
                            onActionClick = {
                                val nextVariant = when (toolSourceVariant) {
                                    ToolPhotoSourceScreenVariant.Default ->
                                        ToolPhotoSourceScreenVariant.FullImageActions

                                    ToolPhotoSourceScreenVariant.FullImageActions ->
                                        ToolPhotoSourceScreenVariant.Default
                                }

                                onToolSourceVariantChange(nextVariant)
                            }
                        )
                    }
                }

                tools.chunked(PixoToolsConstants.GRID_COLUMNS).forEach { rowItems ->
                    item {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(
                                dimensionResource(id = R.dimen._8)
                            )
                        ) {
                            rowItems.forEach { tool ->
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .noRippleClick {
                                            onToolClick(tool, toolSourceVariant)
                                        }
                                ) {
                                    PixoBeforeAfterPreview(
                                        modifier = Modifier.fillMaxWidth(),
                                        titleRes = tool.titleRes,
                                        imageResList = tool.imageResList
                                    )
                                }
                            }

                            if (rowItems.size < PixoToolsConstants.GRID_COLUMNS) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }
        }

        PixoBottomNavigation(
            selectedTab = MainTab.Tools,
            onTabClick = onTabClick,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

private object PixoToolsConstants {
    const val GRID_COLUMNS = 2
}

@Composable
private fun rememberPixoToolItems(): List<PixoToolItem> {
    return remember {
        listOf( // -> to make fit for second preview by android limit for 2000
            PixoToolItem(R.string.tool_ai_enhancer, listOf(R.drawable.tools_ai_enhancher1_1, R.drawable.tools_ai_enhancher1_2)),
            PixoToolItem(R.string.tool_glam_makeup, listOf(R.drawable.tools_glam_makeup1, R.drawable.tools_glam_makeup2)),
            PixoToolItem(R.string.tool_remove_objects, listOf(R.drawable.tools_remove_objects1, R.drawable.tools_remove_objects2)),
            PixoToolItem(R.string.tool_remove_background, listOf(R.drawable.tools_remove_background1, R.drawable.tools_remove_background2)),
            PixoToolItem(R.string.tool_skin_improve, listOf(R.drawable.tools_skin_improve1, R.drawable.tools_skin_improve2)),
            PixoToolItem(R.string.tool_upscale_image, listOf(R.drawable.tools_upscale_image1, R.drawable.tools_upscale_image2)),
            PixoToolItem(R.string.tool_change_scene, listOf(R.drawable.tools_change_scene1, R.drawable.tools_change_scene2)),
            PixoToolItem(R.string.tool_hair_studio, listOf(R.drawable.tools_hair_studio)),
            PixoToolItem(R.string.tool_smile_edit, listOf(R.drawable.tools_smile_edit)),
            PixoToolItem(R.string.tool_ghost_style, listOf(R.drawable.tools_ghost_style1, R.drawable.tools_ghost_style2)),
            PixoToolItem(R.string.tool_ghibli_look, listOf(R.drawable.tools_ghibli_look1, R.drawable.tools_ghibli_look2))
        )
    }
}

@Preview(name = "Pixo / Tools Screen / Phone", showBackground = true)
@Composable
private fun PixoToolsScreenPhonePreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._844))
                .background(BackgroundPrimary)
        ) {
            PixoToolsScreen()
        }
    }
}

@Composable
private fun PixoToolsScreenAllCardsStaticPreviewContent() {
    val tools = rememberPixoToolItems()

    Box(
        modifier = Modifier
            .width(dimensionResource(id = R.dimen._390))
            .height(dimensionResource(id = R.dimen._2000))
            .background(BackgroundPrimary)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            PixoTopBar(
                variant = PixoTopBarVariant.MainGuest
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = dimensionResource(id = R.dimen._16),
                        end = dimensionResource(id = R.dimen._16),
                        bottom = dimensionResource(id = R.dimen._111)
                    ),
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen._16)
                )
            ) {
                Spacer(
                    modifier = Modifier.height(
                        dimensionResource(id = R.dimen._8)
                    )
                )

                Text(
                    text = stringResource(id = R.string.tools_title),
                    color = LabelPrimary,
                    style = MaterialTheme.typography.headlineMedium
                )

                PixoImageLabCard(
                    imageRes = R.drawable.template_preview_image_lab
                )

                tools.chunked(PixoToolsConstants.GRID_COLUMNS).forEach { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(
                            dimensionResource(id = R.dimen._8),
                            alignment = Alignment.CenterHorizontally
                        )
                    ) {
                        rowItems.forEach { tool ->
                            PixoBeforeAfterPreview(
                                titleRes = tool.titleRes,
                                imageResList = tool.imageResList
                            )
                        }

                        if (rowItems.size < PixoToolsConstants.GRID_COLUMNS) {
                            Spacer(
                                modifier = Modifier.width(
                                    dimensionResource(id = R.dimen._175)
                                )
                            )
                        }
                    }
                }
            }
        }

        PixoBottomNavigation(
            selectedTab = MainTab.Tools,
            onTabClick = {},
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Preview(
    name = "Pixo / Tools Screen / All Cards Static",
    showBackground = true,
    widthDp = 390,
    heightDp = 2000
)
@Composable
private fun PixoToolsScreenAllCardsStaticPreview() {
    PixoTheme {
        PixoToolsScreenAllCardsStaticPreviewContent()
    }
}