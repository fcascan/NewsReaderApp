package com.fcascan.newsreaderapp.ui.views

import android.content.Context
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fcascan.newsreaderapp.models.UserModel
import com.fcascan.newsreaderapp.ui.components.UserCard
import com.fcascan.newsreaderapp.ui.viewmodels.UsersScreenViewModel

@Composable
fun UsersScreen(
    navigateToUserMap: (Long) -> Unit,
) {
    val TAG = "UsersScreen"
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("users", Context.MODE_PRIVATE)
    val isDarkTheme = sharedPreferences.getString("isDarkTheme", null)
    val usersList: List<UserModel> by viewModel(UsersScreenViewModel::class.java).usersList.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) {paddingValues ->
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(
            //TODO: Agregar algun callback para cuando se realiza un scroll arriba de todo para en ese momento realizar un refresh de las noticias
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            items(usersList) { item ->
                UserCard(
                    name = item.name,
                    lastName = item.lastName,
                    avatarUrl = item.avatarUrl,
                    onClick = { navigateToUserMap(item.id) },
                )
            }
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