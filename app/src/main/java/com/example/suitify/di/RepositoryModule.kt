package com.example.suitify.di

import com.example.data.cache.source.music.MusicCacheDataSource
import com.example.data.models.DataMusic
import com.example.data.models.DataSavedStatus
import com.example.data.repository.MusicRepositoryImpl
import com.example.data.repository.PlaylistRepositoryImpl
import com.example.domain.base.Mapper
import com.example.domain.models.DomainMusic
import com.example.domain.models.DomainSavedStatus
import com.example.domain.repositories.MusicRepository
import com.example.domain.repositories.PlaylistRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMusicRepository(
        cacheDataSource: MusicCacheDataSource,
        mapMusicDataToDomain: Mapper<DataMusic, DomainMusic>,
        mapMusicListDataToDomain: Mapper<List<DataMusic>, List<DomainMusic>>,
        mapMusicDomainToData: Mapper<DomainMusic, DataMusic>,
        mapSavedStatusDomainToData: Mapper<DomainSavedStatus, DataSavedStatus>,
    ): MusicRepository = MusicRepositoryImpl(
        cacheDataSource = cacheDataSource,
        mapMusicDataToDomain = mapMusicDataToDomain,
        mapMusicListDataToDomain = mapMusicListDataToDomain,
        mapMusicDomainToData = mapMusicDomainToData,
        mapSavedStatusDomainToData = mapSavedStatusDomainToData,
    )

    @Provides
    @Singleton
    fun providePlaylistRepository(): PlaylistRepository = PlaylistRepositoryImpl()

}