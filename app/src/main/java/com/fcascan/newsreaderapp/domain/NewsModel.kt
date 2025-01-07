package com.fcascan.newsreaderapp.domain

import com.fcascan.newsreaderapp.data.local.entity.NewsLocalEntity

class NewsModel(
    val id: Long,
    val title: String,
    val date: String,
    var author: String,
    val content: String,
    val imageUrl: String,
    val sourceUrl: String,
) {
    fun toNewsLocalEntity() = NewsLocalEntity(
        id = id,
        title = title,
        date = date,
        author = author,
        content = content,
        imageUrl = imageUrl,
        sourceUrl = sourceUrl,
    )

    override fun toString(): String {
        return "NewsModel(" +
            "id=$id, " +
            "title='$title', " +
            "date='$date', " +
            "author='$author', " +
            "content='${content.take(20)}', " +
            "imageUrl='$imageUrl', " +
            "sourceUrl='$sourceUrl'" +
        ")"
    }
}