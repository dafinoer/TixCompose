package com.dafinrs.tixcompose.ui.pages.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dafinrs.tixcompose.R
import com.dafinrs.tixcompose.presentations.detail.DetailMovieState
import com.dafinrs.tixcompose.presentations.detail.Loading
import com.dafinrs.tixcompose.presentations.detail.SuccessMovie
import com.dafinrs.tixcompose.ui.theme.TixComposeTheme
import com.dafinrs.tixcompose.utilities.backdropBaseUrl

@Composable
fun BackdropImageComponent(
    stateDetail: DetailMovieState,
    modifier: Modifier = Modifier,
    onPlayButton: () -> Unit,
) {
    Box(modifier = modifier) {
        when (stateDetail) {
            is SuccessMovie -> {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(stateDetail.movieModel.backdropPath?.backdropBaseUrl())
                        .crossfade(true).build(),
                    contentDescription = "image thumbnail",
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Gray),
                    placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentScale = ContentScale.Crop
                )
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(Color.Black)
                            .clickable {
                                onPlayButton()
                            }, contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxSize(),
                            imageVector = Icons.Rounded.PlayArrow,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }

            else -> Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray)
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
                stateDetail = Loading, modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
            ) {

            }
        }
    }
}
