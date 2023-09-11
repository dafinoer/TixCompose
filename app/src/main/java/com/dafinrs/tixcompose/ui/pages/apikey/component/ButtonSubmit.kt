package com.dafinrs.tixcompose.ui.pages.apikey.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dafinrs.tixcompose.R
import com.dafinrs.tixcompose.presentations.sign.SignViewModel

@Composable
fun ButtonSubmit(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    onSubmit: () -> Unit,
) {
    FilledTonalButton(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(), onClick = onSubmit,
        enabled = !isLoading
    ) {
        Text(text = stringResource(id = R.string.submit_api_key))
    }
}