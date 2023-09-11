package com.dafinrs.tixcompose.ui.pages.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dafinrs.tixcompose.domain.model.CinemasModel
import com.dafinrs.tixcompose.domain.usecases.GetNowPlayingUseCase
import com.dafinrs.tixcompose.presentations.nowplaying.NowPlayingState
import com.dafinrs.tixcompose.presentations.nowplaying.NowPlayingViewModel
import com.dafinrs.tixcompose.ui.pages.home.location.LocationCinema
import com.dafinrs.tixcompose.ui.pages.home.movie.MovieContent
import com.dafinrs.tixcompose.ui.pages.home.slider.SliderHomeApp
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenPage(
    modifier: Modifier = Modifier,
    nowPlayingState: NowPlayingState,
    locationName: String?,
    cinemaType: CinemasModel?,
    onSearchBar: () -> Unit,
    onGoToProfile: () -> Unit,
    onGoToNotification: () -> Unit,
    onTapButtonCinemas: (CinemasModel?) -> Unit,
    onClickLocation: () -> Unit,
    onDetail: (String) -> Unit,
) {
    Scaffold(
        modifier = modifier,
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
                    locationName = locationName,
                    onClickLocation = onClickLocation
                )
            }
            item {
                SliderHomeApp()
            }
            item {
                MovieContent(
                    onTapOpenMoreListMovie = { /*TODO*/ },
                    state = nowPlayingState,
                    onChangeCinema = onTapButtonCinemas,
                    cinemaType = cinemaType
                )
            }
        }
    }
}
