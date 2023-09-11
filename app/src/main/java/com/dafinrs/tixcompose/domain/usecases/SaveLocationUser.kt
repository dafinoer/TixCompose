package com.dafinrs.tixcompose.domain.usecases

import com.dafinrs.tixcompose.data.repository.CinemasRepository
import com.dafinrs.tixcompose.domain.model.LocationModel
import org.koin.core.annotation.Factory

@Factory
class SaveLocationUser(private val cinemasRepository: CinemasRepository) {

    suspend fun saveLocation(locationModel: LocationModel) {
        cinemasRepository.writeCinemaLocation(locationModel)
    }
}