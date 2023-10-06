package com.dafinrs.tixcompose.presentations.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dafinrs.tixcompose.data.repository.MovieRepository
import com.dafinrs.tixcompose.domain.model.MovieModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
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

    private val favoriteMovieStatus = MutableStateFlow(false)
    val favoriteMovieStatusUI = favoriteMovieStatus.asStateFlow()


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


    fun onReadFav() {
        viewModelScope.launch {
            try {
                val result = movieRepository.loadFavMovieById(movieId)
                favoriteMovieStatus.value = result != null
            } catch (error: Exception) {
                favoriteMovieStatus.value = false
            }
        }
    }

    fun onFavMovie(movieModel: MovieModel) {
        viewModelScope.launch {
            try {
                val favMovie = movieRepository.loadFavMovieById(movieId)
                if (favMovie != null) {
                    movieRepository.deleteFavMovie(movieId)
                    favoriteMovieStatus.value = false
                } else {
                    movieRepository.insertFavMovie(movieModel)
                    favoriteMovieStatus.value = true
                }
            } catch (error: Exception) {
                Log.e("ERROR FAv", error.message, error)
            }
        }
    }
}