package com.dafinrs.tixcompose.di


import com.dafinrs.tixcompose.services.createKtorHttpClient
import com.squareup.moshi.Moshi
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class ThirdPartyModule {

    @Single
    fun httpClient() = createKtorHttpClient()

    @Single
    fun moshiBuilder(): Moshi = Moshi.Builder().build()
}