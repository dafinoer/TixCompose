package com.dafinrs.tixcompose.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dafinrs.tixcompose.data.models.locals.StatusTicketLocal
import com.dafinrs.tixcompose.data.models.locals.TicketLocal
import kotlinx.coroutines.flow.Flow


@Dao
interface TicketDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTicket(vararg movieLocal: TicketLocal)

    @Query("SELECT * FROM ticket WHERE status_ticket = :statusTicketLocal")
    fun streamStatusTicket(statusTicketLocal: StatusTicketLocal): Flow<List<TicketLocal>>

}