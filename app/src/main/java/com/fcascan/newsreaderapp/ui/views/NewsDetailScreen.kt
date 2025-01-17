package com.fcascan.newsreaderapp.ui.views

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.fcascan.newsreaderapp.R
import com.fcascan.newsreaderapp.ui.components.LoadingIndicator
import com.fcascan.newsreaderapp.ui.theme.MyFonts
import com.fcascan.newsreaderapp.ui.viewmodels.NewsDetailScreenViewModel
import com.fcascan.newsreaderapp.use_cases.GetNewsByNewsIdUseCase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailScreen(
    newsId: Long?,
    viewModel: NewsDetailScreenViewModel,
    navigateBack: () -> Unit,
) {
    val TAG = "NewsDetailScreen"

    val news = viewModel.news.collectAsState().value
    viewModel.getNewsByNewsId(newsId)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "News Detail")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            Log.d(TAG, "Back button clicked")
                            navigateBack()
                        }
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        if (news == null) {
            LoadingIndicator()
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState()),
            ) {
                Image(
                    modifier = Modifier
                        .height(240.dp)
                        .padding(8.dp)
                        .fillMaxWidth(),
                    painter = if(news.imageUrl.isNotEmpty()) rememberAsyncImagePainter(model = news.imageUrl)
                    else painterResource(R.drawable.newspaper),
                    contentDescription = news.title,
                    contentScale = ContentScale.FillWidth
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = news.title,
                    fontSize = 32.sp,
                    fontFamily = MyFonts.TimesNewRoman,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally),
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = news.author,
                        fontSize = 20.sp,
                        fontFamily = MyFonts.TimesNewRoman,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier
                            .padding(8.dp)
                    )
                    Text(
                        text = news.date,
                        fontSize = 18.sp,
                        fontFamily = MyFonts.TimesNewRoman,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier
                            .padding(8.dp)
                    )
                }
                Text(
                    text = news.content,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.Start),
                )

                //TODO: Agregar botones flotantes de incremento y decremento de zoom
                //TODO: Agregar funcionalidad de compartir

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Source: ")
                        }
                        withLink(
                            LinkAnnotation.Url(
                                news.sourceUrl,
                                TextLinkStyles(style = SpanStyle(color = MaterialTheme.colorScheme.tertiary, fontStyle = FontStyle.Italic))                            )
                        ) {
                            append(news.sourceUrl)
                        }
                    },
                    modifier = Modifier.padding(8.dp)
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
@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "spec:shape=Normal,width=700,height=360,unit=dp,dpi=480"
)
@Composable
fun NewsDetailScreenPortraitPreview() {
    NewsDetailScreen(
        newsId = 1,
        viewModel = viewModel(),
        navigateBack = {},
    )
}
