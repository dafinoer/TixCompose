package com.dafinrs.tixcompose.presentations.cinemalocation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dafinrs.tixcompose.domain.model.LocationModel
import com.dafinrs.tixcompose.domain.usecases.GetListLocationCinema
import com.dafinrs.tixcompose.domain.usecases.GetLocationUser
import com.dafinrs.tixcompose.domain.usecases.SaveLocationUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class CinemaLocationViewModel(
    private val getListLocationCinema: GetListLocationCinema,
    getLocationUser: GetLocationUser,
    private val saveLocationUser: SaveLocationUser,
) : ViewModel() {
    private val saveStateLocation = MutableStateFlow<CinemaLocationsState>(
        CinemaLocationsState.Idle
    )
    val saveStateLocationUI = saveStateLocation.asStateFlow()

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

    val getLocationUserFlow = getLocationUser.readLocation().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        null,
    )

    fun saveLocation(locationModel: LocationModel) {
        viewModelScope.launch {
            try {
                saveStateLocation.value = CinemaLocationsState.Loading
                saveLocationUser.saveLocation(locationModel)
                saveStateLocation.value = CinemaLocationsState.SaveSuccess
            } catch (error: Exception) {
                saveStateLocation.value = CinemaLocationsState.Error
            }
        }
    }
}