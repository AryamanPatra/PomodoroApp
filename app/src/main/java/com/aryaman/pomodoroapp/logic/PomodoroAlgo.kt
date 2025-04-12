package com.aryaman.pomodoroapp.logic

import android.annotation.SuppressLint
import android.app.Application
import android.os.CountDownTimer
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import kotlin.math.roundToInt

object Session {
    val sessionCount = mutableIntStateOf(0)
    val currentSessionType = mutableStateOf(SessionType.None)
    val editableFocusTimeState = mutableFloatStateOf(25f)
    val editableBreakTimeState = mutableFloatStateOf(5f)
    val timeMap = mapOf(
        Pair(SessionType.Focus, (editableFocusTimeState.floatValue * 60).roundToInt()),
        Pair(SessionType.Break, (editableBreakTimeState.floatValue * 60).roundToInt()),
        Pair(SessionType.None, 0)
    )
    val timeLeft = mutableIntStateOf(timeMap.getValue(SessionType.Focus))

    private var timer: CountDownTimer? = null

    fun timerStart() {
        timer?.cancel()
        if (currentSessionType.value == SessionType.None) {
            currentSessionType.value = SessionType.Focus
        }
        timer = object : CountDownTimer(timeLeft.intValue * 1000L, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft.intValue = (millisUntilFinished / 1000).toInt()
            }

            override fun onFinish() {
                if (currentSessionType.value == SessionType.Focus) {
                    sessionCount.intValue++
                    currentSessionType.value = SessionType.Break
                } else {
                    currentSessionType.value = SessionType.Focus
                }
                timeLeft.intValue = timeMap.getValue(currentSessionType.value)
                timerStart()
            }
        }.start()
    }

    fun timerStop() {
        timer?.cancel()
        sessionCount.intValue = 0
        currentSessionType.value = SessionType.None
        timeLeft.intValue = timeMap.getValue(SessionType.Focus)
    }

    enum class SessionType {
        Focus,
        Break,
        None
    }
}

@SuppressLint("DefaultLocale")
fun formatTime(seconds: Int): String {
    val minutes = seconds / 60
    val sec = seconds % 60
    return String.format("%02d:%02d", minutes, sec) // Ensures "MM:SS" format
}