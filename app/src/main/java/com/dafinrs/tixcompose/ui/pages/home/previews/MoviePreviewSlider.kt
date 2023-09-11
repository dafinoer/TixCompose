package com.dafinrs.tixcompose.ui.pages.home.previews

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import com.dafinrs.tixcompose.domain.model.MovieModel
import com.dafinrs.tixcompose.ui.pages.home.movie.MovieSlider
import com.dafinrs.tixcompose.ui.theme.TixComposeTheme
import java.time.LocalDate


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun MoviePreviewSlider() {
    val itemModelDummy = MovieModel(
        0,
        "https://cdn.pixabay.com/photo/2023/06/20/10/06/cocktail-8076619_1280.jpg",
        releaseDate = LocalDate.now()
    )
    val items = mutableListOf<MovieModel>()
    for (index in 0..5) {
        items.add(itemModelDummy)
    }

    TixComposeTheme {
        Scaffold {
            val screenWidth = LocalConfiguration.current.screenWidthDp

            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxWidth()
            ) {
                MovieSlider(
                    movies = items,
                    screenWidthSize = screenWidth,
                    onChangeMovie = {

                    }
                ) {

                }
            }
        }
    }
}
