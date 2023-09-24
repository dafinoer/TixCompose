package com.dafinrs.tixcompose.domain.model

import java.time.ZonedDateTime

data class TicketModel(
    val ticketId: Int,
    val movieId: Int,
    val title: String,
    val desc: String,
    val imageUrl: String,
    val totalQty: Int = 0,
    val movieDate: ZonedDateTime?,
    val ticketStatus: TicketStatusModel? = null,
    val createdTicketDate: ZonedDateTime,
)
