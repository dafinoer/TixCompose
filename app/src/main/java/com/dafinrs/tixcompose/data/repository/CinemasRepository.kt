package com.dafinrs.tixcompose.data.repository

import com.dafinrs.tixcompose.domain.model.CinemasModel
import com.dafinrs.tixcompose.domain.model.LocationModel
import com.dafinrs.tixcompose.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface CinemasRepository {

    fun readLocationUser(): Flow<LocationModel>

    fun cinemaList(): List<CinemasModel>

    suspend fun addCinemaLocationToMovies(movies: List<MovieModel>): List<MovieModel>

    suspend fun getCinemasLocation(): List<LocationModel>

    suspend fun writeCinemaLocation(locationModel: LocationModel)
}