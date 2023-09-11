package com.dafinrs.tixcompose.ui.pages.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dafinrs.tixcompose.R
import com.dafinrs.tixcompose.ui.theme.TixComposeTheme


@Composable
fun HomeAppBar(
    onClickProfile: () -> Unit,
    onClickNotification: () -> Unit,
    onClickSearchBar: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                    .fillMaxWidth()
                    .weight(1f),
                color = MaterialTheme.colorScheme.secondaryContainer
            ) {
                Row(
                    modifier = Modifier.padding(8.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Rounded.Search,
                        contentDescription = "Search",
                        tint = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        text = stringResource(id = R.string.find_in_tix),
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }
            IconButton(onClick = onClickProfile) {
                Icon(
                    Icons.Rounded.Person,
                    contentDescription = stringResource(id = R.string.tooltip_search),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
            IconButton(onClick = onClickNotification) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_notifications_none_24),
                    contentDescription = stringResource(id = R.string.tooltip_notification),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewAppBar() {
    TixComposeTheme {
        Scaffold(topBar = {
            HomeAppBar(
                onClickProfile = {},
                onClickNotification = {},
                onClickSearchBar = {},
            )
        }) {
            Box(modifier = Modifier.padding(it))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDarkAppBar() {
    TixComposeTheme(darkTheme = true) {
        Scaffold(topBar = {
            HomeAppBar(
                onClickProfile = {},
                onClickNotification = {},
                onClickSearchBar = {},
            )
        }) {
            Box(modifier = Modifier.padding(it))
        }
    }
}