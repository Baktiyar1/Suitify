package com.example.data.repository

import android.util.Log
import com.example.core.APPLICATION_ERROR_LOG_KEY
import com.example.core.DEFAULT_ERROR_MESSAGE
import com.example.data.cache.source.music.MusicCacheDataSource
import com.example.data.models.DataMusic
import com.example.data.models.DataSavedStatus
import com.example.domain.base.Mapper
import com.example.domain.common.Result
import com.example.domain.models.DomainMusic
import com.example.domain.models.DomainSavedStatus
import com.example.domain.repositories.MusicRepository
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(
    private val cacheDataSource: MusicCacheDataSource,
    private val mapMusicDataToDomain: Mapper<DataMusic, DomainMusic>,
    private val mapMusicListDataToDomain: Mapper<List<DataMusic>, List<DomainMusic>>,
    private val mapMusicDomainToData: Mapper<DomainMusic, DataMusic>,
    private val mapSavedStatusDomainToData: Mapper<DomainSavedStatus, DataSavedStatus>
) : MusicRepository {

    override suspend fun fetchAllMusicsObservable(): Result<List<DomainMusic>> = try {
        Result.Success(data = mapMusicListDataToDomain.map(cacheDataSource.fetchAllMusics()))
    } catch (e: Exception) {
        Log.e(
            APPLICATION_ERROR_LOG_KEY,
            "MusicRepositoryImpl fetchAllMusicsObservable: ${e.message ?: DEFAULT_ERROR_MESSAGE}"
        )
        Result.Error(DEFAULT_ERROR_MESSAGE)
    }

    override fun fetchMusicObservable(musicId: String): Result<DomainMusic> = try {
        Result.Success(data = mapMusicDataToDomain.map(cacheDataSource.fetchMusic(musicId = musicId)))
    } catch (e: Exception) {
        Log.e(
            APPLICATION_ERROR_LOG_KEY,
            "MusicRepositoryImpl fetchMusicObservable: ${e.message ?: DEFAULT_ERROR_MESSAGE}"
        )
        Result.Error(DEFAULT_ERROR_MESSAGE)
    }

    override suspend fun fetchMusicFromId(musicId: String): Result<DomainMusic> = try {
        Result.Success(mapMusicDataToDomain.map(cacheDataSource.fetchMusicFromId(musicId = musicId)))
    } catch (e: Exception) {
        Log.e(
            APPLICATION_ERROR_LOG_KEY,
            "MusicRepositoryImpl fetchMusicFromId: ${e.message ?: DEFAULT_ERROR_MESSAGE}"
        )
        Result.Error(DEFAULT_ERROR_MESSAGE)
    }

    override suspend fun changeFavoritesMusic(musicId: Long) =
        cacheDataSource.changeFavoriteMusic(musicId = musicId)

    override suspend fun addNewMusic(music: DomainMusic) =
        cacheDataSource.addNewMusic(music = mapMusicDomainToData.map(music))

    override suspend fun clearFavoriteTable() = cacheDataSource.clearFavoriteTable()

    override suspend fun updateTitle(id: String, title: String) =
        cacheDataSource.updateTitle(id = id, title = title)

    override suspend fun updatePoster(id: String, iconId: Int) =
        cacheDataSource.updatePoster(id = id, iconId = iconId)

    override suspend fun updateMusicSavedStatus(id: String, savedStatus: DomainSavedStatus) =
        cacheDataSource.updateMusicSavedStatus(
            savedStatus = mapSavedStatusDomainToData.map(savedStatus), id = id
        )

    override suspend fun updateExecutor(id: String, executor: String) =
        cacheDataSource.updateExecutor(id = id, executor = executor)
}