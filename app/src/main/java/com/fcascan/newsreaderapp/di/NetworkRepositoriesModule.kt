package com.fcascan.newsreaderapp.di

import com.fcascan.newsreaderapp.data.network.ApiService
import com.fcascan.newsreaderapp.data.network.repository.NewsNetworkRepository
import com.fcascan.newsreaderapp.data.network.repository.NewsNetworkRepositoryImpl
import com.fcascan.newsreaderapp.data.network.repository.UsersNetworkRepository
import com.fcascan.newsreaderapp.data.network.repository.UsersNetworkRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkRepositoriesModule {
    @Singleton
    @Provides
    fun providesNewsNetworkRepository(apiService: ApiService): NewsNetworkRepository {
        return NewsNetworkRepositoryImpl(apiService)
    }

    @Singleton
    @Provides
    fun providesUserNetworkRepository(apiService: ApiService): UsersNetworkRepository {
        return UsersNetworkRepositoryImpl(apiService)
    }
}