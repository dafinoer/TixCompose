package com.dafinrs.tixcompose.data.remote

import com.dafinrs.tixcompose.data.models.remote.MovieRemoteModel
import com.dafinrs.tixcompose.data.models.remote.MoviesRemoteModel
import com.dafinrs.tixcompose.domain.model.MovieModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.appendPathSegments
import org.koin.core.annotation.Factory

@Factory
class MovieService(private val httpClient: HttpClient, private val moshi: Moshi) {

    @OptIn(ExperimentalStdlibApi::class)
    suspend fun getNowPlaying(page: Int = 1, language: String = "en_Us"): List<MovieModel> {
        val httpResponse = httpClient.get("movie/now_playing") {
            url {
                parameters.append("language", language)
                parameters.append("page", page.toString())
            }
        }
        val jsonAdapter = moshi.adapter<MoviesRemoteModel>()
        val responseResult = jsonAdapter.fromJson(httpResponse.bodyAsText())

        return responseResult?.toEntity() ?: emptyList()
    }

    @OptIn(ExperimentalStdlibApi::class)
    suspend fun getDetail(movieId: Int): MovieRemoteModel? {
        val httpResponse = httpClient.get("movie") {
            url { appendPathSegments(movieId.toString()) }
        }
        val jsonAdapter = moshi.adapter<MovieRemoteModel>()

        return jsonAdapter.fromJson(httpResponse.bodyAsText())
    }
}