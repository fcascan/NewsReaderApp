package com.fcascan.newsreaderapp.data.network.repository

import com.fcascan.newsreaderapp.data.network.ApiService
import com.fcascan.newsreaderapp.domain.UserModel
import javax.inject.Inject

class UsersRemoteRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UsersRemoteRepository {

    override suspend fun getAll() : List<UserModel> {
        return apiService.getUsers().map { it.toUserModel() }
    }
}