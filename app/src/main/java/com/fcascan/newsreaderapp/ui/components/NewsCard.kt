package com.fcascan.newsreaderapp.ui.components

import android.util.Log
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.fcascan.newsreaderapp.R

@Composable
fun NewsCard(
    title: String,
    author: String,
    date: String,
    content: String,
    imageUrl: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .clickable(
                onClick = {
                    Log.d("NewsCard", "Card clicked $title")
                    onClick()
                }
            ),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .clickable(onClick = onClick)
                .padding(16.dp)
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentWidth()
                    .weight(3f)
            ) {
                Image(
                    modifier = Modifier
                        .height(120.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp)),
                    painter = if(imageUrl.isNotEmpty()) rememberAsyncImagePainter(model = imageUrl)
                    else painterResource(R.drawable.newspaper),
                    contentDescription = title,
                    contentScale = ContentScale.FillWidth
                )
                Text(
                    maxLines = 2,
                    fontSize = 26.sp,
                    textAlign = TextAlign.Center,
                    text = title,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        maxLines = 1,
                        fontSize = 18.sp,
                        fontStyle = FontStyle.Italic,
                        text = author
                    )
                    Text(
                        maxLines = 1,
                        fontSize = 18.sp,
                        text = date
                    )
                }
                Text(
                    maxLines = 4,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Justify,
                    text = content,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview
@Composable
fun NewsCardPreview() {
    NewsCard(
        title = "Quisque non ligula laoreet, volutpat velit cursus, condimentum arcu.",
        author = "Author",
        date = "04/02/2023 13:25:21",
        content = "Semper nulla nisi habitasse montes. Ipsum ullamcorper interdum curae;. " +
                "Cras dis justo non litora metus libero scelerisque volutpat per auctor integer. " +
                "Curae; id natoque lacinia blandit lectus venenatis arcu pellentesque nunc " +
                "vestibulum suspendisse. Montes pharetra proin mus orci aptent. Dis, inceptos " +
                "enim mus aliquet libero torquent. Mauris lorem sagittis egestas nibh pulvinar" +
                " luctus nascetur facilisis conubia netus.",
        imageUrl = "https://dummyimage.com/800x430/b3a3c9/aenean.png&text=jsonplaceholder.org",
        onClick = {}
    )
}