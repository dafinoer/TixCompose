package com.dafinrs.tixcompose.ui.pages.home.location


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dafinrs.tixcompose.R
import com.dafinrs.tixcompose.ui.theme.TixComposeTheme


@Composable
fun LocationCinema(
    modifier: Modifier = Modifier,
    locationName: String? = null,
    onClickLocation: () -> Unit,
) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .clickable { onClickLocation() },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(22.dp),
            imageVector = Icons.Rounded.LocationOn,
            contentDescription = stringResource(id = R.string.tooltip_location_on),
            tint = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = locationName ?: stringResource(id = R.string.cinemas_location),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(horizontal = 8.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onBackground
        )
        Icon(
            modifier = Modifier.size(22.dp),
            imageVector = Icons.Rounded.ArrowDropDown,
            contentDescription = stringResource(id = R.string.tooltip_location_on),
            tint = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewChangeLocation() {
    TixComposeTheme {
        LocationCinema(locationName = "Tangerang") {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewChangeLocationDark() {
    TixComposeTheme(darkTheme = true) {
        LocationCinema(locationName = "Tangerang") {

        }
    }
}