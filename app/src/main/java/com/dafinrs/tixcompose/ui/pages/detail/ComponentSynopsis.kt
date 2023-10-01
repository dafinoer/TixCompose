package com.dafinrs.tixcompose.ui.pages.detail

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dafinrs.tixcompose.presentations.detail.DetailMovieState
import com.dafinrs.tixcompose.presentations.detail.SuccessMovie


@Composable
fun ComponentSynopsis(detailMovieState: DetailMovieState) {
    when (detailMovieState) {
        is SuccessMovie -> if (detailMovieState.movieModel.desc.isNullOrEmpty()) {
            Text(
                text = "No Description",
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )
        } else {
            Text(
                text = detailMovieState.movieModel.desc,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )
        }

        else -> Text(
            text = "No Description", modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        )
    }

}