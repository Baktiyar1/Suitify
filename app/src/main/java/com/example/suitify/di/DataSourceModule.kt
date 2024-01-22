package com.example.suitify.di

import com.example.data.cache.db.MusicDao
import com.example.data.cache.models.CacheMusic
import com.example.data.cache.models.CacheSavedStatus
import com.example.data.cache.source.local.ContentResolverHelper
import com.example.data.cache.source.music.MusicCacheDataSource
import com.example.data.cache.source.music.MusicCacheDataSourceImpl
import com.example.data.models.DataMusic
import com.example.data.models.DataSavedStatus
import com.example.domain.DispatchersProvider
import com.example.domain.base.Mapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideMusicCacheDataSource(
        musicDao: MusicDao,
        contentResolverHelper: ContentResolverHelper,
        mapMusicCacheToData: Mapper<CacheMusic, DataMusic>,
        mapMusicListCacheToData: Mapper<List<CacheMusic>, List<DataMusic>>,
        mapMusicDataToCache: Mapper<DataMusic, CacheMusic>,
        mapSavedStatusDataToCache: Mapper<DataSavedStatus, CacheSavedStatus>
    ): MusicCacheDataSource = MusicCacheDataSourceImpl(
        musicDao = musicDao,
        contentResolverHelper = contentResolverHelper,
        mapMusicCacheToData = mapMusicCacheToData,
        mapMusicListCacheToData = mapMusicListCacheToData,
        mapMusicDataToCache = mapMusicDataToCache,
        mapSavedStatusDataToCache = mapSavedStatusDataToCache
    )

}