package com.dafinrs.tixcompose.ui.pages.ticket

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun TicketScreenPage() {
    var indexTabRow by rememberSaveable {
        mutableIntStateOf(0)
    }

    Scaffold {
        Column(modifier = Modifier.padding(it), verticalArrangement = Arrangement.Top) {
            ComponentTabRow(
                indexTabRow = indexTabRow,
                onTabClick = {
                    indexTabRow = it
                },
            )
            ComponentPageSlider(initialSlideIndex = indexTabRow) {
                indexTabRow = it
            }
        }
    }
}