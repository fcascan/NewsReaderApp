package com.fcascan.newsreaderapp.di

import android.content.Context
import com.fcascan.newsreaderapp.utils.SharedPreferencesUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UtilsModule {
    @Singleton
    @Provides
    fun providesSharedPreferencesUtil(@ApplicationContext context: Context): SharedPreferencesUtil =
        SharedPreferencesUtil(context)
}