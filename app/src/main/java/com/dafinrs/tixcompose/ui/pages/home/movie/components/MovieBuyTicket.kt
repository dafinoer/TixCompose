package com.dafinrs.tixcompose.ui.pages.home.movie

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dafinrs.tixcompose.R
import com.dafinrs.tixcompose.ui.theme.ColorApp
import com.dafinrs.tixcompose.ui.theme.TixComposeTheme


@Composable
fun MovieBuyTicket(onTapBuy: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(
                Color(0xff003737),
                shape = RoundedCornerShape(
                    bottomEnd = 12.dp,
                    bottomStart = 12.dp
                )
            )
            .fillMaxWidth()
            .clickable { onTapBuy() }
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Text(
            text = stringResource(id = R.string.buy_ticket),
            style = MaterialTheme.typography.labelLarge,
            color = ColorApp.textColorBuyTicketButton,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMovieBuyTicket() {
    TixComposeTheme {
        MovieBuyTicket(onTapBuy = {

        })
    }
}