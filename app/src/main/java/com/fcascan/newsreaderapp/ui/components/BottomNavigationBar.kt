package com.fcascan.newsreaderapp.ui.components

import android.util.Log
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fcascan.newsreaderapp.ui.models.BottomNavModel
import com.fcascan.newsreaderapp.navigation.utils.getCurrentRoute

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val items = listOf(
        BottomNavModel.News,
        BottomNavModel.Users,
        BottomNavModel.Settings
    )
    NavigationBar {
        items.forEach { item ->
            val currentRoute = getCurrentRoute(navController)
            val isSelected = item.route == currentRoute
            Log.d("BottomNavigationBar", "isSelected: $isSelected") //TODO: Fixme isSelected is always returning false
            BottomNavigationItem(
                modifier = Modifier.weight(1f),
                icon = item.icon,
                contentDescription = "Navigate to " + item.title,
                label = item.title,
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
                        launchSingleTop = true  // Reuse the existing screen instead of stacking it on top
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    val navController = rememberNavController()
    BottomNavigationBar(
        navController = navController
    )
}
