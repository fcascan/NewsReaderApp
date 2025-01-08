package com.fcascan.newsreaderapp.ui.views

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fcascan.newsreaderapp.domain.UserModel
import com.fcascan.newsreaderapp.ui.components.UserCard
import com.fcascan.newsreaderapp.ui.viewmodels.UsersScreenViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun UsersScreen(
    viewModel: UsersScreenViewModel,
    navigateToUserMap: (Long) -> Unit,
) {
    val TAG = "UsersScreen"
    val isRefreshing: Boolean by viewModel.isRefreshing.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)
    val usersList: List<UserModel> by viewModel.usersList.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = { viewModel.fetchUsersFromRemote() },
                indicator = { state, trigger ->
                    SwipeRefreshIndicator(
                        state = state,
                        refreshTriggerDistance = trigger,
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                }
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    items(usersList) { item ->
                        UserCard(
                            name = item.firstName,
                            lastName = item.lastName,
                            address = item.address,
                            email = item.email,
                            websiteUrl = item.websiteUrl,
                            onClick = {
                                Log.d(TAG, "User clicked -> ${item.id}")
                                navigateToUserMap(item.id)
                            },
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "spec:shape=Normal,width=360,height=700,unit=dp,dpi=480"
)
@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "spec:shape=Normal,width=700,height=360,unit=dp,dpi=480"
)
@Composable
fun UsersScreenPortraitPreview() {
    UsersScreen(
        viewModel = viewModel(),
        navigateToUserMap = {},
    )
}