package com.dafinrs.tixcompose.presentations.ticket

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dafinrs.tixcompose.domain.usecases.GetTicketActiveUseCase
import com.dafinrs.tixcompose.domain.usecases.GetTicketSoldUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class TicketSoldViewModel(
    getTicketSoldUseCase: GetTicketSoldUseCase,
    getTicketActiveUseCase: GetTicketActiveUseCase
) : ViewModel() {

    val ticketState = getTicketSoldUseCase.observerTicketSold().catch {
        Log.e("ERROR Ticket", "error observer list", it)
    }.flowOn(Dispatchers.IO).stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList()
    )

    val ticketActiveState =
        getTicketActiveUseCase.streamTicketActive().flowOn(Dispatchers.IO).stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList()
        )
}