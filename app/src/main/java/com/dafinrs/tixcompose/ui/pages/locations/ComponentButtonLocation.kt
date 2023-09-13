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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dafinrs.tixcompose.R
import com.dafinrs.tixcompose.ui.components.AlertDialogPermission
import com.dafinrs.tixcompose.ui.theme.TixComposeTheme


@Composable
internal fun ComponentButtonLocation(
    isButtonEnable: Boolean = true,

    onPermissionLocationResult: (Boolean) -> Unit,
) {
    var isPermissionLocationGranted by remember { mutableStateOf<Boolean?>(null) }

    val locationResult = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
    ) {
        isPermissionLocationGranted = it
        onPermissionLocationResult(it)
    }

    when (isPermissionLocationGranted) {
        false -> {
            AlertDialogPermission(
                title = "Permission Location",
                description = stringResource(id = R.string.permission_location_text),
                onDismissRequest = { isPermissionLocationGranted = null }) {
                isPermissionLocationGranted = null
            }
        }

        else -> Unit
    }

    ElevatedButton(
        onClick = { locationResult.launch(Manifest.permission.ACCESS_COARSE_LOCATION) },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 16.dp)
            .fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        enabled = isButtonEnable
    ) {
        Text(
            text = stringResource(id = R.string.use_location_button),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewButton() {
    TixComposeTheme {
        ComponentButtonLocation {

        }
    }
}