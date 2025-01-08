package com.fcascan.newsreaderapp.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fcascan.newsreaderapp.R

@Composable
fun UserCard(
    name: String,
    lastName: String,
    address: String,
    email: String,
    websiteUrl: String,
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
                .clickable(onClick = {
                    Log.d("UserCard", "UserCard $name $lastName clicked")
                    onClick()
                })
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        maxLines = 1,
                        fontSize = 20.sp,
                        text = lastName
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        maxLines = 1,
                        fontSize = 16.sp,
                        fontStyle = FontStyle.Italic,
                        text = name
                    )
                }
                Text(
                    maxLines = 1,
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Normal,
                    text = "address: $address"
                )
                Text(
                    buildAnnotatedString {
                        append("email: ")
                        withLink(
                            LinkAnnotation.Url(
                                email,
                                TextLinkStyles(style = SpanStyle(color = MaterialTheme.colorScheme.tertiary, fontStyle = FontStyle.Italic))
                            )
                        ) {
                            append(email)
                        }
                    }
                )
                Text(
                    buildAnnotatedString {
                        append("website: ")
                        withLink(
                            LinkAnnotation.Url(
                                websiteUrl,
                                TextLinkStyles(style = SpanStyle(color = MaterialTheme.colorScheme.tertiary, fontStyle = FontStyle.Italic))
                            )
                        ) {
                            append(websiteUrl)
                        }
                    }
                )
            }
            Image(
                modifier = Modifier
                    .weight(0.15f)
                    .clip(RoundedCornerShape(8.dp)),
                painter = painterResource(R.drawable.person_pin),
                contentDescription = name + lastName
            )
        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
fun UserCardPreview() {
    UserCard(
        name = "Fernando",
        lastName = "Castro Canosa",
        address = "123 Calle Falsa, Apt. 4",
        email = "fcc@mail.com",
        websiteUrl = "https://en.wikipedia.org/wiki/FCC",
        onClick = {}
    )
}