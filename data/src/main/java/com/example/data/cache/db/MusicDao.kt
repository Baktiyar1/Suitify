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

    @Query("select * from $MUSICS_TABLE_NAME where id == :musicId")
    suspend fun fetchMusicFromId(musicId: String): CacheMusic

    @Query("select * from $MUSICS_TABLE_NAME where id == :musicId")
    fun fetchMusicObservable(musicId: String): CacheMusic

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMusic(music: CacheMusic)

    @Query("UPDATE $MUSICS_TABLE_NAME SET title  =:title WHERE id = :id")
    suspend fun updateMusicTitle(title: String, id: String)

    @Query("UPDATE $MUSICS_TABLE_NAME SET executor =:executor WHERE id = :id")
    suspend fun updateMusicExecutor(executor: String, id: String)

    @Query("UPDATE $MUSICS_TABLE_NAME SET default_icon_id =:defaultIconId WHERE id = :id")
    suspend fun updateMusicPoster(defaultIconId: Int, id: String)

    @Query("UPDATE $MUSICS_TABLE_NAME SET saved_status =:savedStatus WHERE id = :id")
    suspend fun updateCacheMusicSavedStatus(savedStatus: CacheSavedStatus, id: String)

    @Query("DELETE FROM $MUSICS_TABLE_NAME WHERE id = :id")
    fun deleteById(id: String)

    @Query("DELETE FROM $MUSICS_TABLE_NAME")
    fun clearTable()

}