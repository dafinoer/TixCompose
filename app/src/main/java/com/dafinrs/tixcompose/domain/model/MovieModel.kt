package com.dafinrs.tixcompose.domain.model


import java.time.ZonedDateTime

data class MovieModel(
    val id: Int,
    val title: String,
    val releaseDate: ZonedDateTime,
    val voteCount: Int = 0,
    val desc: String? = null,
    val votes: Double = 0.0,
    val backdropPath: String? = null,
    val cinemaList: Map<String, CinemasModel> = emptyMap(),
    val postPath: String? = null,
)