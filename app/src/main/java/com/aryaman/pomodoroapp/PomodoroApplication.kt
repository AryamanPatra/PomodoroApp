package com.aryaman.pomodoroapp

import android.app.Application
import com.aryaman.pomodoroapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PomodoroApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PomodoroApplication)
            modules(appModule)
        }
    }
}