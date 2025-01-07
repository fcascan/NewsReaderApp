package com.fcascan.newsreaderapp.ui.views

import SearchBar
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fcascan.newsreaderapp.domain.NewsModel
import com.fcascan.newsreaderapp.ui.components.NewsCard
import com.fcascan.newsreaderapp.ui.viewmodels.NewsScreenViewModel

@Composable
fun NewsScreen(
    viewModel: NewsScreenViewModel,
    navigateToNewsDetail: (Long) -> Unit,
) {
    val TAG = "NewsScreen"
    val newsList: List<NewsModel> by viewModel.newsList.collectAsState()
    var query by remember { mutableStateOf("") }

    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            SearchBar(
                query = query,
                onQueryChanged = { query = it },
                onSearch = {},
            )
            LazyColumn(
                //TODO: Agregar algun callback para cuando se realiza un scroll arriba de todo para en ese momento realizar un refresh de las noticias
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                items(newsList) { item ->
                    NewsCard(
                        title = item.title,
                        author = item.author,
                        date = item.date,
                        imageUrl = item.imageUrl,
                        content = item.content,
                        onClick = {
                            Log.d(TAG, "NewsCard onClick -> id=${item.id}")
                            navigateToNewsDetail(item.id)
                        },
                    )
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
fun NewsScreenPortraitPreview() {
    NewsScreen(
        viewModel = viewModel(),
        navigateToNewsDetail = {},
    )
}