package com.aryaman.pomodoroapp.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aryaman.pomodoroapp.ui.screens.HistoryScreen
import com.aryaman.pomodoroapp.ui.screens.HomeScreen
import com.aryaman.pomodoroapp.ui.screens.SettingScreen

@Composable
fun PomodoroNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ScreenNames.HomeScreen.name
    ){
        composable(route = ScreenNames.HomeScreen.name){
            HomeScreen(navController)
        }
        composable(route = ScreenNames.HistoryScreen.name) {
            HistoryScreen(navController)
        }
        composable(route = ScreenNames.SettingScreen.name) {
            SettingScreen(navController)
        }
    }
}