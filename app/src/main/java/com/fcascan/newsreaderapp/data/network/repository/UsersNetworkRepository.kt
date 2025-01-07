package com.fcascan.newsreaderapp.data.network.repository

import com.fcascan.newsreaderapp.data.network.model.UserNetworkEntity

interface UsersNetworkRepository {
    suspend fun getUsers(): List<UserNetworkEntity>
}