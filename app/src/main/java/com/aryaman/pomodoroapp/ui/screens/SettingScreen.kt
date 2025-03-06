package com.aryaman.pomodoroapp.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun SettingScreen(navController: NavHostController = rememberNavController()){
    Text("Settings")
}