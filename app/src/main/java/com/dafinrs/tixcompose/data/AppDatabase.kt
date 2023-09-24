package com.dafinrs.tixcompose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.dafinrs.tixcompose.data.local.MovieDao
import com.dafinrs.tixcompose.data.local.TicketDao
import com.dafinrs.tixcompose.data.models.locals.MovieLocal
import com.dafinrs.tixcompose.data.models.locals.StatusTicketLocal
import com.dafinrs.tixcompose.data.models.locals.TicketLocal
import java.util.Date

class Converters {
    @TypeConverter
    fun fromDateToLong(date: Date?) = date?.time

    @TypeConverter
    fun fromLongToDate(value: Long?) = value?.let { Date(it) }

    @TypeConverter
    fun fromEnumStatusTicketToInt(statusTicket: StatusTicketLocal?) = when (statusTicket) {
        StatusTicketLocal.WAITING -> 0
        StatusTicketLocal.SUCCESS -> 1
        StatusTicketLocal.CANCEL -> 2
        StatusTicketLocal.ACTIVE -> 3
        else -> null
    }

    @TypeConverter
    fun fromStatusTicketCodeToEnumTicket(value: Int?) = when (value) {
        0 -> StatusTicketLocal.WAITING
        1 -> StatusTicketLocal.SUCCESS
        2 -> StatusTicketLocal.CANCEL
        else -> null
    }
}

@Database(entities = [TicketLocal::class, MovieLocal::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ticketDao(): TicketDao
    abstract fun movieDao(): MovieDao
}

