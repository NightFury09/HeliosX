package com.heliosx.heliosx_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.heliosx.heliosx_2.ui.theme.HeliosX_2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HeliosX_2Theme {
                DashboardScreen()
            }
        }
    }
}
