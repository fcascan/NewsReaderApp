package com.fcascan.newsreaderapp.ui.components

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.fcascan.newsreaderapp.R

@Composable
fun UserCard(
    name: String,
    lastName: String,
    avatarUrl: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .clickable(onClick = onClick),
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
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.75f)
            ) {
                Text(
                    maxLines = 1,
                    fontSize = 28.sp,
                    text = lastName
                )
                Text(
                    maxLines = 1,
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Italic,
                    text = name
                )
            }
            Image(
                modifier = Modifier
                    .weight(0.25f)
                    .clip(RoundedCornerShape(8.dp)),
                painter = if(avatarUrl.isNotEmpty()) rememberAsyncImagePainter(model = avatarUrl)
                else painterResource(R.drawable.sample_user_icon),
                contentDescription = name + lastName
            )
        }
    }
}

@Preview
@Composable
fun UserCardPreview() {
    UserCard(
        name = "Fernando",
        lastName = "Castro Canosa",
        avatarUrl = "",
        onClick = {}
    )
}