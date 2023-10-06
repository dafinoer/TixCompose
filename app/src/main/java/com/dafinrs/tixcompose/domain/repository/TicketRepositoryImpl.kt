package com.dafinrs.tixcompose.domain.repository

import com.dafinrs.tixcompose.data.local.TicketDao
import com.dafinrs.tixcompose.data.models.locals.StatusTicketLocal
import com.dafinrs.tixcompose.data.models.locals.toEntity
import com.dafinrs.tixcompose.data.models.locals.toTicketLocal
import com.dafinrs.tixcompose.data.repository.TicketRepository
import com.dafinrs.tixcompose.domain.model.TicketModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
class TicketRepositoryImpl(private val ticketDao: TicketDao) : TicketRepository {
    override fun streamSuccessTicket(): Flow<List<TicketModel>> =
        ticketDao.streamStatusTicket(StatusTicketLocal.SUCCESS).map {
            it.map { element -> element.toEntity() }
        }

    override fun streamActiveTicket(): Flow<List<TicketModel>> =
        ticketDao.streamStatusTicket(StatusTicketLocal.ACTIVE).map {
            it.map { element -> element.toEntity() }
        }

    override suspend fun buyTicket(ticket: TicketModel) {
        ticketDao.insertTicket(ticket.toTicketLocal())
    }
}