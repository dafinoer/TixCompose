package com.dafinrs.tixcompose.presentations.cinemalocation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dafinrs.tixcompose.domain.model.LocationModel
import com.dafinrs.tixcompose.domain.usecases.GetListLocationCinema
import com.dafinrs.tixcompose.domain.usecases.GetLocationUser
import com.dafinrs.tixcompose.domain.usecases.SaveLocationUser
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.InjectedParam

@KoinViewModel
class CinemaLocationViewModel(
    @InjectedParam private val getListLocationCinema: GetListLocationCinema,
    getLocationUser: GetLocationUser,
    private val saveLocationUser: SaveLocationUser,
) : ViewModel() {
    val locationUiState = flow {
        emit(CinemaLocationsState.Loading)
        val locations = getListLocationCinema.getLocations()
        emit(CinemaLocationsState.Success(locations))
    }.catch {
        Log.e("LOCATION_LIST", it.message, it)
        emit(CinemaLocationsState.Error)
    }.stateIn(
        scope = viewModelScope,
        initialValue = CinemaLocationsState.Loading,
        started = SharingStarted.WhileSubscribed(5000)
    )

    val getLocationUserFlow = getLocationUser.readLocation().catch {
        Log.e("CinemaLocationViewModel", it.message ?: "", it)
    }.stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    fun saveLocation(locationModel: LocationModel) {
        viewModelScope.launch {
            saveLocationUser.saveLocation(locationModel)
        }
    }
}