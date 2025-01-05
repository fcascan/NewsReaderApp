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
fun UserMapScreen(
    navigateBack: () -> Unit,
) {
    val TAG = "UserMapScreen"
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("userMap", Context.MODE_PRIVATE)
    val isDarkTheme = sharedPreferences.getString("isDarkTheme", null)

    Scaffold(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = "User Map Screen")
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "spec:shape=Normal,width=360,height=700,unit=dp,dpi=480"
)
@Composable
fun UserMapScreenPortraitPreview() {
    UserMapScreen(
        navigateBack = {},
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "spec:shape=Normal,width=700,height=360,unit=dp,dpi=480"
)
@Composable
fun UserMapScreenLandscapePreview() {
    UserMapScreen(
        navigateBack = {},
    )
}