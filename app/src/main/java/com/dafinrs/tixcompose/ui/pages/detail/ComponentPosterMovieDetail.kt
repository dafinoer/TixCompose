package com.dafinrs.tixcompose.ui.pages.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dafinrs.tixcompose.presentations.detail.DetailMovieState
import com.dafinrs.tixcompose.presentations.detail.SuccessMovie
import com.dafinrs.tixcompose.ui.components.PosterImageComponent
import com.dafinrs.tixcompose.utilities.toFullPosterW300Url


@Composable
fun ComponentPosterMovieDetail(detailMovieState: DetailMovieState) {
    when (detailMovieState) {
        is SuccessMovie -> {
            PosterImageComponent(
                imageUrl = detailMovieState.movieModel.postPath?.toFullPosterW300Url(),
                modifier = Modifier
                    .padding(top = 234.dp)
                    .absolutePadding(left = 16.dp)
            )
        }

        else -> Box(
            modifier = Modifier
                .padding(top = 234.dp)
                .absolutePadding(left = 16.dp)
                .width(90.dp)
                .height(150.dp)
                .clip(shape = RoundedCornerShape(12.dp))
                .background(color = Color.LightGray)
        )
    }
}