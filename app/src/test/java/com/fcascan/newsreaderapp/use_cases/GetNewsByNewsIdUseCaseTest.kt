package com.fcascan.newsreaderapp.use_cases

import com.fcascan.newsreaderapp.data.local.repository.NewsLocalRepository
import com.fcascan.newsreaderapp.domain.NewsModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class GetNewsByNewsIdUseCaseTest {

    private lateinit var newsLocalRepository: NewsLocalRepository
    private lateinit var getNewsByNewsIdUseCase: GetNewsByNewsIdUseCase

    @Before
    fun setUp() {
        newsLocalRepository = mockk()
        getNewsByNewsIdUseCase = GetNewsByNewsIdUseCase(newsLocalRepository)
    }

    @Test
    fun `invoke should return news when newsId is valid`() = runBlocking {
        val newsId = 1L
        val news = NewsModel(
            id = newsId,
            title = "Title",
            date = "2025-01-07",
            author = "Author",
            content = "Content",
            imageUrl = "https://www.example.com/image.jpg",
            sourceUrl = "https://www.example.com"
        )

        coEvery { newsLocalRepository.getById(newsId) } returns news

        val result = getNewsByNewsIdUseCase.invoke(newsId)

        assertEquals(news, result)
    }

    @Test
    fun `invoke should return null when newsId is null`() = runBlocking {
        val result = getNewsByNewsIdUseCase.invoke(null)

        assertNull(result)
    }

    @Test
    fun `invoke should return null when news is not found`() = runBlocking {
        val newsId = 1L

        coEvery { newsLocalRepository.getById(newsId) } returns null

        val result = getNewsByNewsIdUseCase.invoke(newsId)

        assertNull(result)
    }
}