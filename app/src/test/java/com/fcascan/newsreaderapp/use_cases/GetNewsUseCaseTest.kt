package com.fcascan.newsreaderapp.use_cases

import com.fcascan.newsreaderapp.data.local.repository.NewsLocalRepository
import com.fcascan.newsreaderapp.domain.NewsModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetNewsUseCaseTest {

    private lateinit var newsLocalRepository: NewsLocalRepository
    private lateinit var fetchNewsFromRemoteUseCase: FetchNewsFromRemoteUseCase
    private lateinit var getNewsUseCase: GetNewsUseCase

    @Before
    fun setUp() {
        newsLocalRepository = mockk()
        fetchNewsFromRemoteUseCase = mockk()
        getNewsUseCase = GetNewsUseCase(newsLocalRepository, fetchNewsFromRemoteUseCase)
    }

    @Test
    fun `invoke should return local news when available`() = runBlocking {
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

        coEvery { newsLocalRepository.getAll() } returns newsList

        val result = getNewsUseCase.invoke()

        assertEquals(newsList, result)
        coVerify(exactly = 0) { fetchNewsFromRemoteUseCase.invoke() }
    }

    @Test
    fun `invoke should fetch remote news when local news is empty`() = runBlocking {
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

        coEvery { newsLocalRepository.getAll() } returns emptyList()
        coEvery { fetchNewsFromRemoteUseCase.invoke() } returns newsList

        val result = getNewsUseCase.invoke()

        assertEquals(newsList, result)
        coVerify { fetchNewsFromRemoteUseCase.invoke() }
    }
}