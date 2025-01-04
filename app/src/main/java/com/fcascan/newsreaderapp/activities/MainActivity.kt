package com.fcascan.newsreaderapp.activities

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.fcascan.newsreaderapp.navigation.NavigationWrapper
import com.fcascan.newsreaderapp.ui.theme.NewsReaderAppTheme

class MainActivity : ComponentActivity() {
    companion object {
        val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //SharedPreferences:
        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val isDarkTheme = sharedPreferences.getString("isDarkTheme", null)
        Log.d(TAG, "isDarkTheme: $isDarkTheme")

        setContent {
            NewsReaderAppTheme {
                NavigationWrapper()
            }
        }

        //Edge-to-edge:
        enableEdgeToEdge()
    }
}