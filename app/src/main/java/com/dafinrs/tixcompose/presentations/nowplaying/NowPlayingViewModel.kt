package com.dafinrs.tixcompose.presentations.nowplaying

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dafinrs.tixcompose.domain.usecases.GetNowPlayingUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.InjectedParam


@KoinViewModel
class NowPlayingViewModel(@InjectedParam private val nowPlayingUseCase: GetNowPlayingUseCase) :
    ViewModel() {

    private val nowPlayingPage = MutableStateFlow(1)

    @OptIn(ExperimentalCoroutinesApi::class)
    val nowPlayingUiState = nowPlayingPage.flatMapLatest { value ->
        flow {
            emit(NowPlayingState.Loading)
            val result = nowPlayingUseCase.call(value)
            emit(NowPlayingState.Success(result))
        }
    }.catch {
        emit(NowPlayingState.Error())
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = NowPlayingState.Loading
    )

    init {
        onCallNowPlayingPage(1)
    }

    fun onCallNowPlayingPage(page: Int) {
        nowPlayingPage.value = page
    }
}