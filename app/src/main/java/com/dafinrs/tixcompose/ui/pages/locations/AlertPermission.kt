package com.dafinrs.tixcompose.ui.pages.locations

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.dafinrs.tixcompose.R
import com.dafinrs.tixcompose.ui.components.AlertDialogPermission


@Composable
internal fun AlertPermission(isShowDialog: Boolean = false, onDismiss:() -> Unit) {
    if(isShowDialog) {
        AlertDialogPermission(
            title = "Permission Location",
            description = stringResource(id = R.string.permission_location_text),
            onDismissRequest = onDismiss) {
            onDismiss()
        }
    }
}