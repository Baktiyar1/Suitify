package com.example.suitify.di

import android.content.Context
import com.example.core.managers.NavigationManager
import com.example.core.managers.ToastManager
import com.example.suitify.manager.NavigationManagerImpl
import com.example.suitify.manager.ToastManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ManagerModule {

    @Provides
    @Singleton
    fun provideNavigationManager(): NavigationManager = NavigationManagerImpl()

    @Provides
    @Singleton
    fun provideToastManager(@ApplicationContext context: Context): ToastManager =
        ToastManagerImpl(context = context)

}