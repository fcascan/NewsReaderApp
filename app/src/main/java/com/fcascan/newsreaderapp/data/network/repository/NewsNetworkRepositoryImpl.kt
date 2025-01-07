package com.fcascan.newsreaderapp.data.network.repository

import com.fcascan.newsreaderapp.data.network.ApiService
import com.fcascan.newsreaderapp.data.network.model.NewsNetworkEntity
import javax.inject.Inject

class NewsNetworkRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : NewsNetworkRepository {

    override suspend fun getNews() : List<NewsNetworkEntity> {
        return apiService.getNews()
    }
}