package com.dafinrs.tixcompose.ui.pages.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dafinrs.tixcompose.R
import com.dafinrs.tixcompose.ui.theme.TixComposeTheme
import com.dafinrs.tixcompose.utilities.toFullPosterW300Url

@Composable
fun BackdropImageComponent(
    imageUrl: String,
    modifier: Modifier = Modifier,
    onPlayButton: () -> Unit,
) {
    Box(modifier = modifier) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(imageUrl).crossfade(true).build(),
            contentDescription = "image thumbnail",
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray),
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
        )
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Icon(
                modifier = Modifier
                    .size(48.dp)
                    .clickable { onPlayButton() },
                imageVector = Icons.Rounded.PlayArrow,
                contentDescription = null,
                tint = MaterialTheme.colors.primaryVariant
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun PreviewBackdropVideo() {
    TixComposeTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            BackdropImageComponent(
                imageUrl = "/iJQIbOPm81fPEGKt5BPuZmfnA54.jpg".toFullPosterW300Url(),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
            ) {

            }
        }
    }
}
