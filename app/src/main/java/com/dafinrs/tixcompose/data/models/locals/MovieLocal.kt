package com.dafinrs.tixcompose.data.models.locals

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dafinrs.tixcompose.domain.model.MovieModel
import com.dafinrs.tixcompose.utilities.toZoneDateTime
import java.util.Date

@Entity(tableName = "movie")
data class MovieLocal(
    @PrimaryKey val uid: Int,
    val title: String,
    val desc: String,
    @ColumnInfo(name = "image_url") val posterImageUrl: String,
    @ColumnInfo(name = "vote_count") val voteCount: Int = 0,
    @ColumnInfo(name = "vote_average") val voteAverage: Double = 0.0,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean = false,
    @ColumnInfo(name = "releaseDate") val releaseDate: Date,
)

fun MovieLocal.toEntity() = MovieModel(
    id = uid,
    title = title,
    desc = desc,
    releaseDate = releaseDate.toZoneDateTime(),
    postPath = posterImageUrl,
    votes = voteAverage,
    voteCount = voteCount,
)

