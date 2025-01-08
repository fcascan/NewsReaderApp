package com.fcascan.newsreaderapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fcascan.newsreaderapp.data.local.entity.NewsLocalEntity

@Dao
interface NewsDao {
    @Query("SELECT * FROM news_table")
    fun getAll(): List<NewsLocalEntity>

    @Query("SELECT * FROM news_table WHERE id = :id")
    fun getById(id: Long): NewsLocalEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(users: List<NewsLocalEntity>)

    @Query("DELETE FROM news_table")
    fun deleteAll()
}