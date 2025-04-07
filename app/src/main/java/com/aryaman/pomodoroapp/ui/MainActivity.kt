package com.aryaman.pomodoroapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.mutableIntStateOf
import com.aryaman.pomodoroapp.ui.nav.MainNavHost
import com.aryaman.pomodoroapp.ui.theme.PomodoroAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val appThemeState = mutableIntStateOf(getSharedPreferences("theme_prefs", MODE_PRIVATE).getInt("last_theme", 2))
        setContent {
            PomodoroAppTheme(
                darkTheme = when(appThemeState.intValue){
                    0 -> false
                    1 -> true
                    else -> isSystemInDarkTheme()
                }
            ) {
                MainNavHost(appThemeState)
            }
        }
    }
}