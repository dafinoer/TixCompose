package com.dafinrs.tixcompose.ui.pages.locations

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    var isPermissionLocationGranted by remember { mutableStateOf<Boolean?>(null) }

    val locationResult = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
    ) {
        isPermissionLocationGranted = it
    }
    val locationsState = cinemaLocationViewModel.locationUiState.collectAsStateWithLifecycle(
        initialValue = CinemaLocationsState.Loading
    )

    when (isPermissionLocationGranted) {
        false -> {
            AlertDialogPermission(onDismissRequest = { isPermissionLocationGranted = null }) {
                isPermissionLocationGranted = null
            }
        }

        else -> Unit
    }

    Scaffold(topBar = {
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
                        ComponentButtonLocation {
                            locationResult.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
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