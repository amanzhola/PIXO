package com.company.pixo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.company.pixo.R
import com.company.pixo.core.ui.PixoButton
import com.company.pixo.core.ui.PixoIconButton
import com.company.pixo.core.ui.PixoIconButtonVariant
import com.company.pixo.core.ui.PixoTopBar
import com.company.pixo.core.ui.PixoTopBarVariant
import com.company.pixo.core.theme.BackgroundPrimary

@Composable
fun PixoComponentsTestScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundPrimary)
            .verticalScroll(rememberScrollState())
            .padding(dimensionResource(R.dimen._16)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._24))
    ) {
        PixoTopBar(
            variant = PixoTopBarVariant.MainGuestNoLogo
        )

        PixoButton(
            textRes = R.string.common_continue,
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            onClick = {}
        )

        PixoButton(
            textRes = R.string.common_generate,
            modifier = Modifier.fillMaxWidth(),
            enabled = false,
            onClick = {}
        )

        PixoIconButton(
            variant = PixoIconButtonVariant.SettingsRight,
            contentDescription = null,
            onClick = {}
        )
    }
}