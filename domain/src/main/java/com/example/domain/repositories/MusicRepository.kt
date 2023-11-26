package com.example.domain.repositories

import com.example.domain.common.Result
import com.example.domain.models.DomainMusic
import com.example.domain.models.DomainSavedStatus

interface MusicRepository {
    fun fletchAllLocalMusic(): Result<List<DomainMusic>>
    fun fetchAllMusicsObservable(): Result<List<DomainMusic>>
    fun fetchMusicObservable(musicId: String): Result<DomainMusic>
    suspend fun fetchMusicFromId(musicId: String): Result<DomainMusic>
    suspend fun saveMusics(musics: List<DomainMusic>)
    suspend fun addNewMusic(music: DomainMusic)
    suspend fun deleteMusic(id: String)
    suspend fun clearTable()
    suspend fun updateTitle(id: String, title: String)
    suspend fun updatePoster(id: String, iconId: Int)
    suspend fun updateMusicSavedStatus(id: String, savedStatus: DomainSavedStatus)
    suspend fun updateExecutor(id: String, executor: String)
}