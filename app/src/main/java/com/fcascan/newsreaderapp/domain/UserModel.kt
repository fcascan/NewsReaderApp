package com.fcascan.newsreaderapp.domain

import com.fcascan.newsreaderapp.data.local.entity.UserLocalEntity
import com.google.gson.Gson

class UserModel(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val websiteUrl: String,
    val geoLocation: GeoLocationModel
) {
    fun toUserLocalEntity() : UserLocalEntity {
        return UserLocalEntity(
            id = id,
            firstname = firstName,
            lastname = lastName,
            email = email,
            websiteUrl = websiteUrl,
            geoLocation = Gson().toJson(geoLocation)
        )
    }

    override fun toString(): String {
        return "UserModel(" +
            "id=$id, " +
            "firstName='$firstName', " +
            "lastName='$lastName', " +
            "email='$email', " +
            "websiteUrl='$websiteUrl', " +
            "geoLocation=$geoLocation" +
        ")"
    }
}