package com.fcascan.newsreaderapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fcascan.newsreaderapp.domain.GeoLocationModel
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

    private val _geoLocationModel = MutableStateFlow<GeoLocationModel?>(null)
    val geoLocation = _geoLocationModel
    fun setGeoLocation(geoLocationModel: GeoLocationModel?) { _geoLocationModel.value = geoLocationModel }

    fun getGeoLocationByUserId(userId: Long?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (userId == null) return@launch
            val result = getGeoLocationByUserIdUseCase.invoke(userId)
            setGeoLocation(result)
        }
    }
}