package com.dafinrs.tixcompose.domain.usecases

import com.dafinrs.tixcompose.data.repository.TicketRepository
import com.dafinrs.tixcompose.domain.model.TicketModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam

@Factory
class BuyTicket(
    private val ticketRepository: TicketRepository,
    @InjectedParam private val dispatcher: CoroutineDispatcher
) {
    suspend fun buyTicket(ticketModel: TicketModel) = withContext(dispatcher) {
        ticketRepository.buyTicket(ticketModel)
    }
}