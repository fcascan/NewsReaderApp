package com.fcascan.newsreaderapp.ui.views

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun UsersScreen(
    navigateToUserMap: () -> Unit,
) {
    val TAG = "UsersScreen"
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("users", Context.MODE_PRIVATE)
    val isDarkTheme = sharedPreferences.getString("isDarkTheme", null)

    Scaffold(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = "Users Screen")
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "spec:shape=Normal,width=360,height=700,unit=dp,dpi=480"
)
@Composable
fun UsersScreenPortraitPreview() {
    UsersScreen(
        navigateToUserMap = {},
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "spec:shape=Normal,width=700,height=360,unit=dp,dpi=480"
)
@Composable
fun UsersScreenLandscapePreview() {
    UsersScreen(
        navigateToUserMap = {},
    )
}