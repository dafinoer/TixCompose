package com.dafinrs.tixcompose.ui.pages.home.movie

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.dafinrs.tixcompose.domain.model.CinemasModel
import com.dafinrs.tixcompose.domain.model.MovieModel
import com.dafinrs.tixcompose.presentations.nowplaying.NowPlayingState
import com.dafinrs.tixcompose.ui.pages.home.movie.components.CinemasList
import com.dafinrs.tixcompose.ui.pages.home.movie.components.MovieTitle

@Composable
fun MovieContent(
    onTapOpenMoreListMovie: () -> Unit,
    modifier: Modifier = Modifier,
    state: NowPlayingState = NowPlayingState.Initial,
    onChangeCinema: (CinemasModel?) -> Unit,
    cinemaType: CinemasModel?,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val movieNow = remember {
        mutableStateOf<MovieModel?>(null)
    }

    when (state) {
        is NowPlayingState.Loading -> {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.surfaceVariant
            )
        }

        is NowPlayingState.Success -> {
            Column(modifier = modifier.fillMaxWidth()) {
                MovieContentHeading(onTapAllMovie = onTapOpenMoreListMovie)
                MovieRowButtonCinema(cinemaType = cinemaType, onTapButton = onChangeCinema)
                MovieSlider(modifier = Modifier.padding(vertical = 16.dp),
                    state.items,
                    screenWidthSize = screenWidth,
                    onChangeMovie = {
                        movieNow.value = it
                    }) {
                    Log.i("MOVIE NOW", it.title)
                }
                MovieTitle(movieNow.value?.title)
                CinemasList(movieNow.value?.cinemaList ?: setOf())
            }
        }

        else -> Unit
    }
}