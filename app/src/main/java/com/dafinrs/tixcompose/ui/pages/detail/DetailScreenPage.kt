package com.dafinrs.tixcompose.ui.pages.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dafinrs.tixcompose.R
import com.dafinrs.tixcompose.data.repository.MovieRepository
import com.dafinrs.tixcompose.presentations.detail.DetailMovieViewModel

import com.dafinrs.tixcompose.presentations.detail.SuccessMovie

import com.dafinrs.tixcompose.ui.components.PrimaryElevatedButton
import com.dafinrs.tixcompose.ui.pages.notfound.NotFoundScreenPage
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf
import com.dafinrs.tixcompose.utilities.NavigationPageKey

fun NavGraphBuilder.detailPage(onBackButton: () -> Unit) {
    composable(NavigationPageKey.DETAIL_MOVIE) {
        val movieIdArgument = it.arguments?.getString("movieId")
        if (movieIdArgument != null) {
            DetailScreenPage(movieId = movieIdArgument, onBackButton = onBackButton)
        } else {
            NotFoundScreenPage(messageInfo = "MovieId not Found")
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenPage(
    movieId: String,
    movieRepository: MovieRepository = koinInject(parameters = {
        parametersOf(Dispatchers.IO)
    }),
    onBackButton: () -> Unit,
) {
    val backgroundPosterHeight = 250.dp
    val detailMovieViewModel = koinViewModel<DetailMovieViewModel>(parameters = {
        parametersOf(
            movieRepository, movieId.toInt()
        )
    })
    val detailMovieState = detailMovieViewModel.detailMovieUiState.collectAsStateWithLifecycle()
    val favMovieState = detailMovieViewModel.favoriteMovieStatusUI.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        detailMovieViewModel.getDetailMovie()
        detailMovieViewModel.onReadFav()
    }

    Scaffold {
        Column(
            modifier = Modifier.padding(it)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                item {
                    Box {
                        BackdropImageComponent(
                            onPlayButton = { /*TODO*/ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(backgroundPosterHeight),
                            stateDetail = detailMovieState.value
                        )
                        ComponentMovieDetail(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(PaddingValues(top = 250.dp))
                                .absolutePadding(left = 122.dp, right = 16.dp),
                            detailMovieState = detailMovieState.value
                        )
                        ComponentPosterMovieDetail(detailMovieState = detailMovieState.value)
                    }
                }
                item {
                    ComponentRatingAndLike(
                        detailMovieState = detailMovieState.value, isWatchList = favMovieState.value
                    ) {
                        detailMovieViewModel.onFavMovie(it)
                    }
                }

                item {
                    ComponentSynopsis(detailMovieState.value)
                }
            }

            PrimaryElevatedButton(
                onAction = { /*TODO*/ },
                title = stringResource(id = R.string.button_buy_now),
                isEnable = when (detailMovieState.value) {
                    is SuccessMovie -> true
                    else -> false
                }
            )
        }

    }
}
