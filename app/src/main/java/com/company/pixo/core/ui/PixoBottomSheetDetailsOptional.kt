package com.company.pixo.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.company.pixo.R
import com.company.pixo.core.theme.AccentWhite
import com.company.pixo.core.theme.GlamMakeupTextFieldBorder
import com.company.pixo.core.theme.TextIconWhite24
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.LabelPrimary
import com.company.pixo.core.theme.PixoTheme

@Composable
fun PixoOptionalDetailsBottomSheetContent(
    title: String,
    hint: String,
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    onCloseClick: () -> Unit,
    onConfirmClick: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(300)
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(BackgroundPrimary)
            .imePadding()
            .navigationBarsPadding()
            .padding(bottom = dimensionResource(id = R.dimen._20)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen._32))
                .padding(
                    start = dimensionResource(id = R.dimen._16),
                    end = dimensionResource(id = R.dimen._24)
                )
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = title,
                color = LabelPrimary,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                maxLines = 1,
            )

            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onCloseClick
                    ),
                contentAlignment = Alignment.Center
            ) {
                PixoRemoveTextIcon(
                    fontSize = dimensionResource(id = R.dimen._24).value.sp
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen._20))
                .padding(horizontal = dimensionResource(R.dimen._16))
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen._12)
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PixoOptionalDetailsTextField(
                value = value,
                hint = hint,
                onValueChange = onValueChange,
                modifier = Modifier.focusRequester(focusRequester)
            )

            PixoButton(
                textRes = R.string.common_confirm,
                modifier = Modifier.fillMaxWidth(),
                size = PixoButtonSize.Small,
                enabled = value.isNotEmpty(),
                onClick = onConfirmClick
            )
        }
    }
}

@Composable
private fun PixoOptionalDetailsTextField(
    value: String,
    hint: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
    BasicTextField(
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen._44))
            .border(
                width = dimensionResource(id = R.dimen._1),
                color = GlamMakeupTextFieldBorder,
                shape = RoundedCornerShape(dimensionResource(id = R.dimen._8))
            )
            .padding(horizontal = dimensionResource(id = R.dimen._12)),
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = MaterialTheme.typography.labelMedium.copy(
            color = AccentWhite
        ),
        cursorBrush = SolidColor(AccentWhite),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.CenterStart
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = hint,
                        color = TextIconWhite24,
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                innerTextField()
            }
        }
    )
}

@Preview(
    name = "Pixo / Optional Details inactive",
    showBackground = true,
    widthDp = 390,
    heightDp = 160
)
@Composable
private fun PixoOptionalDetailsBottomSheetContentInactivePreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._160))
                .background(BackgroundPrimary),
            contentAlignment = Alignment.TopCenter
        ) {
            PixoOptionalDetailsBottomSheetContent(
                title = stringResource(id = R.string.glam_makeup_optional_details_title),
                hint = stringResource(id = R.string.glam_makeup_style_hint),
                value = "",
                onValueChange = {},
                onCloseClick = {},
                onConfirmClick = {}
            )
        }
    }
}

@Preview(
    name = "Pixo / Optional Details active",
    showBackground = true,
    widthDp = 390,
    heightDp = 160
)
@Composable
private fun PixoOptionalDetailsBottomSheetContentActivePreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._160))
                .background(BackgroundPrimary),
            contentAlignment = Alignment.TopCenter
        ) {
            PixoOptionalDetailsBottomSheetContent(
                title = stringResource(id = R.string.glam_makeup_optional_details_title),
                hint = stringResource(id = R.string.glam_makeup_style_hint),
                value = stringResource(id = R.string.glam_makeup_style_preview_text),
                onValueChange = {},
                onCloseClick = {},
                onConfirmClick = {}
            )
        }
    }
}

@Preview(
    name = "Pixo / Optional Details inactive",
    showBackground = true,
    widthDp = 390,
    heightDp = 160
)
@Composable
private fun PixoRemoveObjectsOptionalDetailsBottomSheetContentInactivePreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._160))
                .background(BackgroundPrimary),
            contentAlignment = Alignment.TopCenter
        ) {
            PixoOptionalDetailsBottomSheetContent(
                title = stringResource(id = R.string.remove_objects_style_title),
                hint = stringResource(id = R.string.remove_objects_style_hint),
                value = "",
                onValueChange = {},
                onCloseClick = {},
                onConfirmClick = {}
            )
        }
    }
}

@Preview(
    name = "Pixo / Optional Details active",
    showBackground = true,
    widthDp = 390,
    heightDp = 160
)
@Composable
private fun PixoRemoveObjectsOptionalDetailsBottomSheetContentActivePreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._160))
                .background(BackgroundPrimary),
            contentAlignment = Alignment.TopCenter
        ) {
            PixoOptionalDetailsBottomSheetContent(
                title = stringResource(id = R.string.remove_objects_style_title),
                hint = stringResource(id = R.string.remove_objects_style_hint),
                value = stringResource(id = R.string.remove_objects_style_preview_text),
                onValueChange = {},
                onCloseClick = {},
                onConfirmClick = {}
            )
        }
    }
}

@Preview(
    name = "Pixo / Optional Details inactive",
    showBackground = true,
    widthDp = 390,
    heightDp = 160
)
@Composable
private fun PixoHairStyleOptionalDetailsBottomSheetContentInactivePreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._160))
                .background(BackgroundPrimary),
            contentAlignment = Alignment.TopCenter
        ) {
            PixoOptionalDetailsBottomSheetContent(
                title = stringResource(id = R.string.hair_style_details_title),
                hint = stringResource(id = R.string.hair_style_hint),
                value = "",
                onValueChange = {},
                onCloseClick = {},
                onConfirmClick = {}
            )
        }
    }
}

@Preview(
    name = "Pixo / Optional Details active",
    showBackground = true,
    widthDp = 390,
    heightDp = 160
)
@Composable
private fun PixoHairStyleOptionalDetailsBottomSheetContentActivePreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._160))
                .background(BackgroundPrimary),
            contentAlignment = Alignment.TopCenter
        ) {
            PixoOptionalDetailsBottomSheetContent(
                title = stringResource(id = R.string.hair_style_details_title),
                hint = stringResource(id = R.string.hair_style_hint),
                value = stringResource(id = R.string.hair_style_details_preview_text_1),
                onValueChange = {},
                onCloseClick = {},
                onConfirmClick = {}
            )
        }
    }
}

@Preview(
    name = "Pixo / Optional Details inactive",
    showBackground = true,
    widthDp = 390,
    heightDp = 160
)
@Composable
private fun PixoChangeSceneOptionalDetailsBottomSheetContentInactivePreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._160))
                .background(BackgroundPrimary),
            contentAlignment = Alignment.TopCenter
        ) {
            PixoOptionalDetailsBottomSheetContent(
                title = stringResource(id = R.string.change_scene_describe_short_title),
                hint = stringResource(id = R.string.change_scene_hint),
                value = "",
                onValueChange = {},
                onCloseClick = {},
                onConfirmClick = {}
            )
        }
    }
}

@Preview(
    name = "Pixo / Optional Details active",
    showBackground = true,
    widthDp = 390,
    heightDp = 160
)
@Composable
private fun PixoChangeSceneOptionalDetailsBottomSheetContentActivePreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._160))
                .background(BackgroundPrimary),
            contentAlignment = Alignment.TopCenter
        ) {
            PixoOptionalDetailsBottomSheetContent(
                title = stringResource(id = R.string.change_scene_describe_short_title),
                hint = "",
                value = stringResource(id = R.string.change_scene_preview_text),
                onValueChange = {},
                onCloseClick = {},
                onConfirmClick = {}
            )
        }
    }
}

@Composable
fun PixoAddPhotoBottomSheetContent(
    modifier: Modifier = Modifier,
    onCloseClick: () -> Unit,
    onCameraClick: () -> Unit,
    onPhotoLibraryClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(BackgroundPrimary)
            .padding(
                bottom = dimensionResource(R.dimen._12)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(top = dimensionResource(R.dimen._8))
                .width(dimensionResource(R.dimen._32))
                .height(dimensionResource(R.dimen._4))
                .clip(RoundedCornerShape(dimensionResource(R.dimen._8)))
                .background(LabelPrimary)
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen._16)))

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(R.string.photo_add_your_photo),
                color = LabelPrimary,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                maxLines = 1
            )

            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onCloseClick
                    ),
                contentAlignment = Alignment.Center
            ) {
                PixoRemoveTextIcon(
                    fontSize = dimensionResource(id = R.dimen._24).value.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen._12)))

        PixoChoosePhotoHorizontalContent(
            onCameraClick = onCameraClick,
            onPhotoLibraryClick = onPhotoLibraryClick
        )
    }
}

@Preview(
    name = "Pixo / Add Photo Bottom Sheet",
    showBackground = true,
    widthDp = 390,
    heightDp = 220
)
@Composable
private fun PixoAddPhotoBottomSheetContentPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary),
            contentAlignment = Alignment.BottomCenter
        ) {
            PixoAddPhotoBottomSheetContent(
                onCloseClick = {},
                onCameraClick = {},
                onPhotoLibraryClick = {}
            )
        }
    }
}