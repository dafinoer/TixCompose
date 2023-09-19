package com.dafinrs.tixcompose.ui.pages.ticket

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ComponentPageSlider(initialSlideIndex: Int = 0, onGoToDetailMovie: (Int) -> Unit) {
    val pagerState = rememberPagerState(initialPage = initialSlideIndex) {
        2
    }
    LaunchedEffect(key1 = initialSlideIndex) {
        pagerState.animateScrollToPage(initialSlideIndex)
    }
    LaunchedEffect(key1 = Unit) {
        snapshotFlow { pagerState.currentPage }.collect {
            onGoToDetailMovie(it)
        }
    }

    HorizontalPager(state = pagerState) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            when (it) {
                0 -> {
                    item {
                        Text(text = "1")
                    }
                }

                1 -> {
                    item {
                        Text(text = "2")
                    }
                }
            }
        }
    }
}