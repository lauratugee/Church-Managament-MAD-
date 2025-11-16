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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.churchmanagementsystem.ChurchManagementApplication
import com.example.churchmanagementsystem.models.MassSchedule
import com.example.churchmanagementsystem.util.DataState
import com.example.churchmanagementsystem.viewmodel.AdminViewModel
import com.example.churchmanagementsystem.viewmodel.AdminViewModelFactory



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateMassScheduleScreen(navController: NavController){
    val context= LocalContext.current
    val application=context.applicationContext as ChurchManagementApplication

    val adminViewModel: AdminViewModel = viewModel(
        factory = AdminViewModelFactory(application.adminRepository)
    )
    val schedules by adminViewModel.massSchedules.collectAsState(initial=DataState.Idle)

    var showDialog by remember { mutableStateOf(false) }
    var selectedSchedule by remember { mutableStateOf<MassSchedule?>(null) }

    LaunchedEffect(Unit) {
        adminViewModel.getMassSchedules()
    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
                selectedSchedule = null
            },
            title = { Text("Delete Mass Schedule") },
            text = { Text("Are you sure you want to delete this mass schedule?") },
            confirmButton={
                Button(
                    onClick = {
                        selectedSchedule?.let { schedule ->
                            adminViewModel.deleteMassSchedule(schedule.id)

                        }
                        showDialog = false
                        selectedSchedule = null
                    }
                ) {
                    Text("Delete")
                }

            },
            dismissButton={
                Button(
                    onClick = {
                        showDialog = false
                        selectedSchedule = null
                    }
                ) {
                    Text("Cancel")
                }
            }
        )



    }

    Scaffold(
        topBar={
                TopAppBar(
                    title = { Text("Update Mass Schedule") },
                    navigationIcon= {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                )

               },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                navController.navigate("add_mass_schedule")
                            }
                        ) {
                            Icon(Icons.Default.Add, contentDescription = "Add Mass Schedule")
                    }

        }

    ){ innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)

            ){
                when (val state = schedules) {
                    is DataState.Loading -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }

                    is DataState.Success -> {
                        val schedules = state.data
                        if (schedules.isEmpty()) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ){
                                Text("No mass schedules available")
                            }
                        } else {
                            LazyColumn(
                                contentPadding = PaddingValues(16.dp),
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ){
                                items(schedules, key = { it.id}){schedule->
                                    MassScheduleCard(
                                        schedule=schedule,
                                        onDeleteClicked = {
                                            showDialog = true
                                            selectedSchedule = schedule
                                        }
                                    )
                                }




                            }
                        }
                        }
                    is DataState.Error -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center){
                            Text(text = "Error: ${state.message}")
                        }
                    }

                    is DataState.Idle -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                    }
            }
        }
}
@Composable
fun MassScheduleCard(
    schedule:MassSchedule,
    onDeleteClicked:() -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)) {
                Text(
                    text = "Day: ${schedule.dayOfWeek} at ${schedule.startTime}",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Language: ${schedule.language}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            IconButton(onClick = onDeleteClicked) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = MaterialTheme.colorScheme.error
                )
            }

        }
    }
}

