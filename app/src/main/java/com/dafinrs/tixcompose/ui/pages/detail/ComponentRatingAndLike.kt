package com.dafinrs.tixcompose.ui.pages.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dafinrs.tixcompose.R
import kotlin.math.roundToInt


@Composable
fun ComponentRatingAndLike(isWatchList: Boolean = false, onActionLikeButton: () -> Unit) {
    Column(modifier = Modifier.padding(vertical = 32.dp)) {
        Divider(
            thickness = 2.dp, color = MaterialTheme.colorScheme.surfaceVariant
        )
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth()
        ) {
            RatingBarInfo()

            LikeInfo(isWatchList = isWatchList) {
                onActionLikeButton()
            }
        }
        Divider(
            thickness = 8.dp, color = MaterialTheme.colorScheme.surfaceVariant
        )
    }
}


@Composable
internal fun RatingBarInfo(
    totalVote: Int = 0,
    rateAverage: Double = 0.0,
) {
    val ratingAverage by remember {
        derivedStateOf { (rateAverage / 2).roundToInt() }
    }

    Column {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.absolutePadding(right = 4.dp),
                text = "9.0",
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                color = colorResource(id = R.color.yellow_light)
            )
            RatingStar(totalStar = ratingAverage)
        }
        Text(text = "$totalVote Vote", style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
internal fun LikeInfo(
    totalPerson: Int = 0,
    isWatchList: Boolean = false,
    onActionLikeButton: (Boolean) -> Unit,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            if (isWatchList) {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onActionLikeButton(false) },
                    imageVector = Icons.Rounded.Favorite,
                    contentDescription = null,
                    tint = Color.Red
                )
            } else {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onActionLikeButton(true) },
                    imageVector = Icons.Rounded.Favorite,
                    contentDescription = null,
                    tint = Color.LightGray
                )
            }
            Text(
                text = "WATCHLIST",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.absolutePadding(left = 4.dp)
            )
        }
        Text(text = "$totalPerson Person", style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
internal fun RatingStar(totalStar: Int = 0) {
    for (index in 1..5) {
        if (index <= totalStar) {
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = colorResource(id = R.color.yellow_light)
            )
        } else {
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = Color.LightGray
            )
        }
    }
}