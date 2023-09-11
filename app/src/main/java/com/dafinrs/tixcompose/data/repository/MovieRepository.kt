package com.dafinrs.tixcompose.data.repository


import com.dafinrs.tixcompose.domain.model.MovieModel

interface MovieRepository {
    suspend fun getNowPlaying(page: Int): List<MovieModel>
}