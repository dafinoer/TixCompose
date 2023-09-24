package com.dafinrs.tixcompose.domain.usecases

import com.dafinrs.tixcompose.data.repository.TicketRepository

class GetTicketSoldUseCase(
    private val ticketRepository: TicketRepository
) {
    fun observerTicketSold() = ticketRepository.streamSuccessTicket()
}