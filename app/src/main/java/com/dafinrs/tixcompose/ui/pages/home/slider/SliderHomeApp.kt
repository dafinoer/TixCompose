package com.dafinrs.tixcompose.ui.pages.home.slider

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dafinrs.tixcompose.R


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SliderHomeApp(modifier: Modifier = Modifier) {
    val currentIndex = remember {
        mutableIntStateOf(0)
    }
    val rememberPageState = rememberPagerState {
        5
    }

    LaunchedEffect(rememberPageState.currentPage) {
        currentIndex.intValue = rememberPageState.currentPage
    }

    Box(
        modifier = modifier
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