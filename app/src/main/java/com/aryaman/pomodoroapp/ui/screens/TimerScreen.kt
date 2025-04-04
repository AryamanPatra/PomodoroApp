package com.aryaman.pomodoroapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.aryaman.pomodoroapp.logic.Session
import com.aryaman.pomodoroapp.logic.formatTime

@Composable
fun TimerScreen(navController: NavHostController, innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
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
            text = Session.sessionCount.intValue.toString(),
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.SemiBold
        )
        IconButton(
            modifier = Modifier
                .size(130.dp),
            onClick = {
                when (Session.currentSessionType.value) {
                    Session.SessionType.Focus -> {
                        Session.timerStop()
                    }
                    Session.SessionType.Break -> {
                        Session.timerStop()
                    }
                    Session.SessionType.None -> {
                        Session.timerStart()
                    }
                }
            },
            colors = IconButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContainerColor = MaterialTheme.colorScheme.surfaceDim,
                disabledContentColor = MaterialTheme.colorScheme.onSurface,
            )
        ) {
            Icon(
                modifier = Modifier.size(100.dp),
                imageVector = when (Session.currentSessionType.value){
                    Session.SessionType.None -> Icons.Default.PlayArrow
                    else -> Icons.Default.Stop
                },
                contentDescription = "Play"
            )
        }
        Text(
            modifier = Modifier.padding(top = 25.dp),
            text = when (Session.currentSessionType.value){
                Session.SessionType.Focus -> "Focus Timer"
                Session.SessionType.Break -> "Break Timer"
                Session.SessionType.None -> "Start Timer"
            },
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            modifier = Modifier,
            text = if (Session.currentSessionType.value == Session.SessionType.None) "--:--" else formatTime(Session.timeLeft.intValue),
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.SemiBold
        )

    }
}