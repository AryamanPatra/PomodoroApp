package com.aryaman.pomodoroapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.aryaman.pomodoroapp.ui.nav.MainNavHost
import com.aryaman.pomodoroapp.ui.theme.PomodoroAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PomodoroAppTheme {
                MainNavHost()
            }
        }
    }
}