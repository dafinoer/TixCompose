package com.dafinrs.tixcompose.data.remote


import android.content.Context
import com.dafinrs.tixcompose.data.models.remote.LocationRemoteModel
import com.dafinrs.tixcompose.domain.model.CinemasModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory

@Factory
class CinemaLocationService(private val moshi: Moshi, private val context: Context) {

    @OptIn(ExperimentalStdlibApi::class)
    suspend fun getCinemaLocation(dispatcher: CoroutineDispatcher = Dispatchers.IO): List<LocationRemoteModel> {
        val file = withContext(dispatcher) {
            context.resources.assets.open("cinemas_location.json").bufferedReader().use {
                it.readText()
            }
        }
        val adapter = moshi.adapter<List<LocationRemoteModel>>()
        val results = adapter.fromJson(file)

        return results ?: emptyList()
    }

    fun getCinemas() = listOf(
        CinemasModel(typeName = "cgv", originalName = "CGV"),
        CinemasModel(typeName = "xx1", originalName = "XXI"),
        CinemasModel(typeName = "cinemapolis", originalName = "Cinemapolis")
    )
}