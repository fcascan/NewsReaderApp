package com.fcascan.newsreaderapp.ui.viewmodels

import com.fcascan.newsreaderapp.domain.NewsModel
import com.fcascan.newsreaderapp.use_cases.FetchNewsFromRemoteUseCase
import com.fcascan.newsreaderapp.use_cases.GetNewsUseCase
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
class NewsScreenViewModelTest {
    //TODO: Fixme Estos Tests estan fallando
    private lateinit var getNewsUseCase: GetNewsUseCase
    private lateinit var fetchNewsFromRemoteUseCase: FetchNewsFromRemoteUseCase
    private lateinit var viewModel: NewsScreenViewModel
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getNewsUseCase = mockk()
        fetchNewsFromRemoteUseCase = mockk()
        viewModel = NewsScreenViewModel(getNewsUseCase, fetchNewsFromRemoteUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `updateNews should update newsList state`() = runTest {
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

        coEvery { getNewsUseCase.invoke() } returns newsList

        viewModel.updateNews()
        testDispatcher.scheduler.advanceUntilIdle()

        delay(100)

        assertEquals(newsList, viewModel.newsList.first())
        coVerify { getNewsUseCase.invoke() }
    }


    @Test
    fun `fetchNewsFromRemote should update newsList state and set isRefreshing to false`() = runTest {
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

        coEvery { fetchNewsFromRemoteUseCase.invoke() } returns newsList

        viewModel.fetchNewsFromRemote()
        testDispatcher.scheduler.advanceUntilIdle()

        delay(100)

        assertEquals(newsList, viewModel.newsList.first())
        assertEquals(false, viewModel.isRefreshing.first())
        coVerify { fetchNewsFromRemoteUseCase.invoke() }
    }

    @Test
    fun `filterNews should update newsList state with filtered news`() = runTest {
        val newsList = listOf(
            NewsModel(
                id = 1,
                title = "Title",
                date = "2025-01-07",
                author = "Author",
                content = "Content",
                imageUrl = "https://www.example.com/image.jpg",
                sourceUrl = "https://www.example.com"
            ),
            NewsModel(
                id = 2,
                title = "Another Title",
                date = "2025-01-08",
                author = "Another Author",
                content = "Another Content",
                imageUrl = "https://www.example.com/another_image.jpg",
                sourceUrl = "https://www.example.com/another"
            )
        )

        coEvery { getNewsUseCase.invoke() } returns newsList

        viewModel.updateNews()
        testDispatcher.scheduler.advanceUntilIdle()

        delay(100)

        viewModel.filterNews("Another")
        testDispatcher.scheduler.advanceUntilIdle()

        delay(100)

        assertEquals(listOf(newsList[1]), viewModel.newsList.first())
    }
}