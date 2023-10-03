package com.dafinrs.tixcompose.ui.pages.home.movie

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dafinrs.tixcompose.domain.model.CinemasModel


@Composable
fun MovieRowButtonCinema(
    onDisableChangeCinema: () -> Boolean,
    onTapButton: (String?) -> Unit,
) {
    val cinemas = itemsCompose()
    var userChoiceCinema by rememberSaveable {
        mutableStateOf<String?>(null)
    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp)
    ) {
        item {
            CardItem(
                onTapAction = {
                    if (!onDisableChangeCinema()) {
                        userChoiceCinema = null
                        onTapButton(null)
                    }
                },
                textName = "All",
                modifier = Modifier.padding(4.dp),
                isActive = userChoiceCinema == null
            )
        }

        items(cinemas.size) {
            CardItem(
                onTapAction = {
                    if (!onDisableChangeCinema()) {
                        userChoiceCinema = cinemas[it].typeName
                        onTapButton(cinemas[it].typeName)
                    }
                },
                textName = cinemas[it].originalName,
                modifier = Modifier.padding(4.dp),
                isActive = userChoiceCinema.equals(cinemas[it].typeName)
            )
        }
    }
}

@Composable
fun itemsCompose(): List<CinemasModel> {
    return remember {
        listOf(
            CinemasModel(typeName = "cgv", originalName = "CGV"),
            CinemasModel(typeName = "xx1", originalName = "XXI"),
            CinemasModel(typeName = "cinemapolis", originalName = "Cinemapolis")
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardItem(
    onTapAction: () -> Unit,
    modifier: Modifier = Modifier,
    textName: String,
    isActive: Boolean = false,
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