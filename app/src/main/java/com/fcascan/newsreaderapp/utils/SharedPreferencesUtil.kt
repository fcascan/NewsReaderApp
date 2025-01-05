package com.fcascan.newsreaderapp.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesUtil(context: Context) {
    companion object {
        private val TAG = SharedPreferencesUtil::class.java.simpleName
        private const val SHARED_PREFERENCES_NAME = "shared_preferences"
        private const val IS_DARK_THEME_ENABLED = "is_dark_theme_enabled"
    }

    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    var isDarkThemeEnabled: Boolean
        get() = sharedPref.getBoolean(IS_DARK_THEME_ENABLED, false)
        set(value) {
            val editor = sharedPref.edit()
            editor.putBoolean(IS_DARK_THEME_ENABLED, value)
            editor.apply()
        }
}