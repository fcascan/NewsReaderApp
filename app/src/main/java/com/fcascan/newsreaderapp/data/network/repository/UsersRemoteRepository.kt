package com.fcascan.newsreaderapp.data.network.repository

import com.fcascan.newsreaderapp.domain.UserModel

interface UsersRemoteRepository {
    suspend fun getAll(): List<UserModel>
}