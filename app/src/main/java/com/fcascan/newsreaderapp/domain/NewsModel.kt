package com.fcascan.newsreaderapp.domain

class NewsModel(
    val id: Long,
    val title: String,
    val date: String,
    var author: String,
    val content: String,
    val imageUrl: String,
    val sourceUrl: String,
) {
}