package com.aryaman.pomodoroapp.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun TimerScreen(navController: NavHostController, innerPadding: PaddingValues) {
    Text("Timer", modifier = Modifier.padding(innerPadding))
}