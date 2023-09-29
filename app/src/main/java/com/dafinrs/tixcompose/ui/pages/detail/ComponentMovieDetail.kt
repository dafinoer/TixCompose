package com.dafinrs.tixcompose.ui.pages.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.dafinrs.tixcompose.R


@Composable
fun ComponentMovieDetail(
    modifier: Modifier = Modifier,
    titleMovie: String,
    directorName: String,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = Modifier.absolutePadding(top = 16.dp),
            text = titleMovie,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        RowBody(
            titleRow = stringResource(id = R.string.genre_textbody),
            content = "Family, Drama",
            modifier = Modifier.absolutePadding(top = 16.dp).padding(bottom = 4.dp)
        )
        RowBody(
            titleRow = stringResource(id = R.string.director),
            content = directorName,
            modifier = Modifier.padding(vertical = 4.dp)
        )
        RowBody(
            titleRow = stringResource(id = R.string.age_rating),
            content = "18+",
            modifier = Modifier.padding(vertical = 4.dp)
        )
    }
}

@Composable
internal fun RowBody(titleRow: String, content: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth()) {
        Box(modifier = Modifier.weight(0.5f)) {
            Text(text = titleRow, style = MaterialTheme.typography.bodyMedium)
        }
        Box(modifier = Modifier.weight(1f)) {
            Text(
                text = content,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}