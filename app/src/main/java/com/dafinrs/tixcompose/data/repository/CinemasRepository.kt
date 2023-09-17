package com.dafinrs.tixcompose.data.repository

import com.dafinrs.tixcompose.domain.model.CinemasModel
import com.dafinrs.tixcompose.domain.model.LocationModel
import kotlinx.coroutines.flow.Flow

interface CinemasRepository {

    fun readLocationUser(): Flow<LocationModel>

    fun cinemaList(): Map<String, CinemasModel>

    suspend fun getCinemasLocation(): List<LocationModel>

    suspend fun writeCinemaLocation(locationModel: LocationModel)
}