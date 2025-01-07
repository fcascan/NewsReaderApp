package com.fcascan.newsreaderapp.domain

class UserModel(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val websiteUrl: String,
    val geoLocation: GeoLocationModel
) {
}