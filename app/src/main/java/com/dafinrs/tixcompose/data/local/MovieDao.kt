package com.dafinrs.tixcompose.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dafinrs.tixcompose.data.models.locals.MovieLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(vararg movieLocal: MovieLocal)

    @Query("SELECT * FROM movie WHERE is_favorite = 1")
    fun streamMovieWatch(): Flow<List<MovieLocal>>
}