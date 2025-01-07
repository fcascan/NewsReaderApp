package com.fcascan.newsreaderapp.di

import android.content.Context
import androidx.room.Room
import com.fcascan.newsreaderapp.data.local.AppDatabase
import com.fcascan.newsreaderapp.data.local.dao.NewsDao
import com.fcascan.newsreaderapp.data.local.dao.UsersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Singleton
    @Provides
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
        .fallbackToDestructiveMigration()
        .build()
    }

    @Singleton
    @Provides
    fun providesNewsDao(db: AppDatabase): NewsDao = db.newsDao()

    @Singleton
    @Provides
    fun providesUsersDao(db: AppDatabase): UsersDao = db.usersDao()
}