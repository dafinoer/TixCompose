package com.dafinrs.tixcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dafinrs.tixcompose.domain.usecases.GetApiToken
import com.dafinrs.tixcompose.ui.pages.apikey.ApiKeyScreenPage
import com.dafinrs.tixcompose.ui.pages.base.BaseScreenPage
import com.dafinrs.tixcompose.ui.pages.detail.detailPage
import com.dafinrs.tixcompose.ui.pages.locations.LocationScreenPage
import com.dafinrs.tixcompose.ui.theme.TixComposeTheme
import com.dafinrs.tixcompose.utilities.NavigationPageKey
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val getApiToken by inject<GetApiToken>()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splash = installSplashScreen()
        var isHaveToken by mutableStateOf<Boolean?>(null)
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                val resultToken = getApiToken.call()
                isHaveToken = resultToken != null
            }
        }

        splash.setKeepOnScreenCondition { isHaveToken == null }

        setContent {
            TixComposeTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = startFirstUrl(isHaveToken ?: false),
                ) {
                    composable(NavigationPageKey.API_KEY_PAGE) {
                        ApiKeyScreenPage {
                            navController.navigate(NavigationPageKey.BASE_KEY_PAGE)
                        }
                    }
                    composable(NavigationPageKey.BASE_KEY_PAGE) {
                        BaseScreenPage(onSearchBar = {},
                            onGoToProfile = {},
                            onGoToNotification = {},
                            onTapLocation = {
                                navController.navigate(NavigationPageKey.LOCATION_CHOICE_KEY_PAGE)
                            }) {
                            navController.navigate("/detail/$it")
                        }
                    }
                    composable(NavigationPageKey.LOCATION_CHOICE_KEY_PAGE) {
                        LocationScreenPage {
                            navController.popBackStack()
                        }
                    }

                    this.detailPage {
                        navController.popBackStack()
                    }
                }
            }
        }
    }

    private fun startFirstUrl(isHaveApiKey: Boolean = false): String {
        if (isHaveApiKey) {
            return NavigationPageKey.BASE_KEY_PAGE
        }

        return NavigationPageKey.API_KEY_PAGE
    }
}