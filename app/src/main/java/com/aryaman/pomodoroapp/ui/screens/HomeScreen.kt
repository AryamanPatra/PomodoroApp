package com.aryaman.pomodoroapp.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.aryaman.pomodoroapp.ui.CustomBottomNavBar
import com.aryaman.pomodoroapp.ui.CustomTopAppBar
import com.aryaman.pomodoroapp.ui.nav.BottomNavHost

@Composable
fun HomeScreen(navController: NavHostController) {
    val bottomNavController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { CustomTopAppBar(navController) },
        bottomBar = { CustomBottomNavBar(bottomNavController) }
    ) { innerPadding ->
        BottomNavHost(bottomNavController,innerPadding)
    }
}