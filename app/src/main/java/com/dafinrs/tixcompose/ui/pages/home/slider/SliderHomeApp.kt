package com.dafinrs.tixcompose.ui.pages.home.slider

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dafinrs.tixcompose.R


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SliderHomeApp() {
    val rememberPageState = rememberPagerState {
        5
    }
    Box(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .aspectRatio(1.7f)
            .fillMaxSize()
    ) {
        HorizontalPager(
            state = rememberPageState, contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
            ), pageSpacing = 8.dp
        ) {
            Surface(shape = RoundedCornerShape(12.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.banner_flashsale),
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = "image",
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}


@Preview(showBackground = true, apiLevel = 29)
@Composable
fun PreviewSlider() {
    Box(modifier = Modifier.fillMaxSize()) {
        SliderHomeApp()
    }
}