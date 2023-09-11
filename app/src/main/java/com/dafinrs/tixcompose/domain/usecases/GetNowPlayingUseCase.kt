package com.dafinrs.tixcompose.domain.usecases

import com.dafinrs.tixcompose.data.repository.CinemasRepository
import com.dafinrs.tixcompose.data.repository.MovieRepository
import com.dafinrs.tixcompose.domain.model.MovieModel

class GetNowPlayingUseCase(
    private val movieRepository: MovieRepository, private val cinemasRepository: CinemasRepository
) : UseCaseParam<Int, List<MovieModel>> {

    override suspend fun call(arg: Int): List<MovieModel> {
        val movies = movieRepository.getNowPlaying(arg)

        return cinemasRepository.addCinemaLocationToMovies(movies)
    }
}