package com.example.data.cache.source.music

import com.example.data.models.DataMusic
import com.example.data.models.DataSavedStatus

interface MusicCacheDataSource {

    fun fetchAllMusics(): List<DataMusic>

    fun fetchMusic(musicId: String): DataMusic

    suspend fun fetchMusicFromId(musicId: String): DataMusic

    suspend fun saveMusics(musics: List<DataMusic>)

    suspend fun addNewMusic(music: DataMusic)

    suspend fun deleteMusic(id: String)

    suspend fun clearTable()

    suspend fun updateTitle(id: String, title: String)

    suspend fun updatePoster(id: String, iconId: Int)

    suspend fun updateMusicSavedStatus(savedStatus: DataSavedStatus, id: String)

    suspend fun updateExecutor(id: String, executor: String)

}