package com.dafinrs.tixcompose.util

import com.dafinrs.tixcompose.utilities.toSimpleLocalDate
import org.junit.Assert
import org.junit.Test

class ExtensionStringTest {

    @Test
    fun stringToLocalDate() {
        val dateString = "2022-10-05"
        val result = dateString.toSimpleLocalDate()
        Assert.assertEquals(result.toString(), "2022-10-05")
    }
}