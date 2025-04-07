package com.aryaman.pomodoroapp.ui.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aryaman.pomodoroapp.ui.SessionLogViewModel
import com.aryaman.pomodoroapp.ui.screens.HistoryScreen
import com.aryaman.pomodoroapp.ui.screens.HomeScreen
import com.aryaman.pomodoroapp.ui.screens.SettingScreen
import com.aryaman.pomodoroapp.ui.screens.TimerScreen
import org.koin.java.KoinJavaComponent.inject

@Composable
fun MainNavHost(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ScreenNames.HomeScreen.name
    ){
        composable(route = ScreenNames.HomeScreen.name){
            HomeScreen(navController)
        }
        composable(route = ScreenNames.SettingScreen.name) {
            SettingScreen(navController)
        }
    }
}

@Composable
fun BottomNavHost(navController: NavHostController, innerPadding: PaddingValues) {
    val viewModel: SessionLogViewModel by inject(SessionLogViewModel::class.java)
    NavHost(
        navController = navController,
        startDestination = ScreenNames.TimerScreen.name
    ){
        composable(route = ScreenNames.TimerScreen.name){
            TimerScreen(viewModel, innerPadding)
        }
        composable(route = ScreenNames.HistoryScreen.name) {
            HistoryScreen(viewModel, innerPadding)
        }
    }
}