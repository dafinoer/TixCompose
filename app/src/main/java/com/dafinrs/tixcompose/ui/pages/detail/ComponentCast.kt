package com.dafinrs.tixcompose.ui.pages.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dafinrs.tixcompose.ui.components.PosterImageComponent


@Composable
fun ComponentCast() {
    Column(modifier = Modifier.absolutePadding(top = 32.dp)) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Cast",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            contentPadding = PaddingValues(horizontal = 8.dp),
        ) {
            items(5) {
                PosterImageComponent(imageUrl = "", modifier = Modifier.absolutePadding(left = 8.dp))
            }
        }
    }
}