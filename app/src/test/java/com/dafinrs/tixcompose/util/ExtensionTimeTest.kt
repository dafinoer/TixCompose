package com.dafinrs.tixcompose.util


import com.dafinrs.tixcompose.utilities.parseToZoneDateTime
import com.dafinrs.tixcompose.utilities.simpleDate
import com.dafinrs.tixcompose.utilities.toDate
import com.dafinrs.tixcompose.utilities.toZoneDateTime
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.Date

class ExtensionTimeTest {
    lateinit var testDateTime: ZonedDateTime
    lateinit var testDate: Date

    @Before
    fun initialize() {
        val localDate = LocalDate.of(2023, 9, 23)
        testDateTime = localDate.atStartOfDay().atZone(ZoneId.systemDefault())
        testDate = Date(testDateTime.withZoneSameInstant(ZoneOffset.UTC).toInstant().toEpochMilli())
    }

    @Test
    fun extensionToDate() {
        val result = testDateTime.toDate()
        Assert.assertEquals(result.time, 1695402000000)
    }

    @Test
    fun extensionToZoneDateTime() {
        val result = testDate.toZoneDateTime()
        Assert.assertEquals(result.dayOfMonth, 23)
        Assert.assertEquals(result.monthValue, 9)
        Assert.assertEquals(result.year, 2023)
    }

    @Test
    fun extensionFormatToSimpleDate() {
        val formatResult = testDateTime.simpleDate()
        Assert.assertEquals(formatResult, "23/09/2023")
    }

    @Test
    fun extensionLocalDateToZoneDateTime() {
        val result = "2023-09-23".parseToZoneDateTime()
        Assert.assertEquals(result.dayOfMonth, 23)
        Assert.assertEquals(result.monthValue, 9)
        Assert.assertEquals(result.year, 2023)
    }
}