package com.fcascan.newsreaderapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fcascan.newsreaderapp.domain.NewsModel
import com.fcascan.newsreaderapp.use_cases.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsScreenViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
) : ViewModel() {

    companion object {
        private val TAG = NewsScreenViewModel::class.java.simpleName
    }

    private val _newsList = MutableStateFlow<List<NewsModel>>(emptyList())
    val newsList = _newsList
    fun setNewsList(newsList: List<NewsModel>) { _newsList.value = newsList }

    init {
        updateNews()
    }

    fun updateNews() {
        viewModelScope.launch(Dispatchers.IO) {
            val newsList = getNewsUseCase.invoke()
            setNewsList(newsList)
        }
    }
}