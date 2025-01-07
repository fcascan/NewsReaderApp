package com.fcascan.newsreaderapp.data.network.repository

import com.fcascan.newsreaderapp.data.network.model.NewsNetworkEntity

interface NewsNetworkRepository {
    suspend fun getNews() : List<NewsNetworkEntity>
}