package com.fcascan.newsreaderapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fcascan.newsreaderapp.data.local.entity.UserLocalEntity

@Dao
interface UsersDao {
    @Query("SELECT * FROM users_table")
    fun getAll(): List<UserLocalEntity>

    @Query("SELECT * FROM users_table WHERE id = :id")
    fun getById(id: Long): UserLocalEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(users: List<UserLocalEntity>)

    @Query("DELETE FROM users_table")
    fun deleteAll()
}