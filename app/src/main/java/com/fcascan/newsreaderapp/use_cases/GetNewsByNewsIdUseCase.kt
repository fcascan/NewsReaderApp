package com.fcascan.newsreaderapp.use_cases

import android.util.Log
import com.fcascan.newsreaderapp.domain.NewsModel
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
            title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            date = "04/02/2023 13:25:21",
            author = "5",
            content = "Semper nulla nisi habitasse montes. Ipsum ullamcorper interdum curae;. " +
                    "Cras dis justo non litora metus libero scelerisque volutpat per auctor integer. " +
                    "Curae; id natoque lacinia blandit lectus venenatis arcu pellentesque nunc " +
                    "vestibulum suspendisse. Montes pharetra proin mus orci aptent. Dis, inceptos " +
                    "enim mus aliquet libero torquent. Mauris lorem sagittis egestas nibh pulvinar" +
                    " luctus nascetur facilisis conubia netus.",
            imageUrl = "https://dummyimage.com/200x200/FFFFFF/lorem-ipsum.png&text=jsonplaceholder.org",
            sourceUrl = "https://jsonplaceholder.org/posts/lorem-ipsum"
        )
    }
}