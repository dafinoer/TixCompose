package com.dafinrs.tixcompose.ui.pages.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.dafinrs.tixcompose.R
import com.dafinrs.tixcompose.ui.theme.TixComposeTheme

@Composable
internal fun NavBottomBar(
    modifier: Modifier = Modifier,
    onTapHome: () -> Unit,
    onTapCinema: () -> Unit,
    onTapTicket: () -> Unit,
    currentPath: String?,
) {
    NavigationBar(modifier = modifier) {
        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Rounded.Home,
                    contentDescription = stringResource(id = R.string.tooltip_home)
                )
            },
            label = {
                Text(text = stringResource(id = R.string.home_navbar_bottom))
            },
            selected = currentPath == "home",
            onClick = onTapHome,
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.outline_location_city_24),
                    contentDescription = stringResource(id = R.string.cinema_navbar_bottom)
                )
            },
            label = {
                Text(text = stringResource(id = R.string.cinema_navbar_bottom))
            },
            selected = currentPath == "cinema",
            onClick = onTapCinema,
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.outline_confirmation_number_24),
                    contentDescription = stringResource(id = R.string.ticket_navbar_bottom)
                )
            },
            label = {
                Text(text = stringResource(id = R.string.ticket_navbar_bottom))
            },
            selected = currentPath == "ticket",
            onClick = onTapTicket,
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun PreviewBottomAppBar() {
    TixComposeTheme {
        Scaffold(bottomBar = {
            NavBottomBar(
                currentPath = "home",
                onTapCinema = {

                },
                onTapHome = {

                },
                onTapTicket = {

                },
            )
        }) {
            Box(modifier = Modifier.padding(it))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun PreviewBottomAppBarDark() {
    TixComposeTheme(darkTheme = true) {
        Scaffold(bottomBar = {
            NavBottomBar(
                currentPath = "home",
                onTapCinema = {

                },
                onTapHome = {

                },
                onTapTicket = {

                },
            )
        }) {
            Box(modifier = Modifier.padding(it))
        }
    }
}