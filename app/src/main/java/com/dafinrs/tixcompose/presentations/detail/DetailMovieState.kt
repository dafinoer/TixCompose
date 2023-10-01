package com.dafinrs.tixcompose.presentations.detail

import androidx.compose.runtime.Stable
import com.dafinrs.tixcompose.domain.model.MovieModel

sealed class DetailMovieState

@Stable
object Loading : DetailMovieState()

data class SuccessMovie(val movieModel: MovieModel) : DetailMovieState()

@Stable
object ErrorDetailMovie : DetailMovieState()
