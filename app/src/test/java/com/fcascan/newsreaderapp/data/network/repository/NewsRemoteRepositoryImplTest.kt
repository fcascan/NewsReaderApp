package com.fcascan.newsreaderapp.data.network.repository

import com.fcascan.newsreaderapp.data.network.ApiService
import com.fcascan.newsreaderapp.domain.NewsModel
import com.fcascan.newsreaderapp.data.network.entity.NewsNetworkEntity
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class NewsRemoteRepositoryImplTest {

    private lateinit var apiService: ApiService
    private lateinit var newsRemoteRepository: NewsRemoteRepositoryImpl

    @Before
    fun setUp() {
        apiService = mockk()
        newsRemoteRepository = NewsRemoteRepositoryImpl(apiService)
    }

    @Test
    fun `getAll should return list of NewsModel`() = runBlocking {
        val newsDtoList = listOf(
            NewsNetworkEntity(
                id = "1",
                title = "Title",
                content = "Content",
                url = "https://www.example.com",
                thumbnail = "https://www.example.com/image.jpg",
                publishedAt = "2025-01-07",
                userId = "5",
            )
        )
        val expectedNewsList = newsDtoList.map { it.toNewsModel() }

        coEvery { apiService.getNews() } returns newsDtoList

        val result = newsRemoteRepository.getAll()

        assertNewsModelListEquals(expectedNewsList, result)
    }

    @Test
    fun `getAll should return empty list when no news available`() = runBlocking {
        coEvery { apiService.getNews() } returns emptyList()

        val result = newsRemoteRepository.getAll()

        assertEquals(emptyList<NewsModel>(), result)
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