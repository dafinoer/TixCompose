package com.dafinrs.tixcompose.ui.pages.apikey.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.dafinrs.tixcompose.presentations.sign.ErrorForm

@Composable
fun rememberApiKeyFormState(onValidationSuccess: (String) -> Unit): ApiKeyFormValidation {
    return remember {
        ApiKeyFormValidation(onValidationSuccess)
    }
}

@Stable
class ApiKeyFormValidation(val onValidationSuccess: (String) -> Unit) {

    val textState = mutableStateOf("")
        get() = field

    val errorForm = mutableStateOf<ErrorForm?>(null)
        get() = field


    fun onTyping(value: String) {
        textState.value = value
    }

    fun onValidation() {
        if (textState.value.isEmpty()) {
            errorForm.value = ErrorForm.EMPTY
        } else {
            errorForm.value = null
            onValidationSuccess(textState.value)
        }
    }
}