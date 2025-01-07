package com.fcascan.newsreaderapp.data.network

import com.fcascan.newsreaderapp.data.network.entity.NewsNetworkEntity
import com.fcascan.newsreaderapp.data.network.entity.UserNetworkEntity
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {
    @Headers("Content-Type: application/json")
    @GET("/posts")
    suspend fun getNews() : List<NewsNetworkEntity>

    @Headers("Content-Type: application/json")
    @GET("/users")
    suspend fun getUsers() : List<UserNetworkEntity>
}