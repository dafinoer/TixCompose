package com.dafinrs.tixcompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
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
import coil.size.Dimension
import coil.size.Size
import com.dafinrs.tixcompose.R
import com.dafinrs.tixcompose.ui.theme.TixComposeTheme


@Composable
fun PosterImageComponent(
    imageUrl: String?, modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current).data(imageUrl).crossfade(true)
            .size(Size(300, Dimension.Undefined)).build(),
        placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = "Image",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .width(90.dp)
            .height(150.dp)
            .clip(shape = RoundedCornerShape(12.dp))
            .background(color = Color.LightGray)
    )
}


@Preview
@Composable
internal fun PreviewPosterImage() {
    TixComposeTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            PosterImageComponent(imageUrl = "")
        }
    }
}
