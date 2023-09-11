package com.dafinrs.tixcompose.ui.pages.apikey

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dafinrs.tixcompose.R
import com.dafinrs.tixcompose.domain.repository.SettingRepositoryImpl
import com.dafinrs.tixcompose.domain.usecases.SaveApiKey
import com.dafinrs.tixcompose.presentations.sign.SignViewModel
import com.dafinrs.tixcompose.ui.components.TextFieldCompose
import com.dafinrs.tixcompose.ui.pages.apikey.component.ButtonSubmit
import com.dafinrs.tixcompose.ui.pages.apikey.state.rememberApiKeyFormState
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApiKeyScreenPage(
    onGoToHomePage: () -> Unit,
) {
    val userRepo = koinInject<SettingRepositoryImpl>(parameters = { parametersOf(Dispatchers.IO) })
    val saveApiKey = koinInject<SaveApiKey>(parameters = { parametersOf(userRepo) })
    val signViewModel: SignViewModel = koinViewModel(parameters = { parametersOf(saveApiKey) })
    val errorText = stringResource(id = R.string.save_success)
    val scaffoldState = remember {
        SnackbarHostState()
    }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = "Register Api Key")
            },
        )
    }, snackbarHost = { SnackbarHost(hostState = scaffoldState) }) {
        Column(modifier = Modifier.padding(it)) {
            val signState = signViewModel.signUiState.collectAsStateWithLifecycle()

            val apiKeyFormState = rememberApiKeyFormState(
                onValidationSuccess = {
                    signViewModel.onSaveToken(it)
                },
            )

            val isError = remember {
                derivedStateOf {
                    apiKeyFormState.errorForm.value != null
                }
            }

            LaunchedEffect(key1 = signState.value) {
                if (signState.value.isSuccess) {
                    scaffoldState.showSnackbar(errorText)
                    onGoToHomePage()
                }
            }


            TextFieldCompose(value = apiKeyFormState.textState.value,
                isError = isError.value,
                errorForm = apiKeyFormState.errorForm.value,
                onClearText = {
                    apiKeyFormState.onTyping(value = "")
                }) {
                apiKeyFormState.onTyping(it)
            }
            Spacer(modifier = Modifier.weight(1f))
            ButtonSubmit {
                apiKeyFormState.onValidation()
            }
        }
    }
}
