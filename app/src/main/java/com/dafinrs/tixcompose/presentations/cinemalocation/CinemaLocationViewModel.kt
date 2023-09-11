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
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.InjectedParam

@KoinViewModel
class CinemaLocationViewModel(
    @InjectedParam private val getListLocationCinema: GetListLocationCinema,
    private val getLocationUser: GetLocationUser,
    private val saveLocationUser: SaveLocationUser,
) : ViewModel() {

    private val mutableLocationUser = MutableStateFlow<LocationModel?>(value = null)
    val locationUserState = mutableLocationUser.asStateFlow()

    val locationUiState = flow {
        emit(CinemaLocationsState.Loading)
        val locations = getListLocationCinema.getLocations()
        emit(CinemaLocationsState.Success(locations))
    }.catch {
        Log.e("LOCATION_LIST", it.message, it)
        emit(CinemaLocationsState.Error)
    }

    init {
        viewModelScope.launch {
            getLocationUser.readLocation().catch {
                Log.e("CinemaLocationViewModel", it.message ?: "", it)
            }.shareIn(
                viewModelScope, replay = 1, started = SharingStarted.WhileSubscribed(5_000)
            ).collect {
                if (it.name.isNotEmpty()) {
                    mutableLocationUser.value = it
                }
            }
        }
    }

    fun saveLocation(locationModel: LocationModel) {
        viewModelScope.launch {
            saveLocationUser.saveLocation(locationModel)
        }
    }
}