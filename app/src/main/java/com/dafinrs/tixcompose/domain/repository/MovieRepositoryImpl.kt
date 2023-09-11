package com.dafinrs.tixcompose.domain.repository

import com.dafinrs.tixcompose.data.remote.MovieService
import com.dafinrs.tixcompose.data.repository.MovieRepository
import com.dafinrs.tixcompose.domain.model.MovieModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam

@Factory(binds = [MovieRepository::class])
class MovieRepositoryImpl(
    @InjectedParam private val coroutineDispatcher: CoroutineDispatcher,
    private val movieService: MovieService,
) : MovieRepository {

    override suspend fun getNowPlaying(page: Int): List<MovieModel> =
        withContext(coroutineDispatcher) {
            movieService.getNowPlaying(page)
        }
}