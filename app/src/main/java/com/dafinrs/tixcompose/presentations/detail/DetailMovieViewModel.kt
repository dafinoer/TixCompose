package com.dafinrs.tixcompose.presentations.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dafinrs.tixcompose.data.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import org.koin.android.annotation.KoinViewModel
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.annotation.InjectedParam


@KoinViewModel
class DetailMovieViewModel(
    @InjectedParam private val movieRepository: MovieRepository, @InjectedParam val movieId: Int
) : ViewModel() {
    private val detailMovieState = MutableStateFlow<DetailMovieState>(Loading)
    val detailMovieUiState = detailMovieState.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000),
        Loading,
    )

    fun getDetailMovie() {
        viewModelScope.launch {
            try {
                detailMovieState.value = Loading
                val response = movieRepository.getDetail(movieId)
                if (response != null) {
                    detailMovieState.value = SuccessMovie(response)
                }
            } catch (error: Exception) {
                detailMovieState.value = ErrorDetailMovie
            }
        }
    }

}