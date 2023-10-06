package com.dafinrs.tixcompose.data.models.locals

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dafinrs.tixcompose.domain.model.MovieModel
import com.dafinrs.tixcompose.utilities.toDate
import com.dafinrs.tixcompose.utilities.toZoneDateTime
import java.util.Date

@Entity(tableName = "favorite")
data class FavMovieLocal(
    @PrimaryKey val uid: Int,
    val title: String,
    val desc: String?,
    @ColumnInfo(name = "image_url") val posterImageUrl: String?,
    @ColumnInfo(name = "vote_count") val voteCount: Int = 0,
    @ColumnInfo(name = "vote_average") val voteAverage: Double = 0.0,
    @ColumnInfo(name = "releaseDate") val releaseDate: Date,
)

fun FavMovieLocal.toEntity() = MovieModel(
    id = uid,
    title = title,
    desc = desc,
    releaseDate = releaseDate.toZoneDateTime(),
    postPath = posterImageUrl,
    votes = voteAverage,
    voteCount = voteCount,
)

fun MovieModel.toFavMovieLocal() = FavMovieLocal(
    uid = id,
    title = title,
    desc = desc,
    posterImageUrl = postPath,
    voteCount = voteCount,
    voteAverage = votes,
    releaseDate = releaseDate.toDate(),
)