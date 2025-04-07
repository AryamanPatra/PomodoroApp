package com.aryaman.pomodoroapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.sql.Date

@Entity(tableName = "session_log")
data class SessionLog(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null ,
    val sessionCount: Int,
    val totalFocusTimeInMin: Int,
    @TypeConverters(DateConverter::class)
    val date: Date
)
