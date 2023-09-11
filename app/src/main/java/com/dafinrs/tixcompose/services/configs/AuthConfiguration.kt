package com.dafinrs.tixcompose.services.configs

import android.security.keystore.UserNotAuthenticatedException
import android.util.Log
import com.dafinrs.tixcompose.domain.usecases.GetApiToken
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.cio.CIOEngineConfig
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent

fun HttpClientConfig<CIOEngineConfig>.authConfiguration() {

    install(Auth) {
        val getTokenApiKey = KoinJavaComponent.get<GetApiToken>(clazz = GetApiToken::class.java)
        bearer {
            loadTokens {
                val apiToken = withContext(Dispatchers.IO) {
                    return@withContext getTokenApiKey.call()
                }
                if (apiToken != null) {
                    BearerTokens(apiToken, apiToken)
                } else {
                    throw UserNotAuthenticatedException("apikey not found")
                }
            }
        }
    }
}