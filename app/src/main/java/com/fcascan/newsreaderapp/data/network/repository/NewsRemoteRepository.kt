package com.fcascan.newsreaderapp.data.network.repository

import com.fcascan.newsreaderapp.domain.NewsModel

interface NewsRemoteRepository {
    suspend fun getAll() : List<NewsModel>
}