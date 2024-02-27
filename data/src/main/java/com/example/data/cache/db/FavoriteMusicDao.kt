package com.example.data.cache.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.cache.models.CacheFavoriteMusic
import com.example.data.cache.models.FAVORITE_MUSICS_TABLE_NAME

@Dao
interface FavoriteMusicDao {

    @Query("select * from $FAVORITE_MUSICS_TABLE_NAME")
    suspend fun fetchAllFavoriteMusics(): List<CacheFavoriteMusic>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMusic(music: CacheFavoriteMusic)

    @Query("DELETE FROM $FAVORITE_MUSICS_TABLE_NAME WHERE musicId = :musicId")
    suspend fun deleteById(musicId: Long)

    @Query("DELETE FROM $FAVORITE_MUSICS_TABLE_NAME")
    fun clearTable()

}