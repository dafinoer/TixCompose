package com.dafinrs.tixcompose.presentations.cinemalocation

import androidx.compose.runtime.Immutable
import com.dafinrs.tixcompose.domain.model.LocationModel

sealed class CinemaLocationsState {
    @Immutable
    data object Idle : CinemaLocationsState()

    @Immutable
    data object Loading : CinemaLocationsState()

    @Immutable
    data class Success(val locations: List<LocationModel>) : CinemaLocationsState()

    @Immutable
    data object SaveSuccess : CinemaLocationsState()

    @Immutable
    data object Error : CinemaLocationsState()
}
