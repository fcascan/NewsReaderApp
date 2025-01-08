package com.fcascan.newsreaderapp.use_cases

import android.util.Log
import com.fcascan.newsreaderapp.data.local.repository.NewsLocalRepository
import com.fcascan.newsreaderapp.data.local.repository.UsersLocalRepository
import com.fcascan.newsreaderapp.data.network.repository.NewsRemoteRepository
import com.fcascan.newsreaderapp.domain.NewsModel
import javax.inject.Inject

class FetchNewsFromRemoteUseCase @Inject constructor(
    private val newsRemoteRepository: NewsRemoteRepository,
    private val newsLocalRepository: NewsLocalRepository,
    private val usersRepository: UsersLocalRepository,
    private val fetchUsersFromRemoteUseCase: FetchUsersFromRemoteUseCase
) {
    companion object {
        private val TAG = FetchNewsFromRemoteUseCase::class.java.simpleName
    }
    suspend operator fun invoke() : List<NewsModel> {
        val newsRemoteList = newsRemoteRepository.getAll()
        if (newsRemoteList.any { it.id == -1L }) {
            Log.d(TAG, "invoke(): newsRemoteList is empty")
            return emptyList()
        } else {
            Log.d(TAG, "invoke(): newsRemoteList=$newsRemoteList")
            fetchUsersFromRemoteUseCase.invoke()
            replaceAuthorWithName(newsRemoteList)
            newsLocalRepository.deleteAll()
            newsLocalRepository.insertList(newsRemoteList)
            return newsRemoteList
        }
    }

    private suspend fun replaceAuthorWithName(newsList: List<NewsModel>) {
        newsList.forEach { news ->
            val userId = news.author.toLongOrNull()
            if (userId == null) {
                news.author = "Unknown"
            } else {
                val user = usersRepository.getById(userId)
                val firstName = user?.firstName ?: ""
                val lastName = user?.lastName ?: ""
                val author = "$firstName $lastName".trim()
                news.author = author.ifEmpty { "Unknown" }
            }
        }
    }
}