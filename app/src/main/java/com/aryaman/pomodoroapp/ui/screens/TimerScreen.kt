package com.aryaman.pomodoroapp.ui.screens

import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aryaman.pomodoroapp.data.model.SessionLog
import com.aryaman.pomodoroapp.logic.Session
import com.aryaman.pomodoroapp.logic.formatTime
import com.aryaman.pomodoroapp.ui.SessionLogViewModel
import java.sql.Date

@Composable
fun TimerScreen(viewModel: SessionLogViewModel, innerPadding: PaddingValues) {
    //Session Times with stored value
    initSessionTime(LocalContext.current)

    //Main UI Code
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
                        saveSession(true, viewModel)
                        Session.timerStop()
                    }

                    Session.SessionType.Break -> {
                        saveSession(false, viewModel)
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
                imageVector = when (Session.currentSessionType.value) {
                    Session.SessionType.None -> Icons.Default.PlayArrow
                    else -> Icons.Default.Stop
                },
                contentDescription = "Play"
            )
        }
        Text(
            modifier = Modifier.padding(top = 25.dp),
            text = when (Session.currentSessionType.value) {
                Session.SessionType.Focus -> "Focus Timer"
                Session.SessionType.Break -> "Break Timer"
                Session.SessionType.None -> "Start Timer"
            },
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            modifier = Modifier,
            text = if (Session.currentSessionType.value == Session.SessionType.None) "--:--" else formatTime(
                Session.timeLeft.intValue
            ),
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.SemiBold
        )

    }
}

private fun initSessionTime(context: Context) {
    Session.editableFocusTimeState.floatValue = context.getSharedPreferences("time_prefs",0).getFloat("focus_time",25f)
    Session.editableBreakTimeState.floatValue = context.getSharedPreferences("time_prefs",0).getFloat("break_time",5f)
}

private fun saveSession(wasFocusing: Boolean, viewModel: SessionLogViewModel) {
    val sessionCount = Session.sessionCount.intValue
    val timeFocusedInMin =
        (if (wasFocusing) Session.timeMap[Session.SessionType.Focus]!! - Session.timeLeft.intValue else 0) +
                (sessionCount * Session.timeMap[Session.SessionType.Focus]!!)
    viewModel.upsertSessionLog(
        SessionLog(
            sessionCount = sessionCount,
            totalFocusTimeInMin = timeFocusedInMin,
            date = Date(System.currentTimeMillis())
        )
    )
}
