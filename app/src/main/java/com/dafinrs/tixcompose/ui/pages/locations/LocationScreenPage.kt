package com.dafinrs.tixcompose.ui.pages.locations

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dafinrs.tixcompose.R
import com.dafinrs.tixcompose.domain.usecases.GetListLocationCinema
import com.dafinrs.tixcompose.presentations.cinemalocation.CinemaLocationViewModel
import com.dafinrs.tixcompose.presentations.cinemalocation.CinemaLocationsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.get


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationScreenPage(
    modifier: Modifier = Modifier,
    cinemaLocationViewModel: CinemaLocationViewModel = koinInject(
        parameters = {
            parametersOf(
                get(clazz = GetListLocationCinema::class.java, parameters = {
                    parametersOf(Dispatchers.IO)
                }),
            )
        },
    ),
    onBackNavigation: () -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    var showDialogEnableGPS by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val rememberController = rememberLocationUser()
    val locationsState = cinemaLocationViewModel.locationUiState.collectAsStateWithLifecycle(
        initialValue = CinemaLocationsState.Loading
    )
    val snackbarMessageNoLocationData = stringResource(id = R.string.snackbar_error_nodata_location)

    LaunchedEffect(rememberController.permissionState.value) {
        when (val state = rememberController.permissionState.value) {
            is PermissionLocationUserState.Success -> {
                if (state.location != null) {
                    cinemaLocationViewModel.saveLocation(state.location)
                    onBackNavigation()
                } else {
                    snackbarHostState.showSnackbar(snackbarMessageNoLocationData)
                }
            }

            is PermissionLocationUserState.DisableGPS -> showDialogEnableGPS = true
            else -> Unit
        }
    }

    AlertDialogLocation(showDialogEnableGPS) { showDialogEnableGPS = false }

    Scaffold(snackbarHost = {
        SnackbarHost(snackbarHostState)
    }, topBar = {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = onBackNavigation) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            title = {
                Text(
                    text = stringResource(id = R.string.cinemas_location),
                    style = MaterialTheme.typography.headlineMedium,
                )
            },
        )
    }) {
        LazyColumn(modifier = modifier.padding(it)) {
            when (val state = locationsState.value) {
                is CinemaLocationsState.Success -> {
                    items(state.locations.size) {
                        ComponentCard(title = state.locations[it].name) {
                            cinemaLocationViewModel.saveLocation(state.locations[it])
                        }
                    }

                    item {
                        ComponentButtonLocation(rememberController.permissionState.value) {
                            coroutineScope.launch {
                                rememberController.findLocationFromDeviceLocation(state.locations)
                            }
                        }
                    }
                }

                else -> item {
                    Box(
                        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}