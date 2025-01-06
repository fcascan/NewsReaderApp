package com.fcascan.newsreaderapp.use_cases

import com.fcascan.newsreaderapp.models.GeoLocation
import javax.inject.Inject

class GetGeoLocationByUserIdUseCase  @Inject constructor(

) {
    companion object {
        private val TAG = GetGeoLocationByUserIdUseCase::class.java.simpleName
    }

    suspend operator fun invoke(userId: Long?) : GeoLocation? {
        if (userId == null) return null
        return GeoLocation(
            latitude = -34.603851,
            longitude = -58.381775,
        )
    }
}