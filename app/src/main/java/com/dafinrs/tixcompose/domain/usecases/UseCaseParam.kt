package com.dafinrs.tixcompose.domain.usecases

interface UseCaseParam<T, E> {
    suspend fun call(arg: T): E
}