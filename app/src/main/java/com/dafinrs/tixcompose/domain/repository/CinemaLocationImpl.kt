package com.dafinrs.tixcompose.domain.repository

import com.dafinrs.tixcompose.data.local.LocationUserStorage
import com.dafinrs.tixcompose.data.models.remote.LocationRemoteModel
import com.dafinrs.tixcompose.data.remote.CinemaLocationService
import com.dafinrs.tixcompose.data.repository.CinemasRepository
import com.dafinrs.tixcompose.domain.model.CinemasModel
import com.dafinrs.tixcompose.domain.model.LocationModel
import com.dafinrs.tixcompose.domain.model.MovieModel
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

    override fun cinemaList(): List<CinemasModel> = listOf(
        CinemasModel(typeName = "cgv", originalName = "CGV"),
        CinemasModel(typeName = "xx1", originalName = "XXI"),
        CinemasModel(typeName = "cinemapolis", originalName = "Cinemapolis")
    )

    override suspend fun addCinemaLocationToMovies(movies: List<MovieModel>): List<MovieModel> {
        val cinemas = cinemaList()
        val size = cinemas.size
        val result = withContext(coroutineDispatcher) {
            movies.map {
                val randomLength = Random.nextInt(1, size.plus(1))
                val movieCinemas = mutableSetOf<CinemasModel>()
                for (index in 0 until randomLength) {
                    val randomIndex = Random.nextInt(0, size)
                    movieCinemas.add(cinemas[randomIndex])
                }
                return@map it.copy(cinemaList = movieCinemas)
            }
        }

        return result
    }

    override suspend fun getCinemasLocation(): List<LocationModel> =
        withContext(coroutineDispatcher) {
            cinemaLocationService.getCinemaLocation().map { it.toEntity() }
        }

    override suspend fun writeCinemaLocation(locationModel: LocationModel) {
        locationUserStorage.saveLocation(locationModel)
    }

}