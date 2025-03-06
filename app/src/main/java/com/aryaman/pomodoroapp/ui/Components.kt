package com.aryaman.pomodoroapp.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.aryaman.pomodoroapp.ui.nav.ScreenNames


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun CustomTopAppBar(navController: NavHostController = rememberNavController()) {
    TopAppBar(
        modifier = Modifier.padding(8.dp),
        title = {
            Text(text = "Pomodoro", modifier = Modifier.padding(start = 8.dp),
                fontSize = 24.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.SansSerif)
        },
        navigationIcon = {},
        actions = {
            IconButton(onClick = { navController.navigate(ScreenNames.SettingScreen.name) }) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings")
            }
        },
        expandedHeight = TopAppBarDefaults.TopAppBarExpandedHeight,
        windowInsets = TopAppBarDefaults.windowInsets,
        colors = TopAppBarDefaults.topAppBarColors(),
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    )
}

@Composable
@Preview
fun CustomBottomNavBar(navController: NavHostController = rememberNavController()) {
    NavigationBar {
        NavigationBarItem(
            modifier = Modifier,
            selected = navController.currentDestination?.route == ScreenNames.HomeScreen.name,
            onClick = { navController.navigate(ScreenNames.HomeScreen.name) },
            icon = { Icon(imageVector = Icons.Default.Timer, contentDescription = "HomeScreen") },
            enabled = true,
            label = { Text("Timer") },
            alwaysShowLabel = false,
            colors = NavigationBarItemDefaults.colors(),
            interactionSource = null,
        )
        NavigationBarItem(
            modifier = Modifier,
            selected = navController.currentDestination?.route == ScreenNames.HistoryScreen.name,
            onClick = { navController.navigate(ScreenNames.HistoryScreen.name) },
            icon = { Icon(imageVector = Icons.Default.History, contentDescription = "History") },
            enabled = true,
            label = { Text("History") },
            alwaysShowLabel = false,
            colors = NavigationBarItemDefaults.colors(),
            interactionSource = null,
        )
    }
}
