package com.fcascan.newsreaderapp.data.local.repository

import com.fcascan.newsreaderapp.data.local.dao.NewsDao
import com.fcascan.newsreaderapp.data.local.entity.NewsLocalEntity
import com.fcascan.newsreaderapp.domain.NewsModel
import javax.inject.Inject

class NewsLocalRepositoryImpl @Inject constructor(
    private val newsDao: NewsDao,
) : NewsLocalRepository {
    override suspend fun getAll() = newsDao.getAll().map { it.toNewsModel() }

    override suspend fun insertList(news: List<NewsModel>) {
        val newsLocalEntityList = news.map { it.toNewsLocalEntity() }
        newsDao.insertList(newsLocalEntityList)
    }

    override suspend fun deleteAll() {
        newsDao.deleteAll()
    }
}