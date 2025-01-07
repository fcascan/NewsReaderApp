package com.fcascan.newsreaderapp.data.network.repository

import com.fcascan.newsreaderapp.data.network.ApiService
import com.fcascan.newsreaderapp.domain.NewsModel
import javax.inject.Inject

class NewsRemoteRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : NewsRemoteRepository {

    override suspend fun getAll() : List<NewsModel> {
        return apiService.getNews().map { it.toNewsModel() }
    }
}