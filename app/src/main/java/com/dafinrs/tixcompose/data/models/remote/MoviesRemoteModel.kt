package com.dafinrs.tixcompose.data.models.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoviesRemoteModel(val results: List<MovieRemoteModel>) {
    fun toEntity() = results.map { it.toEntity() }
}