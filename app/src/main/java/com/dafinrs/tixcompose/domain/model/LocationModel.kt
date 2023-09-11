package com.dafinrs.tixcompose.domain.model

data class LocationModel(
    val id: Int,
    val name: String,
    val lat: Double? = null,
    val lon: Double? = null,
)