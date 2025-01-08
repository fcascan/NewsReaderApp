package com.fcascan.newsreaderapp.ui.views

import androidx.lifecycle.ViewModel
import com.fcascan.newsreaderapp.utils.SharedPreferencesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    val sharedPreferencesUtil: SharedPreferencesUtil,
) : ViewModel() {
    companion object {
        private val TAG = MainActivityViewModel::class.java.simpleName
    }

    private val _isDarkTheme = MutableStateFlow(sharedPreferencesUtil.isDarkThemeEnabled)
    val isDarkTheme = _isDarkTheme
    fun setIsDarkTheme(isDarkTheme: Boolean) { _isDarkTheme.value = isDarkTheme }
}