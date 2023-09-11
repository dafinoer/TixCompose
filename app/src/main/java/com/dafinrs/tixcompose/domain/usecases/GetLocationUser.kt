package com.dafinrs.tixcompose.domain.usecases

import com.dafinrs.tixcompose.data.repository.CinemasRepository
import com.dafinrs.tixcompose.domain.model.LocationModel
import kotlinx.coroutines.flow.Flow

class GetLocationUser(private val cinemasRepository: CinemasRepository) {

    fun readLocation(): Flow<LocationModel> = cinemasRepository.readLocationUser()
}