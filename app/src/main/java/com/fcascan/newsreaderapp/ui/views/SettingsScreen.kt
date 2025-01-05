package com.fcascan.newsreaderapp.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fcascan.newsreaderapp.ui.components.SettingsSwitch
import com.fcascan.newsreaderapp.ui.viewmodels.SettingsScreenViewModel
import com.fcascan.newsreaderapp.utils.SharedPreferencesUtil

@Composable
fun SettingsScreen(
    viewModel: SettingsScreenViewModel,
) {
    val TAG = "SettingsScreen"
    val isDarkTheme = viewModel.isDarkModeEnabled.collectAsState().value
    //TODO: Estas opciones extra por ahora no hacen nada, evaluar si agregar otras funcionalidades
    var option2 by remember { mutableStateOf(false) }
    var option3 by remember { mutableStateOf(false) }

    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SettingsSwitch(
                name = "Dark Mode",
                isChecked = isDarkTheme,
                onCheckedChange = {
                    viewModel.setIsDarkMode(it)
                }
            )
            SettingsSwitch(
                name = "Option #2",
                isChecked = option2,
                onCheckedChange = {
//                    viewModel.setOption2(it)
                    option2 = it
                }
            )
            SettingsSwitch(
                name = "Option #3",
                isChecked = option3,
                onCheckedChange = {
//                    viewModel.setOption3(it)
                    option3 = it
                }
            )
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
fun SettingsScreenPortraitPreview() {
    SettingsScreen(
        viewModel = SettingsScreenViewModel(
            sharedPreferencesUtil = SharedPreferencesUtil(
                context = androidx.compose.ui.platform.LocalContext.current
            )
        ),
    )
}