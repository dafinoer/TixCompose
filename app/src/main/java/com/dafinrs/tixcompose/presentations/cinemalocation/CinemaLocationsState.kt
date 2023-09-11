package com.dafinrs.tixcompose.presentations.cinemalocation

import com.dafinrs.tixcompose.domain.model.LocationModel

sealed class CinemaLocationsState {
    object Loading: CinemaLocationsState()
    data class Success(val locations: List<LocationModel>): CinemaLocationsState()
    object Error: CinemaLocationsState()
}
