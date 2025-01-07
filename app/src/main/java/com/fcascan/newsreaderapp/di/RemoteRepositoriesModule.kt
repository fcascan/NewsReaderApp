package com.fcascan.newsreaderapp.di

import com.fcascan.newsreaderapp.data.network.ApiService
import com.fcascan.newsreaderapp.data.network.repository.NewsRemoteRepository
import com.fcascan.newsreaderapp.data.network.repository.NewsRemoteRepositoryImpl
import com.fcascan.newsreaderapp.data.network.repository.UsersRemoteRepository
import com.fcascan.newsreaderapp.data.network.repository.UsersRemoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteRepositoriesModule {
    @Singleton
    @Provides
    fun providesNewsNetworkRepository(apiService: ApiService): NewsRemoteRepository {
        return NewsRemoteRepositoryImpl(apiService)
    }

    @Singleton
    @Provides
    fun providesUserNetworkRepository(apiService: ApiService): UsersRemoteRepository {
        return UsersRemoteRepositoryImpl(apiService)
    }
}