package com.fcascan.newsreaderapp.ui.viewmodels

import com.fcascan.newsreaderapp.domain.NewsModel
import com.fcascan.newsreaderapp.use_cases.GetNewsByNewsIdUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NewsDetailScreenViewModelTest {

    private lateinit var getNewsByNewsIdUseCase: GetNewsByNewsIdUseCase
    private lateinit var viewModel: NewsDetailScreenViewModel
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getNewsByNewsIdUseCase = mockk()
        viewModel = NewsDetailScreenViewModel(getNewsByNewsIdUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `setNews should update news state`() = runTest {
        val news = NewsModel(
            id = 1,
            title = "Title",
            date = "2025-01-07",
            author = "Author",
            content = "Content",
            imageUrl = "https://www.example.com/image.jpg",
            sourceUrl = "https://www.example.com"
        )

        viewModel.setNews(news)

        assertEquals(news, viewModel.news.first())
    }

    @Test
    fun `getNewsByNewsId should update news state when newsId is valid`() = runTest {
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

        coEvery { getNewsByNewsIdUseCase.invoke(newsId) } returns news

        viewModel.getNewsByNewsId(newsId)
        testDispatcher.scheduler.advanceUntilIdle()

        delay(100)

        assertEquals(news, viewModel.news.first())
        coVerify { getNewsByNewsIdUseCase.invoke(newsId) }
    }

    @Test
    fun `getNewsByNewsId should not update news state when newsId is null`() = runTest {
        viewModel.getNewsByNewsId(null)
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(null, viewModel.news.first())
        coVerify(exactly = 0) { getNewsByNewsIdUseCase.invoke(any()) }
    }
}