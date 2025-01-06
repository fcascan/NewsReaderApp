package com.fcascan.newsreaderapp.use_cases

import android.util.Log
import com.fcascan.newsreaderapp.models.NewsModel
import javax.inject.Inject

class GetNewsByNewsIdUseCase @Inject constructor(

) {
    companion object {
        private val TAG = GetNewsByNewsIdUseCase::class.java.simpleName
    }

    suspend operator fun invoke(newsId: Long?) : NewsModel? {
        Log.d(TAG, "invoke: newsId: $newsId")
        if (newsId == null) {
            return null
        }
        return NewsModel(
            id = newsId,
            title = "Title",
            date = "2025-01-05",
            author = "Author",
            content = "lorem ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor " +
                    "incididunt ut labore et dolore magna aliqua lorem ipsum dolor sit amet consectetur" +
                    " adipiscing elit sed do eiusmod tempor incididunt ut labore et dolore magna aliqua" +
                    " lorem ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt" +
                    " ut labore et dolore magna aliqua lorem ipsum dolor sit amet consectetur adipiscing elit" +
                    " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua lorem ipsum dolor sit" +
                    " amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
            imageUrl = "https://es.wikipedia.org/static/images/icons/wikipedia.png",
            sourceUrl = "https://www.google.com"
        )
    }
}