package com.example.data.cache.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.cache.models.CacheMusic
import com.example.data.cache.models.CacheSavedStatus
import com.example.data.cache.models.MUSICS_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface MusicDao {

    @Query("select * from $MUSICS_TABLE_NAME")
    fun fetchAllMusicsObservable(): Flow<MutableList<CacheMusic>>

    @Query("select * from $MUSICS_TABLE_NAME")
    suspend fun fetchAllMusicsSingle(): MutableList<CacheMusic>

    @Query("select * from $MUSICS_TABLE_NAME where id == :bookId")
    suspend fun fetchMusicFromId(bookId: String): CacheMusic

    @Query("select * from $MUSICS_TABLE_NAME where id == :bookId")
    fun fetchMusicObservable(bookId: String): Flow<CacheMusic?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewMusic(book: CacheMusic)

    @Query("UPDATE $MUSICS_TABLE_NAME SET title  =:title WHERE id = :id")
    suspend fun updateMusicTitle(title: String, id: String)

    @Query("UPDATE $MUSICS_TABLE_NAME SET executor =:executor WHERE id = :id")
    suspend fun updateMusicAuthor(executor: String, id: String)

    @Query("UPDATE $MUSICS_TABLE_NAME SET icon_id =:iconId WHERE id = :id")
    suspend fun updateMusicPoster(iconId: Int, id: String)

    @Query("UPDATE $MUSICS_TABLE_NAME SET saved_status =:savedStatus WHERE id = :id")
    suspend fun updateCacheMusicSavedStatus(savedStatus: CacheSavedStatus, id: String)

    @Query("DELETE FROM $MUSICS_TABLE_NAME WHERE id = :id")
    fun deleteById(id: String)

    @Query("DELETE FROM $MUSICS_TABLE_NAME")
    fun clearTable()

}