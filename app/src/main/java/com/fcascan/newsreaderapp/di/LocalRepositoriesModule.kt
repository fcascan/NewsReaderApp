package com.fcascan.newsreaderapp.di

import com.fcascan.newsreaderapp.data.local.dao.NewsDao
import com.fcascan.newsreaderapp.data.local.dao.UsersDao
import com.fcascan.newsreaderapp.data.local.repository.NewsLocalRepository
import com.fcascan.newsreaderapp.data.local.repository.NewsLocalRepositoryImpl
import com.fcascan.newsreaderapp.data.local.repository.UsersLocalRepository
import com.fcascan.newsreaderapp.data.local.repository.UsersLocalRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalRepositoriesModule {
    @Singleton
    @Provides
    fun providesNewsRoomRepository(newsDao: NewsDao): NewsLocalRepository = NewsLocalRepositoryImpl(newsDao)

    @Singleton
    @Provides
    fun providesUsersRoomRepository(usersDao: UsersDao): UsersLocalRepository = UsersLocalRepositoryImpl(usersDao)
}