package com.aryaman.pomodoroapp.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.aryaman.pomodoroapp.data.model.SessionLog
import kotlinx.coroutines.flow.Flow
import java.sql.Date

@Dao
interface SessionLogDao {

    @Upsert
    suspend fun upsertSessionLog(sessionLog: SessionLog)

    @Query("SELECT * FROM session_log")
    fun getAllSessionLogs(): Flow<List<SessionLog>>

    @Query("DELETE FROM session_log")
    suspend fun deleteAllSessionLogs()

    @Delete
    suspend fun deleteSessionLog(sessionLog: SessionLog)

}
