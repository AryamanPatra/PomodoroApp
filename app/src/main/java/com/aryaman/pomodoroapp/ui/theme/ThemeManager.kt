package com.aryaman.pomodoroapp.ui.theme

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

class ThemeManager(context: Context) {
    private val prefs = context.getSharedPreferences("theme_prefs", Context.MODE_PRIVATE)

    fun saveTheme(mode: Int) {
        prefs.edit().putInt("theme_mode", mode).apply()
        AppCompatDelegate.setDefaultNightMode(mode) // 🔥 Apply theme change
    }

    fun getTheme(): Int {
        return prefs.getInt("theme_mode", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM) // Default: System Mode
    }
}