package com.dafinrs.tixcompose.ui.pages.locations

import androidx.compose.runtime.Composable
import com.dafinrs.tixcompose.ui.components.AlertDialogPermission


@Composable
internal fun AlertDialogLocation(isShowError: Boolean = false, onDismiss: () -> Unit) {

    if (isShowError) {
        AlertDialogPermission(
            title = "GPS Enable",
            description = "You need activate GPS",
            onDismissRequest = onDismiss,
            onConfirmRequest = onDismiss,
        )
    }
}