package com.dafinrs.tixcompose.ui.pages.locations

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dafinrs.tixcompose.R


@Composable
internal fun ComponentButtonLocation(
    permissionState: PermissionLocationUserState,
    onSearchLocation: () -> Unit,
) {
    var isShowDialog by remember { mutableStateOf(false) }
    var isEnableButton by remember { mutableStateOf(true) }
    val locationResult = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
    ) {
        isShowDialog = !it
        if (it) {
            onSearchLocation()
        }
    }

    LaunchedEffect(key1 = permissionState) {
        isEnableButton = permissionState !is PermissionLocationUserState.LoadingGetLocation
    }

    ElevatedButton(
        onClick = { locationResult.launch(Manifest.permission.ACCESS_COARSE_LOCATION) },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.surface,
            disabledContentColor = MaterialTheme.colorScheme.onSurface,
        ),
        modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 16.dp)
            .fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        enabled = isEnableButton,
    ) {
        Text(
            text = stringResource(id = R.string.use_location_button),
            style = MaterialTheme.typography.titleLarge,
        )
    }
}