package com.aryaman.pomodoroapp.ui.screens

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.aryaman.pomodoroapp.data.model.SessionLog
import com.aryaman.pomodoroapp.ui.SessionLogCard
import com.aryaman.pomodoroapp.ui.SessionLogViewModel
import org.koin.java.KoinJavaComponent.inject
import java.sql.Date

@Composable
fun HistoryScreen(navController: SessionLogViewModel, innerPadding: PaddingValues) {

    val viewModel: SessionLogViewModel by inject(SessionLogViewModel::class.java)
    val sessionLogs = viewModel.sessionLogs.collectAsState()

    val deleteDialogState = remember { mutableStateOf(false) }
    if (deleteDialogState.value)
        DeleteAllDialog(viewModel) {
            deleteDialogState.value = false
        }

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 12.dp)
                .fillMaxWidth()
                .scrollable(
                    state = rememberScrollState(),
                    orientation = Orientation.Vertical,
                    reverseDirection = true,
                    flingBehavior = ScrollableDefaults.flingBehavior(),
                )
                .weight(0.88f),
        ) {
            items(sessionLogs.value.size) { index ->
                SessionLogCard(sessionLog = sessionLogs.value[sessionLogs.value.size - index - 1])
            }
        }
        TextButton(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .fillMaxWidth()
                .weight(0.12f),
            onClick = { deleteDialogState.value = true },
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

@Composable
fun DeleteAllDialog(viewModel: SessionLogViewModel, onDismissRequest: () -> Unit) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = true
        )
    ) {
        Surface(
            modifier = Modifier
                .padding(12.dp)
                .wrapContentSize(),
            shape = RoundedCornerShape(12),
            color = MaterialTheme.colorScheme.surfaceContainer,
        ) {
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(vertical = 15.dp, horizontal = 20.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Are you sure you want to delete all history?")
                Spacer(modifier = Modifier.size(15.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp, horizontal = 5.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        modifier = Modifier,
                        onClick = {
                            viewModel.deleteAllSessionLogs()
                            onDismissRequest.invoke()
                        },
                        shape = RoundedCornerShape(22),
                        colors = ButtonColors(
                            containerColor = MaterialTheme.colorScheme.error,
                            contentColor = MaterialTheme.colorScheme.onError,
                            disabledContainerColor = Color.Gray,
                            disabledContentColor = Color.White
                        ),
                    ) { Text("Delete") }
                    TextButton(
                        modifier = Modifier,
                        onClick = {
                            onDismissRequest.invoke()
                        },
                        shape = RoundedCornerShape(22),
                        colors = ButtonColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            disabledContainerColor = Color.Gray,
                            disabledContentColor = Color.White
                        ),
                    ) { Text("Cancel") }
                }
            }
        }
    }
}
