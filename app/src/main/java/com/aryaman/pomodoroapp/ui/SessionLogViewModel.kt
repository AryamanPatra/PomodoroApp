package com.aryaman.pomodoroapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aryaman.pomodoroapp.data.model.SessionLog
import com.aryaman.pomodoroapp.data.repository.SessionLogRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class SessionLogViewModel(private val sessionLogRepository: SessionLogRepository): ViewModel() {

    val sessionLogs = MutableStateFlow<List<SessionLog>>(emptyList())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            sessionLogRepository.getAllSessionLogs().distinctUntilChanged().collect(){
                sessionLogs.value = it
            }
        }
    }

    fun upsertSessionLog(sessionLog: SessionLog) = viewModelScope.launch {
        sessionLogRepository.upsertSessionLog(sessionLog)
    }

    fun deleteAllSessionLogs() = viewModelScope.launch{
        sessionLogRepository.deleteAllSessionLogs()
    }

    fun deleteSessionLog(sessionLog: SessionLog) = viewModelScope.launch {
        sessionLogRepository.deleteSessionLog(sessionLog)
    }
}