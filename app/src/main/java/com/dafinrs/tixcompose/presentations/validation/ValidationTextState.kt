package com.dafinrs.tixcompose.presentations.validation

import androidx.annotation.StringRes
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.flow.MutableStateFlow


data class ValidationTextState(val isErrorForm: Boolean = false, val valueString: String)

fun MutableStateFlow<ValidationTextState>.success(arg: String) = value.copy(false, arg)
fun MutableStateFlow<ValidationTextState>.error(arg: String) = value.copy(true, arg)