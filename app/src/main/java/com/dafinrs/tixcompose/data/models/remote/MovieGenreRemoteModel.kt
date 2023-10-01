package com.dafinrs.tixcompose.data.models.remote

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class MovieGenreRemoteModel(val id: Int, val name: String)