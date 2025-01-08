package com.fcascan.newsreaderapp.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.fcascan.newsreaderapp.ui.components.BottomNavigationBar
import com.fcascan.newsreaderapp.navigation.NavigationWrapper
import com.fcascan.newsreaderapp.ui.theme.NewsReaderAppTheme
import com.fcascan.newsreaderapp.ui.viewmodels.NewsDetailScreenViewModel
import com.fcascan.newsreaderapp.ui.viewmodels.NewsScreenViewModel
import com.fcascan.newsreaderapp.ui.viewmodels.SettingsScreenViewModel
import com.fcascan.newsreaderapp.ui.viewmodels.UsersScreenViewModel
import com.fcascan.newsreaderapp.ui.viewmodels.UsersMapScreenViewModel
import com.fcascan.newsreaderapp.ui.views.MainActivityViewModel
import com.fcascan.newsreaderapp.utils.SharedPreferencesUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    companion object {
        val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //ViewModels:
        val mainActivityViewModel: MainActivityViewModel by viewModels()
        val newsScreenViewModel: NewsScreenViewModel by viewModels()
        val newsDetailScreenViewModel: NewsDetailScreenViewModel by viewModels()
        val usersScreenViewModel: UsersScreenViewModel by viewModels()
        val usersMapScreenViewModel: UsersMapScreenViewModel by viewModels()
        val settingsScreenViewModel: SettingsScreenViewModel by viewModels()


        setContent {
            //Dark Theme State Flow:
            val isDarkTheme by mainActivityViewModel.isDarkTheme.collectAsState()

            NewsReaderAppTheme(
                darkTheme = isDarkTheme
            ) {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.padding(0.dp),
                    bottomBar = { BottomNavigationBar(navController) }
                ) { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues)) {
                        NavigationWrapper(
                            navController = navController,
                            mainActivityViewModel = mainActivityViewModel,
                            newsScreenViewModel = newsScreenViewModel,
                            newsDetailScreenViewModel = newsDetailScreenViewModel,
                            usersScreenViewModel = usersScreenViewModel,
                            usersMapScreenViewModel = usersMapScreenViewModel,
                            settingsScreenViewModel = settingsScreenViewModel
                        )
                    }
                }
            }
        }

        //Edge-to-edge:
        enableEdgeToEdge()
    }
}