package com.fcascan.newsreaderapp.ui.views

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fcascan.newsreaderapp.ui.components.LoadingIndicator
import com.fcascan.newsreaderapp.ui.viewmodels.UsersMapScreenViewModel
import com.fcascan.newsreaderapp.use_cases.GetGeoLocationByUserIdUseCase
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserMapScreen(
    userId: Long?,
    viewModel: UsersMapScreenViewModel,
    navigateBack: () -> Unit,
) {
    val TAG = "UserMapScreen"

    val geoLocation = viewModel.geoLocation.collectAsState().value
    viewModel.getGeoLocationByUserId(userId)

    val cameraPositionState = rememberCameraPositionState()
    val markerState = rememberMarkerState()

    LaunchedEffect(geoLocation) {
        geoLocation?.let {
            cameraPositionState.position = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(
                LatLng(it.latitude, it.longitude), 15f
            )
            markerState.position = LatLng(it.latitude, it.longitude)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
             TopAppBar(
                 title = {
                     Text(text = "User Location")
                 },
                 navigationIcon = {
                     IconButton(
                         onClick = {
                             Log.d(TAG, "Back button clicked")
                             navigateBack()
                         }
                     ) {
                         Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                     }
                 }
             )
        }
    ) { paddingValues ->
        if (geoLocation != null) {
            GoogleMap(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                cameraPositionState = cameraPositionState
            ) {
                Marker(
                    state = markerState,
                    title = "User Location",
                    snippet = "latitude: ${geoLocation.latitude}, longitude: ${geoLocation.longitude}",
                    draggable = false,
                )
            }
        } else {
            LoadingIndicator()
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "spec:shape=Normal,width=360,height=700,unit=dp,dpi=480"
)
@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "spec:shape=Normal,width=700,height=360,unit=dp,dpi=480"
)
@Composable
fun UserMapScreenPortraitPreview() {
    UserMapScreen(
        userId = 1,
        viewModel = UsersMapScreenViewModel(
            GetGeoLocationByUserIdUseCase()
        ),
        navigateBack = {},
    )
}
