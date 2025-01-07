package com.fcascan.newsreaderapp.data.network.entity

import com.fcascan.newsreaderapp.domain.GeoLocationModel
import com.fcascan.newsreaderapp.domain.UserModel

data class UserNetworkEntity(
    val id: Int?,
    val firstname: String?,
    val lastname: String?,
    val email: String?,
    val birthDate: String?,
    val userLogin: UserLogin?,
    val userAddress: UserAddress?,
    val phone: String?,
    val website: String?,
    val userCompany: UserCompany?
) {
    fun toUserModel(): UserModel {
        return UserModel(
            id = id?.toLong() ?: -1L,
            firstName = firstname ?: "",
            lastName = lastname ?: "",
            email = email ?: "",
            websiteUrl = website ?: "",
            geoLocation = GeoLocationModel(
                //TODO: Cambiar el valor default o tener en cuenta para null
                latitude = userAddress?.userGeo?.lat?.toDouble() ?: -34.603851,
                longitude = userAddress?.userGeo?.lng?.toDouble() ?: -58.381775
            ),
        )
    }
}

data class UserLogin(
    val uuid: String?,
    val username: String?,
    val password: String?,
    val md5: String?,
    val sha1: String?,
    val registered: String?
)

data class UserAddress(
    val street: String?,
    val suite: String?,
    val city: String?,
    val zipcode: String?,
    val userGeo: UserGeo?
)

data class UserGeo(
    val lat: String?,
    val lng: String?
)

data class UserCompany(
    val name: String?,
    val catchPhrase: String?,
    val bs: String?
)