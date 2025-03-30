package com.aryaman.pomodoroapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.aryaman.pomodoroapp.logic.formatTime
import com.aryaman.pomodoroapp.logic.timerStart

@Composable
fun TimerScreen(navController: NavHostController, innerPadding: PaddingValues) {
    val isRunning = remember { mutableStateOf(false) }
    val timeLeft = remember { mutableIntStateOf(25*60) }
    Column(
        modifier = Modifier.padding(innerPadding).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier,
            text = "Sessions",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            modifier = Modifier.padding(bottom = 25.dp),
            text = "0",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.SemiBold
        )
        IconButton(
            modifier = Modifier
                .size(130.dp),
            onClick = {
                timerStart(isRunning, timeLeft)
            },
            colors = IconButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContainerColor = MaterialTheme.colorScheme.surfaceDim,
                disabledContentColor = MaterialTheme.colorScheme.onSurface,
            )
        ) {
            Icon(modifier = Modifier.size(100.dp),imageVector = if(isRunning.value) Icons.Default.Pause else Icons.Default.PlayArrow, contentDescription = "Play")
        }
        Text(
            modifier = Modifier.padding(top = 25.dp),
            text = "Focus Time",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            modifier = Modifier,
            text = formatTime(timeLeft.intValue),
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.SemiBold
        )

    }
}