package com.example.data.cache.source.music

import com.example.data.cache.db.FavoriteMusicDao
import com.example.data.cache.db.MusicDao
import com.example.data.cache.models.CacheFavoriteMusic
import com.example.data.cache.models.CacheMusic
import com.example.data.cache.models.CacheSavedStatus
import com.example.data.cache.source.local.ContentResolverHelper
import com.example.data.models.DataMusic
import com.example.data.models.DataSavedStatus
import com.example.domain.base.Mapper
import javax.inject.Inject

class MusicCacheDataSourceImpl @Inject constructor(
    private val musicDao: MusicDao,
    private val favoriteMusicDao: FavoriteMusicDao,
    private val contentResolverHelper: ContentResolverHelper,
    private val mapMusicCacheToData: Mapper<CacheMusic, DataMusic>,
    private val mapMusicListCacheToData: Mapper<List<CacheMusic>, List<DataMusic>>,
    private val mapMusicDataToCache: Mapper<DataMusic, CacheMusic>,
    private val mapSavedStatusDataToCache: Mapper<DataSavedStatus, CacheSavedStatus>
) : MusicCacheDataSource {

    override var favoriteMusic: MutableList<Long> = mutableListOf()

    override suspend fun fetchAllMusics(): List<DataMusic> {
        favoriteMusic = fetchAllFavoritesMusics().toMutableList()
        return contentResolverHelper.getAudioData()
//        .filterNot { it in musicDao.fetchAllMusicsObservable() }
//        .onEach { musicDao.saveMusic(it) }
            .map {
                val isFavorite = it.musicId in favoriteMusic
                it.copy(
                    isFavorite = isFavorite,
                    savedStatus = if (isFavorite) CacheSavedStatus.SAVED else CacheSavedStatus.NOT_SAVED
                )
            }
            .let(mapMusicListCacheToData::map)
    }

    override fun fetchMusic(musicId: String): DataMusic =
        mapMusicCacheToData.map(musicDao.fetchMusicObservable(musicId = musicId))

    override suspend fun fetchMusicFromId(musicId: String): DataMusic =
        mapMusicCacheToData.map(musicDao.fetchMusicFromId(musicId = musicId))

    override suspend fun changeFavoriteMusic(musicId: Long) =
        if (musicId in favoriteMusic) favoriteMusicDao.deleteById(musicId)
        else favoriteMusicDao.saveMusic(CacheFavoriteMusic(musicId))

    override suspend fun addNewMusic(music: DataMusic) =
        musicDao.saveMusic(music = mapMusicDataToCache.map(music))

    override suspend fun clearFavoriteTable() = musicDao.clearTable()

    override suspend fun updateTitle(id: String, title: String) =
        musicDao.updateMusicTitle(title = title, musicId = id)

    override suspend fun updatePoster(id: String, iconId: Int) =
        musicDao.updateMusicPoster(defaultIconId = iconId, musicId = id)

    override suspend fun updateMusicSavedStatus(savedStatus: DataSavedStatus, id: String) =
        musicDao.updateCacheMusicSavedStatus(
            savedStatus = mapSavedStatusDataToCache.map(savedStatus), musicId = id
        )

    override suspend fun updateExecutor(id: String, executor: String) =
        musicDao.updateMusicExecutor(executor = executor, musicId = id)

    private suspend fun fetchAllFavoritesMusics(): List<Long> =
        favoriteMusicDao.fetchAllFavoriteMusics().map { it.musicId }.distinct()

}