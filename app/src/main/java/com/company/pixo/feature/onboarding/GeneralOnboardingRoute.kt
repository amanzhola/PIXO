package com.company.pixo.feature.onboarding

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.company.pixo.core.ui.PixoOnboardingBeforeAfterScreen
import com.company.pixo.core.ui.PixoPromptsOnboardingScreen
import com.company.pixo.core.ui.PixoTemplatesOnboardingScreen
import com.company.pixo.domain.repository.RateAppRepository
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

private sealed interface GeneralOnboardingItem {
    data class Tool(
        val destination: PremiumOnboardingDestination
    ) : GeneralOnboardingItem

    data object Templates : GeneralOnboardingItem
    data object Prompts : GeneralOnboardingItem
}

@Composable
fun GeneralOnboardingRoute(
    onFinished: () -> Unit
) {
    val context = LocalContext.current
    val activity = context as? Activity
    val rateAppRepository: RateAppRepository = koinInject()
    val coroutineScope = rememberCoroutineScope()

    var currentIndex by remember {
        mutableIntStateOf(0)
    }

    val items = remember {
        buildList {
            addAll(
                PixoPremiumOnboardingDestinations.tools.map {
                    GeneralOnboardingItem.Tool(it)
                }
            )

            add(GeneralOnboardingItem.Templates)
            add(GeneralOnboardingItem.Prompts)
        }
    }

    val currentItem = items[currentIndex]
    val isLastSlide = currentIndex == items.lastIndex

    fun onContinueClick() {
        if (isLastSlide) {
            coroutineScope.launch {
                if (activity != null) {
                    rateAppRepository.requestReview(activity)
                }

                onFinished()
            }
        } else {
            currentIndex += 1
        }
    }

    when (val item = currentItem) {
        is GeneralOnboardingItem.Tool -> {
            val slide = item.destination

            if (slide.afterImageRes == null) {
                PixoOnboardingBeforeAfterScreen(
                    beforeImageRes = slide.beforeImageRes,
                    title = stringResource(slide.titleRes),
                    subtitle = stringResource(slide.subtitleRes),
                    onContinueClick = { onContinueClick() }
                )
            } else {
                PixoOnboardingBeforeAfterScreen(
                    beforeImageRes = slide.beforeImageRes,
                    afterImageRes = slide.afterImageRes,
                    title = stringResource(slide.titleRes),
                    subtitle = stringResource(slide.subtitleRes),
                    onContinueClick = { onContinueClick() }
                )
            }
        }

        GeneralOnboardingItem.Templates -> {
            PixoTemplatesOnboardingScreen(
                onContinueClick = { onContinueClick() }
            )
        }

        GeneralOnboardingItem.Prompts -> {
            PixoPromptsOnboardingScreen(
                onContinueClick = { onContinueClick() }
            )
        }
    }
}