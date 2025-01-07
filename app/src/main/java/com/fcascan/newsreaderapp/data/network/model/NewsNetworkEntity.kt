package com.fcascan.newsreaderapp.data.network.model

import com.fcascan.newsreaderapp.domain.NewsModel

data class NewsNetworkEntity(
    val id: String?,
    val title: String?,
    val content: String?,
    val url: String?,
    val thumbnail: String?,
    val publishedAt: String?,
    val userId: String?
) {
    fun toNewsModel() = NewsModel(
        id = id?.toLong() ?: -1,
        title = title ?: "",
        date = publishedAt ?: "",
        author = userId ?: "",
        content = content  ?: "",
        imageUrl = thumbnail ?: "",
        sourceUrl = url ?: ""
    )
}

