package com.dafinrs.tixcompose.di

import com.dafinrs.tixcompose.data.repository.CinemasRepository
import com.dafinrs.tixcompose.data.repository.MovieRepository
import com.dafinrs.tixcompose.data.repository.SettingRepository
import com.dafinrs.tixcompose.domain.usecases.GetApiToken
import com.dafinrs.tixcompose.domain.usecases.GetListLocationCinema
import com.dafinrs.tixcompose.domain.usecases.GetLocationUser
import com.dafinrs.tixcompose.domain.usecases.GetNowPlayingUseCase
import com.dafinrs.tixcompose.domain.usecases.SaveApiKey
import com.dafinrs.tixcompose.domain.usecases.SaveLocationUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.Module
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.get


@Module
class DomainsModule {
    @Factory
    fun getApiToken() = GetApiToken(get(parameters = {
        parametersOf(get(parameters = {
            parametersOf(Dispatchers.IO)
        }, clazz = CoroutineDispatcher::class.java))
    }, clazz = SettingRepository::class.java))

    @Factory
    fun saveApiKeyUseCase() = SaveApiKey(
        get(
            clazz = SettingRepository::class.java,
            parameters = {
                parametersOf(
                    get(clazz = CoroutineDispatcher::class.java,
                        parameters = { parametersOf(Dispatchers.IO) })
                )
            },
        )
    )

    @Factory
    fun nowPlayingUseCase(@InjectedParam dispatcher: CoroutineDispatcher) = GetNowPlayingUseCase(
        get(
            clazz = MovieRepository::class.java,
            parameters = {
                parametersOf(
                    get(clazz = CoroutineDispatcher::class.java,
                        parameters = { parametersOf(dispatcher) })
                )
            },
        ), get(clazz = CinemasRepository::class.java, parameters = {
            parametersOf(
                get(
                    clazz = CoroutineDispatcher::class.java,
                    parameters = {
                        parametersOf(dispatcher)
                    },
                )
            )
        })
    )

    @Factory
    fun getListLocationCinema(@InjectedParam dispatcher: CoroutineDispatcher): GetListLocationCinema =
        GetListLocationCinema(
            get(
                clazz = CinemasRepository::class.java,
                parameters = {
                    parametersOf(
                        get(
                            clazz = CoroutineDispatcher::class.java,
                            parameters = {
                                parametersOf(dispatcher)
                            },
                        )
                    )
                },
            )
        )

    @Factory
    fun getLocationUser() =
        GetLocationUser(get(clazz = CinemasRepository::class.java, parameters = {
            parametersOf(
                get(clazz = CoroutineDispatcher::class.java,
                    parameters = { parametersOf(Dispatchers.IO) })
            )
        }))

    @Factory
    fun saveLocationUser() = SaveLocationUser(
        get(
            clazz = CinemasRepository::class.java,
            parameters = {
                parametersOf(get(clazz = CoroutineDispatcher::class.java,
                    parameters = { parametersOf(Dispatchers.IO) }))
            },
        )
    )
}