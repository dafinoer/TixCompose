package com.dafinrs.tixcompose.domain.usecases

import com.dafinrs.tixcompose.data.repository.SettingRepository
import com.dafinrs.tixcompose.utilities.LocalStorageKey
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class SaveApiKey(private val settingRepository: SettingRepository) :
    UseCaseParam<String, Unit> {

    override suspend fun call(arg: String) {
        settingRepository.createApiToken(arg)
    }
}