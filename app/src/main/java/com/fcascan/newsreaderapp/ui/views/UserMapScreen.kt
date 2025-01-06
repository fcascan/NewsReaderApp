package com.fcascan.newsreaderapp.ui.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

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

    LaunchedEffect(geoLocation) {
        geoLocation?.let {
            cameraPositionState.position = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(
                LatLng(it.latitude, it.longitude), 15f
            )
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        if (geoLocation != null) {
            GoogleMap(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                cameraPositionState = cameraPositionState
            ) {
                Marker(
                    state = MarkerState(position = LatLng(geoLocation.latitude, geoLocation.longitude)),
                    title = "User Location"
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
