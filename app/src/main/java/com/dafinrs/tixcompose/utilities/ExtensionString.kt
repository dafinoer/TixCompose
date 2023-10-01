package com.dafinrs.tixcompose.utilities

import java.time.LocalDate
import java.time.format.DateTimeFormatter


fun String.toFullPosterW300Url(): String = "https://image.tmdb.org/t/p/w300/$this"

fun String.backdropBaseUrl() = "https://image.tmdb.org/t/p/w1280/$this"

fun String.toSimpleLocalDate() = LocalDate.parse(
    this, DateTimeFormatter.ISO_DATE
)