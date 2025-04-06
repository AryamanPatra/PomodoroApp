package com.aryaman.pomodoroapp.data.repository

import com.aryaman.pomodoroapp.data.model.SessionLog
import com.aryaman.pomodoroapp.data.source.SessionLogDao

class SessionLogRepository(private val sessionLogDao: SessionLogDao){

    suspend fun upsertSessionLog(sessionLog: SessionLog) = sessionLogDao.upsertSessionLog(sessionLog)

    fun getAllSessionLogs() = sessionLogDao.getAllSessionLogs()

    suspend fun deleteAllSessionLogs() = sessionLogDao.deleteAllSessionLogs()

    suspend fun deleteSessionLog(sessionLog: SessionLog) = sessionLogDao.deleteSessionLog(sessionLog)

}