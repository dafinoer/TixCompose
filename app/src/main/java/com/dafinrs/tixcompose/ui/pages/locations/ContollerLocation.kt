package com.dafinrs.tixcompose.ui.pages.locations

import android.content.Context
import android.location.Location
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield


@Composable
fun rememberLocationUser(coroutineScope: CoroutineScope): ControllerLocation {
    val currentContext = LocalContext.current

    return remember {
        ControllerLocation(currentContext, coroutineScope)
    }
}


class ControllerLocation(private val context: Context, private val coroutineScope: CoroutineScope) {

    private val fusedLocation = LocationServices.getFusedLocationProviderClient(context)
    private val settingClient = LocationServices.getSettingsClient(context)
    private val locationSettingBuilder = LocationSettingsRequest.Builder()
    private val locationRequest = LocationRequest.Builder(10000).apply {
        setPriority(Priority.PRIORITY_BALANCED_POWER_ACCURACY)
    }.build()
    private var cancellationToken = CancellationTokenSource()


    @RequiresPermission(
        value = "android.permission.ACCESS_COARSE_LOCATION"
    )
    fun getLocation() {
        val locationRequestBuilded  = locationSettingBuilder.apply {
            addLocationRequest(locationRequest)
        }.build()
        val task = settingClient.checkLocationSettings(locationRequestBuilded)
        task.addOnSuccessListener {
            if (it.locationSettingsStates?.isLocationUsable == true) {
                coroutineScope.launch {
                    try {
                        val resultLocation = getCurrentLocation()
                        if (resultLocation != null) {
                            Log.i("RESULT", resultLocation.latitude.toString())
                        }
                    } catch (cancel: CancellationException) {
                        Log.d("CANCEL LOCATION", "Cancel Location", cancel)
                    } catch (error: Exception) {
                        Log.e("ERROR LOCATION", error.message, error)
                    }
                }
            }
        }

        task.addOnFailureListener {
            when(it) {
                is ResolvableApiException -> {

                } else -> Unit
            }
        }
    }

    @RequiresPermission(
        value = "android.permission.ACCESS_COARSE_LOCATION"
    )
    private suspend fun getCurrentLocation(): Location? {
        return withContext(Dispatchers.IO) {
            yield()
            fusedLocation.getCurrentLocation(
                Priority.PRIORITY_BALANCED_POWER_ACCURACY, cancellationToken.token
            ).result
        }
    }

    fun cancelLocation() {
        cancellationToken.cancel()
        cancellationToken.token.onCanceledRequested {
            cancellationToken = CancellationTokenSource()
        }
    }
}