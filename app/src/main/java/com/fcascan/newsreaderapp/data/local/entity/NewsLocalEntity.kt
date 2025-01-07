package com.fcascan.newsreaderapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fcascan.newsreaderapp.data.network.entity.UserAddress
import com.fcascan.newsreaderapp.data.network.entity.UserCompany
import com.fcascan.newsreaderapp.data.network.entity.UserLogin
import com.fcascan.newsreaderapp.domain.NewsModel

@Entity(tableName = "news_table")
data class NewsLocalEntity(
    @PrimaryKey
    val id: Long,
    val title: String,
    val date: String,
    val author: String,
    val content: String,
    val imageUrl: String,
    val sourceUrl: String,
) {
    fun toNewsModel(): NewsModel {
        return NewsModel(
            id = id,
            title = title,
            date = date,
            author = author,
            content = content,
            imageUrl = imageUrl,
            sourceUrl = sourceUrl
        )
    }
}