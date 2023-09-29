package com.dafinrs.tixcompose.ui.pages.detail

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun CompoenentSynopsis(textDesc: String) {
    Text(
        text = textDesc, modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    )
}