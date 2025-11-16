package com.example.churchmanagementsystem.ui.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.platform.LocalContext
import com.example.churchmanagementsystem.ChurchManagementApplication
import com.example.churchmanagementsystem.models.MassSchedule
import com.example.churchmanagementsystem.viewmodel.AdminViewModel
import com.example.churchmanagementsystem.viewmodel.AdminViewModelFactory




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMassScheduleScreen(navController: NavController) {
    val context = LocalContext.current
    val application = context.applicationContext as ChurchManagementApplication
    val adminViewModel: AdminViewModel = viewModel(
        factory = AdminViewModelFactory(application.adminRepository)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Mass Schedule") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")

                    }
                }
            )
        }

    ) { innerPadding ->
        var dayOfWeek by remember { mutableStateOf("") }
        var startTime by remember { mutableStateOf("") }
        var language by remember { mutableStateOf("") }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {
            OutlinedTextField(
                value = dayOfWeek,
                onValueChange = { dayOfWeek = it },
                label = { Text("Day of the week") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = startTime,
                onValueChange = { startTime = it },
                label = { Text("Start time") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = language,
                onValueChange = { language = it },
                label = { Text("Language") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val newSchedule = MassSchedule(
                        id=0,
                        dayOfWeek = dayOfWeek,
                        startTime = startTime,
                        language = language
                    )

                    adminViewModel.addMassSchedule(newSchedule) { isSuccess ->
                        if (isSuccess) {
                            navController.navigateUp()
                        } else {

                        }
                    }
                },

                modifier = Modifier.fillMaxWidth()
            ) {


                Text("Save schedule")
            }
        }
    }
}


