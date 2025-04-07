package com.aryaman.pomodoroapp

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.aryaman.pomodoroapp.data.model.SessionLog
import com.aryaman.pomodoroapp.data.source.PomodoroDatabase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.sql.Date
import kotlin.time.Duration

class SessionLogDaoTest {

    private lateinit var database: PomodoroDatabase

    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PomodoroDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @Test
    fun insertSessionLogAndGetLogs() = runTest(context = StandardTestDispatcher(), timeout = Duration.parse("20s")) {
        val sessionLog = SessionLog(
            sessionCount = 4,
            totalFocusTimeInMin = 27,
            date = Date(System.currentTimeMillis())
        )

        database.sessionLogDao().upsertSessionLog(sessionLog)

        lateinit var sessionLogs: List<SessionLog>
        database.sessionLogDao().getAllSessionLogs().collect {
            sessionLogs = it
            assertEquals(1, sessionLogs.size)
        }
    }

    @Test
    fun insertUpdateAndDeletionTest() = runTest(context = StandardTestDispatcher(), timeout = Duration.parse("20s")) {
        lateinit var sessionLogs: List<SessionLog>
        database.sessionLogDao().getAllSessionLogs().collect {
            sessionLogs = it
        }

        val testSessionLogs = listOf(
            SessionLog(
                sessionCount = 4,
                totalFocusTimeInMin = 27,
                date = Date(System.currentTimeMillis())
            ),
            SessionLog(
                sessionCount = 2,
                totalFocusTimeInMin = 15,
                date = Date(System.currentTimeMillis())
            ),
            SessionLog(
                sessionCount = 1,
                totalFocusTimeInMin = 5,
                date = Date(System.currentTimeMillis())
            ),
            SessionLog(
                sessionCount = 3,
                totalFocusTimeInMin = 21,
                date = Date(System.currentTimeMillis())
            ),
            SessionLog(
                sessionCount = 5,
                totalFocusTimeInMin = 30,
                date = Date(System.currentTimeMillis())
            )
        )

        testSessionLogs.forEach {
            database.sessionLogDao().upsertSessionLog(it)
        }

        assertEquals(5, sessionLogs.size)

        val updatedVal = SessionLog(
            id = sessionLogs[3].id,
            sessionCount = sessionLogs[3].sessionCount,
            totalFocusTimeInMin = sessionLogs[3].totalFocusTimeInMin+3,
            date = sessionLogs[3].date
        )

        database.sessionLogDao().upsertSessionLog(updatedVal)

        assertEquals(sessionLogs[3].totalFocusTimeInMin, updatedVal.totalFocusTimeInMin)

        database.sessionLogDao().deleteSessionLog(sessionLogs[2])

        assertEquals(4, sessionLogs.size)

        database.sessionLogDao().deleteAllSessionLogs()

        assertEquals(0, sessionLogs.size)

    }

}