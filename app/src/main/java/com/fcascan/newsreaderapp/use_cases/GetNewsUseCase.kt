package com.fcascan.newsreaderapp.use_cases

import android.util.Log
import com.fcascan.newsreaderapp.data.local.repository.NewsLocalRepository
import com.fcascan.newsreaderapp.data.network.repository.NewsRemoteRepository
import com.fcascan.newsreaderapp.domain.NewsModel
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val newsLocalRepository: NewsLocalRepository,
    private val fetchNewsFromRemoteUseCase: FetchNewsFromRemoteUseCase,
) {
    companion object {
        private val TAG = GetNewsUseCase::class.java.simpleName
    }

    suspend operator fun invoke() : List<NewsModel> {
        val newsLocalList = newsLocalRepository.getAll()
        if (newsLocalList.isNotEmpty()) {
            return newsLocalList
        }
        Log.i(TAG, "invoke(): newsLocalList is empty. Fetching from network...")
        return fetchNewsFromRemoteUseCase.invoke()
    }
}