package com.example.suitify.di

import com.example.data.cache.mappers.MapMusicFromCacheToData
import com.example.data.cache.mappers.MapMusicFromDataToCache
import com.example.data.cache.mappers.MapMusicListFromCacheToData
import com.example.data.cache.mappers.MapPlaylistFromCacheToData
import com.example.data.cache.mappers.MapPlaylistFromDataToCache
import com.example.data.cache.mappers.MapSavedStatusFromCacheToData
import com.example.data.cache.mappers.MapSavedStatusFromDataToCache
import com.example.data.cache.models.CacheMusic
import com.example.data.cache.models.CachePlaylist
import com.example.data.cache.models.CacheSavedStatus
import com.example.data.mappers.MapMusicFromDataToDomain
import com.example.data.mappers.MapMusicFromDomainToData
import com.example.data.mappers.MapMusicListFromDataToDomain
import com.example.data.mappers.MapPlaylistFromDataToDomain
import com.example.data.mappers.MapPlaylistFromDomainToData
import com.example.data.mappers.MapSavedStatusFromDataToDomain
import com.example.data.mappers.MapSavedStatusFromDomainToData
import com.example.data.models.DataMusic
import com.example.data.models.DataPlaylist
import com.example.data.models.DataSavedStatus
import com.example.domain.base.Mapper
import com.example.domain.models.DomainMusic
import com.example.domain.models.DomainPlaylist
import com.example.domain.models.DomainSavedStatus
import com.example.suitify.mappers.MapMusicFromDomainToUi
import com.example.suitify.mappers.MapMusicFromUiToDomain
import com.example.suitify.mappers.MapMusicListFromDomainToUi
import com.example.suitify.mappers.MapPlaylistFromDomainToUi
import com.example.suitify.mappers.MapPlaylistFromUiToDomain
import com.example.suitify.mappers.MapSavedStatusFromDomainToUi
import com.example.suitify.mappers.MapSavedStatusFromUiToDomain
import com.example.suitify.models.Music
import com.example.suitify.models.Playlist
import com.example.suitify.models.SavedStatus
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    @Singleton
    fun provideMapMusicFromDataToCache(mapSavedStatus: Mapper<DataSavedStatus, CacheSavedStatus>): Mapper<DataMusic, CacheMusic> =
        MapMusicFromDataToCache(mapSavedStatus = mapSavedStatus)

    @Provides
    @Singleton
    fun provideMapMusicFromCacheToData(mapSavedStatus: Mapper<CacheSavedStatus, DataSavedStatus>): Mapper<CacheMusic, DataMusic> =
        MapMusicFromCacheToData(mapSavedStatus = mapSavedStatus)

    @Provides
    @Singleton
    fun provideMapMusicFromDomainToData(mapSavedStatus: Mapper<DomainSavedStatus, DataSavedStatus>): Mapper<DomainMusic, DataMusic> =
        MapMusicFromDomainToData(mapSavedStatus = mapSavedStatus)

    @Provides
    @Singleton
    fun provideMapMusicListFromDataToDomain(mapSavedStatus: Mapper<DataSavedStatus, DomainSavedStatus>): Mapper<DataMusic, DomainMusic> =
        MapMusicFromDataToDomain(mapSavedStatus = mapSavedStatus)

    @Provides
    @Singleton
    fun provideMapMusicListFromUiToDomain(mapSavedStatus: Mapper<SavedStatus, DomainSavedStatus>): Mapper<Music, DomainMusic> =
        MapMusicFromUiToDomain(mapSavedStatus = mapSavedStatus)

    @Provides
    @Singleton
    fun provideMapMusicFromDomainToUi(mapSavedStatus: Mapper<DomainSavedStatus, SavedStatus>): Mapper<DomainMusic, Music> =
        MapMusicFromDomainToUi(mapSavedStatus = mapSavedStatus)

    @Provides
    @Singleton
    fun provideMapPlaylistFromCacheToData(
        mapMusic: Mapper<CacheMusic, DataMusic>,
        mapSavedStatus: Mapper<CacheSavedStatus, DataSavedStatus>
    ): Mapper<CachePlaylist, DataPlaylist> = MapPlaylistFromCacheToData(
        mapMusic = mapMusic, mapSavedStatus = mapSavedStatus
    )

    @Provides
    @Singleton
    fun provideMapPlaylistFromDataToCache(
        mapMusic: Mapper<DataMusic, CacheMusic>,
        mapSavedStatus: Mapper<DataSavedStatus, CacheSavedStatus>
    ): Mapper<DataPlaylist, CachePlaylist> = MapPlaylistFromDataToCache(
        mapMusic = mapMusic, mapSavedStatus = mapSavedStatus
    )

    @Provides
    @Singleton
    fun provideMapPlaylistFromDataToDomain(
        mapMusic: Mapper<DataMusic, DomainMusic>,
        mapSavedStatus: Mapper<DataSavedStatus, DomainSavedStatus>
    ): Mapper<DataPlaylist, DomainPlaylist> = MapPlaylistFromDataToDomain(
        mapMusic = mapMusic, mapSavedStatus = mapSavedStatus
    )

    @Provides
    @Singleton
    fun provideMapPlaylistFromDomainToData(
        mapMusic: Mapper<DomainMusic, DataMusic>,
        mapSavedStatus: Mapper<DomainSavedStatus, DataSavedStatus>
    ): Mapper<DomainPlaylist, DataPlaylist> = MapPlaylistFromDomainToData(
        mapMusic = mapMusic, mapSavedStatus = mapSavedStatus
    )

    @Provides
    @Singleton
    fun provideMapPlaylistFromDomainToUi(
        mapMusic: Mapper<DomainMusic, Music>, mapSavedStatus: Mapper<DomainSavedStatus, SavedStatus>
    ): Mapper<DomainPlaylist, Playlist> = MapPlaylistFromDomainToUi(
        mapMusic = mapMusic, mapSavedStatus = mapSavedStatus
    )

    @Provides
    @Singleton
    fun provideMapPlaylistFromUiToDomain(
        mapMusic: Mapper<Music, DomainMusic>, mapSavedStatus: Mapper<SavedStatus, DomainSavedStatus>
    ): Mapper<Playlist, DomainPlaylist> = MapPlaylistFromUiToDomain(
        mapMusic = mapMusic, mapSavedStatus = mapSavedStatus
    )

    @Provides
    @Singleton
    fun provideMapSavedStatusCacheToData(): Mapper<CacheSavedStatus, DataSavedStatus> =
        MapSavedStatusFromCacheToData()

    @Provides
    @Singleton
    fun provideMapSavedStatusDataToCache(): Mapper<DataSavedStatus, CacheSavedStatus> =
        MapSavedStatusFromDataToCache()

    @Provides
    @Singleton
    fun provideMapSavedStatusDataToDomain(): Mapper<DataSavedStatus, DomainSavedStatus> =
        MapSavedStatusFromDataToDomain()

    @Provides
    @Singleton
    fun provideMapSavedStatusDomainToData(): Mapper<DomainSavedStatus, DataSavedStatus> =
        MapSavedStatusFromDomainToData()

    @Provides
    @Singleton
    fun provideMapSavedStatusDomainToUi(): Mapper<DomainSavedStatus, SavedStatus> =
        MapSavedStatusFromDomainToUi()

    @Provides
    @Singleton
    fun provideMapSavedStatusUiToDomain(): Mapper<SavedStatus, DomainSavedStatus> =
        MapSavedStatusFromUiToDomain()

    @Provides
    @Singleton
    fun provideMapMusicListFromCacheToData(mapMusic: Mapper<CacheMusic, DataMusic>): Mapper<List<CacheMusic>, List<DataMusic>> =
        MapMusicListFromCacheToData(mapMusic = mapMusic)

    @Provides
    @Singleton
    fun provideMapMusicFromDataToDomain(mapMusic: Mapper<DataMusic, DomainMusic>): Mapper<List<DataMusic>, List<DomainMusic>> =
        MapMusicListFromDataToDomain(mapMusic = mapMusic)

    @Provides
    @Singleton
    fun provideMapListMusicFromDomainToUi(mapMusic: Mapper<DomainMusic, Music>): Mapper<List<DomainMusic>, List<Music>> =
        MapMusicListFromDomainToUi(mapMusic = mapMusic)

}