package com.dafinrs.tixcompose.data.local

import androidx.datastore.core.DataStore
import com.dafinrs.tixcompose.LocationUserMessage
import com.dafinrs.tixcompose.domain.model.LocationModel
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class LocationUserStorage(private val dataStoreLocation: DataStore<LocationUserMessage>) {

    fun readLocation(): Flow<LocationUserMessage> = dataStoreLocation.data

    suspend fun saveLocation(location: LocationModel) {
        dataStoreLocation.updateData {
            it.toBuilder().also { currentLocation ->
                currentLocation.id = location.id
                currentLocation.name = location.name
            }.build()
        }
    }
}