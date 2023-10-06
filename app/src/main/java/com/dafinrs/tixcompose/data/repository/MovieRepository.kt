package com.dafinrs.tixcompose.data.repository


import com.dafinrs.tixcompose.domain.model.MovieModel

interface MovieRepository {
    suspend fun getNowPlaying(page: Int): List<MovieModel>

    suspend fun getDetail(movieId: Int): MovieModel?

    suspend fun insertFavMovie(movie: MovieModel)

    suspend fun loadFavMovies(): List<MovieModel>

    suspend fun deleteFavMovie(movieId: Int)

    suspend fun loadFavMovieById(movieId: Int): MovieModel?
}