package com.dafinrs.tixcompose.ui.pages.home.slider

import androidx.compose.foundation.Canvas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun DotPositionSlider(
    modifier: Modifier = Modifier,
    currentIndex: Int, totalLength: Int,
) {

    Row(
        modifier = modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        repeat(totalLength) {
            DotItem(currentIndex = it == currentIndex, modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun DotItem(modifier: Modifier = Modifier, currentIndex: Boolean = false) {
    val colorCircleCurrentIndex = colorScheme.secondaryContainer
    val colorCircleDefault = colorScheme.onSecondaryContainer
    val size = modifier.size(12.dp)

    if (currentIndex) {
        Canvas(modifier = size) {
            drawCircle(color = colorCircleCurrentIndex)
        }
    } else {
        Canvas(modifier = size) {
            drawCircle(color = colorCircleDefault)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDotItems() {
    Box(modifier = Modifier.size(400.dp, height = 400.dp)) {
        DotPositionSlider(
            currentIndex = 2,
            totalLength = 5,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCurrentIndexDot() {
    DotItem(currentIndex = true)
}

@Preview(showBackground = true)
@Composable
private fun PreviewDot() {
    DotItem()
}