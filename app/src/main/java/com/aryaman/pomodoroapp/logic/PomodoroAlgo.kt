package com.aryaman.pomodoroapp.logic

import android.annotation.SuppressLint
import android.os.CountDownTimer
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf

object Session{
    var sessionCount = mutableIntStateOf(0)
    var isFocusing = mutableStateOf(false)
    var isBreak = mutableStateOf(false)
    var focusTimeLeft = mutableIntStateOf((0.15*60).toInt()) //For testing purpose the time is set low
    var breakTimeLeft = mutableIntStateOf((0.1*60).toInt())

    private val focusTimer = object : CountDownTimer(focusTimeLeft.intValue * 1000L, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            focusTimeLeft.intValue = (millisUntilFinished / 1000).toInt()
        }
        override fun onFinish() {
            focusTimeLeft.intValue = 0
            isFocusing.value = false
            focusTimeLeft.intValue = (0.15*60).toInt()
            sessionCount.intValue++
            breakTimerStart()
            isBreak.value = true
        }

    }

    fun focusTimerStart(){
        focusTimer.start()
    }

    fun focusTimerStop(){
        focusTimer.cancel()
        focusTimeLeft.intValue = (0.15*60).toInt()
    }

    private val breakTimer = object : CountDownTimer(focusTimeLeft.intValue * 1000L, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            breakTimeLeft.intValue = (millisUntilFinished / 1000).toInt()
        }
        override fun onFinish() {
            breakTimeLeft.intValue = 0
            isBreak.value = false
        }

    }

    fun breakTimerStart(){
        breakTimer.start()
    }

    fun breakTimerStop(){
        breakTimer.cancel()
        breakTimeLeft.intValue = (0.1*60).toInt()
    }
}

@SuppressLint("DefaultLocale")
fun formatTime(seconds: Int): String {
    val minutes = seconds / 60
    val sec = seconds % 60
    return String.format("%02d:%02d", minutes, sec) // Ensures "MM:SS" format
}