package com.dafinrs.tixcompose.data.repository

interface SettingRepository {

    fun getApiKeyToken(): String?

    suspend fun createApiToken(value: String)

    suspend fun updateApiToken(value: String)
}