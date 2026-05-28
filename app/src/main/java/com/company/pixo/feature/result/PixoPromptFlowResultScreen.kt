package com.company.pixo.feature.result

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.company.pixo.R
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.PixoTheme
import com.company.pixo.core.ui.PixoDeleteClipDialog
import com.company.pixo.core.ui.PixoResultActionButtons
import com.company.pixo.core.ui.PixoToast
import com.company.pixo.core.ui.PixoTopBar
import com.company.pixo.core.ui.PixoTopBarVariant
import kotlinx.coroutines.launch

private enum class ResultToastState {
    Saved,
    SaveError,
    ShareError
}

@Composable
fun PixoPromptFlowResultScreen(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int = R.string.prompt_flow_title,
    resultImageUrl: String?,
    onCloseClick: () -> Unit,
    onSaveClick: suspend () -> Boolean,
    onShareClick: suspend () -> Boolean,
    onRegenerateClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onTitleClick: () -> Unit = {},
) {
    var toastState by remember {
        mutableStateOf<ResultToastState?>(null)
    }

    var showDeleteDialog by remember {
        mutableStateOf(false)
    }

    val scope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundPrimary)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            PixoTopBar(
                variant = PixoTopBarVariant.Result,
                titleRes = titleRes,
                onCloseClick = onCloseClick,
                onTitleClick = onTitleClick
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(
                        top = dimensionResource(R.dimen._6),
                        start = dimensionResource(R.dimen._16),
                        end = dimensionResource(R.dimen._16),
                        bottom = dimensionResource(R.dimen._16)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PixoPromptFlowResultImage(
                    resultImageUrl = resultImageUrl,
                    modifier = Modifier.weight(2f)
                )

                Spacer(modifier = Modifier.weight(1f))

                PixoResultActionButtons(
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(bottom = dimensionResource(R.dimen._8)),
                    onSaveClick = {
                        scope.launch {
                            val success = onSaveClick()

                            toastState = if (success) {
                                ResultToastState.Saved
                            } else {
                                ResultToastState.SaveError
                            }
                        }
                    },
                    onShareClick = {
                        scope.launch {
                            val success = onShareClick()

                            if (!success) {
                                toastState = ResultToastState.ShareError
                            }
                        }
                    },
                    onRegenerateClick = onRegenerateClick,
                    onDeleteClick = {
                        showDeleteDialog = true
                    }
                )
            }
        }

        toastState?.let { state ->
            LaunchedEffect(state) {
                kotlinx.coroutines.delay(2_000)
                toastState = null
            }

            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = dimensionResource(R.dimen._121))
            ) {
                when (state) {
                    ResultToastState.Saved -> {
                        PixoToast(
                            iconRes = R.drawable.ic_check_rate,
                            textRes = R.string.toast_saved_successfully
                        )
                    }

                    ResultToastState.SaveError -> {
                        PixoToast(
                            iconRes = R.drawable.ic_error,
                            textRes = R.string.toast_couldnt_save
                        )
                    }

                    ResultToastState.ShareError -> {
                        PixoToast(
                            iconRes = R.drawable.ic_error,
                            textRes = R.string.toast_couldnt_share
                        )
                    }
                }
            }
        }

        if (showDeleteDialog) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(y = dimensionResource(R.dimen._40))
            ) {
                PixoDeleteClipDialog(
                    onCancelClick = {
                        showDeleteDialog = false
                    },
                    onDeleteClick = {
                        showDeleteDialog = false
                        onDeleteClick()
                    }
                )
            }
        }
    }
}

@Composable
private fun PixoPromptFlowResultImage(
    resultImageUrl: String?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(dimensionResource(R.dimen._16)))
    ) {
        AsyncImage(
            model = resultImageUrl,
            contentDescription = null,
            placeholder = painterResource(R.drawable.tools_prompt_sceen_test),
            error = painterResource(R.drawable.tools_prompt_sceen_test),
            fallback = painterResource(R.drawable.tools_prompt_sceen_test),
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

@Preview(
    name = "Pixo / Prompt Flow Result Screen",
    showBackground = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
private fun PixoPromptFlowResultScreenPreview() {
    PixoTheme {
        PixoPromptFlowResultScreen(
            resultImageUrl = "android.resource://com.company.pixo/${R.drawable.tools_ghibli_look}",
            onCloseClick = {},
            onSaveClick = { true },
            onShareClick = { true },
            onRegenerateClick = {},
            onDeleteClick = {}
        )
    }
}