package com.company.pixo.core.ui.permission

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.company.pixo.R

@Composable
fun PixoCameraPermissionDeniedDialog(
    onCancelClick: () -> Unit,
    onTryAgainClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onCancelClick,
        title = {
            Text(text = stringResource(R.string.camera_permission_denied_title))
        },
        text = {
            Text(text = stringResource(R.string.camera_permission_denied_message))
        },
        confirmButton = {
            TextButton(onClick = onTryAgainClick) {
                Text(text = stringResource(R.string.common_try_again))
            }
        },
        dismissButton = {
            TextButton(onClick = onCancelClick) {
                Text(text = stringResource(R.string.common_cancel))
            }
        }
    )
}

@Composable
fun PixoCameraPermissionSettingsDialog(
    onCancelClick: () -> Unit,
    onOpenSettingsClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onCancelClick,
        title = {
            Text(text = stringResource(R.string.camera_permission_settings_title))
        },
        text = {
            Text(text = stringResource(R.string.camera_permission_settings_message))
        },
        confirmButton = {
            TextButton(onClick = onOpenSettingsClick) {
                Text(text = stringResource(R.string.cd_settings))
            }
        },
        dismissButton = {
            TextButton(onClick = onCancelClick) {
                Text(text = stringResource(R.string.common_cancel))
            }
        }
    )
}