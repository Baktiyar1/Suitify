package com.example.suitify.di

import android.content.Context
import androidx.room.Room
import com.example.data.cache.db.AppDatabase
import com.example.data.cache.db.MusicDao
import com.example.data.cache.db.PlaylistDao
import com.example.data.utils.APP_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, APP_DATABASE_NAME)
            .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideMusicDao(database: AppDatabase): MusicDao = database.getMusicsDao()

    @Provides
    @Singleton
    fun providePlaylistDao(database: AppDatabase): PlaylistDao = database.getPlaylistsDao()

}