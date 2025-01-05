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
import com.fcascan.newsreaderapp.ui.viewmodels.NewsScreenViewModel
import com.fcascan.newsreaderapp.ui.viewmodels.SettingsScreenViewModel
import com.fcascan.newsreaderapp.ui.viewmodels.UsersScreenViewModel

@Composable
fun NavigationWrapper(
    navController: NavHostController,
    newsScreenViewModel: NewsScreenViewModel,
    usersScreenViewModel: UsersScreenViewModel,
    settingsScreenViewModel: SettingsScreenViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = News,
    ) {
        composable<News> {
            NewsScreen(
                viewModel = newsScreenViewModel,
                navigateToNewsDetail = {
                    navController.navigate(NewsDetail)
                },
            )
        }
        composable<Settings> {
            SettingsScreen(
                viewModel = settingsScreenViewModel,
            )
        }
        composable<NewsDetail> {
            NewsDetailScreen(
                navigateBack = {
                    navController.popBackStack()
                },
            )
        }
        composable<Users> {
            UsersScreen(
                viewModel = usersScreenViewModel,
                navigateToUserMap = {
                    navController.navigate(UserMap)
                },
            )
        }
        composable<UserMap> {
            UserMapScreen(
                navigateBack = {
                    navController.popBackStack()
                },
            )
        }
    }
}