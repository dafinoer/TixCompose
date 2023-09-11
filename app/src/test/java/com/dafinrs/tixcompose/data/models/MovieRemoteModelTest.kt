package com.dafinrs.tixcompose.data.models

import com.dafinrs.tixcompose.data.models.remote.MovieRemoteModel
import com.dafinrs.tixcompose.domain.model.MovieModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import org.junit.Assert
import org.junit.Test


private val jsonDummy: String = """
    {
      "adult": false,
      "backdrop_path": "/iJQIbOPm81fPEGKt5BPuZmfnA54.jpg",
      "genre_ids": [
        16,
        12,
        10751,
        14,
        35
      ],
      "id": 502356,
      "original_language": "en",
      "original_title": "The Super Mario Bros. Movie",
      "overview": "While working underground to fix a water main, Brooklyn plumbers—and brothers—Mario and Luigi are transported down a mysterious pipe and wander into a magical new world. But when the brothers are separated, Mario embarks on an epic quest to find Luigi.",
      "popularity": 6572.614,
      "poster_path": "/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg",
      "release_date": "2023-04-05",
      "title": "The Super Mario Bros. Movie",
      "video": false,
      "vote_average": 7.5,
      "vote_count": 1456
    }
""".trimIndent()

class MovieRemoteModelTest {
    var moshiBuilder: Moshi = Moshi.Builder().build()

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun parseTest() {
        val modelAdapter = moshiBuilder.adapter<MovieRemoteModel>()
        val result = modelAdapter.fromJson(jsonDummy)
        Assert.assertEquals(result?.id, 502356)
    }
}