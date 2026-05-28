package com.company.pixo.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.company.pixo.R
import com.company.pixo.core.theme.AccentPrimary
import com.company.pixo.core.theme.BackgroundPrimary
import com.company.pixo.core.theme.LabelPrimary
import com.company.pixo.core.theme.LabelQuintuple
import com.company.pixo.core.theme.PixoTheme
import com.company.pixo.feature.main.MainTab

@Composable
fun PixoBottomNavigation(
    selectedTab: MainTab,
    onTabClick: (MainTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(dimensionResource(R.dimen._111))
            .background(BackgroundPrimary)
            .padding(
                horizontal = dimensionResource(R.dimen._16)
            )
            .windowInsetsPadding(WindowInsets.navigationBars),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        MainTab.entries.forEach { tab ->
            PixoBottomNavigationItem(
                tab = tab,
                selected = selectedTab == tab,
                onClick = { onTabClick(tab) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

private const val GLOW_ALPHA = 0.34f

@Composable
fun PixoBottomNavigationItem(
    tab: MainTab,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val contentColor = if (selected) LabelPrimary else LabelQuintuple

    Box(
        modifier = modifier
            .height(dimensionResource(R.dimen._61))
            .clip(
                RoundedCornerShape(
                    dimensionResource(R.dimen._12)
                )
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        if (selected) {
            Box(
                modifier = Modifier
                    .size(
                        width = dimensionResource(R.dimen._86_5),
                        height = dimensionResource(R.dimen._61)
                    )
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                AccentPrimary.copy(alpha = GLOW_ALPHA),
                                Color.Transparent
                            ),
                            radius = dimensionResource(
                                R.dimen._85
                            ).value
                        )
                    )
            )
        }

        Column(
            modifier = Modifier
                .height(dimensionResource(R.dimen._61))
                .padding(
                    PaddingValues(
                        top = dimensionResource(R.dimen._6),
                        bottom = dimensionResource(R.dimen._6)
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = dimensionResource(R.dimen._4),
                alignment = Alignment.CenterVertically
            )
        ) {
            Icon(
                painter = painterResource(id = tab.iconRes),
                contentDescription = tab.title,
                tint = contentColor,
                modifier = Modifier.size(
                    dimensionResource(R.dimen._32)
                )
            )

            Text(
                text = tab.title,
                color = contentColor,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Preview(name = "PixoBottomNavigation", showBackground = true)
@Composable
private fun PixoBottomNavigationPreview() {
    PixoTheme {
        Box(
            modifier = Modifier
                .size(
                    width = dimensionResource(R.dimen._390),
                    height = dimensionResource(R.dimen._111)
                )
                .background(BackgroundPrimary)
        ) {
            PixoBottomNavigation(
//                selectedTab = MainTab.Tools,
//                selectedTab = MainTab.Prompts,
                selectedTab = MainTab.Templates,
                onTabClick = {}
            )
        }
    }
}