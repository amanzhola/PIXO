package com.company.pixo.core.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.company.pixo.R
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.LabelPrimary
import com.company.pixo.core.theme.PixoTheme
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.company.pixo.core.theme.AccentRed
import com.company.pixo.core.theme.AccentWhite
import com.company.pixo.core.theme.DialogBackground
import com.company.pixo.core.theme.DialogBorder
import com.company.pixo.core.theme.DialogGlassBackground
import com.company.pixo.core.theme.DialogGlassBorder
import com.company.pixo.core.theme.DialogGlassButtonBackground
import com.company.pixo.core.theme.DialogGlassTextPrimary
import com.company.pixo.core.theme.DialogGlow
import com.company.pixo.core.theme.TextIconWhite42
import com.company.pixo.core.theme.TextIconSoftMain

@Composable
fun PixoDialog(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.prompt_dialog_title),
    description: String = stringResource(id = R.string.prompt_dialog_description),
    bullets: List<String> = listOf(
        stringResource(id = R.string.prompt_dialog_cartoon),
        stringResource(id = R.string.prompt_dialog_lighting),
        stringResource(id = R.string.prompt_dialog_headshot)
    ),
    counter: String = stringResource(id = R.string.prompt_dialog_counter),
    inputText: String = "",
    borderColor: Color = DialogGlow.copy(alpha = 0.8f),
    borderWidth: Dp = dimensionResource(id = R.dimen._1),
    glowEnabled: Boolean = true,
    onTrashClick: () -> Unit = {},
    onValueChange: (String) -> Unit = {},
    maxLength: Int = 300,
) {
    Box(
        modifier = modifier
            .width(dimensionResource(id = R.dimen._342))
            .heightIn(min = dimensionResource(id = R.dimen._206))
            .then(
                if (glowEnabled) {
                    Modifier.shadow(
                        elevation = dimensionResource(id = R.dimen._24),
                        shape = RoundedCornerShape(dimensionResource(id = R.dimen._16)),
                        ambientColor = DialogGlow.copy(alpha = 0.9f),
                        spotColor = DialogGlow.copy(alpha = 0.9f)
                    )
                } else {
                    Modifier
                }
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(dimensionResource(id = R.dimen._16)))
                .background(DialogBackground)
                .border(
                    width = borderWidth,
                    color = borderColor,
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen._16))
                )
                .padding(dimensionResource(id = R.dimen._16)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen._12))
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen._8))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen._22)),
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen._8)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_onb15_stars),
                        contentDescription = null,
                        modifier = Modifier
                            .width(dimensionResource(id = R.dimen._22))
                            .height(dimensionResource(id = R.dimen._22))
                    )

                    Text(
                        text = title,
                        color = LabelPrimary,
                        style = MaterialTheme.typography.titleSmall
                    )
                }

                val focusRequester = remember { FocusRequester() }
                val keyboardController = LocalSoftwareKeyboardController.current

                BasicTextField(
                    value = inputText,
                    onValueChange = { nextValue ->
                        if (nextValue.length <= maxLength) {
                            onValueChange(nextValue)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = dimensionResource(id = R.dimen._100))
                        .focusRequester(focusRequester)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            focusRequester.requestFocus()
                            keyboardController?.show()
                        },
                    textStyle = MaterialTheme.typography.bodyLarge.copy(
                        color = AccentWhite
                    ),
                    cursorBrush = SolidColor(AccentWhite),
                    decorationBox = { innerTextField ->
                        if (inputText.isEmpty()) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(
                                    dimensionResource(id = R.dimen._4)
                                )
                            ) {
                                Text(
                                    text = description,
                                    color = TextIconWhite42,
                                    style = MaterialTheme.typography.titleSmall
                                )

                                bullets.forEach { bullet ->
                                    PixoDialogBulletText(text = bullet)
                                }
                            }
                        }

                        innerTextField()
                    }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen._32)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_trash_can),
                    contentDescription = null,
                    modifier = Modifier
                        .width(dimensionResource(id = R.dimen._32))
                        .height(dimensionResource(id = R.dimen._32))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = onTrashClick
                        )
                )

                Text(
                    text = "${inputText.length}/$maxLength",
                    color = TextIconSoftMain,
                    textAlign = TextAlign.Right,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

@Composable
private fun PixoDialogBulletText(
    text: String
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen._8)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_dot_paywall),
            contentDescription = null,
            tint = TextIconWhite42
        )

        Text(
            text = text,
            color = TextIconWhite42,
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Preview(name = "Pixo / Dialog Prompt", showBackground = true)
@Composable
private fun PixoDialogPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._844))
                .background(BackgroundPrimary),
            contentAlignment = Alignment.Center
        ) {
            PixoDialog()
        }
    }
}

@Composable
fun PixoCameraPermissionDialog(
    modifier: Modifier = Modifier,
    onDontAllowClick: () -> Unit,
    onOkClick: () -> Unit
) {
    Box(
        modifier = modifier
            .width(dimensionResource(R.dimen._300))
            .height(dimensionResource(R.dimen._216))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    color = DialogGlassBackground,
                    shape = RoundedCornerShape(
                        dimensionResource(R.dimen._36)
                    )
                )
                .drawBehind {
                    val strokeWidth = 1.dp.toPx()
                    val radius = 36.dp.toPx()
                    val halfStroke = strokeWidth / 2f
                    val endGap = 6.dp.toPx()
                    val tipLength = 10.dp.toPx()

                    val path = Path().apply {
                        // top-left rounded corner
                        moveTo(halfStroke, radius)

                        arcTo(
                            rect = Rect(
                                left = halfStroke,
                                top = halfStroke,
                                right = radius * 2f - halfStroke,
                                bottom = radius * 2f - halfStroke
                            ),
                            startAngleDegrees = 180f,
                            sweepAngleDegrees = 90f,
                            forceMoveTo = false
                        )

                        // top line, stops before top-right
                        lineTo(size.width - radius - endGap, halfStroke)

                        // right line, starts after top-right
                        moveTo(size.width - halfStroke, radius + endGap)
                        lineTo(size.width - halfStroke, size.height - radius)

                        // bottom-right rounded corner
                        arcTo(
                            rect = Rect(
                                left = size.width - radius * 2f + halfStroke,
                                top = size.height - radius * 2f + halfStroke,
                                right = size.width - halfStroke,
                                bottom = size.height - halfStroke
                            ),
                            startAngleDegrees = 0f,
                            sweepAngleDegrees = 90f,
                            forceMoveTo = false
                        )

                        // bottom line, stops before bottom-left
                        lineTo(radius + endGap, size.height - halfStroke)

                        // left line, starts after bottom-left
                        moveTo(halfStroke, size.height - radius - endGap)
                        lineTo(halfStroke, radius)
                    }

                    drawPath(
                        path = path,
                        color = DialogGlassBorder,
                        style = Stroke(width = strokeWidth)
                    )

                    // sharp tip: top line → top-right
                    drawPath(
                        path = Path().apply {
                            val x = size.width - radius - endGap
                            val y = halfStroke

                            moveTo(x, y - strokeWidth / 2f)
                            lineTo(x + tipLength, y)
                            lineTo(x, y + strokeWidth / 2f)
                            close()
                        },
                        color = DialogGlassBorder
                    )

                    // sharp tip: right line → top-right
                    drawPath(
                        path = Path().apply {
                            val x = size.width - halfStroke
                            val y = radius + endGap

                            moveTo(x - strokeWidth / 2f, y)
                            lineTo(x, y - tipLength)
                            lineTo(x + strokeWidth / 2f, y)
                            close()
                        },
                        color = DialogGlassBorder
                    )

                    // sharp tip: bottom line → bottom-left
                    drawPath(
                        path = Path().apply {
                            val x = radius + endGap
                            val y = size.height - halfStroke

                            moveTo(x, y - strokeWidth / 2f)
                            lineTo(x - tipLength, y)
                            lineTo(x, y + strokeWidth / 2f)
                            close()
                        },
                        color = DialogGlassBorder
                    )

                    // sharp tip: left line → bottom-left
                    drawPath(
                        path = Path().apply {
                            val x = halfStroke
                            val y = size.height - radius - endGap

                            moveTo(x - strokeWidth / 2f, y)
                            lineTo(x, y + tipLength)
                            lineTo(x + strokeWidth / 2f, y)
                            close()
                        },
                        color = DialogGlassBorder
                    )
                }
                .padding(dimensionResource(R.dimen._14)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(R.dimen._10)
            )
        ) {
            Spacer(
                modifier = Modifier.height(
                    dimensionResource(R.dimen._4)
                )
            )

            Text(
                modifier = Modifier
                    .width(dimensionResource(R.dimen._256))
                    .height(dimensionResource(R.dimen._44))
                    .align(Alignment.Start),
                text = stringResource(
                    R.string.camera_permission_title
                ),
                color = DialogGlassTextPrimary,
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Start,
                maxLines = 2
            )

            Text(
                modifier = Modifier
                    .width(dimensionResource(R.dimen._256))
                    .height(dimensionResource(R.dimen._44))
                    .align(Alignment.Start),
                text = stringResource(
                    R.string.camera_permission_message
                ),
                color = DialogGlassTextPrimary,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Start,
                maxLines = 2
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    dimensionResource(R.dimen._10)
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                PixoPermissionDialogButton(
                    textRes = R.string.permission_dont_allow,
                    onClick = onDontAllowClick
                )

                PixoPermissionDialogButton(
                    textRes = R.string.common_ok,
                    onClick = onOkClick
                )
            }
        }
    }
}

@Composable
private fun PixoPermissionDialogButton(
    @StringRes textRes: Int,
    modifier: Modifier = Modifier,
    width: Dp = dimensionResource(R.dimen._128),
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .width(width)
            .height(dimensionResource(R.dimen._48))
            .background(
                color = DialogGlassButtonBackground,
                shape = RoundedCornerShape(dimensionResource(R.dimen._100))
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(
                horizontal = dimensionResource(R.dimen._16),
                vertical = dimensionResource(R.dimen._13)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(textRes),
            color = DialogGlassTextPrimary,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}

@Preview(
    name = "Pixo / Camera Permission Dialog",
    showBackground = true,
    widthDp = 390,
    heightDp = 300
)
@Composable
private fun PixoCameraPermissionDialogPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary),
            contentAlignment = Alignment.Center
        ) {
            PixoCameraPermissionDialog(
                onDontAllowClick = {},
                onOkClick = {}
            )
        }
    }
}

@Composable
fun PixoMediaLibraryPermissionDialog(
    modifier: Modifier = Modifier,
    onRestrictAccessClick: () -> Unit,
    onAllowFullAccessClick: () -> Unit,
    onAllowLimitedAccessClick: () -> Unit
) {
    Box(
        modifier = modifier
            .width(dimensionResource(R.dimen._300))
            .height(dimensionResource(R.dimen._278))
    ) {
        Column(
            modifier = Modifier
                .matchParentSize()
                .background(
                    color = DialogGlassBackground,
                    shape = RoundedCornerShape(
                        dimensionResource(R.dimen._36)
                    )
                )
                .drawBehind {
                    val strokeWidth = 1.dp.toPx()
                    val radius = 36.dp.toPx()
                    val halfStroke = strokeWidth / 2f
                    val endGap = 6.dp.toPx()
                    val tipLength = 10.dp.toPx()

                    val path = Path().apply {
                        moveTo(halfStroke, radius)

                        arcTo(
                            rect = Rect(
                                left = halfStroke,
                                top = halfStroke,
                                right = radius * 2f - halfStroke,
                                bottom = radius * 2f - halfStroke
                            ),
                            startAngleDegrees = 180f,
                            sweepAngleDegrees = 90f,
                            forceMoveTo = false
                        )

                        lineTo(size.width - radius - endGap, halfStroke)

                        moveTo(size.width - halfStroke, radius + endGap)
                        lineTo(size.width - halfStroke, size.height - radius)

                        arcTo(
                            rect = Rect(
                                left = size.width - radius * 2f + halfStroke,
                                top = size.height - radius * 2f + halfStroke,
                                right = size.width - halfStroke,
                                bottom = size.height - halfStroke
                            ),
                            startAngleDegrees = 0f,
                            sweepAngleDegrees = 90f,
                            forceMoveTo = false
                        )

                        lineTo(radius + endGap, size.height - halfStroke)

                        moveTo(halfStroke, size.height - radius - endGap)
                        lineTo(halfStroke, radius)
                    }

                    drawPath(
                        path = path,
                        color = DialogGlassBorder,
                        style = Stroke(width = strokeWidth)
                    )

                    drawPath(
                        path = Path().apply {
                            val x = size.width - radius - endGap
                            val y = halfStroke

                            moveTo(x, y - strokeWidth / 2f)
                            lineTo(x + tipLength, y)
                            lineTo(x, y + strokeWidth / 2f)
                            close()
                        },
                        color = DialogGlassBorder
                    )

                    drawPath(
                        path = Path().apply {
                            val x = size.width - halfStroke
                            val y = radius + endGap

                            moveTo(x - strokeWidth / 2f, y)
                            lineTo(x, y - tipLength)
                            lineTo(x + strokeWidth / 2f, y)
                            close()
                        },
                        color = DialogGlassBorder
                    )

                    drawPath(
                        path = Path().apply {
                            val x = radius + endGap
                            val y = size.height - halfStroke

                            moveTo(x, y - strokeWidth / 2f)
                            lineTo(x - tipLength, y)
                            lineTo(x, y + strokeWidth / 2f)
                            close()
                        },
                        color = DialogGlassBorder
                    )

                    drawPath(
                        path = Path().apply {
                            val x = halfStroke
                            val y = size.height - radius - endGap

                            moveTo(x - strokeWidth / 2f, y)
                            lineTo(x, y + tipLength)
                            lineTo(x + strokeWidth / 2f, y)
                            close()
                        },
                        color = DialogGlassBorder
                    )
                }
                .padding(dimensionResource(R.dimen._14)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(R.dimen._10)
            )
        ) {
            Spacer(
                modifier = Modifier.height(
                    dimensionResource(R.dimen._4)
                )
            )

            Text(
                modifier = Modifier
                    .width(dimensionResource(R.dimen._272))
                    .align(Alignment.Start),
                text = stringResource(
                    R.string.media_permission_title,
                    stringResource(R.string.app_name)
                ),
                color = DialogGlassTextPrimary,
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Start,
                maxLines = 3
            )

            Spacer(modifier = Modifier.weight(1f))

            Column(
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(R.dimen._10)
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PixoPermissionDialogButton(
                    textRes = R.string.permission_restrict_access,
                    width = dimensionResource(R.dimen._272),
                    onClick = onRestrictAccessClick
                )

                PixoPermissionDialogButton(
                    textRes = R.string.permission_allow_full_access,
                    width = dimensionResource(R.dimen._272),
                    onClick = onAllowFullAccessClick
                )

                PixoPermissionDialogButton(
                    textRes = R.string.permission_do_not_authorise,
                    width = dimensionResource(R.dimen._272),
                    onClick = onAllowLimitedAccessClick
                )
            }
        }
    }
}

@Preview(
    name = "Pixo / Media Library Permission Dialog",
    showBackground = true,
    widthDp = 390,
    heightDp = 360
)
@Composable
private fun PixoMediaLibraryPermissionDialogPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary),
            contentAlignment = Alignment.Center
        ) {
            PixoMediaLibraryPermissionDialog(
                onRestrictAccessClick = {},
                onAllowFullAccessClick = {},
                onAllowLimitedAccessClick = {}
            )
        }
    }
}

@Composable
private fun PixoPermissionDialogButton(
    @StringRes textRes: Int,
    modifier: Modifier = Modifier,
    width: Dp = dimensionResource(R.dimen._128),
    backgroundColor: Color = DialogGlassButtonBackground,
    textColor: Color = DialogGlassTextPrimary,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .width(width)
            .height(dimensionResource(R.dimen._48))
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(dimensionResource(R.dimen._100))
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(
                horizontal = dimensionResource(R.dimen._16),
                vertical = dimensionResource(R.dimen._13)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(textRes),
            color = textColor,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}

@Composable
fun PixoDeleteClipDialog(
    modifier: Modifier = Modifier,
    cancelTextRes: Int = R.string.common_cancel,
    deleteTextRes: Int = R.string.common_delete,
    onCancelClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Box(
        modifier = modifier
            .width(dimensionResource(R.dimen._300))
            .height(dimensionResource(R.dimen._194))
    ) {
        Column(
            modifier = Modifier
                .matchParentSize()
                .background(
                    color = DialogGlassBackground,
                    shape = RoundedCornerShape(dimensionResource(R.dimen._34))
                )
                .drawBehind {
                    val strokeWidth = 1.dp.toPx()
                    val radius = 34.dp.toPx()
                    val halfStroke = strokeWidth / 2f
                    val endGap = 6.dp.toPx()
                    val tipLength = 10.dp.toPx()

                    val path = Path().apply {
                        moveTo(halfStroke, radius)

                        arcTo(
                            rect = Rect(
                                left = halfStroke,
                                top = halfStroke,
                                right = radius * 2f - halfStroke,
                                bottom = radius * 2f - halfStroke
                            ),
                            startAngleDegrees = 180f,
                            sweepAngleDegrees = 90f,
                            forceMoveTo = false
                        )

                        lineTo(size.width - radius - endGap, halfStroke)

                        moveTo(size.width - halfStroke, radius + endGap)
                        lineTo(size.width - halfStroke, size.height - radius)

                        arcTo(
                            rect = Rect(
                                left = size.width - radius * 2f + halfStroke,
                                top = size.height - radius * 2f + halfStroke,
                                right = size.width - halfStroke,
                                bottom = size.height - halfStroke
                            ),
                            startAngleDegrees = 0f,
                            sweepAngleDegrees = 90f,
                            forceMoveTo = false
                        )

                        lineTo(radius + endGap, size.height - halfStroke)

                        moveTo(halfStroke, size.height - radius - endGap)
                        lineTo(halfStroke, radius)
                    }

                    drawPath(
                        path = path,
                        color = DialogGlassBorder,
                        style = Stroke(width = strokeWidth)
                    )

                    drawPath(
                        path = Path().apply {
                            val x = size.width - radius - endGap
                            val y = halfStroke

                            moveTo(x, y - strokeWidth / 2f)
                            lineTo(x + tipLength, y)
                            lineTo(x, y + strokeWidth / 2f)
                            close()
                        },
                        color = DialogGlassBorder
                    )

                    drawPath(
                        path = Path().apply {
                            val x = size.width - halfStroke
                            val y = radius + endGap

                            moveTo(x - strokeWidth / 2f, y)
                            lineTo(x, y - tipLength)
                            lineTo(x + strokeWidth / 2f, y)
                            close()
                        },
                        color = DialogGlassBorder
                    )

                    drawPath(
                        path = Path().apply {
                            val x = radius + endGap
                            val y = size.height - halfStroke

                            moveTo(x, y - strokeWidth / 2f)
                            lineTo(x - tipLength, y)
                            lineTo(x, y + strokeWidth / 2f)
                            close()
                        },
                        color = DialogGlassBorder
                    )

                    drawPath(
                        path = Path().apply {
                            val x = halfStroke
                            val y = size.height - radius - endGap

                            moveTo(x - strokeWidth / 2f, y)
                            lineTo(x, y + tipLength)
                            lineTo(x + strokeWidth / 2f, y)
                            close()
                        },
                        color = DialogGlassBorder
                    )
                }
                .padding(dimensionResource(R.dimen._14)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._10))
        ) {
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen._4)))

            Text(
                modifier = Modifier
                    .width(dimensionResource(R.dimen._256))
                    .align(Alignment.Start),
                text = stringResource(R.string.delete_clip_title),
                color = DialogGlassTextPrimary,
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Start,
                maxLines = 1
            )

            Text(
                modifier = Modifier
                    .width(dimensionResource(R.dimen._256))
                    .align(Alignment.Start),
                text = stringResource(R.string.delete_clip_message),
                color = DialogGlassTextPrimary,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Start,
                maxLines = 2
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._10)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                PixoPermissionDialogButton(
                    textRes = cancelTextRes,
                    onClick = onCancelClick
                )

                PixoPermissionDialogButton(
                    textRes = deleteTextRes,
                    backgroundColor = AccentRed,
                    textColor = AccentWhite,
                    onClick = onDeleteClick
                )
            }
        }
    }
}

@Preview(
    name = "Pixo / Delete Clip Dialog",
    showBackground = true,
    widthDp = 390,
    heightDp = 300
)
@Composable
private fun PixoDeleteClipDialogPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary),
            contentAlignment = Alignment.Center
        ) {
            PixoDeleteClipDialog(
                onCancelClick = {},
                onDeleteClick = {}
            )
        }
    }
}

@Composable
fun PixoPromptFlowScreenContent(
    promptText: String,
    hasPicture: Boolean,
    modifier: Modifier = Modifier,
    onGenerateClick: () -> Unit,
) {
    val hasPrompt = promptText.isNotEmpty()
    val generateTextRes = when {
        hasPrompt && hasPicture -> R.string.common_generate_two
        hasPrompt -> R.string.common_generate_four
        else -> R.string.common_generate
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen._16))
            .background(BackgroundPrimary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PixoTopBar(
            variant = PixoTopBarVariant.MainGuest
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._8)))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.prompt_flow_title),
            color = AccentWhite,
            style = MaterialTheme.typography.headlineMedium,
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._8)))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            PixoDialog(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen._206)),
                borderColor = DialogBorder,
                borderWidth = 0.5.dp,
                glowEnabled = false,
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._8)))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = dimensionResource(id = R.dimen._8))
            ) {
                PixoPromptPicture(
                    imageUri = if (hasPicture) "preview" else null
                )
            }
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._16)))

        PixoButton(
            textRes = generateTextRes,
            modifier = Modifier.fillMaxWidth(),
            enabled = hasPrompt,
            trailingIconRes = if (hasPrompt) R.drawable.ic_sparkle else null,
            onClick = onGenerateClick
        )
    }
}

@Preview(
    name = "Pixo / Prompt Flow empty",
    showBackground = true,
    widthDp = 390,
    heightDp = 430
)
@Composable
private fun PixoPromptFlowScreenContentEmptyPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary),
            contentAlignment = Alignment.TopCenter
        ) {
            PixoPromptFlowScreenContent(
                promptText = "",
                hasPicture = false,
                onGenerateClick = {}
            )
        }
    }
}

@Preview(
    name = "Pixo / Prompt Flow text only",
    showBackground = true,
    widthDp = 390,
    heightDp = 430
)
@Composable
private fun PixoPromptFlowScreenContentTextOnlyPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary),
            contentAlignment = Alignment.TopCenter
        ) {
            PixoPromptFlowScreenContent(
                promptText = stringResource(id = R.string.prompt_dialog_cartoon),
                hasPicture = true,
                onGenerateClick = {}
            )
        }
    }
}

@Preview(
    name = "Pixo / Prompt Flow text and picture",
    showBackground = true,
    widthDp = 390,
    heightDp = 430
)
@Composable
private fun PixoPromptFlowScreenContentTextPicturePreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary),
            contentAlignment = Alignment.TopCenter
        ) {
            PixoPromptFlowScreenContent(
                promptText = stringResource(id = R.string.prompt_dialog_headshot),
                hasPicture = true,
                onGenerateClick = {}
            )
        }
    }
}