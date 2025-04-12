package com.aryaman.pomodoroapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.aryaman.pomodoroapp.logic.Session

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
    navController: NavHostController = rememberNavController(),
    themeChangeState: MutableIntState
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier,
                title = {
                    Text(text = "Settings")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {},
                expandedHeight = TopAppBarDefaults.MediumAppBarCollapsedHeight,
                windowInsets = TopAppBarDefaults.windowInsets,
                colors = TopAppBarDefaults.topAppBarColors(),
                scrollBehavior = null
            )
        }
    ) {

        //For theme change
        val themeDialogState = remember { mutableStateOf(false) }
        val themeChoices = listOf("Light", "Dark", "System Default")
        if (themeDialogState.value) {
            ThemeDialog(themeChoices, themeChangeState) {
                themeDialogState.value = false
            }
        }

        //For Editable Focus & Break time
        val focusTimeDialogState = remember { mutableStateOf(false) }
        val breakTimeDialogState = remember { mutableStateOf(false) }
        if (focusTimeDialogState.value) {
            TimeDialog("Focus") {
                focusTimeDialogState.value = false
            }
        }
        if (breakTimeDialogState.value) {
            TimeDialog("Break") {
                breakTimeDialogState.value = false
            }
        }

        //Main UI Code
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clickable {
                        focusTimeDialogState.value = true
                    }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(vertical = 15.dp, horizontal = 50.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = "Focus Time",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.W600
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = Session.editableFocusTimeState.floatValue.toString(),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.W400
                    )
                }
            }
            ThemeChangeSetting(themeDialogState, themeChoices, themeChangeState)
        }
    }
}

@Preview
@Composable
fun TimeDialog(focusOrBreak: String = "Focus", onDismissRequest: () -> Unit = {}) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = true
        )
    ) {
        val sharedPreferences = LocalContext.current.getSharedPreferences("time_prefs", 0)
        val editState =
            remember { mutableStateOf(if (focusOrBreak == "Focus") Session.editableFocusTimeState.floatValue.toString() else Session.editableBreakTimeState.floatValue.toString()) }
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
                Text(
                    text = "Edit $focusOrBreak Time",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.W500
                )
                Spacer(modifier = Modifier.height(15.dp))
                OutlinedTextField(
                    modifier = Modifier,
                    value = editState.value,
                    onValueChange = {
                        if (
                            it.all { char ->
                                char.isDigit() or (char== '.')
                            }
                        )
                            editState.value = it
                    },
                    enabled = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal,
                        imeAction = ImeAction.Done
                    )
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp, horizontal = 5.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val localContext = LocalContext.current
                    TextButton(
                        shape = RoundedCornerShape(22),
                        onClick = {
                            try {
                                if (focusOrBreak == "Focus") {
                                    Session.editableFocusTimeState.floatValue =
                                        editState.value.toFloat()
                                    sharedPreferences.edit().putFloat(
                                        "focus_time",
                                        Session.editableFocusTimeState.floatValue
                                    ).apply()
                                }
                                else{
                                    Session.editableBreakTimeState.floatValue = editState.value.toFloat()
                                    sharedPreferences.edit().putFloat(
                                        "break_time",
                                        Session.editableBreakTimeState.floatValue
                                    ).apply()
                                }
                            }
                            catch (e : NumberFormatException){
                                Toast.makeText(localContext, "Invalid Input", Toast.LENGTH_SHORT).show()
                            }
                            onDismissRequest()
                        }) { Text(text = "Save") }
                    TextButton(
                        shape = RoundedCornerShape(22),
                        onClick = {
                            onDismissRequest()
                        }) { Text(text = "Cancel") }
                }
            }
        }
    }
}

@Composable
private fun ThemeChangeSetting(
    themeDialogState: MutableState<Boolean>,
    themeChoices: List<String>,
    themeChangeState: MutableIntState
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                themeDialogState.value = true
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 15.dp, horizontal = 50.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Theme",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.W600
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = themeChoices[themeChangeState.intValue],
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.W400
            )
        }
    }
}

//@Preview
@Composable
private fun ThemeDialog(
    themeChoices: List<String>,
    themeChangeState: MutableIntState,
    onDismissRequest: () -> Unit = {}
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = true
        )
    ) {
        val sharedPreferences = LocalContext.current.getSharedPreferences("theme_prefs", 0)
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
                Text(
                    text = "Choose theme",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.W500
                )
                Spacer(modifier = Modifier.height(10.dp))
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalAlignment = Alignment.Start,
                ) {
                    items(themeChoices.size) { i ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    themeChangeState.intValue = i
                                    sharedPreferences.edit().putInt("last_theme", i).apply()
                                    onDismissRequest()
                                },
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            RadioButton(
                                modifier = Modifier,
                                selected = i == themeChangeState.intValue,
                                onClick = {
                                    themeChangeState.intValue = i
                                    sharedPreferences.edit().putInt("last_theme", i).apply()
                                    onDismissRequest()
                                },
                            )
                            Text(text = themeChoices[i])
                        }
                    }
                }
            }
        }
    }
}
