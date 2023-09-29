package com.dafinrs.tixcompose.ui.pages.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dafinrs.tixcompose.R
import com.dafinrs.tixcompose.ui.components.PosterImageComponent
import com.dafinrs.tixcompose.ui.components.PrimaryElevatedButton
import com.dafinrs.tixcompose.ui.theme.TixComposeTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenPage(onBackButton: () -> Unit) {
    val backgroundPosterHeight = 250.dp

    Scaffold {
        Column(
            modifier = Modifier.padding(it)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                item {
                    Box {
                        BackdropImageComponent(
                            onPlayButton = { /*TODO*/ },
                            imageUrl = "",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(backgroundPosterHeight)
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(PaddingValues(top = 250.dp))
                                .absolutePadding(left = 122.dp, right = 16.dp)
                        ) {
                            ComponentMovieDetail(
                                titleMovie = "Lorem Ipsum",
                                directorName = "Lorem",
                            )
                        }
                        PosterImageComponent(
                            imageUrl = "",
                            modifier = Modifier
                                .padding(top = 234.dp)
                                .absolutePadding(left = 16.dp)
                        )
                    }
                }
                item {
                    ComponentRatingAndLike {

                    }
                }

                item {
                    CompoenentSynopsis("Lorem")
                }

                item {
                    ComponentCast()
                }
            }

            PrimaryElevatedButton(
                onAction = { /*TODO*/ }, title = stringResource(id = R.string.button_buy_now)
            )
        }

    }
}

@Preview
@Composable
fun PreviewScreenPage() {
    TixComposeTheme {
        DetailScreenPage {

        }
    }
}

@Preview
@Composable
fun PreviewDarkScreenPage() {
    TixComposeTheme(darkTheme = true) {
        DetailScreenPage {

        }
    }
}