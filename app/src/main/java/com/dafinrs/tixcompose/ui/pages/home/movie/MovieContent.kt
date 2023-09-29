package com.dafinrs.tixcompose.ui.pages.home.movie

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import com.dafinrs.tixcompose.domain.model.MovieModel
import com.dafinrs.tixcompose.ui.pages.home.movie.components.CinemasList
import com.dafinrs.tixcompose.ui.pages.home.movie.components.MovieTitle

@Composable
fun MovieContent(
    moviesItem: List<MovieModel>,
    useChoiceCinema: String? = null,
    onChangeCinema: (String?) -> Unit,
    onDetailMovie: (Int) -> Unit,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val movieNow = remember { mutableStateOf<MovieModel?>(null) }

    Column(modifier = Modifier.fillMaxWidth()) {
        MovieRowButtonCinema(useChoiceCinema, onTapButton = onChangeCinema)
        MovieSlider(moviesItem, paddingSize = { screenWidth * 0.25 }, onChangeMovie = {
            movieNow.value = it
        }, onTapBuyButton = {
            Log.i("MOVIE NOW", it.title)
        }) {
            onDetailMovie(it)
        }
        MovieTitle(movieNow.value?.title)
        CinemasList(movieNow.value?.cinemaList?.values?.toList() ?: emptyList())
    }
}