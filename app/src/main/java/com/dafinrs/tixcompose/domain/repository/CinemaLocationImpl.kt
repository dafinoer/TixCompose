package com.dafinrs.tixcompose.domain.repository

import com.dafinrs.tixcompose.data.local.LocationUserStorage
import com.dafinrs.tixcompose.data.remote.CinemaLocationService
import com.dafinrs.tixcompose.data.repository.CinemasRepository
import com.dafinrs.tixcompose.domain.model.CinemasModel
import com.dafinrs.tixcompose.domain.model.LocationModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam
import kotlin.random.Random

@Factory(binds = [CinemasRepository::class])
class CinemaLocationImpl(
    private val cinemaLocationService: CinemaLocationService,
    @InjectedParam private val coroutineDispatcher: CoroutineDispatcher,
    private val locationUserStorage: LocationUserStorage,
) : CinemasRepository {

    override fun readLocationUser(): Flow<LocationModel> {
        return locationUserStorage.readLocation().map { LocationModel(it.id, it.name) }
    }

    override fun cinemaList(): Map<String, CinemasModel> {
        val cinemas = cinemaLocationService.getCinemas()
        val size = cinemas.size
        val movieCinemas = mutableMapOf<String, CinemasModel>()
        val randomLength = Random.nextInt(1, cinemas.size.plus(1))
        for (index in 0 until randomLength) {
            val randomIndex = Random.nextInt(0, size)
            movieCinemas.putIfAbsent(cinemas[randomIndex].typeName, cinemas[randomIndex])
        }

        return movieCinemas.mapValues { it.value }
    }

    override suspend fun getCinemasLocation(): List<LocationModel> =
        withContext(coroutineDispatcher) {
            cinemaLocationService.getCinemaLocation().map { it.toEntity() }
        }

    override suspend fun writeCinemaLocation(locationModel: LocationModel) {
        locationUserStorage.saveLocation(locationModel)
    }

}