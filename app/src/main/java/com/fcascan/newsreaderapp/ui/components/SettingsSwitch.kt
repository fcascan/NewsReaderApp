package com.fcascan.newsreaderapp.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SettingsSwitch(
    name: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    val TAG = "SettingsSwitch"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name
        )
        Switch(
            checked = isChecked,
            onCheckedChange = {
                Log.d(TAG, "SettingsSwitch: Switch Dark Mode -> onCheckedChange=$it")
                onCheckedChange(it)
            }
        )
    }
}

@Preview(
    showBackground = true,
)
@Composable
fun SettingsSwitchPreview() {
    SettingsSwitch(
        name = "Dark Mode",
        isChecked = true,
        onCheckedChange = {}
    )
}