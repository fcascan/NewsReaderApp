package com.fcascan.newsreaderapp.navigation.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun getCurrentRoute(navController: NavController): String? {
    return navController.currentBackStackEntryAsState().value?.destination?.route
}