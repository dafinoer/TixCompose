package com.dafinrs.tixcompose.presentations.nowplaying

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dafinrs.tixcompose.domain.usecases.GetNowPlayingUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.InjectedParam


@KoinViewModel
class NowPlayingViewModel(@InjectedParam private val nowPlayingUseCase: GetNowPlayingUseCase) :
    ViewModel() {
    private val nowPlayingState = MutableStateFlow<NowPlayingState>(InitialNowPlaying)
    val nowPlayingUIState = nowPlayingState.asStateFlow()

    fun onCallNowPlayingPage(page: Int) {
        viewModelScope.launch {
            try {
                nowPlayingState.value = LoadingNowPlayingMovie
                val result = nowPlayingUseCase.call(page)
                nowPlayingState.value = SuccessNowPlayingMovie(result)
            } catch (error: Exception) {
                nowPlayingState.value = ErrorMovie()
                Log.e("NowPlaying", error.message ?: "", error.cause)
            }
        }
    }

    fun getPlayNowMovieWithFilter(filterType: String, page: Int = 1) {
        viewModelScope.launch {
            try {
                nowPlayingState.value = LoadingNowPlayingMovie
                val result = nowPlayingUseCase.filterMovieByType(filterType, page)
                nowPlayingState.value = SuccessNowPlayingMovie(result)
            } catch (error: Exception) {
                nowPlayingState.value = ErrorMovie()
            }
        }
    }
}