package com.company.pixo.feature.editor

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.company.pixo.R
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.ui.PixoButton
import com.company.pixo.core.ui.PixoPromptTextField
import com.company.pixo.core.ui.PixoSmileIntensitySlider
import com.company.pixo.core.ui.PixoTopBar
import com.company.pixo.core.ui.PixoTopBarVariant

@Composable
fun PixoSmileEditEditScreen(
    imageUri: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onGenerateClick: (Int) -> Unit
) {
    var smileLevel by remember {
        mutableIntStateOf(0)
    }

    var showSmileSample by remember {
        mutableStateOf(false)
    }

    val sampleImageRes = smilePreviewRes(smileLevel)

    if (showSmileSample) {
        sampleImageRes
    } else {
        R.drawable.tools_smile_edit_camera_image
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundPrimary)
    ) {
        PixoTopBar(
            variant = PixoTopBarVariant.Detail,
            titleRes = R.string.tool_smile_edit,
            onBackClick = onBackClick
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = dimensionResource(R.dimen._16)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(
                modifier = Modifier.height(
                    dimensionResource(R.dimen._8)
                )
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(dimensionResource(R.dimen._16)))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {
                            showSmileSample = !showSmileSample
                        }
                    )
            ) {
                if (showSmileSample) {
                    Image(
                        painter = painterResource(sampleImageRes),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    AsyncImage(
                        model = imageUri.takeIf { it.isNotBlank() },
                        contentDescription = null,
                        placeholder = if (imageUri.isBlank()) {
                            painterResource(R.drawable.tools_smile_edit_camera_image)
                        } else {
                            null
                        },
                        error = painterResource(R.drawable.tools_smile_edit_camera_image),
                        fallback = painterResource(R.drawable.tools_smile_edit_camera_image),
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(
                    dimensionResource(R.dimen._16)
                )
            )

            PixoPromptTextField(
                value = stringResource(
                    R.string.smile_edit_prompt
                )
            )

            Spacer(
                modifier = Modifier.height(
                    dimensionResource(R.dimen._16)
                )
            )
            PixoSmileIntensitySlider(
                level = smileLevel,
                onLevelChange = {
                    smileLevel = it
                }
            )

//            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen._8)))

            PixoButton(
                textRes = R.string.common_generate_two,
                modifier = Modifier
                    .width(dimensionResource(R.dimen._358))
                    .navigationBarsPadding()
                    .padding(bottom = dimensionResource(R.dimen._8)),
                enabled = true,
                trailingIconRes = R.drawable.ic_sparkle,
                onClick = {
                    onGenerateClick(smileLevel)
                }
            )
        }
    }
}
