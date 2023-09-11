package com.dafinrs.tixcompose.presentations.sign

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dafinrs.tixcompose.domain.usecases.GetApiToken
import com.dafinrs.tixcompose.domain.usecases.SaveApiKey
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.InjectedParam

@KoinViewModel
class SignViewModel(
    @InjectedParam private val saveToken: SaveApiKey
) : ViewModel() {

    private val signState = MutableStateFlow(SignViewState())
    val signUiState: StateFlow<SignViewState> = signState.asStateFlow()

    fun onSaveToken(apiToken: String) {
        viewModelScope.launch {
            try {
                signState.value = signState.value.copy(
                    isLoading = true,
                    isSuccess = false,
                    error = null,
                )
                saveToken.call(apiToken)
                signState.value = signState.value.copy(isLoading = false, isSuccess = true)
            } catch (cancelation: CancellationException) {
                Log.i("CANCEL", cancelation.message ?: "", cancelation)
            } catch (error: Exception) {
                signState.value = signState.value.copy(isLoading = false, error = "Opps")
            }
        }
    }
}