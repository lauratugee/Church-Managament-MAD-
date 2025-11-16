package com.example.churchmanagementsystem.ui.mass_schedule

import android.app.Application
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.churchmanagementsystem.data.local.AppDatabase
import com.example.churchmanagementsystem.data.MassScheduleRepository
import com.example.churchmanagementsystem.models.MassSchedule



@Composable
fun MassScheduleScreen () {
    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context)
    val repository = MassScheduleRepository(db.massScheduleDao())
    val viewModel: MassScheduleViewModel = viewModel(
        factory = MassScheduleViewModelFactory(repository)
    )
    val massSchedules by viewModel.allMassSchedules.collectAsState()

    var dayOfWeek by remember { mutableStateOf("") }
    var startTime by remember { mutableStateOf("") }
    var endTime by remember { mutableStateOf("") }
    var language by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {
        Text("Mass Schedule", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = dayOfWeek,
            onValueChange = { dayOfWeek = it },
            label = { Text("Day of the week") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = startTime,
            onValueChange = { startTime = it },
            label = { Text("Start time") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = endTime,
            onValueChange = { endTime = it },
            label = { Text("End time") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = language,
            onValueChange = { language = it },
            label = { Text("Language") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (dayOfWeek.isNotBlank() && startTime.isNotBlank() && endTime.isNotBlank() && language.isNotBlank()) {
                    viewModel.addMassSchedule(dayOfWeek, startTime, endTime, language)

                    dayOfWeek = ""
                    startTime = ""
                    endTime = ""
                    language = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Mass Schedule")

        }
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(massSchedules) { massSchedule ->
                MassScheduleItem(massSchedule)
                Divider()
            }
        }
    }
}
@Composable
fun MassScheduleItem(schedule: MassSchedule) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation=CardDefaults.cardElevation(defaultElevation = 4.dp),
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = " ${schedule.dayOfWeek}: ${schedule.startTime} - ${schedule.endTime}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Language: ${schedule.language}",
                style = MaterialTheme.typography.bodyMedium
            )



        }

}





    }
