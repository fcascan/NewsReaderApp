package com.fcascan.newsreaderapp.data.network.repository

import com.fcascan.newsreaderapp.data.network.ApiService
import com.fcascan.newsreaderapp.data.network.model.UserNetworkEntity
import javax.inject.Inject

class UsersNetworkRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UsersNetworkRepository {

    override suspend fun getUsers() : List<UserNetworkEntity> {
        return apiService.getUsers()
    }
}