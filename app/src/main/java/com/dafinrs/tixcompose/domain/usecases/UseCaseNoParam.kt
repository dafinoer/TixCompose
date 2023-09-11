package com.dafinrs.tixcompose.domain.usecases

interface UseCaseNoParam<T> {
    fun call(): T
}