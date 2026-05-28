package com.company.pixo.core.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.company.pixo.R
import com.company.pixo.core.theme.AccentPrimary
import com.company.pixo.core.theme.AccentWhite
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.LabelPrimary
import com.company.pixo.core.theme.LabelQuaternary
import com.company.pixo.core.theme.PhotoSourceButtonBackground
import com.company.pixo.core.theme.PixoTheme
import com.company.pixo.core.theme.TextIconSoft200
import com.company.pixo.core.theme.TextIconSoft700
import com.company.pixo.core.theme.TextIconWeak50
import kotlin.math.roundToInt

enum class PixoImageSide {
    Left,
    Right
}

@Composable
fun PixoCard(
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(
        dimensionResource(R.dimen._24)
    ),
    background: Color = Color.Transparent,
    contentPadding: PaddingValues = PaddingValues(),
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier
            .clip(shape)
            .then(
                if (onClick != null) {
                    Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onClick
                    )
                } else {
                    Modifier
                }
            ),
        shape = shape,
        color = background,
        tonalElevation = dimensionResource(R.dimen._0),
        shadowElevation = dimensionResource(R.dimen._0)
    ) {
        Box(
            modifier = Modifier.padding(contentPadding)
        ) {
            content()
        }
    }
}

@Composable
fun PixoImageLabCard(
    @DrawableRes imageRes: Int,
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int = R.string.home_card_image_lab_title,
    @StringRes subtitleRes: Int = R.string.home_card_image_lab_subtitle,
    @StringRes actionRes: Int = R.string.common_try_it,
    onClick: () -> Unit = {},
    onActionClick: () -> Unit = onClick
) {
    PixoCard(
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen._204)),
        shape = RoundedCornerShape(
            topStart = dimensionResource(R.dimen._24),
            bottomStart = dimensionResource(R.dimen._24),
            topEnd = dimensionResource(R.dimen._0),
            bottomEnd = dimensionResource(R.dimen._0)
        ),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(imageRes),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colorStops = arrayOf(
                                0f to Color.Transparent,
                                0.7062f to BackgroundPrimary.copy(alpha = 0.528f)
                            )
                        )
                    )
            )

            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(
                        start = dimensionResource(R.dimen._24),
                        end = dimensionResource(R.dimen._24),
                        bottom = dimensionResource(R.dimen._16)
                    )
                    .width(dimensionResource(R.dimen._310))
                    .height(dimensionResource(R.dimen._47)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.width(dimensionResource(R.dimen._229))
                ) {
                    Text(
                        text = stringResource(titleRes),
                        color = TextIconWeak50,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = stringResource(subtitleRes),
                        color = TextIconSoft200,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                PixoTryItButton(
                    text = stringResource(actionRes),
                    onClick = onActionClick
                )
            }
        }
    }
}

@Composable
fun PixoGlamMakeupCard(
    @DrawableRes imageRes: Int,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    PixoCard(
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen._444)),
        shape = RoundedCornerShape(
            dimensionResource(R.dimen._16)
        ),
        onClick = onClick
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun PixoTemplatePreviewCard(
    @StringRes titleRes: Int,
    @DrawableRes imageRes: Int,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    Column(
        modifier = modifier.width(dimensionResource(R.dimen._175)),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen._8)
        )
    ) {
        Text(
            modifier = Modifier
                .width(dimensionResource(R.dimen._175))
                .height(dimensionResource(R.dimen._22)),
            text = stringResource(titleRes),
            color = AccentWhite,
            style = MaterialTheme.typography.titleSmall
        )

        PixoCard(
            modifier = Modifier
                .width(dimensionResource(R.dimen._175))
                .height(dimensionResource(R.dimen._311)),
            shape = RoundedCornerShape(
                dimensionResource(R.dimen._16)
            ),
            onClick = onClick
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(imageRes),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

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
            }
        }
    }
}

@Composable
private fun PixoTryItButton(
    text: String,
    onClick: () -> Unit
) {
    val compactTextStyle = when {
        text.length > 12 -> MaterialTheme.typography.labelSmall.copy(
            fontSize = 8.sp,
            lineHeight = 12.sp
        )

        text.length > 7 -> MaterialTheme.typography.labelMedium.copy(
            fontSize = 9.sp,
            lineHeight = 16.sp
        )

        else -> MaterialTheme.typography.bodyLarge
    }

    Surface(
        modifier = Modifier
            .height(dimensionResource(R.dimen._37))
            .wrapContentWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        shape = RoundedCornerShape(dimensionResource(R.dimen._150)),
        color = AccentWhite
    ) {
        Box(
            modifier = Modifier.padding(
                horizontal = dimensionResource(R.dimen._14),
                vertical = dimensionResource(R.dimen._8)
            ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = BackgroundPrimary,
                style = compactTextStyle,
                maxLines = 1,
                softWrap = false
            )
        }
    }
}

@Preview(name = "PixoCard / Image Lab", showBackground = true)
@Composable
private fun PixoImageLabCardPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(R.dimen._390))
                .background(BackgroundPrimary)
                .padding(dimensionResource(R.dimen._16)),
            contentAlignment = Alignment.Center
        ) {
            PixoImageLabCard(
                imageRes = R.drawable.template_preview_image_lab
            )
        }
    }
}

@Preview(name = "PixoCard / Glam Makeup", showBackground = true)
@Composable
private fun PixoGlamMakeupCardPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(R.dimen._390))
                .background(BackgroundPrimary)
                .padding(dimensionResource(R.dimen._16)),
            contentAlignment = Alignment.Center
        ) {
            PixoGlamMakeupCard(
                imageRes = R.drawable.tools_glam
            )
        }
    }
}

@Preview(name = "PixoCard / Template Preview", showBackground = true)
@Composable
private fun PixoTemplatePreviewCardPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(R.dimen._390))
                .background(BackgroundPrimary)
                .padding(dimensionResource(R.dimen._16)),
            contentAlignment = Alignment.Center
        ) {
            PixoTemplatePreviewCard(
                titleRes = R.string.template_cherry,
                imageRes = R.drawable.template_cherry
            )
        }
    }
}

@Composable
private fun PixoSplitImage(
    @DrawableRes imageRes: Int,
    imageSide: PixoImageSide,
    modifier: Modifier = Modifier
) {
    val imageBitmap = ImageBitmap.imageResource(id = imageRes)

    Canvas(
        modifier = modifier
    ) {
        drawSplitImage(
            imageBitmap = imageBitmap,
            imageSide = imageSide
        )
    }
}

private fun DrawScope.drawSplitImage(
    imageBitmap: ImageBitmap,
    imageSide: PixoImageSide
) {
    val sourceWidth = imageBitmap.width / 2
    val sourceHeight = imageBitmap.height

    val sourceOffsetX = when (imageSide) {
        PixoImageSide.Left -> 0
        PixoImageSide.Right -> sourceWidth
    }

    drawImage(
        image = imageBitmap,
        srcOffset = IntOffset(
            x = sourceOffsetX,
            y = 0
        ),
        srcSize = IntSize(
            width = sourceWidth,
            height = sourceHeight
        ),
        dstOffset = IntOffset(
            x = 0,
            y = 0
        ),
        dstSize = IntSize(
            width = size.width.roundToInt(),
            height = size.height.roundToInt()
        )
    )
}

@Composable
fun PixoTemplateDoublePreviewCard(
    @StringRes titleRes: Int,
    @DrawableRes imageRes: Int,
    imageSide: PixoImageSide,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    Column(
        modifier = modifier.width(dimensionResource(R.dimen._175)),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen._8)
        )
    ) {
        Text(
            modifier = Modifier
                .width(dimensionResource(R.dimen._175))
                .height(dimensionResource(R.dimen._22)),
            text = stringResource(titleRes),
            color = AccentWhite,
            style = MaterialTheme.typography.titleSmall
        )

        PixoCard(
            modifier = Modifier
                .width(dimensionResource(R.dimen._175))
                .height(dimensionResource(R.dimen._311)),
            shape = RoundedCornerShape(
                dimensionResource(R.dimen._16)
            ),
            onClick = onClick
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                PixoSplitImage(
                    imageRes = imageRes,
                    imageSide = imageSide,
                    modifier = Modifier.fillMaxSize()
                )

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
            }
        }
    }
}

@Composable
fun PixoDoublePictureCard(
    @DrawableRes imageRes: Int,
    modifier: Modifier = Modifier,
    imageSide: PixoImageSide = PixoImageSide.Left,
    onClick: (() -> Unit)? = null
) {
    PixoCard(
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen._607)),
        shape = RoundedCornerShape(
            dimensionResource(R.dimen._16)
        ),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            PixoSplitImage(
                imageRes = imageRes,
                imageSide = imageSide,
                modifier = Modifier.fillMaxSize()
            )

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
        }
    }
}

@Preview(name = "PixoCard / Gloria Model Left", showBackground = true)
@Composable
private fun PixoTemplateGloriaModelLeftPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(R.dimen._390))
                .background(BackgroundPrimary)
                .padding(dimensionResource(R.dimen._16)),
            contentAlignment = Alignment.Center
        ) {
            PixoTemplateDoublePreviewCard(
                titleRes = R.string.template_gloria_model,
                imageRes = R.drawable.template_doublepicture,
                imageSide = PixoImageSide.Left
            )
        }
    }
}

@Preview(name = "PixoCard / Gloria Model Right", showBackground = true)
@Composable
private fun PixoTemplateGloriaModelRightPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(R.dimen._390))
                .background(BackgroundPrimary)
                .padding(dimensionResource(R.dimen._16)),
            contentAlignment = Alignment.Center
        ) {
            PixoTemplateDoublePreviewCard(
                titleRes = R.string.template_gloria_model,
                imageRes = R.drawable.template_doublepicture,
                imageSide = PixoImageSide.Right
            )
        }
    }
}

@Preview(name = "PixoCard / Double Picture Large Left", showBackground = true)
@Composable
private fun PixoDoublePictureCardLeftPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(R.dimen._390))
                .background(BackgroundPrimary)
                .padding(dimensionResource(R.dimen._16)),
            contentAlignment = Alignment.Center
        ) {
            PixoDoublePictureCard(
                imageRes = R.drawable.template_doublepicture,
                imageSide = PixoImageSide.Left
            )
        }
    }
}

@Composable
fun PixoPaywallCardsBackground(
    modifier: Modifier = Modifier,
    @DrawableRes imageRes: Int = R.drawable.paywall_cards
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(PIXO_PAYWALL_BACKGROUND_HEIGHT_FRACTION)
            .background(BackgroundPrimary)
            .clipToBounds()
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    rotationZ = PIXO_PAYWALL_CARDS_ROTATION
                    scaleX = PIXO_PAYWALL_CARDS_SCALE
                    scaleY = PIXO_PAYWALL_CARDS_SCALE
                    translationY = PIXO_PAYWALL_CARDS_TRANSLATION_Y
                    transformOrigin = TransformOrigin.Center
                },
            contentScale = ContentScale.FillHeight
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        PIXO_PAYWALL_GRADIENT_START to Color.Transparent,
                        PIXO_PAYWALL_GRADIENT_END to BackgroundPrimary
                    )
                )
        )
    }
}

private const val PIXO_PAYWALL_BACKGROUND_HEIGHT_FRACTION = 0.75f
private const val PIXO_PAYWALL_CARDS_ROTATION = -3f
private const val PIXO_PAYWALL_CARDS_SCALE = 1.15f
private const val PIXO_PAYWALL_CARDS_TRANSLATION_Y = -80f
private const val PIXO_PAYWALL_GRADIENT_START = 0f
private const val PIXO_PAYWALL_GRADIENT_END = 0.91f

@Preview(name = "PixoCard / Paywall Cards Background", showBackground = true)
@Composable
private fun PixoPaywallCardsBackgroundPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(R.dimen._390))
                .height(dimensionResource(R.dimen._844))
                .background(BackgroundPrimary)
        ) {
            PixoPaywallCardsBackground()
        }
    }
}

private const val FULLSCREEN_BOTTOM_GRADIENT_HEIGHT_FRACTION = 0.35f

@Composable
fun PixoTokensGhostFace(
    @DrawableRes imageRes: Int,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit = {}
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundPrimary)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(maxHeight * FULLSCREEN_BOTTOM_GRADIENT_HEIGHT_FRACTION)
                .background(
                    Brush.verticalGradient(
                        colorStops = arrayOf(
                            PixoBackgroundConstants.GRADIENT_START to BackgroundPrimary.copy(alpha = PixoBackgroundConstants.TRANSPARENT_ALPHA),
                            PixoBackgroundConstants.GRADIENT_END to BackgroundPrimary
                        )
                    )
                )
        )

        content()
    }
}

@Composable
fun Onb14(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundPrimary)
    ) {
        Image(
            painter = painterResource(id = R.drawable.onb14),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun Onb15(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundPrimary),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.onb15),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._294))
                .height(dimensionResource(id = R.dimen._592))
                .clip(
                    RoundedCornerShape(
                        dimensionResource(id = R.dimen._51)
                    )
                )
        )
    }
}
private const val RATE_WALL_IMAGE_HEIGHT_FRACTION = 0.8f

@Composable
fun RateBackGround(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundPrimary),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.rate),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(RATE_WALL_IMAGE_HEIGHT_FRACTION)
        )
    }
}

private object PixoBackgroundConstants {
    const val GRADIENT_START = 0f
    const val GRADIENT_END = 1f
    const val TRANSPARENT_ALPHA = 0f
}

@Preview(name = "Pixo / Fullscreen Image Background", showBackground = true)
@Composable
private fun PixoTokensGhostFacePreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._844))
                .background(BackgroundPrimary)
        ) {
            PixoTokensGhostFace(
                imageRes = R.drawable.tokens_hostface
            )
        }
    }
}

@Preview(name = "Pixo / Onb14", showBackground = true)
@Composable
private fun Onb14Preview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._844))
                .background(BackgroundPrimary)
        ) {
            Onb14()
        }
    }
}

@Preview(name = "Pixo / Onb15", showBackground = true)
@Composable
private fun Onb15Preview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._844))
                .background(BackgroundPrimary)
        ) {
            Onb15()
        }
    }
}

private const val PREVIEW_API_LEVEL = 35

@Preview(
    name = "Pixo / Rate Wall",
    showBackground = true,
    apiLevel = PREVIEW_API_LEVEL
)
@Composable
private fun RateBackGroundPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .height(dimensionResource(id = R.dimen._844))
                .background(BackgroundPrimary)
        ) {
            RateBackGround()
        }
    }
}

@Composable
fun PixoPromptPicture(
    modifier: Modifier = Modifier,
    imageUri: String?,
    onClick: (() -> Unit)? = null,
    onRemoveClick: (() -> Unit)? = null
) {
    if (!imageUri.isNullOrBlank()) {
        PixoPromptPictureFilled(
            modifier = modifier,
            imageUri = imageUri,
            onClick = onClick,
            onRemoveClick = onRemoveClick
        )
    } else {
        PixoPromptPictureEmpty(
            modifier = modifier,
            onClick = onClick
        )
    }
}

@Composable
private fun PixoPromptPictureFilled(
    modifier: Modifier = Modifier,
    imageUri: String,
    onClick: (() -> Unit)?,
    onRemoveClick: (() -> Unit)?
) {
    Box(
        modifier = modifier
            .width(dimensionResource(id = R.dimen._72))
            .height(dimensionResource(id = R.dimen._72))
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen._16)))
            .background(BackgroundPrimary)
            .then(
                if (onClick != null) {
                    Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onClick
                    )
                } else {
                    Modifier
                }
            )
    ) {
        AsyncImage(
            model = imageUri,
            contentDescription = stringResource(id = R.string.cd_prompt_image),
            contentScale = ContentScale.Crop,
            placeholder = null,
            error = painterResource(id = R.drawable.tools_prompt_image),
            fallback = painterResource(id = R.drawable.tools_prompt_image),
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .width(dimensionResource(id = R.dimen._24))
                .height(dimensionResource(id = R.dimen._24))
                .clip(RoundedCornerShape(dimensionResource(id = R.dimen._100)))
                .background(BackgroundPrimary)
                .then(
                    if (onRemoveClick != null) {
                        Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = onRemoveClick
                        )
                    } else {
                        Modifier
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            PixoRemoveTextIcon()
        }
    }
}

@Composable
private fun PixoPromptPictureEmpty(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)?
) {
    Box(
        modifier = modifier
            .width(dimensionResource(id = R.dimen._72))
            .height(dimensionResource(id = R.dimen._72))
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen._16)))
            .background(PhotoSourceButtonBackground)
            .then(
                if (onClick != null) {
                    Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onClick
                    )
                } else {
                    Modifier
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_prompt),
            contentDescription = stringResource(id = R.string.cd_add_prompt_image),
            colorFilter = ColorFilter.tint(TextIconSoft700),
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._25_64))
                .height(dimensionResource(id = R.dimen._26_81))
        )
    }
}

private object PromptPictureConstants {
    const val REMOVE_TEXT = "+"
    const val REMOVE_TEXT_ROTATION = -45f
    const val REMOVE_TEXT_LINE_HEIGHT = 22
    const val REMOVE_TEXT_LETTER_SPACING = -0.43f
}

@Preview(
    name = "PixoCard / Prompt Picture",
    showBackground = true,
    apiLevel = PREVIEW_API_LEVEL
)
@Composable
private fun PixoPromptPicturePreview() {
    PixoTheme {
        Row(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen._390))
                .background(BackgroundPrimary)
                .padding(dimensionResource(id = R.dimen._16)),
            horizontalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen._4)
            )
        ) {
            PixoPromptPicture(
                imageUri = "preview://prompt_image"
            )

            PixoPromptPicture(
                imageUri = null
            )
        }
    }
}

enum class PixoHistoryCardState {
    Empty,
    Loading,
    Error,
    Image
}

@Composable
fun PixoHistoryCard(
    state: PixoHistoryCardState,
    modifier: Modifier = Modifier,
    @DrawableRes imageRes: Int = R.drawable.history_image,
    onCloseClick: (() -> Unit)? = null,
    onTryAgainClick: (() -> Unit)? = null,
    onClick: (() -> Unit)? = null
) {
    when (state) {
        PixoHistoryCardState.Empty -> PixoHistoryEmptyCard(
            modifier = modifier,
            onClick = onClick
        )

        PixoHistoryCardState.Loading -> PixoHistoryLoadingCard(
            modifier = modifier
        )

        PixoHistoryCardState.Error -> PixoHistoryErrorCard(
            modifier = modifier,
            onCloseClick = onCloseClick,
            onTryAgainClick = onTryAgainClick
        )

        PixoHistoryCardState.Image -> PixoHistoryImageCard(
            modifier = modifier,
            imageRes = imageRes,
            onClick = onClick
        )
    }
}

@Composable
private fun PixoHistoryEmptyCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    PixoHistoryBaseCard(
        modifier = modifier,
        onClick = onClick
    )
}

@Composable
private fun PixoHistoryLoadingCard(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(
        label = "history_loader_transition"
    )

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 900,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "history_loader_rotation"
    )

    PixoHistoryBaseCard(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen._8)
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_history_loader),
                contentDescription = null,
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen._30))
                    .height(dimensionResource(id = R.dimen._30))
                    .rotate(rotation)
            )

            Text(
                text = stringResource(id = R.string.history_wait),
                color = AccentWhite,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
private fun PixoHistoryErrorCard(
    modifier: Modifier = Modifier,
    onCloseClick: (() -> Unit)?,
    onTryAgainClick: (() -> Unit)?
) {
    PixoHistoryBaseCard(
        modifier = modifier,
        paddingValues = PaddingValues(
            horizontal = dimensionResource(id = R.dimen._16),
            vertical = dimensionResource(id = R.dimen._2)
        )
    ) {
        Image(
            painter = painterResource(id = R.drawable.history_close),
            contentDescription = stringResource(id = R.string.common_close),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .width(dimensionResource(id = R.dimen._24))
                .height(dimensionResource(id = R.dimen._24))
                .then(
                    if (onCloseClick != null) {
                        Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = onCloseClick
                        )
                    } else {
                        Modifier
                    }
                )
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .width(dimensionResource(id = R.dimen._141)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen._16)
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.history_error_title),
                    color = LabelPrimary,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall
                )

                Text(
                    text = stringResource(id = R.string.history_error_message),
                    color = LabelQuaternary,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelSmall
                )
            }

            Surface(
                modifier = Modifier
                    .wrapContentWidth()
                    .heightIn(min = dimensionResource(id = R.dimen._32))
                    .then(
                        if (onTryAgainClick != null) {
                            Modifier.clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = onTryAgainClick
                            )
                        } else {
                            Modifier
                        }
                    ),
                shape = RoundedCornerShape(
                    dimensionResource(id = R.dimen._100)
                ),
                color = PhotoSourceButtonBackground,
                border = BorderStroke(
                    width = dimensionResource(id = R.dimen._1),
                    color = AccentPrimary
                )
            ) {
                val isRussian = LocalConfiguration.current.locales[0].language == "ru"

                val tryAgainTextStyle = if (isRussian) {
                    MaterialTheme.typography.labelSmall
                } else {
                    MaterialTheme.typography.labelMedium
                }
                Box(
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(id = R.dimen._8),
                        vertical = dimensionResource(id = R.dimen._4)
                    ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.common_try_again),
                        color = LabelPrimary,
                        style = tryAgainTextStyle,
                        maxLines = 1,
                        softWrap = false
                    )
                }
            }
        }
    }
}

@Composable
private fun PixoHistoryImageCard(
    @DrawableRes imageRes: Int,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    PixoHistoryBaseCard(
        modifier = modifier,
        onClick = onClick,
        paddingValues = PaddingValues()
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = stringResource(id = R.string.cd_history_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Text(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(dimensionResource(id = R.dimen._16))
                .width(dimensionResource(id = R.dimen._141)),
            text = stringResource(id = R.string.tool_ghibli_look),
            color = AccentWhite,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun PixoHistoryBaseCard(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(
        horizontal = dimensionResource(id = R.dimen._16),
        vertical = dimensionResource(id = R.dimen._16)
    ),
    contentAlignment: Alignment = Alignment.TopStart,
    onClick: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(175f / 311f)
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen._16)))
            .background(PhotoSourceButtonBackground)
            .then(
                if (onClick != null) {
                    Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onClick
                    )
                } else {
                    Modifier
                }
            )
            .padding(paddingValues),
        contentAlignment = contentAlignment,
        content = content
    )
}

@Preview(name = "PixoCard / History Empty", showBackground = true)
@Composable
private fun PixoHistoryEmptyCardPreview() {
    PixoTheme {
        PixoHistoryPreviewContainer {
            PixoHistoryCard(state = PixoHistoryCardState.Empty)
        }
    }
}

@Preview(name = "PixoCard / History Loading", showBackground = true)
@Composable
private fun PixoHistoryLoadingCardPreview() {
    PixoTheme {
        PixoHistoryPreviewContainer {
            PixoHistoryCard(state = PixoHistoryCardState.Loading)
        }
    }
}

@Preview(name = "PixoCard / History Error", showBackground = true)
@Composable
private fun PixoHistoryErrorCardPreview() {
    PixoTheme {
        PixoHistoryPreviewContainer {
            PixoHistoryCard(state = PixoHistoryCardState.Error)
        }
    }
}

@Preview(name = "PixoCard / History Image", showBackground = true)
@Composable
private fun PixoHistoryImageCardPreview() {
    PixoTheme {
        PixoHistoryPreviewContainer {
            PixoHistoryCard(state = PixoHistoryCardState.Image)
        }
    }
}

@Composable
private fun PixoHistoryPreviewContainer(
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .width(dimensionResource(id = R.dimen._390))
            .background(BackgroundPrimary)
            .padding(dimensionResource(id = R.dimen._16)),
        contentAlignment = Alignment.Center,
        content = content
    )
}

@Composable
fun PixoTemplateCapturedPicture(
    imageUri: String?,
    @DrawableRes placeholderImageRes: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onRemoveClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(dimensionResource(R.dimen._56))
    ) {
        AsyncImage(
            model = imageUri.takeIf { !it.isNullOrBlank() },
            contentDescription = null,
            placeholder = if (imageUri.isNullOrBlank()) {
                painterResource(placeholderImageRes)
            } else {
                null
            },
            error = painterResource(placeholderImageRes),
            fallback = painterResource(placeholderImageRes),
            modifier = Modifier
                .matchParentSize()
                .clip(RoundedCornerShape(dimensionResource(R.dimen._100)))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onClick
                ),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(dimensionResource(R.dimen._20))
                .background(
                    color = BackgroundPrimary,
                    shape = RoundedCornerShape(dimensionResource(R.dimen._100))
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onRemoveClick
                ),
            contentAlignment = Alignment.Center
        ) {
            PixoRemoveTextIcon(
                fontSize = dimensionResource(R.dimen._14).value.sp
            )
        }
    }
}

@Preview(
    name = "Pixo / Template Captured Picture",
    showBackground = true,
    widthDp = 390,
    heightDp = 96,
    apiLevel = PREVIEW_API_LEVEL
)
@Composable
private fun PixoTemplateCapturedPicturePreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary)
                .padding(start = dimensionResource(id = R.dimen._16)),
            contentAlignment = Alignment.CenterStart
        ) {
            PixoTemplateCapturedPicture(
                imageUri = null,
                placeholderImageRes = R.drawable.template_v2_image,
                onClick = {},
                onRemoveClick = {}
            )
        }
    }
}

@Composable
fun PixoRemoveTextIcon(
    modifier: Modifier = Modifier,
    color: Color = AccentWhite,
    fontSize: TextUnit = dimensionResource(id = R.dimen._17).value.sp
) {
    Text(
        text = PromptPictureConstants.REMOVE_TEXT,
        color = color,
        fontSize = fontSize,
        lineHeight = PromptPictureConstants.REMOVE_TEXT_LINE_HEIGHT.sp,
        letterSpacing = PromptPictureConstants.REMOVE_TEXT_LETTER_SPACING.sp,
        textAlign = TextAlign.Center,
        modifier = modifier.graphicsLayer {
            rotationZ = PromptPictureConstants.REMOVE_TEXT_ROTATION
        }
    )
}

@Composable
fun PixoTemplateGenerateActions(
    imageUri: String?,
    modifier: Modifier = Modifier,
    onPictureClick: () -> Unit,
    onPictureRemoveClick: () -> Unit,
    onGenerateClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(BackgroundPrimary)
            .padding(
                start = dimensionResource(R.dimen._16),
                end = dimensionResource(R.dimen._16)
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PixoTemplateCapturedPicture(
            imageUri = imageUri,
            placeholderImageRes = R.drawable.template_v2_image,
            onClick = onPictureClick,
            onRemoveClick = onPictureRemoveClick
        )

        PixoButton(
            textRes = R.string.common_generate_two,
            modifier = Modifier
                .width(dimensionResource(R.dimen._222))
                .height(dimensionResource(R.dimen._56)),
            enabled = true,
            trailingIconRes = R.drawable.ic_sparkle,
            onClick = onGenerateClick
        )
    }
}

@Preview(
    name = "Pixo / Template Generate Actions",
    showBackground = true,
    widthDp = 390,
    heightDp = 96
)
@Composable
private fun PixoTemplateGenerateActionsPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary),
            contentAlignment = Alignment.Center
        ) {
            PixoTemplateGenerateActions(
                imageUri = null,
                onPictureClick = {},
                onPictureRemoveClick = {},
                onGenerateClick = {}
            )
        }
    }
}