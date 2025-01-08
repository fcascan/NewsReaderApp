package com.fcascan.newsreaderapp.data.local.repository

import com.fcascan.newsreaderapp.data.local.dao.NewsDao
import com.fcascan.newsreaderapp.data.local.entity.NewsLocalEntity
import com.fcascan.newsreaderapp.domain.NewsModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.just
import io.mockk.runs
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class NewsLocalRepositoryImplTest {

    private lateinit var newsDao: NewsDao
    private lateinit var newsLocalRepository: NewsLocalRepositoryImpl

    @Before
    fun setUp() {
        newsDao = mockk()
        newsLocalRepository = NewsLocalRepositoryImpl(newsDao)
    }

    @Test
    fun `getAll should return list of NewsModel`() = runBlocking {
        val newsLocalEntityList = listOf(
            NewsLocalEntity(
                id = 1,
                title = "Title",
                date = "2025-01-07",
                author = "Author",
                content = "Content",
                imageUrl = "https://www.example.com/image.jpg",
                sourceUrl = "https://www.example.com"
            )
        )
        val expectedNewsList = newsLocalEntityList.map { it.toNewsModel() }

        coEvery { newsDao.getAll() } returns newsLocalEntityList

        val result = newsLocalRepository.getAll()

        assertNewsModelListEquals(expectedNewsList, result)
    }

    @Test
    fun `getById should return NewsModel`() = runBlocking {
        val newsLocalEntity = NewsLocalEntity(
            id = 1,
            title = "Title",
            date = "2025-01-07",
            author = "Author",
            content = "Content",
            imageUrl = "https://www.example.com/image.jpg",
            sourceUrl = "https://www.example.com"
        )
        val expectedNews = newsLocalEntity.toNewsModel()

        coEvery { newsDao.getById(1) } returns newsLocalEntity

        val result = newsLocalRepository.getById(1)

        assertNewsModelEquals(expectedNews, result!!)
    }

    @Test
    fun `insertList should insert list of NewsModel`() = runBlocking {
        val newsList = listOf(
            NewsModel(
                id = 1,
                title = "Title",
                date = "2025-01-07",
                author = "Author",
                content = "Content",
                imageUrl = "https://www.example.com/image.jpg",
                sourceUrl = "https://www.example.com"
            )
        )
        val newsLocalEntityList = newsList.map { it.toNewsLocalEntity() }

        coEvery { newsDao.insertList(newsLocalEntityList) } just runs

        newsLocalRepository.insertList(newsList)

        coVerify { newsDao.insertList(newsLocalEntityList) }
    }

    @Test
    fun `deleteAll should delete all news`() = runBlocking {
        coEvery { newsDao.deleteAll() } just runs

        newsLocalRepository.deleteAll()

        coVerify { newsDao.deleteAll() }
    }

    private fun assertNewsModelListEquals(expected: List<NewsModel>, actual: List<NewsModel>) {
        assertEquals(expected.size, actual.size)
        for (i in expected.indices) {
            assertNewsModelEquals(expected[i], actual[i])
        }
    }

    private fun assertNewsModelEquals(expected: NewsModel, actual: NewsModel) {
        assertEquals(expected.id, actual.id)
        assertEquals(expected.title, actual.title)
        assertEquals(expected.date, actual.date)
        assertEquals(expected.author, actual.author)
        assertEquals(expected.content, actual.content)
        assertEquals(expected.imageUrl, actual.imageUrl)
        assertEquals(expected.sourceUrl, actual.sourceUrl)
    }
}