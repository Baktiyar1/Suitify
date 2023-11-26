package com.example.data.cache.source.music

import com.example.data.cache.db.MusicDao
import com.example.data.cache.models.CacheMusic
import com.example.data.cache.models.CacheSavedStatus
import com.example.data.models.DataMusic
import com.example.data.models.DataSavedStatus
import com.example.domain.base.Mapper
import javax.inject.Inject

class MusicCacheDataSourceImpl @Inject constructor(
    private val musicDao: MusicDao,
    private val mapMusicCacheToData: Mapper<CacheMusic, DataMusic>,
    private val mapMusicListCacheToData: Mapper<List<CacheMusic>, List<DataMusic>>,
    private val mapMusicDataToCache: Mapper<DataMusic, CacheMusic>,
    private val mapSavedStatusDataToCache: Mapper<DataSavedStatus, CacheSavedStatus>
) : MusicCacheDataSource {
    override fun fetchAllMusics(): List<DataMusic> =
        mapMusicListCacheToData.map(musicDao.fetchAllMusicsObservable())

    override fun fetchMusic(musicId: String): DataMusic =
        mapMusicCacheToData.map(musicDao.fetchMusicObservable(musicId = musicId))

    override suspend fun fetchMusicFromId(musicId: String): DataMusic =
        mapMusicCacheToData.map(musicDao.fetchMusicFromId(musicId = musicId))

    override suspend fun saveMusics(musics: List<DataMusic>) =
        musics.forEach { music -> musicDao.saveMusic(mapMusicDataToCache.map(music)) }

    override suspend fun addNewMusic(music: DataMusic) =
        musicDao.saveMusic(music = mapMusicDataToCache.map(music))

    override suspend fun deleteMusic(id: String) = musicDao.deleteById(id = id)

    override suspend fun clearTable() = musicDao.clearTable()

    override suspend fun updateTitle(id: String, title: String) =
        musicDao.updateMusicTitle(title = title, id = id)

    override suspend fun updatePoster(id: String, iconId: Int) =
        musicDao.updateMusicPoster(defaultIconId = iconId, id = id)

    override suspend fun updateMusicSavedStatus(savedStatus: DataSavedStatus, id: String) =
        musicDao.updateCacheMusicSavedStatus(
            savedStatus = mapSavedStatusDataToCache.map(savedStatus), id = id
        )

    override suspend fun updateExecutor(id: String, executor: String) =
        musicDao.updateMusicExecutor(executor = executor, id = id)
}