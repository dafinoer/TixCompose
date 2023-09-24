package com.dafinrs.tixcompose.ui.pages.ticket

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dafinrs.tixcompose.domain.model.TicketModel
import com.dafinrs.tixcompose.utilities.simpleDate
import com.dafinrs.tixcompose.utilities.toFullPosterW300Url

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ComponentPageSlider(
    initialSlideIndex: Int = 0,
    itemsSoldTicket: List<TicketModel> = emptyList(),
    itemsActiveTickets: List<TicketModel> = emptyList(),
    onGoToSlideByIndex: (Int) -> Unit,
    onGoToDetailMovie: (Int) -> Unit
) {
    val pagerState = rememberPagerState(initialPage = initialSlideIndex) {
        2
    }
    LaunchedEffect(key1 = initialSlideIndex) {
        pagerState.animateScrollToPage(initialSlideIndex)
    }
    LaunchedEffect(key1 = Unit) {
        snapshotFlow { pagerState.currentPage }.collect {
            onGoToSlideByIndex(it)
        }
    }

    HorizontalPager(state = pagerState) {
        when (it) {
            0 -> {
                if (itemsSoldTicket.isEmpty()) {
                    BoxEmpty("Empty")
                } else {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(itemsActiveTickets.size) {
                            ComponentTicketList(
                                title = itemsActiveTickets[it].title,
                                imageUrl = itemsActiveTickets[it].imageUrl.toFullPosterW300Url(),
                                location = "Jakarta",
                                movieDate = itemsActiveTickets[it].movieDate?.simpleDate() ?: "",
                                totalTicket = itemsActiveTickets[it].totalQty,
                            ) {
                                onGoToDetailMovie(itemsActiveTickets[it].movieId)
                            }
                        }
                    }
                }
            }

            1 -> {
                if (itemsActiveTickets.isEmpty()) {
                    BoxEmpty("Empty")
                } else {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(itemsSoldTicket.size) {
                            ComponentTicketList(
                                title = itemsSoldTicket[it].title,
                                imageUrl = itemsSoldTicket[it].imageUrl.toFullPosterW300Url(),
                                location = "Jakarta",
                                movieDate = itemsSoldTicket[it].movieDate?.simpleDate() ?: "",
                                totalTicket = itemsSoldTicket[it].totalQty,
                            ) {
                                onGoToDetailMovie(itemsSoldTicket[it].movieId)
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
internal fun BoxEmpty(title: String) {
    Box(modifier = Modifier.fillMaxSize(), Alignment.Center) {
        Text(text = title)
    }
}