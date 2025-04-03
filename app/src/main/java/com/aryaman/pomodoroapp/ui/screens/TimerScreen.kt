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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.aryaman.pomodoroapp.logic.Session
import com.aryaman.pomodoroapp.logic.formatTime

@Composable
fun TimerScreen(navController: NavHostController, innerPadding: PaddingValues) {
    val displayTimeLeft = remember(Session.isFocusing.value, Session.isBreak.value) {
        if (Session.isFocusing.value && !Session.isBreak.value) {
            Session.focusTimeLeft
        } else if (Session.isBreak.value && !Session.isFocusing.value) {
            Session.breakTimeLeft
        } else {
            mutableIntStateOf(0)
        }
    }

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
                Session.isFocusing.value = !Session.isFocusing.value
                if (Session.isFocusing.value) {
                    Session.focusTimerStart()
                } else {
                    if (Session.isBreak.value) {
                        Session.isBreak.value = false
                        Session.breakTimerStop()
                    } else {
                        Session.focusTimerStop()
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
                imageVector = if (Session.isFocusing.value && !Session.isBreak.value) Icons.Default.Stop
                else if (Session.isBreak.value && !Session.isFocusing.value) Icons.Default.Stop
                else Icons.Default.PlayArrow,
                contentDescription = "Play"
            )
        }
        Text(
            modifier = Modifier.padding(top = 25.dp),
            text = if (Session.isFocusing.value && !Session.isBreak.value) "Focus Time"
            else if (Session.isBreak.value && !Session.isFocusing.value) "Break Time"
            else "Start Timer",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            modifier = Modifier,
            text = formatTime(displayTimeLeft.intValue),
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.SemiBold
        )

    }
}