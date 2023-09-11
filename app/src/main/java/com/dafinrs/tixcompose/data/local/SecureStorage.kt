package com.dafinrs.tixcompose.data.local

import android.content.SharedPreferences
import org.koin.core.annotation.Factory

@Factory
class SecureStorage(private val sharedPreferences: SharedPreferences) {

    fun saveApiKey(keyValue: String, valueKey: String) = with(sharedPreferences.edit()) {
        putString(keyValue, valueKey)
        apply()
    }

    fun getApiKey(keyValue: String): String? = sharedPreferences.getString(keyValue, null)
}