package com.fcascan.newsreaderapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fcascan.newsreaderapp.domain.GeoLocationModel
import com.fcascan.newsreaderapp.domain.UserModel
import com.google.gson.Gson

@Entity(tableName = "users_table")
data class UserLocalEntity(
    @PrimaryKey
    val id: Long,
    val firstname: String,
    val lastname: String,
    val email: String,
    val websiteUrl: String,
    val geoLocation: String
) {
    fun toUserModel(): UserModel {
        return UserModel(
            id = id,
            firstName = firstname,
            lastName = lastname,
            email = email,
            websiteUrl = websiteUrl,
            geoLocation = Gson().fromJson(geoLocation, GeoLocationModel::class.java)
        )
    }
}