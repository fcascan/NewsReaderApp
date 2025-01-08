package com.fcascan.newsreaderapp.use_cases

import android.util.Log
import com.fcascan.newsreaderapp.data.local.repository.NewsLocalRepository
import com.fcascan.newsreaderapp.domain.NewsModel
import javax.inject.Inject

class GetNewsByNewsIdUseCase @Inject constructor(
    private val newsLocalRepository: NewsLocalRepository,
) {
    companion object {
        private val TAG = GetNewsByNewsIdUseCase::class.java.simpleName
    }

    suspend operator fun invoke(newsId: Long?) : NewsModel? {
        Log.d(TAG, "invoke(): newsId: $newsId")
        if (newsId == null) {
            return null
        }
        val news = newsLocalRepository.getById(newsId)
        Log.d(TAG, "invoke(): news from db: $news")
        return news
    }
}