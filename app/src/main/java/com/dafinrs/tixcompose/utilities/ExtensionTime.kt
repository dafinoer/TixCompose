package com.dafinrs.tixcompose.utilities


import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

fun Date.toZoneDateTime() =
    ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.time), ZoneOffset.systemDefault())

fun ZonedDateTime.toDate() =
    Date(this.withZoneSameInstant(ZoneOffset.UTC).toInstant().toEpochMilli())

fun ZonedDateTime.simpleDate(): String {
    val simpleDateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val localDate = this.toLocalDate()
    return simpleDateFormat.format(localDate)
}

fun ZonedDateTime.fullDate(): String {
    val fullDateTimeFormat = DateTimeFormatter.ISO_ZONED_DATE_TIME
    return fullDateTimeFormat.format(this)
}

fun String.parseToZoneDateTime(): ZonedDateTime {
    val localDate = LocalDate.parse(this, DateTimeFormatter.ISO_DATE)
    return localDate.atStartOfDay(ZoneOffset.systemDefault())
}