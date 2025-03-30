package com.aryaman.pomodoroapp.logic

import android.annotation.SuppressLint
import android.os.CountDownTimer
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf

object Session{
    var isRunning = mutableStateOf(false)
    var timeLeft = mutableIntStateOf(25*60)
    private val timer = object : CountDownTimer(timeLeft.intValue * 1000L, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            timeLeft.intValue = (millisUntilFinished / 1000).toInt()
        }
        override fun onFinish() {
            timeLeft.intValue = 0
            isRunning.value = false
        }

    }

    fun timerStart(){
        timer.start()
    }

    fun timerStop(){
        timer.cancel()
        timeLeft.intValue = 25*60
    }
}

@SuppressLint("DefaultLocale")
fun formatTime(seconds: Int): String {
    val minutes = seconds / 60
    val sec = seconds % 60
    return String.format("%02d:%02d", minutes, sec) // Ensures "MM:SS" format
}