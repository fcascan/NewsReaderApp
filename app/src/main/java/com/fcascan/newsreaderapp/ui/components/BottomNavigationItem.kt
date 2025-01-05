package com.fcascan.newsreaderapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavigationItem(
    modifier: Modifier,
    icon: ImageVector,
    contentDescription: String,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
    val contentColor = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface

    Surface(
        modifier = modifier
            .padding(4.dp)
            .fillMaxHeight()
            .clickable(onClick = onClick),
        color = backgroundColor,
        shape = MaterialTheme.shapes.small,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(4.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = contentColor
            )
            Text(
                text = label,
                color = contentColor,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(
    widthDp = 120,
    heightDp = 80
)
@Composable
fun BottomNavigationItemHomePreview() {
    BottomNavigationItem(
        modifier = Modifier.fillMaxSize(),
        icon = Icons.Default.List,
        contentDescription = "Home",
        label = "Home",
        selected = true,
        onClick = {}
    )
}

@Preview(
    widthDp = 120,
    heightDp = 80
)
@Composable
fun BottomNavigationItemUsersPreview() {
    BottomNavigationItem(
        modifier = Modifier.fillMaxSize(),
        icon = Icons.Default.Face,
        contentDescription = "Users",
        label = "Users",
        selected = false,
        onClick = {}
    )
}

@Preview(
    widthDp = 120,
    heightDp = 80
)
@Composable
fun BottomNavigationItemPreview() {
    BottomNavigationItem(
        modifier = Modifier.fillMaxSize(),
        icon = Icons.Default.Settings,
        contentDescription = "Settings",
        label = "Settings",
        selected = false,
        onClick = {}
    )
}