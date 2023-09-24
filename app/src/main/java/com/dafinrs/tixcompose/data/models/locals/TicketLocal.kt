package com.dafinrs.tixcompose.data.models.locals

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dafinrs.tixcompose.domain.model.TicketModel
import com.dafinrs.tixcompose.domain.model.TicketStatusModel
import com.dafinrs.tixcompose.utilities.toDate
import com.dafinrs.tixcompose.utilities.toZoneDateTime
import java.util.Date

@Entity(tableName = "ticket")
data class TicketLocal(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    val movieId: Int,
    val title: String,
    val desc: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "total_qty") val totalQty: Int = 0,
    @ColumnInfo(name = "movie_date") val date: Date?,
    @ColumnInfo(name = "status_ticket") val statusTicket: StatusTicketLocal?,
    val createdAt: Date,
)

fun TicketModel.toTicketLocal() = TicketLocal(
    uid = ticketId,
    movieId = movieId,
    title = title,
    desc = desc,
    imageUrl = imageUrl,
    totalQty = totalQty,
    date = movieDate?.toDate(),
    statusTicket = when (ticketStatus) {
        TicketStatusModel.WAITING -> StatusTicketLocal.WAITING
        TicketStatusModel.SUCCESS -> StatusTicketLocal.SUCCESS
        TicketStatusModel.CANCEL -> StatusTicketLocal.CANCEL
        else -> null
    },
    createdAt = createdTicketDate.toDate(),
)

fun TicketLocal.toEntity() = TicketModel(
    ticketId = uid,
    movieId = movieId,
    title = title,
    desc = desc,
    imageUrl = imageUrl,
    totalQty = totalQty,
    ticketStatus = when (statusTicket) {
        StatusTicketLocal.WAITING -> TicketStatusModel.WAITING
        StatusTicketLocal.CANCEL -> TicketStatusModel.CANCEL
        StatusTicketLocal.SUCCESS -> TicketStatusModel.SUCCESS
        else -> null
    },
    movieDate = date?.toZoneDateTime(),
    createdTicketDate = createdAt.toZoneDateTime()
)