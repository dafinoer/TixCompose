package com.dafinrs.tixcompose.ui.pages.home.movie

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dafinrs.tixcompose.domain.model.CinemasModel
import com.dafinrs.tixcompose.ui.theme.TixComposeTheme


@Composable
fun MovieRowButtonCinema(
    modifier: Modifier = Modifier,
    cinemaType: CinemasModel?,
    onTapButton: (CinemasModel?) -> Unit,
) {
    val items = listOf(
        CinemasModel(typeName = "cgv", originalName = "CGV"),
        CinemasModel(typeName = "xx1", originalName = "XXI"),
        CinemasModel(typeName = "cinemapolis", originalName = "Cinemapolis")
    )

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp)
    ) {
        item {
            CardItem(
                onTapAction = { onTapButton(null) },
                textName = "All",
                modifier = Modifier.padding(4.dp),
                isActive = cinemaType == null
            )
        }

        items(items.size) {
            CardItem(
                onTapAction = { onTapButton(items[it]) },
                textName = items[it].originalName,
                modifier = Modifier.padding(4.dp),
                isActive = cinemaType?.typeName?.equals(items[it].typeName) ?: false
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardItem(
    onTapAction: () -> Unit,
    modifier: Modifier = Modifier,
    textName: String,
    isActive: Boolean = false
) {
    val colorScheme = if (isActive) {
        MaterialTheme.colorScheme.onSecondaryContainer
    } else {
        MaterialTheme.colorScheme.secondaryContainer
    }

    Surface(
        modifier = modifier.height(32.dp),
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.secondaryContainer,
        border = BorderStroke(1.dp, colorScheme),
        onClick = onTapAction,
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = textName,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textAlign = TextAlign.Center
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PreviewLight() {
    TixComposeTheme() {
        Scaffold {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                CardItem(
                    onTapAction = {},
                    textName = "Semua Bioskop",
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewDark() {
    TixComposeTheme(true) {
        Scaffold {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                CardItem(
                    onTapAction = {},
                    textName = "Cinema 21",
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PreviewHorizontalButton() {
    TixComposeTheme {
        Scaffold {
            MovieRowButtonCinema(
                modifier = Modifier.padding(it),
                cinemaType = null,
            ) {

            }
        }
    }
}