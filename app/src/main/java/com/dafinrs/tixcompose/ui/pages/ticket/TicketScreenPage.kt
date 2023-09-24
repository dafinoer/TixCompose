package com.dafinrs.tixcompose.ui.pages.ticket


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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dafinrs.tixcompose.presentations.ticket.TicketSoldViewModel
import org.koin.androidx.compose.koinViewModel



@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun TicketScreenPage(
    ticketSoldViewModel: TicketSoldViewModel = koinViewModel(),
    onDetailMovie: (Int) -> Unit,
) {
    var indexTabRow by rememberSaveable { mutableIntStateOf(0) }

    Scaffold {
        Column(modifier = Modifier.padding(it), verticalArrangement = Arrangement.Top) {
            val ticketListState = ticketSoldViewModel.ticketState.collectAsStateWithLifecycle()
            val activeTicketsState =
                ticketSoldViewModel.ticketActiveState.collectAsStateWithLifecycle()

            ComponentTabRow(
                indexTabRow = indexTabRow,
                onTabClick = { indexTabRow = it },
            )
            ComponentPageSlider(initialSlideIndex = indexTabRow,
                itemsSoldTicket = ticketListState.value,
                itemsActiveTickets = activeTicketsState.value,
                onGoToSlideByIndex = { indexTabRow = it }) { onDetailMovie(it) }
        }
    }
}