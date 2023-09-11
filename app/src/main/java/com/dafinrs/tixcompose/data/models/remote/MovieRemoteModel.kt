package com.dafinrs.tixcompose.data.models.remote

import com.dafinrs.tixcompose.domain.model.MovieModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDate

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
) {
    fun toEntity() = MovieModel(
        id,
        title,
        desc = desc,
        postPath = posterPath,
        backdropPath = backdropPath,
        releaseDate = LocalDate.parse(releaseDate),
        voteCount = voteCount,
        votes = voteAverage
    )
}
