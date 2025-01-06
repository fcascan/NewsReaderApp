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
import com.fcascan.newsreaderapp.ui.viewmodels.NewsDetailScreenViewModel
import com.fcascan.newsreaderapp.ui.viewmodels.NewsScreenViewModel
import com.fcascan.newsreaderapp.ui.viewmodels.SettingsScreenViewModel
import com.fcascan.newsreaderapp.ui.viewmodels.UsersScreenViewModel
import com.fcascan.newsreaderapp.ui.viewmodels.UsersMapScreenViewModel

@Composable
fun NavigationWrapper(
    navController: NavHostController,
    newsScreenViewModel: NewsScreenViewModel,
    newsDetailScreenViewModel: NewsDetailScreenViewModel,
    usersScreenViewModel: UsersScreenViewModel,
    usersMapScreenViewModel: UsersMapScreenViewModel,
    settingsScreenViewModel: SettingsScreenViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = News,
    ) {
        composable<News> {
            NewsScreen(
                viewModel = newsScreenViewModel,
                navigateToNewsDetail = { newsId ->
                    navController.navigate(NewsDetail(id = newsId))
                },
            )
        }
        composable<Settings> {
            SettingsScreen(
                viewModel = settingsScreenViewModel,
            )
        }
        composable<NewsDetail> {
            val newsId = it.arguments?.getLong("id")
            NewsDetailScreen(
                newsId = newsId,
                viewModel = newsDetailScreenViewModel,
                navigateBack = {
                    navController.popBackStack()
                },
            )
        }
        composable<Users> {
            UsersScreen(
                viewModel = usersScreenViewModel,
                navigateToUserMap = { userId ->
                    navController.navigate(UserMap(id = userId))
                },
            )
        }
        composable<UserMap> {
            val userId = it.arguments?.getLong("id")
            UserMapScreen(
                userId = userId,
                viewModel = usersMapScreenViewModel,
                navigateBack = {
                    navController.popBackStack()
                },
            )
        }
    }
}