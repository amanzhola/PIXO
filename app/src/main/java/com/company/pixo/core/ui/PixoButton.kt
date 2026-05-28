package com.company.pixo.core.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.company.pixo.R
import com.company.pixo.core.theme.AccentBlack
import com.company.pixo.core.theme.AccentPrimary
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.BackgroundTertiary
import com.company.pixo.core.theme.LabelQuintuple
import com.company.pixo.core.theme.PixoShapes
import com.company.pixo.core.theme.PixoTheme
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.company.pixo.core.theme.AccentWhite
import com.company.pixo.core.theme.ColorActionsGhost
import com.company.pixo.core.theme.LabelQuaternary
import com.company.pixo.core.theme.PhotoSourceButtonBackground
import com.company.pixo.core.theme.PhotoSourceIcon
import com.company.pixo.core.theme.PhotoSourceText
import com.company.pixo.core.theme.SeparatorSecondary
import com.company.pixo.core.theme.SettingsChevron

enum class PixoButtonSize {
    Main,
    Small
}

@Composable
fun PixoButton(
    @StringRes textRes: Int,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    size: PixoButtonSize = PixoButtonSize.Main,
    @DrawableRes trailingIconRes: Int? = null,
    onClick: () -> Unit
) {
    val height = when (size) {
        PixoButtonSize.Main -> dimensionResource(R.dimen._56)
        PixoButtonSize.Small -> dimensionResource(R.dimen._40)
    }

    val horizontalPadding = when (size) {
        PixoButtonSize.Main -> dimensionResource(R.dimen._12)
        PixoButtonSize.Small -> dimensionResource(R.dimen._12)
    }

    val verticalPadding = when (size) {
        PixoButtonSize.Main -> dimensionResource(R.dimen._8)
        PixoButtonSize.Small -> dimensionResource(R.dimen._8)
    }

    Button(
        modifier = modifier.height(height),
        enabled = enabled,
        shape = PixoShapes.button,
        contentPadding = PaddingValues(
            horizontal = horizontalPadding,
            vertical = verticalPadding
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = AccentPrimary,
            contentColor = AccentBlack,
            disabledContainerColor = BackgroundTertiary,
            disabledContentColor = LabelQuintuple
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = dimensionResource(R.dimen._0),
            pressedElevation = dimensionResource(R.dimen._0),
            disabledElevation = dimensionResource(R.dimen._0),
            focusedElevation = dimensionResource(R.dimen._0),
            hoveredElevation = dimensionResource(R.dimen._0)
        ),
        onClick = onClick
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                dimensionResource(R.dimen._0)
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(textRes),
                style = MaterialTheme.typography.labelLarge
            )

            if (trailingIconRes != null) {
                Icon(
                    painter = painterResource(trailingIconRes),
                    contentDescription = null,
                    modifier = Modifier
                        .width(dimensionResource(R.dimen._20))
                        .height(dimensionResource(R.dimen._20))
                )
            }
        }
    }
}

@Preview(name = "PixoButton / Continue active", showBackground = true)
@Composable
private fun PixoButtonContinueActivePreview() {
    PixoButtonPreviewContainer {
        PixoButton(
            textRes = R.string.common_continue,
            modifier = Modifier.width(dimensionResource(R.dimen._358)),
            enabled = true,
            onClick = {}
        )
    }
}

@Preview(name = "PixoButton / Generate inactive", showBackground = true)
@Composable
private fun PixoButtonGenerateInactivePreview() {
    PixoButtonPreviewContainer {
        PixoButton(
            textRes = R.string.common_generate,
            modifier = Modifier.width(dimensionResource(R.dimen._358)),
            enabled = false,
            onClick = {}
        )
    }
}

@Preview(name = "PixoButton / Generate 2 active", showBackground = true)
@Composable
private fun PixoButtonGenerateTwoActivePreview() {
    PixoButtonPreviewContainer {
        PixoButton(
            textRes = R.string.common_generate_two,
            modifier = Modifier.width(dimensionResource(R.dimen._358)),
            enabled = true,
            trailingIconRes = R.drawable.ic_sparkle,
            onClick = {}
        )
    }
}

@Preview(name = "PixoButton / Go to history active", showBackground = true)
@Composable
private fun PixoButtonGoToHistoryActivePreview() {
    PixoButtonPreviewContainer {
        PixoButton(
            textRes = R.string.common_go_to_history,
            modifier = Modifier.width(dimensionResource(R.dimen._358)),
            enabled = true,
            onClick = {}
        )
    }
}

@Preview(name = "PixoButton / Small active", showBackground = true)
@Composable
private fun PixoButtonSmallActivePreview() {
    PixoButtonPreviewContainer {
        PixoButton(
            textRes = R.string.common_confirm,
            modifier = Modifier.width(dimensionResource(R.dimen._294)),
            size = PixoButtonSize.Small,
            enabled = true,
            onClick = {}
        )
    }
}

@Preview(name = "PixoButton / Small inactive", showBackground = true)
@Composable
private fun PixoButtonSmallInactivePreview() {
    PixoButtonPreviewContainer {
        PixoButton(
            textRes = R.string.common_confirm,
            modifier = Modifier.width(dimensionResource(R.dimen._294)),
            size = PixoButtonSize.Small,
            enabled = false,
            onClick = {}
        )
    }
}

@Composable
private fun PixoButtonPreviewContainer(
    content: @Composable () -> Unit
) {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(R.dimen._390))
                .background(BackgroundPrimary),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}

@Composable
fun PixoChoosePhotoContent(
    modifier: Modifier = Modifier,
    onCameraClick: () -> Unit,
    onPhotoLibraryClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(BackgroundPrimary)
            .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
            .padding(
                top = dimensionResource(R.dimen._16),
                bottom = dimensionResource(R.dimen._16)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen._12)
        )
    ) {
        Text(
            text = stringResource(R.string.photo_picker_title),
            color = AccentWhite,
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            maxLines = 1
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._8))
        ) {
            PixoPhotoSourceButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                iconRes = R.drawable.ic_camera,
                textRes = R.string.cd_camera,
                contentDescriptionRes = R.string.cd_camera,
                onClick = onCameraClick
            )

            PixoPhotoSourceButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                iconRes = R.drawable.ic_library,
                textRes = R.string.common_photo_library,
                contentDescriptionRes = R.string.common_photo_library,
                onClick = onPhotoLibraryClick
            )
        }
    }
}

@Composable
private fun PixoPhotoSourceButton(
    @DrawableRes iconRes: Int,
    @StringRes textRes: Int,
    @StringRes contentDescriptionRes: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        shape = RoundedCornerShape(dimensionResource(R.dimen._16)),
        color = ColorActionsGhost
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size(
                    dimensionResource(R.dimen._32)
                ),
                painter = painterResource(iconRes),
                contentDescription = stringResource(contentDescriptionRes),
                tint = PhotoSourceIcon
            )

            Spacer(
                modifier = Modifier.height(
                    dimensionResource(R.dimen._8)
                )
            )

            Text(
                text = stringResource(textRes),
                color = PhotoSourceText,
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
    }
}

@Preview(
    name = "Pixo / Choose Photo",
    showBackground = true,
    widthDp = 390,
    heightDp = 405
)
@Composable
private fun PixoChoosePhotoContentPreview() {
    PixoTheme {
        PixoChoosePhotoContent(
            onCameraClick = {},
            onPhotoLibraryClick = {}
        )
    }
}

@Composable
fun PixoResultActionButtons(
    modifier: Modifier = Modifier,
    onSaveClick: () -> Unit,
    onShareClick: () -> Unit,
    onRegenerateClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen._124))
            .background(BackgroundPrimary),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen._12)
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                dimensionResource(R.dimen._12)
            )
        ) {
            PixoResultActionButton(
                modifier = Modifier.weight(1f),
                iconRes = R.drawable.ic_save,
                textRes = R.string.common_save,
                contentDescriptionRes = R.string.common_save,
                onClick = onSaveClick
            )

            PixoResultActionButton(
                modifier = Modifier.weight(1f),
                iconRes = R.drawable.ic_share,
                textRes = R.string.common_share,
                contentDescriptionRes = R.string.common_share,
                onClick = onShareClick
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(
                dimensionResource(R.dimen._12)
            )
        ) {
            PixoResultActionButton(
                modifier = Modifier.weight(1f),
                iconRes = R.drawable.ic_regenerate,
                textRes = R.string.cd_regenerate,
                contentDescriptionRes = R.string.cd_regenerate,
                onClick = onRegenerateClick
            )

            PixoResultActionButton(
                modifier = Modifier.weight(1f),
                iconRes = R.drawable.ic_trash,
                textRes = R.string.common_delete,
                contentDescriptionRes = R.string.common_delete,
                onClick = onDeleteClick
            )
        }
    }
}

@Composable
private fun PixoResultActionButton(
    @DrawableRes iconRes: Int,
    @StringRes textRes: Int,
    @StringRes contentDescriptionRes: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen._56))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        shape = RoundedCornerShape(
            dimensionResource(R.dimen._12)
        ),
        color = BackgroundTertiary
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = dimensionResource(R.dimen._12),
                    vertical = dimensionResource(R.dimen._8)
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .width(dimensionResource(R.dimen._16))
                    .height(dimensionResource(R.dimen._18)),
                painter = painterResource(iconRes),
                contentDescription = stringResource(contentDescriptionRes),
                tint = AccentWhite
            )

            Spacer(
                modifier = Modifier.width(
                    dimensionResource(R.dimen._4)
                )
            )

            Text(
                text = stringResource(textRes),
                color = AccentWhite,
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
    }
}

@Preview(
    name = "Pixo / Result Action Buttons",
    showBackground = true,
    widthDp = 390,
    heightDp = 160
)
@Composable
private fun PixoResultActionButtonsPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary),
            contentAlignment = Alignment.Center
        ) {
            PixoResultActionButtons(
                onSaveClick = {},
                onShareClick = {},
                onRegenerateClick = {},
                onDeleteClick = {}
            )
        }
    }
}

@Composable
fun PixoChoosePhotoHorizontalContent(
    modifier: Modifier = Modifier,
    onCameraClick: () -> Unit,
    onPhotoLibraryClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(BackgroundPrimary)
            .padding(horizontal = dimensionResource(R.dimen._16)),
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen._8)
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        PixoHorizontalPhotoSourceButton(
            modifier = Modifier.weight(1f),
            iconRes = R.drawable.ic_camera,
            textRes = R.string.cd_camera,
            contentDescriptionRes = R.string.cd_camera,
            onClick = onCameraClick
        )

        PixoHorizontalPhotoSourceButton(
            modifier = Modifier.weight(1f),
            iconRes = R.drawable.ic_library,
            textRes = R.string.common_photo_library,
            contentDescriptionRes = R.string.common_photo_library,
            onClick = onPhotoLibraryClick
        )
    }
}

@Composable
private fun PixoHorizontalPhotoSourceButton(
    @DrawableRes iconRes: Int,
    @StringRes textRes: Int,
    @StringRes contentDescriptionRes: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .height(dimensionResource(R.dimen._115))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        shape = RoundedCornerShape(
            dimensionResource(R.dimen._16)
        ),
        color = ColorActionsGhost
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size(
                    dimensionResource(R.dimen._32)
                ),
                painter = painterResource(iconRes),
                contentDescription = stringResource(contentDescriptionRes),
                tint = PhotoSourceIcon
            )

            Spacer(
                modifier = Modifier.height(
                    dimensionResource(R.dimen._8)
                )
            )

            Text(
                text = stringResource(textRes),
                color = PhotoSourceText,
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
    }
}

@Preview(
    name = "Pixo / Choose Photo Horizontal",
    showBackground = true,
    widthDp = 390,
    heightDp = 147
)
@Composable
private fun PixoChoosePhotoHorizontalContentPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary),
            contentAlignment = Alignment.Center
        ) {
            PixoChoosePhotoHorizontalContent(
                onCameraClick = {},
                onPhotoLibraryClick = {}
            )
        }
    }
}

enum class PixoAddPhotoBorderStyle {
    Accent,
    Gradient
}

@Composable
fun PixoPhotoGenerateActions(
    modifier: Modifier = Modifier,
    generateEnabled: Boolean = false,
    addPhotoBorderStyle: PixoAddPhotoBorderStyle = PixoAddPhotoBorderStyle.Accent,
    onAddPhotoClick: () -> Unit,
    onGenerateClick: () -> Unit
) {
    Row(
        modifier = modifier
            .width(dimensionResource(R.dimen._390))
            .height(dimensionResource(R.dimen._56))
            .background(BackgroundPrimary)
            .windowInsetsPadding(
                WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal)
            )
            .padding(horizontal = dimensionResource(R.dimen._16)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._8)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        PixoAddPhotoOutlineButton(
            modifier = Modifier
                .width(dimensionResource(R.dimen._175))
                .height(dimensionResource(R.dimen._56)),
            borderStyle = addPhotoBorderStyle,
            onClick = onAddPhotoClick
        )

        PixoButton(
            textRes = R.string.common_generate,
            modifier = Modifier.width(dimensionResource(R.dimen._175)),
            enabled = generateEnabled,
            onClick = onGenerateClick
        )
    }
}

@Composable
private fun PixoAddPhotoOutlineButton(
    modifier: Modifier = Modifier,
    borderStyle: PixoAddPhotoBorderStyle = PixoAddPhotoBorderStyle.Accent,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(dimensionResource(R.dimen._12))
    val borderWidth = dimensionResource(R.dimen._1)

    val borderModifier = when (borderStyle) {
        PixoAddPhotoBorderStyle.Accent -> Modifier.border(
            width = borderWidth,
            color = AccentPrimary,
            shape = shape
        )

        PixoAddPhotoBorderStyle.Gradient -> Modifier.border(
            width = borderWidth,
            brush = Brush.horizontalGradient(
                colors = listOf(
                    Color(0xFFE69A43),
                    Color(0xFFB95594),
                    Color(0xFF6747E8)
                )
            ),
            shape = shape
        )
    }

    Surface(
        modifier = modifier
            .then(borderModifier)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        shape = shape,
        color = BackgroundPrimary
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = dimensionResource(R.dimen._12),
                    vertical = dimensionResource(R.dimen._8)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.photo_add_your_photo),
                color = AccentWhite,
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
    }
}

@Preview(
    name = "Pixo / Photo Generate Actions",
    showBackground = true,
    widthDp = 390,
    heightDp = 56
)
@Composable
private fun PixoPhotoGenerateActionsPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary),
            contentAlignment = Alignment.Center
        ) {
            PixoPhotoGenerateActions(
                generateEnabled = false,
                onAddPhotoClick = {},
                onGenerateClick = {}
            )
        }
    }
}

@Preview(
    name = "Pixo / Photo Generate Actions / Gradient Border",
    showBackground = true,
    widthDp = 390,
    heightDp = 56
)
@Composable
private fun PixoPhotoGenerateActionsGradientBorderPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary),
            contentAlignment = Alignment.Center
        ) {
            PixoPhotoGenerateActions(
                generateEnabled = false,
                addPhotoBorderStyle = PixoAddPhotoBorderStyle.Gradient,
                onAddPhotoClick = {},
                onGenerateClick = {}
            )
        }
    }
}

enum class PixoPhotoMode {
    White,
    Transparent
}

@Composable
fun PixoPhotoModeSelector(
    selectedMode: PixoPhotoMode?,
    modifier: Modifier = Modifier,
    onModeClick: (PixoPhotoMode) -> Unit
) {
    Row(
        modifier = modifier
            .width(dimensionResource(R.dimen._390))
            .background(BackgroundPrimary)
            .padding(horizontal = dimensionResource(R.dimen._16)),
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen._4)
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        PixoPhotoModeButton(
            textRes = R.string.photo_mode_white,
            selected = selectedMode == PixoPhotoMode.White,
            modifier = Modifier
                .width(179.dp)
                .height(41.dp),
            onClick = { onModeClick(PixoPhotoMode.White) }
        )

        PixoPhotoModeButton(
            textRes = R.string.photo_mode_transparent,
            selected = selectedMode == PixoPhotoMode.Transparent,
            modifier = Modifier
                .width(179.dp)
                .height(41.dp),
            onClick = { onModeClick(PixoPhotoMode.Transparent) }
        )
    }
}

@Composable
private fun PixoPhotoModeButton(
    @StringRes textRes: Int,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(
        dimensionResource(R.dimen._8)
    )

    Surface(
        modifier = modifier
            .then(
                if (selected) {
                    Modifier.border(
                        width = 1.5.dp,
                        color = AccentPrimary,
                        shape = shape
                    )
                } else {
                    Modifier
                }
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        shape = shape,
        color = if (selected) {
            PhotoSourceButtonBackground
        } else {
            BackgroundPrimary
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen._8)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(textRes),
                color = AccentWhite,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
    }
}

@Preview(
    name = "Pixo / Photo Mode Selector inactive",
    showBackground = true,
    widthDp = 390,
    heightDp = 41
)
@Composable
private fun PixoPhotoModeSelectorInactivePreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary),
            contentAlignment = Alignment.Center
        ) {
            PixoPhotoModeSelector(
                selectedMode = null,
                onModeClick = {}
            )
        }
    }
}

@Preview(
    name = "Pixo / Photo Mode Selector white active",
    showBackground = true,
    widthDp = 390,
    heightDp = 41
)
@Composable
private fun PixoPhotoModeSelectorWhiteActivePreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary),
            contentAlignment = Alignment.Center
        ) {
            PixoPhotoModeSelector(
                selectedMode = PixoPhotoMode.White,
                onModeClick = {}
            )
        }
    }
}

@Composable
fun PixoSettingsButtons(
    modifier: Modifier = Modifier,
    onContactUsClick: () -> Unit,
    onShareWithFriendsClick: () -> Unit,
    onPrivacyPolicyClick: () -> Unit,
    onTermsOfUseClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._28))
    ) {
        PixoSettingsButtonsGroup {
            PixoSettingsButton(
                iconRes = R.drawable.ic_envelope,
                textRes = R.string.settings_contact_us,
                onClick = onContactUsClick
            )

            PixoSettingsButton(
                iconRes = R.drawable.ic_arrowshape,
                textRes = R.string.settings_share_with_friends,
                onClick = onShareWithFriendsClick
            )
        }

        PixoSettingsButtonsGroup {
            PixoSettingsButton(
                iconRes = R.drawable.ic_lock_shield,
                textRes = R.string.common_privacy_policy,
                onClick = onPrivacyPolicyClick
            )

            PixoSettingsButton(
                iconRes = R.drawable.ic_doc_text,
                textRes = R.string.common_terms_of_use,
                onClick = onTermsOfUseClick
            )
        }
    }
}

@Composable
private fun PixoSettingsButtonsGroup(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._8)),
        content = content
    )
}

@Composable
private fun PixoSettingsButton(
    @DrawableRes iconRes: Int,
    @StringRes textRes: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen._44))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        shape = RoundedCornerShape(dimensionResource(R.dimen._8)),
        color = BackgroundTertiary
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = dimensionResource(R.dimen._10),
                    vertical = dimensionResource(R.dimen._10)
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._8)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(dimensionResource(R.dimen._24)),
                    painter = painterResource(iconRes),
                    contentDescription = null,
                    tint = Color.Unspecified
                )

                Text(
                    text = stringResource(textRes),
                    color = AccentWhite,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1
                )
            }

            Icon(
                modifier = Modifier
                    .width(7.69.dp)
                    .height(13.36.dp),
                painter = painterResource(R.drawable.ic_setting_chevron),
                contentDescription = null,
                tint = SettingsChevron
            )
        }
    }
}

@Preview(
    name = "Pixo / Settings Buttons",
    showBackground = true,
    widthDp = 390,
    heightDp = 220
)
@Composable
private fun PixoSettingsButtonsPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary),
            contentAlignment = Alignment.Center
        ) {
            PixoSettingsButtons(
                onContactUsClick = {},
                onShareWithFriendsClick = {},
                onPrivacyPolicyClick = {},
                onTermsOfUseClick = {}
            )
        }
    }
}

@Composable
private fun PixoHorizontalChipSelector(
    @StringRes titleRes: Int,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    val startPadding = dimensionResource(R.dimen._16)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen._69)),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen._12)
        )
    ) {
        Text(
            modifier = Modifier
                .padding(start = startPadding)
                .width(dimensionResource(R.dimen._358))
                .height(dimensionResource(R.dimen._25)),
            text = stringResource(titleRes),
            color = AccentWhite,
            style = MaterialTheme.typography.titleMedium
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clipToBounds()
        ) {
            Row(
                modifier = Modifier
//                    .fillMaxWidth()
                    .horizontalScroll(
                        state = rememberScrollState(),
                        reverseScrolling = false
                    )
                    .padding(start = startPadding, end = 0.dp),
                horizontalArrangement = Arrangement.spacedBy(
                    dimensionResource(R.dimen._8)
                ),
                verticalAlignment = Alignment.CenterVertically,
                content = content
            )
        }
    }
}

@Composable
private fun PixoSelectorChip(
    @StringRes textRes: Int,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .width(dimensionResource(R.dimen._110))
            .height(dimensionResource(R.dimen._32))
            .then(
                if (!selected) {
                    Modifier.border(
                        width = dimensionResource(R.dimen._1),
                        color = SeparatorSecondary,
                        shape = RoundedCornerShape(
                            dimensionResource(R.dimen._12)
                        )
                    )
                } else {
                    Modifier
                }
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        shape = RoundedCornerShape(
            dimensionResource(R.dimen._12)
        ),
        color = if (selected) AccentPrimary else BackgroundPrimary
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(textRes),
                color = if (selected) AccentBlack else LabelQuaternary,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
    }
}

enum class PixoMakeupStyle {
    NaturalGlow,
    GentleGlam,
    RichGlam,
    EveningLook
}

fun PixoMakeupStyle.toServerTitle(): String {
    return when (this) {
        PixoMakeupStyle.NaturalGlow -> "Natural Glow"
        PixoMakeupStyle.GentleGlam -> "Gentle Glam"
        PixoMakeupStyle.RichGlam -> "Rich Glam"
        PixoMakeupStyle.EveningLook -> "Evening Look"
    }
}

@Composable
fun PixoMakeupStyleSelector(
    selectedStyle: PixoMakeupStyle,
    modifier: Modifier = Modifier,
    onStyleClick: (PixoMakeupStyle) -> Unit
) {
    PixoHorizontalChipSelector(
        modifier = modifier,
        titleRes = R.string.glam_makeup_style_title
    ) {
        PixoSelectorChip(
            textRes = R.string.glam_makeup_style_natural_glow,
            selected = selectedStyle == PixoMakeupStyle.NaturalGlow,
            onClick = { onStyleClick(PixoMakeupStyle.NaturalGlow) }
        )

        PixoSelectorChip(
            textRes = R.string.glam_makeup_style_gentle_glam,
            selected = selectedStyle == PixoMakeupStyle.GentleGlam,
            onClick = { onStyleClick(PixoMakeupStyle.GentleGlam) }
        )

        PixoSelectorChip(
            textRes = R.string.glam_makeup_style_rich_glam,
            selected = selectedStyle == PixoMakeupStyle.RichGlam,
            onClick = { onStyleClick(PixoMakeupStyle.RichGlam) }
        )

        PixoSelectorChip(
            textRes = R.string.glam_makeup_style_evening_look,
            selected = selectedStyle == PixoMakeupStyle.EveningLook,
            onClick = { onStyleClick(PixoMakeupStyle.EveningLook) }
        )
    }
}

enum class PixoProcessingOption {
    HdEnhance,
    PortraitRetouch,
    LightFix,
    ColorBoost
}

fun PixoProcessingOption.toServerName(): String {
    return when (this) {
        PixoProcessingOption.HdEnhance -> "HD Enhance"
        PixoProcessingOption.PortraitRetouch -> "Portrait Retouch"
        PixoProcessingOption.LightFix -> "Light Fix"
        PixoProcessingOption.ColorBoost -> "Color Boost"
    }
}

fun PixoProcessingOption.firstBulletRes(): Int {
    return when (this) {
        PixoProcessingOption.HdEnhance -> R.string.ai_enhancer_hd_quality
        PixoProcessingOption.PortraitRetouch -> R.string.ai_enhancer_portrait_quality
        PixoProcessingOption.LightFix -> R.string.ai_enhancer_light_quality
        PixoProcessingOption.ColorBoost -> R.string.ai_enhancer_color_quality
    }
}

fun PixoProcessingOption.secondBulletRes(): Int {
    return when (this) {
        PixoProcessingOption.HdEnhance -> R.string.ai_enhancer_hd_natural
        PixoProcessingOption.PortraitRetouch -> R.string.ai_enhancer_portrait_natural
        PixoProcessingOption.LightFix -> R.string.ai_enhancer_light_natural
        PixoProcessingOption.ColorBoost -> R.string.ai_enhancer_color_natural
    }
}

@Composable
fun PixoProcessingOptionsSelector(
    selectedOption: PixoProcessingOption,
    modifier: Modifier = Modifier,
    onOptionClick: (PixoProcessingOption) -> Unit
) {
    PixoHorizontalChipSelector(
        modifier = modifier,
        titleRes = R.string.ai_enhancer_processing_options_title
    ) {
        PixoSelectorChip(
            textRes = R.string.ai_enhancer_option_hd_enhance,
            selected = selectedOption == PixoProcessingOption.HdEnhance,
            onClick = { onOptionClick(PixoProcessingOption.HdEnhance) }
        )

        PixoSelectorChip(
            textRes = R.string.ai_enhancer_option_portrait_retouch,
            selected = selectedOption == PixoProcessingOption.PortraitRetouch,
            onClick = { onOptionClick(PixoProcessingOption.PortraitRetouch) }
        )

        PixoSelectorChip(
            textRes = R.string.ai_enhancer_option_light_fix,
            selected = selectedOption == PixoProcessingOption.LightFix,
            onClick = { onOptionClick(PixoProcessingOption.LightFix) }
        )

        PixoSelectorChip(
            textRes = R.string.ai_enhancer_option_color_boost,
            selected = selectedOption == PixoProcessingOption.ColorBoost,
            onClick = { onOptionClick(PixoProcessingOption.ColorBoost) }
        )
    }
}

@Preview(name = "PixoButton / Processing Options Selector", showBackground = true)
@Composable
private fun PixoProcessingOptionsSelectorPreview() {
    PixoButtonPreviewContainer {
        PixoProcessingOptionsSelector(
            modifier = Modifier.fillMaxWidth(),
            selectedOption = PixoProcessingOption.HdEnhance,
            onOptionClick = {}
        )
    }
}

@Preview(name = "PixoButton / Makeup Style Selector", showBackground = true)
@Composable
private fun PixoMakeupStyleSelectorPreview() {
    PixoButtonPreviewContainer {
        PixoMakeupStyleSelector(
            modifier = Modifier.fillMaxWidth(),
            selectedStyle = PixoMakeupStyle.NaturalGlow,
            onStyleClick = {}
        )
    }
}