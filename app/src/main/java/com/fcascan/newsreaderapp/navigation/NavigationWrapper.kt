package com.fcascan.newsreaderapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fcascan.newsreaderapp.ui.views.NewsDetailScreen
import com.fcascan.newsreaderapp.ui.views.NewsScreen
import com.fcascan.newsreaderapp.ui.views.SettingsScreen
import com.fcascan.newsreaderapp.ui.views.UserMapScreen
import com.fcascan.newsreaderapp.ui.views.UsersScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = News,
    ) {
        composable<News> {
            NewsScreen(
                navigateToNewsDetail = {
                    navController.navigate(NewsDetail)
                },
                navigateToUsers = {
                    navController.navigate(Users)
                },
                navigateToSettings = {
                    navController.navigate(Settings)
                },
            )
        }
        composable<Settings> {
            SettingsScreen(
                navigateToNews = {
                    navController.navigate(News)
                },
                navigateToUsers = {
                    navController.navigate(Users)
                },
            )
        }
        composable<NewsDetail> {
            NewsDetailScreen(
                navigateBack = {
                    navController.popBackStack()
                },
                navigateToNews = {
                    navController.navigate(News)
                },
                navigateToUsers = {
                    navController.navigate(Users)
                },
                navigateToSettings = {
                    navController.navigate(Settings)
                },
            )
        }
        composable<Users> {
            UsersScreen(
                navigateToUserMap = {
                    navController.navigate(UserMap)
                },
                navigateToNews = {
                    navController.navigate(News)
                },
                navigateToSettings = {
                    navController.navigate(Settings)
                },
            )
        }
        composable<UserMap> {
            UserMapScreen(
                navigateBack = {
                    navController.popBackStack()
                },
                navigateToNews = {
                    navController.navigate(News)
                },
                navigateToUsers = {
                    navController.navigate(Users)
                },
                navigateToSettings = {
                    navController.navigate(Settings)
                },
            )
        }
    }
}