package com.fcascan.newsreaderapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fcascan.newsreaderapp.data.local.dao.NewsDao
import com.fcascan.newsreaderapp.data.local.dao.UsersDao
import com.fcascan.newsreaderapp.data.local.entity.NewsLocalEntity
import com.fcascan.newsreaderapp.data.local.entity.UserLocalEntity

@Database(
    entities = [NewsLocalEntity::class, UserLocalEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase()  {
    abstract fun newsDao(): NewsDao
    abstract fun usersDao(): UsersDao
    companion object {
        const val DATABASE_NAME = "news_reader_app_db"
        private var INSTANCE: AppDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE
                ?: run {
                    val aux: AppDatabase
                    synchronized(AppDatabase::class.java) {
                        aux = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java, DATABASE_NAME
                        )
                            .build()
                        INSTANCE = aux
                        aux
                    }
                }
        }
    }
}