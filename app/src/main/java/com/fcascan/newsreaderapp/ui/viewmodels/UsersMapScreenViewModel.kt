package com.fcascan.newsreaderapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fcascan.newsreaderapp.models.GeoLocation
import com.fcascan.newsreaderapp.use_cases.GetGeoLocationByUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersMapScreenViewModel @Inject constructor(
    private val getGeoLocationByUserIdUseCase: GetGeoLocationByUserIdUseCase,
) : ViewModel() {
    companion object {
        private val TAG = UsersMapScreenViewModel::class.java.simpleName
    }

    private val _geoLocation = MutableStateFlow<GeoLocation?>(null)
    val geoLocation = _geoLocation
    fun setGeoLocation(geoLocation: GeoLocation?) { _geoLocation.value = geoLocation }

    fun getGeoLocationByUserId(userId: Long?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (userId == null) return@launch
            val result = getGeoLocationByUserIdUseCase.invoke(userId)
            setGeoLocation(result)
        }
    }
}