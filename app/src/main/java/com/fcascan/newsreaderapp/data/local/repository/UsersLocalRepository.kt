package com.fcascan.newsreaderapp.data.local.repository

import com.fcascan.newsreaderapp.domain.UserModel

interface UsersLocalRepository {
    suspend fun getAll(): List<UserModel>
    suspend fun getById(userId: Long): UserModel?
    suspend fun insertList(users: List<UserModel>)
    suspend fun deleteAll()
}