package com.company.pixo.feature.photo

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.company.pixo.R
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.PixoTheme
import com.company.pixo.core.ui.PixoBeforeAfterSlider
import com.company.pixo.core.ui.PixoCameraPermissionDialog
import com.company.pixo.core.ui.PixoChoosePhotoContent
import com.company.pixo.core.ui.PixoMediaLibraryPermissionDialog
import com.company.pixo.core.ui.PixoTopBar
import com.company.pixo.core.ui.PixoTopBarVariant
import com.company.pixo.core.ui.photo.PixoPhotoRequirementsSheetHost

enum class ToolPhotoSourceScreenVariant {
    Default,
    FullImageActions
}

@Composable
fun ToolPhotoSourceScreen(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    @DrawableRes beforeImageRes: Int,
    @DrawableRes afterImageRes: Int? = null,
    variant: ToolPhotoSourceScreenVariant = ToolPhotoSourceScreenVariant.Default,
    onBackClick: () -> Unit,
    onCameraClick: () -> Unit,
    onPhotoLibraryClick: () -> Unit,
    highlightSliderOnCameraDialog: Boolean = false,
    onTryClick: () -> Unit = {},
) {
    var sliderPosition by remember {
        mutableFloatStateOf(0.5f)
    }

    var showCameraPermissionDialog by remember {
        mutableStateOf(false)
    }

    var showMediaLibraryPermissionDialog by remember {
        mutableStateOf(false)
    }

    var showPhotoRequirementsSheet by remember {
        mutableStateOf(false)
    }

    val sliderHandleBrush = if (
        (showCameraPermissionDialog || showMediaLibraryPermissionDialog) &&
        highlightSliderOnCameraDialog
    ) {
        Brush.horizontalGradient(
            colors = listOf(
                Color(0xFFE07A8E),
                Color(0xFFB24CCF),
                Color(0xFF5B4DFF)
            )
        )
    } else {
        null
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundPrimary)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            PixoTopBar(
                variant = PixoTopBarVariant.Detail,
                titleRes = titleRes,
                onBackClick = onBackClick
            )

            when (variant) {
                ToolPhotoSourceScreenVariant.Default -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(
                                top = dimensionResource(R.dimen._8),
                                start = dimensionResource(R.dimen._16),
                                end = dimensionResource(R.dimen._16),
                                bottom = dimensionResource(R.dimen._8)
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        PixoBeforeAfterSlider(
                            beforeImageRes = beforeImageRes,
                            afterImageRes = afterImageRes,
                            sliderPosition = sliderPosition,
                            onSliderPositionChange = {
                                sliderPosition = it
                            },
                            handleBackgroundBrush = sliderHandleBrush
                        )

                        Spacer(
                            modifier = Modifier.height(dimensionResource(R.dimen._16))
                        )

                        PixoChoosePhotoContent(
                            modifier = Modifier.weight(1f),
                            onCameraClick = {
                                showCameraPermissionDialog = true
                            },
                            onPhotoLibraryClick = {
                                showPhotoRequirementsSheet = true
                            }
                        )
                    }
                }

                ToolPhotoSourceScreenVariant.FullImageActions -> {
                    PixoBeforeAfterSlider(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                start = dimensionResource(R.dimen._16),
                                end = dimensionResource(R.dimen._16),
                                bottom = dimensionResource(R.dimen._56)
                            ),
                        beforeImageRes = beforeImageRes,
                        afterImageRes = afterImageRes,
                        sliderPosition = sliderPosition,
                        onSliderPositionChange = {
                            sliderPosition = it
                        },
                        handleBackgroundBrush = sliderHandleBrush,
                        labelsAsIcons = true,
                        onBeforeLabelClick = {
                            showPhotoRequirementsSheet = true
                        },
                        onAfterLabelClick = {
                            showCameraPermissionDialog = true
                        }
                    )
                }
            }
        }

        if (showCameraPermissionDialog) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = dimensionResource(R.dimen._310))
            ) {
                PixoCameraPermissionDialog(
                    onDontAllowClick = {
                        showCameraPermissionDialog = false
                    },
                    onOkClick = {
                        showCameraPermissionDialog = false
                        onCameraClick()
                    }
                )
            }
        }

        if (showMediaLibraryPermissionDialog) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = dimensionResource(R.dimen._310))
            ) {
                PixoMediaLibraryPermissionDialog(
                    onRestrictAccessClick = {
                        showMediaLibraryPermissionDialog = false
                    },
                    onAllowFullAccessClick = {
                        showMediaLibraryPermissionDialog = false
                        onPhotoLibraryClick()
                    },
                    onAllowLimitedAccessClick = {
                        showMediaLibraryPermissionDialog = false
                        onPhotoLibraryClick()
                    }
                )
            }
        }

        PixoPhotoRequirementsSheetHost(
            show = showPhotoRequirementsSheet,
            onDismiss = {
                showPhotoRequirementsSheet = false
            },
            onContinueClick = {
                showPhotoRequirementsSheet = false
                showMediaLibraryPermissionDialog = true
            }
        )
    }
}

@Preview(
    name = "ToolPhotoSourceScreen / Glam Makeup",
    showBackground = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
private fun ToolPhotoSourceScreenGlamMakeupPreview() {
    PixoTheme {
        ToolPhotoSourceScreen(
            titleRes = R.string.tool_glam_makeup,
            beforeImageRes = R.drawable.tools_glam_makeup_2_1,
            afterImageRes = R.drawable.tools_glam_makeup_2_2,
            onBackClick = {},
            onCameraClick = {},
            onPhotoLibraryClick = {}
        )
    }
}