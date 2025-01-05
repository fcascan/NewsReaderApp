package com.fcascan.newsreaderapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.fcascan.newsreaderapp.models.NewsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class NewsScreenViewModel @Inject constructor(

) : ViewModel() {

    companion object {
        private val TAG = NewsScreenViewModel::class.java.simpleName
    }

    private val _newsList = MutableStateFlow<List<NewsModel>>(emptyList())
    val newsList = _newsList
    fun setNewsList(newsList: List<NewsModel>) { _newsList.value = newsList }


    init {
        val mockNewsList = listOf(
            NewsModel(id = 1L, title = "Title 1", author = "Author 1", imageUrl = "https://es.wikipedia.org/static/images/icons/wikipedia.png",
                content = "lorem ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut labore et dolore magna aliqua"
            ),
            NewsModel(id = 2L, title = "Title 2", author = "Author 2", imageUrl = "https://es.wikipedia.org/static/images/icons/wikipedia.png",
                content = "lorem ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut labore et dolore magna aliqua"
            ),
            NewsModel(id = 3L, title = "Title 3", author = "Author 3", imageUrl = "https://es.wikipedia.org/static/images/icons/wikipedia.png",
                content = "lorem ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut labore et dolore magna aliqua"
            ),
            NewsModel(id = 4L, title = "Title 4", author = "Author 4", imageUrl = "https://es.wikipedia.org/static/images/icons/wikipedia.png",
                content = "lorem ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut labore et dolore magna aliqua"
            ),
            NewsModel(id = 5L, title = "Title 5", author = "Author 5", imageUrl = "https://es.wikipedia.org/static/images/icons/wikipedia.png",
                content = "lorem ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut labore et dolore magna aliqua"
            ),
        )
        setNewsList(mockNewsList)
    }

}