package com.dafinrs.tixcompose.domain.usecases

import com.dafinrs.tixcompose.data.repository.CinemasRepository
import com.dafinrs.tixcompose.domain.model.LocationModel
import org.koin.core.annotation.Factory


class GetListLocationCinema(private val cinemasRepository: CinemasRepository) {

    suspend fun getLocations(): List<LocationModel> = cinemasRepository.getCinemasLocation()
}