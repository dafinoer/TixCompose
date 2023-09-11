package com.dafinrs.tixcompose.presentations.sign

import androidx.annotation.StringRes
import com.dafinrs.tixcompose.R

enum class ErrorForm(@StringRes val errorDesc: Int) {
    EMPTY(R.string.error_empty_form)
}

data class SignViewState(
    val errorForm: ErrorForm? = null,
    val isActiveButton: Boolean = false,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null,
)
