package com.company.pixo.feature.templates

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.company.pixo.R
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.ui.PixoAddPhotoBottomSheetContent
import com.company.pixo.core.ui.PixoAddPhotoBorderStyle
import com.company.pixo.core.ui.PixoCameraPermissionDialog
import com.company.pixo.core.ui.PixoMediaLibraryPermissionDialog
import com.company.pixo.core.ui.PixoPhotoGenerateActions
import com.company.pixo.core.ui.PixoTemplateGenerateActions
import com.company.pixo.core.ui.PixoTopBar
import com.company.pixo.core.ui.PixoTopBarVariant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PixoTemplateDetailsRoute(
    @DrawableRes templateImageRes: Int,
    @StringRes templateTitleRes: Int,
    capturedImageUri: String?,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onCameraClick: () -> Unit,
    onPhotoLibraryClick: () -> Unit,
    onPictureRemoveClick: () -> Unit,
    onGenerateClick: () -> Unit,
    onTitleClick: () -> Unit = {}
) {
    var showAddPhotoSheet by remember {
        mutableStateOf(false)
    }

    var showCameraDialog by remember {
        mutableStateOf(false)
    }

    var showMediaLibraryPermissionDialog by remember {
        mutableStateOf(false)
    }

    val addPhotoBorderStyle = if (showCameraDialog) {
        PixoAddPhotoBorderStyle.Gradient
    } else {
        PixoAddPhotoBorderStyle.Accent
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
                titleRes = templateTitleRes,
                onBackClick = onBackClick,
                onTitleClick = onTitleClick
            )

            Image(
                painter = painterResource(templateImageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(
                        start = dimensionResource(R.dimen._16),
                        end = dimensionResource(R.dimen._16),
                        top = dimensionResource(R.dimen._8),
                        bottom = dimensionResource(R.dimen._6)
                    ),
                contentScale = ContentScale.Crop
            )

            if (capturedImageUri == null) {
                PixoPhotoGenerateActions(
                    generateEnabled = false,
                    addPhotoBorderStyle = addPhotoBorderStyle,
                    modifier = Modifier
                        .navigationBarsPadding()
                        .padding(
                            top = dimensionResource(R.dimen._16),
                            bottom = dimensionResource(R.dimen._16)
                        ),
                    onAddPhotoClick = {
                        showAddPhotoSheet = true
                    },
                    onGenerateClick = {}
                )
            } else {
                PixoTemplateGenerateActions(
                    imageUri = capturedImageUri,
                    modifier = Modifier
                        .navigationBarsPadding()
                        .padding(
                            top = dimensionResource(R.dimen._16),
                            bottom = dimensionResource(R.dimen._16)
                        ),
                    onPictureClick = {
                        showAddPhotoSheet = true
                    },
                    onPictureRemoveClick = onPictureRemoveClick,
                    onGenerateClick = onGenerateClick
                )
            }
        }

        if (showCameraDialog) {
            Box(
                modifier = Modifier.align(Alignment.Center)
            ) {
                PixoCameraPermissionDialog(
                    onDontAllowClick = {
                        showCameraDialog = false
                    },
                    onOkClick = {
                        showCameraDialog = false
                        onCameraClick()
                    }
                )
            }
        }

        if (showMediaLibraryPermissionDialog) {
            Box(
                modifier = Modifier.align(Alignment.Center)
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
    }

    if (showAddPhotoSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showAddPhotoSheet = false
            },
            containerColor = BackgroundPrimary,
            dragHandle = null
        ) {
            PixoAddPhotoBottomSheetContent(
                onCloseClick = {
                    showAddPhotoSheet = false
                },
                onCameraClick = {
                    showAddPhotoSheet = false
                    showCameraDialog = true
                },
                onPhotoLibraryClick = {
                    showAddPhotoSheet = false
                    showMediaLibraryPermissionDialog = true
                }
            )
        }
    }
}
