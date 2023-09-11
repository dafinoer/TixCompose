package com.dafinrs.tixcompose.ui.pages.apikey.preview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dafinrs.tixcompose.ui.pages.apikey.component.ButtonSubmit
import com.dafinrs.tixcompose.ui.theme.TixComposeTheme


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun ButtonSubmitPreview() {
    TixComposeTheme {
        Scaffold {
            ButtonSubmit(modifier = Modifier.padding(it)) {

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun ButtonSubmitPreviewEnable() {
    TixComposeTheme {
        Scaffold {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(it), contentAlignment = Alignment.Center
            ) {
                ButtonSubmit(modifier = Modifier.padding(it)) {

                }
            }
        }
    }
}