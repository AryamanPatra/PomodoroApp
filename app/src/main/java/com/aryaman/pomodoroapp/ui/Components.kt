package com.aryaman.pomodoroapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aryaman.pomodoroapp.data.model.SessionLog
import com.aryaman.pomodoroapp.ui.nav.ScreenNames
import java.sql.Date


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun CustomTopAppBar(navController: NavHostController = rememberNavController()) {
    TopAppBar(
        modifier = Modifier.padding(8.dp),
        title = {
            Text(
                text = "Pomodoro", modifier = Modifier.padding(start = 8.dp),
                fontSize = 24.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.SansSerif
            )
        },
        navigationIcon = {},
        actions = {
            IconButton(onClick = { navController.navigate(ScreenNames.SettingScreen.name) }) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings")
            }
        },
        expandedHeight = TopAppBarDefaults.MediumAppBarCollapsedHeight,
        windowInsets = TopAppBarDefaults.windowInsets,
        colors = TopAppBarDefaults.topAppBarColors(),
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    )
}

@Composable
@Preview
fun CustomBottomNavBar(navController: NavHostController = rememberNavController()) {
    val navBackStackEntry by navController.currentBackStackEntryAsState() // ✅ Get current route
    val currentRoute = navBackStackEntry?.destination?.route
    NavigationBar {
        NavigationBarItem(
            modifier = Modifier,
            selected = currentRoute == ScreenNames.TimerScreen.name,
            onClick = {
                navController.navigate(ScreenNames.TimerScreen.name) {
                    popUpTo(ScreenNames.HomeScreen.name) { inclusive = false }
                    launchSingleTop = true
                }
            },
            icon = { Icon(imageVector = Icons.Default.Timer, contentDescription = "TimerScreen") },
            enabled = true,
            label = {
                Text(
                    "Timer"
                )
            },
            alwaysShowLabel = false,
            colors = NavigationBarItemDefaults.colors(),
            interactionSource = null,
        )
        NavigationBarItem(
            modifier = Modifier,
            selected = currentRoute == ScreenNames.HistoryScreen.name,
            onClick = {
                navController.navigate(ScreenNames.HistoryScreen.name) {
                    popUpTo(ScreenNames.HomeScreen.name) { inclusive = false }
                    launchSingleTop = true
                }
            },
            icon = { Icon(imageVector = Icons.Default.History, contentDescription = "History") },
            enabled = true,
            label = { Text("History") },
            alwaysShowLabel = false,
            colors = NavigationBarItemDefaults.colors(),
            interactionSource = null,
        )
    }
}

@Preview
@Composable
fun SessionLogCard(sessionLog: SessionLog = SessionLog(1, 2, 3, Date(System.currentTimeMillis()))) {
    Card(
        modifier = Modifier.padding(8.dp).fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Sessions: ${sessionLog.sessionCount}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Minutes focused: ${sessionLog.totalFocusTimeInMin}", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.size(5.dp))
            Text(text = "${sessionLog.date}", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}