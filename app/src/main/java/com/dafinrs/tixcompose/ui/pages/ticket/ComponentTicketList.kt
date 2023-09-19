package com.dafinrs.tixcompose.ui.pages.ticket

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Dimension
import coil.size.Size
import com.dafinrs.tixcompose.R
import com.dafinrs.tixcompose.ui.theme.TixComposeTheme
import com.dafinrs.tixcompose.utilities.toFullPosterW300Url


@Composable
fun ComponentTicketList(
    modifier: Modifier = Modifier,
    title: String,
    location: String,
    totalTicket: Int = 0,
    movieDate: String,
    imageUrl: String,
    onActionToDetailTicket: () -> Unit,
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .clickable { onActionToDetailTicket() }) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(imageUrl).crossfade(true)
                .size(Size(300, Dimension.Undefined)).build(),
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Image",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .width(90.dp)
                .height(150.dp)
                .background(color = MaterialTheme.colorScheme.surface)
                .clip(RoundedCornerShape(12.dp)),
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth(1f),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text = location,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 4.dp),
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = totalTicket.toString(),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 4.dp),
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = movieDate, style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun PreviewTicketListTile() {
    TixComposeTheme {
        ComponentTicketList(
            title = "Lorem Ipsum",
            location = "Serang",
            movieDate = "Senin, 10 Dek 2022",
            totalTicket = 20,
            imageUrl = "/iJQIbOPm81fPEGKt5BPuZmfnA54.jpg".toFullPosterW300Url()
        ) {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
internal fun PreviewTicketListTileWithScafold() {
    TixComposeTheme {
        Scaffold {
            ComponentTicketList(
                modifier = Modifier.padding(it),
                title = "Lorem Ipsum",
                location = "Serang",
                movieDate = "Senin, 10 Dek 2022",
                totalTicket = 20,
                imageUrl = "Lorem"
            ) {

            }
        }
    }
}