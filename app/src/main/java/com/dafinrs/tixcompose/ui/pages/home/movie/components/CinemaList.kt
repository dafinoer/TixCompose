package com.dafinrs.tixcompose.ui.pages.home.movie.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dafinrs.tixcompose.domain.model.CinemasModel

@Composable
fun CinemasList(cinemas:  List<CinemasModel> = emptyList()) {
    if (cinemas.isNotEmpty()) {
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(bottom = 16.dp).fillMaxWidth()) {
            cinemas.forEach {
                Text(
                    text = it.originalName,
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.absolutePadding(right = 4.dp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}