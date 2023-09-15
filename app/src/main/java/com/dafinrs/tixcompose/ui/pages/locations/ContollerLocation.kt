package com.dafinrs.tixcompose.ui.pages.locations

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import com.dafinrs.tixcompose.domain.model.LocationModel
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume


@Composable
fun rememberLocationUser(dispatcher: CoroutineDispatcher = Dispatchers.IO): PermissionLocationHolder {
    val currentContext = LocalContext.current

    return remember { PermissionLocationHolder(currentContext, dispatcher) }
}

sealed class PermissionLocationUserState {
    object Initial : PermissionLocationUserState()

    object LoadingGetLocation : PermissionLocationUserState()

    data class Success(val location: LocationModel?) : PermissionLocationUserState()

    object DisableGPS : PermissionLocationUserState()
    object Failure : PermissionLocationUserState()
}

class PermissionLocationHolder(
    private val context: Context, private val dispatcher: CoroutineDispatcher
) {
    val permissionState =
        mutableStateOf<PermissionLocationUserState>(PermissionLocationUserState.Initial)
    private val geoCoding = Geocoder(context)
    private val fusedLocation = LocationServices.getFusedLocationProviderClient(context)
    private val locationSettingBuilder = LocationSettingsRequest.Builder()
    private val locationRequest = LocationRequest.Builder(10000).apply {
        setPriority(Priority.PRIORITY_LOW_POWER)
    }.build()

    suspend fun findLocationFromDeviceLocation(
        cinemaLocations: List<LocationModel>
    ) {
        try {
            permissionState.value = PermissionLocationUserState.LoadingGetLocation
            val locations = findLocation()
            var locationModel: LocationModel? = null
            if (cinemaLocations.isNotEmpty() || locations.isNotEmpty()) {
                withContext(dispatcher) {
                    for (location in locations) {
                        if (locationModel != null) break
                        for (cinemaLocation in cinemaLocations) {
                            if (location.contains(cinemaLocation.name)) {
                                locationModel = cinemaLocation
                                break
                            }
                        }
                    }
                }
            }
            permissionState.value = PermissionLocationUserState.Success(locationModel)
        } catch (resolveError: ResolvableApiException) {
            permissionState.value = PermissionLocationUserState.DisableGPS
        } catch (error: Exception) {
            permissionState.value = PermissionLocationUserState.Failure
        }
    }

    @SuppressLint("MissingPermission")
    suspend fun findLocation(): List<String> {
        val locationRequestBuild = locationSettingBuilder.apply {
            addLocationRequest(locationRequest)
        }.build()
        when (isPermissionGrant()) {
            true -> {
                val isLocationEnable = isLocationUsable(locationRequestBuild)
                if (isLocationEnable) {
                    return getCurrentLocation()
                }
            }

            else -> Unit
        }

        return emptyList()
    }

    private fun isPermissionGrant(): Boolean {
        return ActivityCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private suspend fun isLocationUsable(settingRequest: LocationSettingsRequest): Boolean {
        return withContext(dispatcher) {
            val locationSetting =
                LocationServices.getSettingsClient(context).checkLocationSettings(settingRequest)
                    .await()
            locationSetting.locationSettingsStates?.isLocationUsable ?: false
        }
    }

    @RequiresPermission(
        value = "android.permission.ACCESS_COARSE_LOCATION"
    )
    private suspend fun getCurrentLocation(): List<String> {
        val latestLocation = withContext(dispatcher) {
            fusedLocation.getCurrentLocation(
                Priority.PRIORITY_LOW_POWER, CancellationTokenSource().token
            ).await()
        }
        return when (latestLocation != null) {
            true -> convertToLocationName(latestLocation.latitude, latestLocation.longitude)
            else -> emptyList()
        }
    }

    private suspend fun convertToLocationName(lat: Double, lon: Double): List<String> {
        return withContext(dispatcher) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                locationNameNewApi(lat, lon)
            } else {
                locationNameOldApi(lat, lon)
            }
        }
    }

    private fun locationNameOldApi(lat: Double, lon: Double): List<String> {
        val result = geoCoding.getFromLocation(lat, lon, 5)
        if (result?.isNotEmpty() == true) {
            return result.map { it.subAdminArea }
        }

        return emptyList()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private suspend fun locationNameNewApi(lat: Double, lon: Double): List<String> =
        suspendCancellableCoroutine { continuation ->
            try {
                geoCoding.getFromLocation(lat, lon, 5) {
                    if (it.isNotEmpty()) {
                        continuation.resume(it.map { value -> value.subAdminArea })
                    } else {
                        continuation.resume(emptyList())
                    }
                }
            } catch (error: Exception) {
                continuation.cancel(error)
            }
        }
}