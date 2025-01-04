package com.fcascan.newsreaderapp.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.fcascan.newsreaderapp.navigation.NavRoutes

sealed class BottomNavModel(val icon: ImageVector, val title: String, val route: Any) {
    data object News : BottomNavModel(Icons.Default.Star, "News", NavRoutes.News)
    data object Users : BottomNavModel(Icons.Default.Face, "Users", NavRoutes.Users)
    data object Settings : BottomNavModel(Icons.Default.Settings, "Settings", NavRoutes.Settings)
}