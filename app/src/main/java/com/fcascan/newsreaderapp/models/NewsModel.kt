package com.fcascan.newsreaderapp.models

class NewsModel(
    val id: Long,
    val title: String,
    val date: String,
    val author: String,
    val content: String,
    val imageUrl: String,
    val sourceUrl: String,
) {
}