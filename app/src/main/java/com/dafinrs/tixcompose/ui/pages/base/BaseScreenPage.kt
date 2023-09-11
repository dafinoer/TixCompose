package com.dafinrs.tixcompose.ui.pages.base

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dafinrs.tixcompose.data.repository.CinemasRepository
import com.dafinrs.tixcompose.domain.model.CinemasModel
import com.dafinrs.tixcompose.domain.usecases.GetListLocationCinema
import com.dafinrs.tixcompose.domain.usecases.GetNowPlayingUseCase
import com.dafinrs.tixcompose.presentations.cinemalocation.CinemaLocationViewModel
import com.dafinrs.tixcompose.presentations.nowplaying.NowPlayingState
import com.dafinrs.tixcompose.presentations.nowplaying.NowPlayingViewModel
import com.dafinrs.tixcompose.ui.pages.base.components.bottomnav.BottomNavBasePage
import com.dafinrs.tixcompose.ui.pages.cinema.CinemaScreenPage
import com.dafinrs.tixcompose.ui.pages.home.HomeScreenPage
import com.dafinrs.tixcompose.ui.pages.ticket.TicketScreenPage
import io.ktor.util.reflect.instanceOf
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.get

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseScreenPage(
    locationViewModel: CinemaLocationViewModel = koinViewModel(parameters = {
        parametersOf(
            get(
                clazz = GetListLocationCinema::class.java,
                parameters = { parametersOf(Dispatchers.IO) },
            )
        )
    }),
    nowPlayingViewModel: NowPlayingViewModel = koinViewModel(
        parameters = {
            parametersOf(
                get(
                    clazz = GetNowPlayingUseCase::class.java,
                    parameters = { parametersOf(Dispatchers.IO) },
                )
            )
        },
    ),
    onSearchBar: () -> Unit,
    onGoToProfile: () -> Unit,
    onGoToNotification: () -> Unit,
    onTapLocation: () -> Unit,
    onTapDetailCard: (String) -> Unit,
) {
    val rememberNavigationBarController = rememberNavController()
    val nowPlayingMovieState = nowPlayingViewModel.nowPlayingUiState.collectAsStateWithLifecycle(
        initialValue = NowPlayingState.Initial,
    )
    var cinemaType by remember { mutableStateOf<CinemasModel?>(null) }
    val cinemaLocationState = locationViewModel.locationUserState.collectAsStateWithLifecycle()

    val nowPlayingValues by remember {
        derivedStateOf {
            when (nowPlayingMovieState.value) {
                is NowPlayingState.Success -> {
                    val successState = nowPlayingMovieState.value
                    if (successState is NowPlayingState.Success) {
                        val newItems =
                            successState.items.filter { it.cinemaList.contains(cinemaType) }
                        if (newItems.isNotEmpty()) {
                            successState.copy(items = newItems)
                        } else {
                            successState
                        }
                    } else {
                        nowPlayingMovieState.value
                    }
                }

                else -> nowPlayingMovieState.value
            }
        }
    }


    Scaffold(
        bottomBar = {
            BottomNavBasePage(rememberNavigationBarController)
        },
    ) {
        NavHost(navController = rememberNavigationBarController,
            startDestination = "home",
            modifier = Modifier.padding(it),
            enterTransition = {
                EnterTransition.None
            },
            exitTransition = {
                ExitTransition.None
            }) {
            composable(
                "home"
            ) {
                HomeScreenPage(
                    onSearchBar = onSearchBar,
                    onGoToProfile = onGoToProfile,
                    onGoToNotification = onGoToNotification,
                    onDetail = onTapDetailCard,
                    nowPlayingState = nowPlayingValues,
                    onTapButtonCinemas = { cinemaType = it },
                    onClickLocation = onTapLocation,
                    cinemaType = cinemaType,
                    locationName = cinemaLocationState.value?.name
                )
            }
            composable("cinema") {
                CinemaScreenPage()
            }
            composable("ticket") {
                TicketScreenPage()
            }
        }
    }
}