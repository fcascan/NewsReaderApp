package com.fcascan.newsreaderapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fcascan.newsreaderapp.ui.views.NewsDetailScreen
import com.fcascan.newsreaderapp.ui.views.NewsScreen
import com.fcascan.newsreaderapp.ui.views.SettingsScreen
import com.fcascan.newsreaderapp.ui.views.UserMapScreen
import com.fcascan.newsreaderapp.ui.views.UsersScreen
import com.fcascan.newsreaderapp.navigation.NavRoutes.News
import com.fcascan.newsreaderapp.navigation.NavRoutes.NewsDetail
import com.fcascan.newsreaderapp.navigation.NavRoutes.Settings
import com.fcascan.newsreaderapp.navigation.NavRoutes.UserMap
import com.fcascan.newsreaderapp.navigation.NavRoutes.Users

@Composable
fun NavigationWrapper(
    navController: NavHostController,
) {
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