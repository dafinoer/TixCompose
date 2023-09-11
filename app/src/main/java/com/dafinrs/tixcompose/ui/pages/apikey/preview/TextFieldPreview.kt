package com.dafinrs.tixcompose.ui.pages.apikey.preview

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dafinrs.tixcompose.ui.components.TextFieldCompose
import com.dafinrs.tixcompose.ui.theme.TixComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun TextFieldPreview() {
    TixComposeTheme {
        Scaffold {
            TextFieldCompose(
                modifier = Modifier.padding(it),
                errorForm = null,
                value = "Halo",
                onClearText = {}
            ) {

            }
        }
    }
}