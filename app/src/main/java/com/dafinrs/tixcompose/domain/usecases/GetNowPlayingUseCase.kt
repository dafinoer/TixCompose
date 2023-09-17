package com.dafinrs.tixcompose.domain.usecases

import com.dafinrs.tixcompose.data.repository.CinemasRepository
import com.dafinrs.tixcompose.data.repository.MovieRepository
import com.dafinrs.tixcompose.domain.model.MovieModel

class GetNowPlayingUseCase(
    private val movieRepository: MovieRepository, private val cinemasRepository: CinemasRepository
) : UseCaseParam<Int, List<MovieModel>> {

    override suspend fun call(arg: Int): List<MovieModel> {
        val movies = movieRepository.getNowPlaying(arg)
        return movies.map { it.copy(cinemaList = cinemasRepository.cinemaList()) }
    }

    suspend fun filterMovieByType(filterType: String, pageIndex: Int): List<MovieModel> {
        val movies = movieRepository.getNowPlaying(pageIndex)
            .map { it.copy(cinemaList = cinemasRepository.cinemaList()) }
        return movies.filter { it.cinemaList.containsKey(filterType) }
    }
}