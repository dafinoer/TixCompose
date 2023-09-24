package com.dafinrs.tixcompose.utilities

import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory

@Factory
class CinemaLocationFile(private val context: Context) {

    suspend fun readCinemaLocationFile(dispatcher: CoroutineDispatcher = Dispatchers.IO): String {
        val cinemaLocationResult = withContext(dispatcher) {
            context.resources.assets.open("cinemas_location.json").let {
                it.bufferedReader().use { value -> value.readText() }
            }
        }
        return cinemaLocationResult
    }
}