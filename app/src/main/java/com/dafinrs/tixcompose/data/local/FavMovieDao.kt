package com.dafinrs.tixcompose.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dafinrs.tixcompose.data.models.locals.FavMovieLocal

@Dao
interface FavMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(vararg favMovieLocal: FavMovieLocal)

    @Query("SELECT * FROM favorite")
    suspend fun loadFavMovies(): List<FavMovieLocal>

    @Query("DELETE from favorite WHERE uid = :movieId")
    suspend fun deleteFavMovieById(vararg movieId: Int)

    @Query("SELECT * FROM favorite WHERE uid = :movieId")
    suspend fun loadFavMovieById(vararg movieId: Int): FavMovieLocal?
}