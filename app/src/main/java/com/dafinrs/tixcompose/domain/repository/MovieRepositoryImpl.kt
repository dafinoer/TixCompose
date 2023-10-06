package com.dafinrs.tixcompose.domain.repository

import com.dafinrs.tixcompose.data.local.FavMovieDao
import com.dafinrs.tixcompose.data.models.locals.toEntity
import com.dafinrs.tixcompose.data.models.locals.toFavMovieLocal
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
    private val movieDao: FavMovieDao,
) : MovieRepository {

    override suspend fun getNowPlaying(page: Int): List<MovieModel> =
        withContext(coroutineDispatcher) {
            movieService.getNowPlaying(page)
        }

    override suspend fun getDetail(movieId: Int): MovieModel? {
        val result = withContext(coroutineDispatcher) {
            movieService.getDetail(movieId)
        }

        return result?.toEntity()
    }

    override suspend fun insertFavMovie(movie: MovieModel) {
        val toMovieLocal = movie.toFavMovieLocal()
        withContext(coroutineDispatcher) {
            movieDao.insertMovie(toMovieLocal)
        }
    }

    override suspend fun loadFavMovies(): List<MovieModel> {
        val favMovie = withContext(coroutineDispatcher) {
            movieDao.loadFavMovies()
        }
        return favMovie.map { it.toEntity() }
    }

    override suspend fun deleteFavMovie(movieId: Int) {
        withContext(coroutineDispatcher) {
            movieDao.deleteFavMovieById(movieId)
        }
    }

    override suspend fun loadFavMovieById(movieId: Int): MovieModel? {
        val result = withContext(coroutineDispatcher) {
            movieDao.loadFavMovieById(movieId)
        }

        return result?.toEntity()
    }

}