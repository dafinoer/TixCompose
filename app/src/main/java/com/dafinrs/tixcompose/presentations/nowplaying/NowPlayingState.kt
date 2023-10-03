package com.dafinrs.tixcompose.presentations.nowplaying

import androidx.compose.runtime.Immutable
import com.dafinrs.tixcompose.domain.model.MovieModel

sealed class NowPlayingState

@Immutable
object InitialNowPlaying : NowPlayingState()

@Immutable
object LoadingNowPlayingMovie : NowPlayingState()

@Immutable
data class SuccessNowPlayingMovie(val items: List<MovieModel>) : NowPlayingState()

@Immutable
data class ErrorMovie(val message: String = "Oops Something Wrong") : NowPlayingState()
