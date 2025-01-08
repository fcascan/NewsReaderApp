package com.fcascan.newsreaderapp.use_cases

import android.util.Log
import com.fcascan.newsreaderapp.data.local.repository.UsersLocalRepository
import com.fcascan.newsreaderapp.domain.GeoLocationModel
import javax.inject.Inject

class GetGeoLocationByUserIdUseCase  @Inject constructor(
private val usersLocalRepository: UsersLocalRepository,
) {
    companion object {
        private val TAG = GetGeoLocationByUserIdUseCase::class.java.simpleName
    }

    suspend operator fun invoke(userId: Long?) : GeoLocationModel? {
        Log.d(TAG, "invoke(): userId: $userId")
        if (userId == null) return null
        val user = usersLocalRepository.getById(userId)
        Log.d(TAG, "invoke(): user from db: $user")
        if (user == null) return null
        return GeoLocationModel(
            latitude = user.geoLocation.latitude,
            longitude = user.geoLocation.longitude,
        )
    }
}