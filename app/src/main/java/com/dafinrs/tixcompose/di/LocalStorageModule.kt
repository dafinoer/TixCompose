package com.dafinrs.tixcompose.di

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.dataStore
import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.dafinrs.tixcompose.data.AppDatabase
import com.dafinrs.tixcompose.data.models.locals.LocationUserSerializer
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.core.annotation.Singleton

@Module
class LocalStorageModule {

    @Singleton
    fun bindEncryptionSharedPref(appContext: Context): SharedPreferences {
        return EncryptedSharedPreferences.create(
            "TixComposeSharedPref",
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            appContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
        )
    }

    @Singleton
    fun locationUser(appContext: Context) =
        dataStore("location_user.pb", serializer = LocationUserSerializer).getValue(
            appContext, LocationUserSerializer::defaultValue
        )

    @Single(createdAtStart = true)
    fun createRoomDataBase(applicationContext: Context) =
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-movie").build()

    @Factory
    fun createTicketDao(appDatabase: AppDatabase) = appDatabase.ticketDao()

    @Factory
    fun createMovieDao(appDatabase: AppDatabase) = appDatabase.movieDao()
}