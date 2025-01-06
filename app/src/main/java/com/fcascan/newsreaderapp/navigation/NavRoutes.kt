package com.fcascan.newsreaderapp.navigation

import kotlinx.serialization.Serializable

object NavRoutes {
    @Serializable
    object News

    @Serializable
    object Settings

    @Serializable
    data class NewsDetail(val id: Long)

    @Serializable
    object Users

    @Serializable
    data class UserMap(val id: Long)
}
