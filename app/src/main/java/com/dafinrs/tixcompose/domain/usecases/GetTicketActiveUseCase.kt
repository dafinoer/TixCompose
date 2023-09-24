package com.dafinrs.tixcompose.domain.usecases

import com.dafinrs.tixcompose.data.repository.TicketRepository


class GetTicketActiveUseCase(val ticketRepository: TicketRepository) {
    fun streamTicketActive() = ticketRepository.streamActiveTicket()
}