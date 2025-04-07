package com.aryaman.pomodoroapp.di

import androidx.room.Room.databaseBuilder
import com.aryaman.pomodoroapp.data.repository.SessionLogRepository
import com.aryaman.pomodoroapp.data.source.PomodoroDatabase
import com.aryaman.pomodoroapp.ui.SessionLogViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val POMODORO_DATABASE = "pomodoro_database"

val appModule = module {
    single {
        databaseBuilder(
            androidContext(),
            PomodoroDatabase::class.java,
            POMODORO_DATABASE
        ).build()
    }
    single {
        get<PomodoroDatabase>().sessionLogDao()
    }
    single {
        SessionLogRepository(get())
    }
    single {
        SessionLogViewModel(get())
    }

}