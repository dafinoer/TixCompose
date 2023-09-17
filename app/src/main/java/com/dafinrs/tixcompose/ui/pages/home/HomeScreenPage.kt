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
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dafinrs.tixcompose.presentations.cinemalocation.CinemaLocationViewModel
import com.dafinrs.tixcompose.presentations.nowplaying.NowPlayingState
import com.dafinrs.tixcompose.presentations.nowplaying.NowPlayingViewModel
import com.dafinrs.tixcompose.ui.pages.home.location.LocationCinema
import com.dafinrs.tixcompose.ui.pages.home.movie.MovieContent
import com.dafinrs.tixcompose.ui.pages.home.movie.MovieContentHeading
import com.dafinrs.tixcompose.ui.pages.home.slider.SliderHomeApp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenPage(
    nowPlayingViewModel: NowPlayingViewModel,
    cinemaLocationViewModel: CinemaLocationViewModel,
    onSearchBar: () -> Unit,
    onGoToProfile: () -> Unit,
    onGoToNotification: () -> Unit,
    onClickLocation: () -> Unit,
    onMoreMovie: () -> Unit,
    onDetail: (String) -> Unit,
) {
    val nowPlayingMovieState = nowPlayingViewModel.nowPlayingUIState.collectAsStateWithLifecycle()
    val cinemaLocationState =
        cinemaLocationViewModel.getLocationUserFlow.collectAsStateWithLifecycle()
    var userChoiceCinema by rememberSaveable {
        mutableStateOf<String?>(null)
    }
    SideEffect {
        when (nowPlayingMovieState.value) {
            is NowPlayingState.Initial -> nowPlayingViewModel.onCallNowPlayingPage(1)
            else -> Unit
        }
    }

    Scaffold(
        topBar = {
            HomeAppBar(
                onClickSearchBar = onSearchBar,
                onClickProfile = onGoToProfile,
                onClickNotification = onGoToNotification,
            )
        },
    ) {
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
                when (val state = nowPlayingMovieState.value) {
                    is NowPlayingState.Loading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(392.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is NowPlayingState.Success -> {
                        MovieContent(
                            moviesItem = state.items,
                            useChoiceCinema = userChoiceCinema,
                        ) { cinemaType ->
                            userChoiceCinema = cinemaType
                            if (cinemaType != null) {
                                nowPlayingViewModel.getPlayNowMovieWithFilter(cinemaType)
                            } else {
                                nowPlayingViewModel.onCallNowPlayingPage(1)
                            }
                        }
                    }

                    else -> Unit
                }
            }
        }
    }
}
