package com.dafinrs.tixcompose.presentations.validation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class ValidationViewModel : ViewModel() {

    private val textValidationState = MutableStateFlow(ValidationTextState(valueString = ""))
    val textValidationUIState = textValidationState.asStateFlow()

    fun onSetValue(value: String) {
        if (isInvalidFormat(value)) {
            textValidationState.success(value)
        } else {
            textValidationState.error(value)
        }
    }

    private fun isInvalidFormat(value: String): Boolean {
        return value.isNotEmpty() && value.isNotBlank()
    }
}