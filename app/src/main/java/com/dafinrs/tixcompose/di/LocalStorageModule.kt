package com.dafinrs.tixcompose.di

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.dafinrs.tixcompose.LocationUserMessage
import com.dafinrs.tixcompose.data.models.locals.LocationUserSerializer
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
}