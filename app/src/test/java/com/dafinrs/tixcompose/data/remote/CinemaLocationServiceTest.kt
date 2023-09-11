package com.dafinrs.tixcompose.data.remote

import com.squareup.moshi.Moshi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CinemaLocationServiceTest {

    lateinit var locationService: CinemaLocationService
    lateinit var moshi: Moshi

    @Before
    fun onSetRun() {
        moshi = Moshi.Builder().build()
       locationService = CinemaLocationService(moshi)
    }

    @Test
    fun getLocationTest() = runTest {
        val result = locationService.getCinemaLocation()
        Assert.assertEquals(result.size, 6)
        Assert.assertEquals(result[0].id, 0)
    }
}