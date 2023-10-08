package com.dafinrs.tixcompose.ui.pages.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dafinrs.tixcompose.presentations.cinemalocation.CinemaLocationViewModel
import com.dafinrs.tixcompose.presentations.nowplaying.InitialNowPlaying
import com.dafinrs.tixcompose.presentations.nowplaying.LoadingNowPlayingMovie
import com.dafinrs.tixcompose.presentations.nowplaying.NowPlayingViewModel
import com.dafinrs.tixcompose.presentations.nowplaying.SuccessNowPlayingMovie
import com.dafinrs.tixcompose.ui.pages.home.location.LocationCinema
import com.dafinrs.tixcompose.ui.pages.home.movie.MovieContent
import com.dafinrs.tixcompose.ui.pages.home.movie.MovieContentHeading
import com.dafinrs.tixcompose.ui.pages.home.movie.MovieRowButtonCinema
import com.dafinrs.tixcompose.ui.pages.home.slider.SliderHomeApp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenPage(
    nowPlayingViewModel: NowPlayingViewModel,
    cinemaLocationViewModel: CinemaLocationViewModel,
    onClickLocation: () -> Unit,
    onMoreMovie: () -> Unit,
    onDetail: (Int) -> Unit,
) {
    val nowPlayingMovieState = nowPlayingViewModel.nowPlayingUIState.collectAsStateWithLifecycle()
    val cinemaLocationState =
        cinemaLocationViewModel.getLocationUserFlow.collectAsStateWithLifecycle(initialValue = null)

    LaunchedEffect(key1 = Unit) {
        when (nowPlayingMovieState.value) {
            is InitialNowPlaying -> nowPlayingViewModel.onCallNowPlayingPage(1)
            else -> Unit
        }
    }

    Scaffold {
        LazyColumn(modifier = Modifier.padding(it)) {
            item {
                LocationCinema(
                    locationName = cinemaLocationState.value?.name,
                    onClickLocation = onClickLocation
                )
            }
            item {
                SliderHomeApp()
            }
            item {
                MovieContentHeading(onTapAllMovie = onMoreMovie)
            }

            item {
                MovieRowButtonCinema(
                    onDisableChangeCinema = {
                        when (nowPlayingMovieState.value) {
                            is SuccessNowPlayingMovie -> false
                            else -> true
                        }
                    },
                ) { cinemaName ->
                    if (cinemaName != null) {
                        nowPlayingViewModel.getPlayNowMovieWithFilter(cinemaName)
                    } else {
                        nowPlayingViewModel.onCallNowPlayingPage(1)
                    }
                }
            }
            item {
                when (val state = nowPlayingMovieState.value) {
                    is LoadingNowPlayingMovie -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(392.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is SuccessNowPlayingMovie -> {
                        MovieContent(
                            moviesItem = state.items,
                        ) {
                            onDetail(it)
                        }
                    }

                    else -> Unit
                }
            }
        }
    }
}
