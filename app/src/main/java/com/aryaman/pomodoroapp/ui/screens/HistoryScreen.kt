package com.aryaman.pomodoroapp.ui.screens

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aryaman.pomodoroapp.data.model.SessionLog
import com.aryaman.pomodoroapp.ui.SessionLogCard
import com.aryaman.pomodoroapp.ui.SessionLogViewModel
import org.koin.java.KoinJavaComponent.inject
import java.sql.Date

@Composable
fun HistoryScreen(navController: SessionLogViewModel, innerPadding: PaddingValues) {

    val viewModel: SessionLogViewModel by inject(SessionLogViewModel::class.java)
    val sessionLogs = viewModel.sessionLogs.collectAsState()

    Column(
        modifier = Modifier.padding(innerPadding).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyColumn(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp).fillMaxWidth().scrollable(
                state = rememberScrollState(),
                orientation = Orientation.Vertical,
                reverseDirection = true,
                flingBehavior = ScrollableDefaults.flingBehavior(),
            ).weight(0.88f),
        ) {
            items(sessionLogs.value.size) { index ->
                SessionLogCard(sessionLog = sessionLogs.value[index])
            }
        }
        TextButton(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .fillMaxWidth().weight(0.12f),
            onClick = { viewModel.deleteAllSessionLogs() },
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.White
            ),
            shape = RoundedCornerShape(22),
            enabled = sessionLogs.value.isNotEmpty()
        ) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = "Delete All")
        }
    }
}