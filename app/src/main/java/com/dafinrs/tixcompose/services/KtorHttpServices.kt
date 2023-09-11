package com.dafinrs.tixcompose.services


import com.dafinrs.tixcompose.services.configs.authConfiguration
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.http.path


fun createKtorHttpClient(): HttpClient {
    return HttpClient(CIO) {
        expectSuccess = true
        install(Logging)
        defaultRequest {
            url("https://api.themoviedb.org/3/")
        }
        authConfiguration()
    }
}