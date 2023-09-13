package com.dafinrs.tixcompose.ui.pages.locations

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.dafinrs.tixcompose.ui.components.AlertDialogPermission


@Composable
fun AlertGPSEnable(state: LocationUserState) {
    val isShow = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = state) {
        isShow.value = state is LocationUserState.DisableGPS
    }

    if (isShow.value) {
        AlertDialogPermission(
            title = "GPS Enable",
            description = "You need activate GPS",
            onDismissRequest = { isShow.value = false },
            onConfirmRequest = { isShow.value = false }
        )
    }
}