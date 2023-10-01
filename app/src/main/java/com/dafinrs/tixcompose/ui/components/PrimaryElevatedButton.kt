package com.dafinrs.tixcompose.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dafinrs.tixcompose.ui.theme.TixComposeTheme

@Composable
fun PrimaryElevatedButton(onAction: () -> Unit, title: String, isEnable: Boolean = false) {
    ElevatedButton(
        onClick = onAction,
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.surface,
            disabledContentColor = MaterialTheme.colorScheme.onSurface,
        ),
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 16.dp),
        enabled = isEnable
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
            maxLines = 1,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewElevatedButton() {
    TixComposeTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            PrimaryElevatedButton(onAction = { /*TODO*/ }, title = "Lorem Ipsum")
        }
    }
}