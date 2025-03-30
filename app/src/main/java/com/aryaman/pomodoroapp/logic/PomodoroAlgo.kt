package com.aryaman.pomodoroapp.logic

import android.annotation.SuppressLint
import android.os.CountDownTimer
import androidx.compose.runtime.MutableState

fun timerStart(isRunning: MutableState<Boolean>, timeLeft: MutableState<Int>){
    if (!isRunning.value){
        isRunning.value = true
        object : CountDownTimer(timeLeft.value * 1000L, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft.value = (millisUntilFinished / 1000).toInt()
            }
            override fun onFinish() {
                timeLeft.value = 0
                isRunning.value = false
            }

        }.start()
    }
}

@SuppressLint("DefaultLocale")
fun formatTime(seconds: Int): String {
    val minutes = seconds / 60
    val sec = seconds % 60
    return String.format("%02d:%02d", minutes, sec) // Ensures "MM:SS" format
}