package com.dafinrs.tixcompose.domain.repository

import com.dafinrs.tixcompose.data.local.SecureStorage
import com.dafinrs.tixcompose.data.repository.SettingRepository
import com.dafinrs.tixcompose.utilities.LocalStorageKey
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam

@Factory(binds = [SettingRepository::class])
class SettingRepositoryImpl(
    private val secureStorage: SecureStorage,
    @InjectedParam private val dispatcher: CoroutineDispatcher
) : SettingRepository {

    override fun getApiKeyToken(): String? = secureStorage.getApiKey(LocalStorageKey.API_TOKEN)

    override suspend fun createApiToken(value: String) = withContext(dispatcher) {
        secureStorage.saveApiKey(LocalStorageKey.API_TOKEN, value)
    }

    override suspend fun updateApiToken(value: String) = withContext(dispatcher) {
        secureStorage.saveApiKey(LocalStorageKey.API_TOKEN, value)
    }
}