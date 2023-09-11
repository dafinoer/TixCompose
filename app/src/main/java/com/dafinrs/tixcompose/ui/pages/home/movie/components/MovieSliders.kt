package com.dafinrs.tixcompose.ui.pages.home.movie

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.dafinrs.tixcompose.R
import com.dafinrs.tixcompose.domain.model.MovieModel
import com.dafinrs.tixcompose.utilities.toFullPosterW300Url
import kotlinx.coroutines.Dispatchers

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieSlider(
    modifier: Modifier = Modifier,
    movies: List<MovieModel> = listOf(),
    screenWidthSize: Int,
    onChangeMovie: (MovieModel) -> Unit,
    onTapBuyButton: (MovieModel) -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = {
        Int.MAX_VALUE
    }, initialPage = (Int.MAX_VALUE / 2) - (Math.floorMod(Int.MAX_VALUE / 2, movies.size)))

    var indexButtonShow by remember {
        mutableIntStateOf(0)
    }

    LaunchedEffect(movies) {
        snapshotFlow { pagerState.currentPage }.collect {
            indexButtonShow = Math.floorMod(it, movies.size)
            onChangeMovie(movies[indexButtonShow])
        }
    }

    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(
            start = (screenWidthSize * 0.25).dp,
            end = (screenWidthSize * 0.25).dp,
        ),
        pageSize = PageSize.Fill,
        pageSpacing = 16.dp,
        modifier = modifier,
    ) {
        val pageIndex = Math.floorMod(it, movies.size)
        val isShow = indexButtonShow == pageIndex

        ImageItem(movieModel = movies[pageIndex], isShowButtonBuyTicket = isShow, onTapBuyButton = onTapBuyButton)
    }
}

@Composable
private fun ImageItem(
    modifier: Modifier = Modifier, movieModel: MovieModel,
    isShowButtonBuyTicket: Boolean = false,
    onTapBuyButton: (MovieModel) -> Unit,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(392.dp),
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
    ) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movieModel.postPath?.toFullPosterW300Url()).crossfade(true)
                .dispatcher(Dispatchers.IO).build(),
        )
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painter,
            contentDescription = stringResource(id = R.string.banner_image_home),
            contentScale = ContentScale.Crop
        )
        if (isShowButtonBuyTicket) {
            Box(
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier.fillMaxSize(),
            ) {
                MovieBuyTicket(
                    onTapBuy = { onTapBuyButton(movieModel) },
                )
            }
        }
    }
}

