import com.fcascan.newsreaderapp.data.local.repository.NewsLocalRepository
import com.fcascan.newsreaderapp.data.local.repository.UsersLocalRepository
import com.fcascan.newsreaderapp.data.network.repository.NewsRemoteRepository
import com.fcascan.newsreaderapp.domain.GeoLocationModel
import com.fcascan.newsreaderapp.domain.NewsModel
import com.fcascan.newsreaderapp.domain.UserModel
import com.fcascan.newsreaderapp.use_cases.FetchNewsFromRemoteUseCase
import com.fcascan.newsreaderapp.use_cases.FetchUsersFromRemoteUseCase
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FetchNewsFromRemoteUseCaseTest {
    private lateinit var newsRemoteRepository: NewsRemoteRepository
    private lateinit var newsLocalRepository: NewsLocalRepository
    private lateinit var usersRepository: UsersLocalRepository
    private lateinit var fetchUsersFromRemoteUseCase: FetchUsersFromRemoteUseCase
    private lateinit var fetchNewsFromRemoteUseCase: FetchNewsFromRemoteUseCase

    @Before
    fun setUp() {
        newsRemoteRepository = mockk()
        newsLocalRepository = mockk()
        usersRepository = mockk()
        fetchUsersFromRemoteUseCase = mockk()
        fetchNewsFromRemoteUseCase = FetchNewsFromRemoteUseCase(
            newsRemoteRepository,
            newsLocalRepository,
            usersRepository,
            fetchUsersFromRemoteUseCase
        )
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `invoke should return empty list when newsRemoteList is empty`() = runBlocking {
        coEvery { newsRemoteRepository.getAll() } returns emptyList()
        coEvery { fetchUsersFromRemoteUseCase.invoke() } returns emptyList()
        coEvery { newsLocalRepository.deleteAll() } just Runs
        coEvery { newsLocalRepository.insertList(emptyList()) } just Runs

        val result = fetchNewsFromRemoteUseCase.invoke()

        assertEquals(emptyList<NewsModel>(), result)
        coVerify { newsLocalRepository.insertList(emptyList()) }
    }

    @Test
    fun `invoke should fetch users and save news when newsRemoteList is not empty`() = runBlocking {
        val newsList = listOf(
            NewsModel(
                id = 1,
                title = "Title",
                date = "2025-01-07",
                author = "1",
                content = "Content",
                imageUrl = "https://www.example.com/image.jpg",
                sourceUrl = "https://www.example.com",
            )
        )
        val user = UserModel(
            id = 1,
            firstName = "John",
            lastName = "Doe",
            address = "123",
            email = "jd@mail.com",
            websiteUrl = "https://www.example.com",
            geoLocation = GeoLocationModel(-34.603851, -58.381775)
        )
        coEvery { newsRemoteRepository.getAll() } returns newsList
        coEvery { usersRepository.getById(1) } returns user
        coEvery { fetchUsersFromRemoteUseCase.invoke() } returns listOf(user)
        coEvery { newsLocalRepository.deleteAll() } just Runs
        coEvery { newsLocalRepository.insertList(newsList) } just Runs

        val result = fetchNewsFromRemoteUseCase.invoke()

        assertEquals(newsList, result)
        coVerify { fetchUsersFromRemoteUseCase.invoke() }
        coVerify { newsLocalRepository.deleteAll() }
        coVerify { newsLocalRepository.insertList(newsList) }
    }
}