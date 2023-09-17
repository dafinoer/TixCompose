package com.dafinrs.tixcompose.ui.pages.base

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dafinrs.tixcompose.domain.usecases.GetListLocationCinema
import com.dafinrs.tixcompose.domain.usecases.GetNowPlayingUseCase
import com.dafinrs.tixcompose.presentations.cinemalocation.CinemaLocationViewModel
import com.dafinrs.tixcompose.presentations.nowplaying.NowPlayingViewModel
import com.dafinrs.tixcompose.ui.pages.base.components.bottomnav.BottomNavBasePage
import com.dafinrs.tixcompose.ui.pages.cinema.CinemaScreenPage
import com.dafinrs.tixcompose.ui.pages.home.HomeScreenPage
import com.dafinrs.tixcompose.ui.pages.ticket.TicketScreenPage
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.compose.koinViewModel
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
                    nowPlayingViewModel = nowPlayingViewModel,
                    cinemaLocationViewModel = locationViewModel,
                    onClickLocation = onTapLocation,
                    onMoreMovie = {
                        /// TODO: navigate to more list movies
                    },
                ) {
                    onTapDetailCard(it)
                }
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