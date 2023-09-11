package com.dafinrs.tixcompose.ui.pages.locations

import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ComponentCard(modifier: Modifier = Modifier, title: String, onTapAction: () -> Unit) {
    ListItem(
        modifier = modifier.clickable { onTapAction() },
        headlineText = {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleLarge
            )
        },
    )
}