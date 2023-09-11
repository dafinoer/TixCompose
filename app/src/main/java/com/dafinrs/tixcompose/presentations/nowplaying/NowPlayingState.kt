package com.dafinrs.tixcompose.presentations.nowplaying

import com.dafinrs.tixcompose.domain.model.MovieModel

sealed class NowPlayingState {
    object Initial : NowPlayingState()
    object Loading : NowPlayingState()
    data class Success(val items: List<MovieModel>) : NowPlayingState()
    data class Error(val message: String = "Oops Something Wrong") : NowPlayingState()
}
