package com.dafinrs.tixcompose.data.models.remote

import com.dafinrs.tixcompose.domain.model.LocationModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LocationRemoteModel(val id: Int, val title: String) {
    fun toEntity(): LocationModel = LocationModel(id = id, name = title)
    }
