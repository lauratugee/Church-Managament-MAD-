package com.example.churchmanagementsystem.ui.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class MassSchedule(val id:Int, val day:String, val time:String, val description:String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateMassScheduleScreen(navController: NavController){
    val schedules by remember {
        mutableStateOf(
            listOf(
                MassSchedule(1, "Sunday", "8:30AM", "Kiswahili"),
                MassSchedule(2, "Sunday", "11:00AM", "English")
            )
        )
    }
    Scaffold(
        topBar={
                TopAppBar(
                    title = { Text("Update Mass Schedule") },
                    navigationIcon={
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
        }

    ){ innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ){
                Text(
                    text = "Current Mass Schedule",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ){
                    items(schedules,key={it.id}){schedule->
                        MassScheduleCard(schedule=schedule)
                    }
                }

            }
        }
}
@Composable
fun MassScheduleCard(schedule:MassSchedule) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Day: ${schedule.day} at ${schedule.time}",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = schedule.description,
                style = MaterialTheme.typography.bodyMedium
            )

        }
    }
}

