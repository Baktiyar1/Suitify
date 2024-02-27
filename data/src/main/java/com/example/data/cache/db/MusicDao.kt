package com.example.data.cache.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.cache.models.CacheMusic
import com.example.data.cache.models.CacheSavedStatus
import com.example.data.cache.models.MUSICS_TABLE_NAME

@Dao
interface MusicDao {

    @Query("select * from $MUSICS_TABLE_NAME")
    fun fetchAllMusicsObservable(): List<CacheMusic>

    @Query("select * from $MUSICS_TABLE_NAME where musicId == :musicId")
    suspend fun fetchMusicFromId(musicId: String): CacheMusic

    @Query("select * from $MUSICS_TABLE_NAME where musicId == :musicId")
    fun fetchMusicObservable(musicId: String): CacheMusic

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMusic(music: CacheMusic)

    @Query("UPDATE $MUSICS_TABLE_NAME SET title  =:title WHERE musicId = :musicId")
    suspend fun updateMusicTitle(title: String, musicId: String)

    @Query("UPDATE $MUSICS_TABLE_NAME SET executor =:executor WHERE musicId = :musicId")
    suspend fun updateMusicExecutor(executor: String, musicId: String)

    @Query("UPDATE $MUSICS_TABLE_NAME SET default_icon_id =:defaultIconId WHERE musicId = :musicId")
    suspend fun updateMusicPoster(defaultIconId: Int, musicId: String)

    @Query("UPDATE $MUSICS_TABLE_NAME SET saved_status =:savedStatus WHERE musicId = :musicId")
    suspend fun updateCacheMusicSavedStatus(savedStatus: CacheSavedStatus, musicId: String)

    @Query("DELETE FROM $MUSICS_TABLE_NAME WHERE musicId = :musicId")
    fun deleteById(musicId: String)

    @Query("DELETE FROM $MUSICS_TABLE_NAME")
    fun clearTable()

}