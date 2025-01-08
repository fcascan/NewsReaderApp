package com.fcascan.newsreaderapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.fcascan.newsreaderapp.utils.SharedPreferencesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(
    private val sharedPreferencesUtil: SharedPreferencesUtil,
) : ViewModel() {
    companion object {
        private val TAG = SettingsScreenViewModel::class.java.simpleName
    }

    private val _isDarkModeEnabled = MutableStateFlow(sharedPreferencesUtil.isDarkThemeEnabled)
    val isDarkModeEnabled = _isDarkModeEnabled
    fun setIsDarkMode(isDarkMode: Boolean) {
        sharedPreferencesUtil.isDarkThemeEnabled = isDarkMode
        _isDarkModeEnabled.value = isDarkMode
    }
}