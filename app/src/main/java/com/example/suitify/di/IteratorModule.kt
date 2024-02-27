package com.example.suitify.di

import com.example.domain.repositories.MusicRepository
import com.example.domain.use_cases.AddNewMusicUseCase
import com.example.domain.use_cases.AddNewMusicUseCaseImpl
import com.example.domain.use_cases.ClearTableUseCase
import com.example.domain.use_cases.ClearTableUseCaseImpl
import com.example.domain.use_cases.FetchAllMusicsObservableUseCase
import com.example.domain.use_cases.FetchAllMusicsObservableUseCaseImpl
import com.example.domain.use_cases.FetchMusicFromIdUseCase
import com.example.domain.use_cases.FetchMusicFromIdUseCaseImpl
import com.example.domain.use_cases.FetchMusicObservableUseCase
import com.example.domain.use_cases.FetchMusicObservableUseCaseImpl
import com.example.domain.use_cases.ChangeFavoriteMusicUseCase
import com.example.domain.use_cases.ChangeFavoriteMusicUseCaseImpl
import com.example.domain.use_cases.UpdateExecutorUseCase
import com.example.domain.use_cases.UpdateExecutorUseCaseImpl
import com.example.domain.use_cases.UpdateMusicSavedStatusUseCase
import com.example.domain.use_cases.UpdateMusicSavedStatusUseCaseImpl
import com.example.domain.use_cases.UpdatePosterUseCase
import com.example.domain.use_cases.UpdatePosterUseCaseImpl
import com.example.domain.use_cases.UpdateTitleUseCase
import com.example.domain.use_cases.UpdateTitleUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object IteratorModule {

    @Provides
    @Singleton
    fun provideAddNewMusicUseCase(repository: MusicRepository): AddNewMusicUseCase =
        AddNewMusicUseCaseImpl(repository = repository)

    @Provides
    @Singleton
    fun provideClearTableUseCase(repository: MusicRepository): ClearTableUseCase =
        ClearTableUseCaseImpl(repository = repository)

    @Provides
    @Singleton
    fun provideFetchAllMusicsObservableUseCase(repository: MusicRepository): FetchAllMusicsObservableUseCase =
        FetchAllMusicsObservableUseCaseImpl(repository = repository)

    @Provides
    @Singleton
    fun provideFetchMusicFromIdUseCase(repository: MusicRepository): FetchMusicFromIdUseCase =
        FetchMusicFromIdUseCaseImpl(repository = repository)

    @Provides
    @Singleton
    fun provideFetchMusicObservableUseCase(repository: MusicRepository): FetchMusicObservableUseCase =
        FetchMusicObservableUseCaseImpl(repository = repository)

    @Provides
    @Singleton
    fun provideSaveMusicsUseCase(repository: MusicRepository): ChangeFavoriteMusicUseCase =
        ChangeFavoriteMusicUseCaseImpl(repository = repository)

    @Provides
    @Singleton
    fun provideUpdateExecutorUseCase(repository: MusicRepository): UpdateExecutorUseCase =
        UpdateExecutorUseCaseImpl(repository = repository)

    @Provides
    @Singleton
    fun provideUpdateMusicSavedStatusUseCase(repository: MusicRepository): UpdateMusicSavedStatusUseCase =
        UpdateMusicSavedStatusUseCaseImpl(repository = repository)

    @Provides
    @Singleton
    fun provideUpdatePosterUseCase(repository: MusicRepository): UpdatePosterUseCase =
        UpdatePosterUseCaseImpl(repository = repository)

    @Provides
    @Singleton
    fun provideUpdateTitleUseCase(repository: MusicRepository): UpdateTitleUseCase =
        UpdateTitleUseCaseImpl(repository = repository)

}