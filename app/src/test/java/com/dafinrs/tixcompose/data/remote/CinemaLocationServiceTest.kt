package com.dafinrs.tixcompose.data.remote

import com.dafinrs.tixcompose.utilities.CinemaLocationFile
import com.squareup.moshi.Moshi
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CinemaLocationServiceTest {

    lateinit var locationService: CinemaLocationService
    lateinit var moshi: Moshi
    lateinit var cinemaLocationFile: CinemaLocationFile

    @Before
    fun onSetRun() {
        moshi = Moshi.Builder().build()
        cinemaLocationFile = mockk(relaxed = true)
        locationService = CinemaLocationService(moshi, cinemaLocationFile)
    }

    @Test
    fun getLocationTest() = runTest {
        coEvery { cinemaLocationFile.readCinemaLocationFile() } returns """
            [{"id": 2, "title": "Bogor"}]
        """.trimIndent()
        val result = locationService.getCinemaLocation()
        coEvery { cinemaLocationFile.readCinemaLocationFile() }

        Assert.assertEquals(result.size, 1)
    }
}