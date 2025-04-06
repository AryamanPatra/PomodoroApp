package com.aryaman.pomodoroapp.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aryaman.pomodoroapp.data.model.DateConverter
import com.aryaman.pomodoroapp.data.model.SessionLog

@Database(
    entities = [SessionLog::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(DateConverter::class)
abstract class PomodoroDatabase: RoomDatabase(){

    abstract fun sessionLogDao(): SessionLogDao

}