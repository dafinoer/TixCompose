package com.dafinrs.tixcompose.domain.usecases

import com.dafinrs.tixcompose.data.repository.SettingRepository

class GetApiToken(private val settingRepository: SettingRepository) :
    UseCaseNoParam<String?> {

    override fun call(): String? = settingRepository.getApiKeyToken()
}