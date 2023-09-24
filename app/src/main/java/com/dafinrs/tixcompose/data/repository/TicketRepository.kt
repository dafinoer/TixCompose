package com.dafinrs.tixcompose.data.repository

import com.dafinrs.tixcompose.domain.model.TicketModel
import kotlinx.coroutines.flow.Flow

interface TicketRepository {
    fun streamSuccessTicket(): Flow<List<TicketModel>>

    fun streamActiveTicket(): Flow<List<TicketModel>>

    suspend fun buyTicket(ticket: TicketModel)
}