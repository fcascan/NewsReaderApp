package com.fcascan.newsreaderapp.use_cases

import android.util.Log
import com.fcascan.newsreaderapp.data.network.repository.NewsNetworkRepository
import com.fcascan.newsreaderapp.domain.NewsModel
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val newsNetworkRepository: NewsNetworkRepository,
//    private val usersRepository: UsersRepository,
) {
    companion object {
        private val TAG = GetNewsUseCase::class.java.simpleName
    }

    suspend operator fun invoke() : List<NewsModel> {
        val newsNetworkList = newsNetworkRepository.getNews()
        Log.d(TAG, "invoke: newsNetworkList=$newsNetworkList")
        val newsModelList = newsNetworkList.map { news -> news.toNewsModel() }
        if (newsModelList.any { it.id == -1L }) {
            return emptyList()
        } else {
//            replaceAuthorWithName(newsModelList)
            return newsModelList
        }
    }

    //TODO: implementar
//    private suspend fun replaceAuthorWithName(newsList: List<NewsModel>) {
//        newsList.forEach { news ->
//            val author = usersRepository.getAuthorById(news.author)
//            news.author = author?.name ?: "Unknown"
//        }
//    }
}