package com.fcascan.newsreaderapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fcascan.newsreaderapp.domain.NewsModel
import com.fcascan.newsreaderapp.use_cases.FetchNewsFromRemoteUseCase
import com.fcascan.newsreaderapp.use_cases.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsScreenViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    private val fetchNewsFromRemoteUseCase: FetchNewsFromRemoteUseCase,
) : ViewModel() {

    companion object {
        private val TAG = NewsScreenViewModel::class.java.simpleName
    }

    private val newsListBackup = mutableListOf<NewsModel>()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing
    fun setIsRefreshing(isRefreshing: Boolean) { _isRefreshing.value = isRefreshing }

    private val _newsList = MutableStateFlow<List<NewsModel>>(emptyList())
    val newsList = _newsList
    fun setNewsList(newsList: List<NewsModel>) { _newsList.value = newsList }


    init {
        updateNews()
    }

    fun updateNews() {
        viewModelScope.launch(Dispatchers.IO) {
            val newsList = getNewsUseCase.invoke()
            newsListBackup.removeAll { true }   //remove all elements
            newsListBackup.addAll(newsList)
            setNewsList(newsList)
        }
    }

    fun fetchNewsFromRemote() {
        setIsRefreshing(true)
        viewModelScope.launch(Dispatchers.IO) {
            val newsList = fetchNewsFromRemoteUseCase.invoke()
            newsListBackup.removeAll { true }   //remove all elements
            newsListBackup.addAll(newsList)
            setNewsList(newsList)
            setIsRefreshing(false)
        }
    }

    fun filterNews(query: String) {
        val filteredNewsList = newsListBackup.filter {
            it.title.contains(query, ignoreCase = true) ||
            it.author.contains(query, ignoreCase = true)
        }
        setNewsList(filteredNewsList)
    }
}