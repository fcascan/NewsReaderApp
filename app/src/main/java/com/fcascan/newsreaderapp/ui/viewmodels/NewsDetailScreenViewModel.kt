package com.fcascan.newsreaderapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fcascan.newsreaderapp.models.NewsModel
import com.fcascan.newsreaderapp.use_cases.GetNewsByNewsIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailScreenViewModel @Inject constructor(
    private val getNewsByNewsIdUseCase: GetNewsByNewsIdUseCase,
) : ViewModel() {
    companion object {
        private val TAG = NewsDetailScreenViewModel::class.java.simpleName
    }

    private val _news = MutableStateFlow<NewsModel?>(null)
    val news = _news
    fun setNews(news: NewsModel?) { _news.value = news }

    fun getNewsByNewsId(newsId: Long?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (newsId == null) return@launch
            val result = getNewsByNewsIdUseCase.invoke(newsId)
            setNews(result)
        }
    }
}