package com.dafinrs.tixcompose.ui.pages.locations

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.dafinrs.tixcompose.R


@Composable
internal fun AlertDialogPermission(
    onDismissRequest: () -> Unit,
    onConfirmRequest: () -> Unit,
) {
    AlertDialog(title = {
        Text(text = "Permission", style = MaterialTheme.typography.titleLarge)
    }, onDismissRequest = onDismissRequest, text = {
        Text(
            text = stringResource(id = R.string.permission_location_text),
            style = MaterialTheme.typography.bodyMedium
        )
    }, confirmButton = {
        TextButton(onClick = onConfirmRequest) {
            Text(text = "Ok")
        }
    }, titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
    )
}