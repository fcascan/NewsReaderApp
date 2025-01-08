package com.fcascan.newsreaderapp.data.local.repository

import com.fcascan.newsreaderapp.domain.NewsModel

interface NewsLocalRepository {
    suspend fun getAll(): List<NewsModel>
    suspend fun getById(id: Long): NewsModel?
    suspend fun insertList(news: List<NewsModel>)
    suspend fun deleteAll()
}