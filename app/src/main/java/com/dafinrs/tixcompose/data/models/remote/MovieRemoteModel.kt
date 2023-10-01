package com.dafinrs.tixcompose.data.models.remote

import com.dafinrs.tixcompose.domain.model.MovieModel
import com.dafinrs.tixcompose.utilities.parseToZoneDateTime
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieRemoteModel(
    val id: Int,
    val title: String,
    @Json(name = "overview") val desc: String,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "vote_average") val voteAverage: Double = 0.0,
    @Json(name = "vote_count") val voteCount: Int = 0,
    val genres: List<MovieGenreRemoteModel> = emptyList(),
) {
    fun toEntity() = MovieModel(id,
        title,
        desc = desc,
        postPath = posterPath,
        backdropPath = backdropPath,
        releaseDate = releaseDate.parseToZoneDateTime(),
        voteCount = voteCount,
        votes = voteAverage,
        genres = genres.map { it.name },
        genreCompact = genres.let {
            when (it.isNotEmpty()) {
                true -> it.joinToString(separator = ", ") { value -> value.name }
                else -> null
            }
        })
}
