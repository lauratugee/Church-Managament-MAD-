package com.example.churchmanagementsystem.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGroupScreen(navController: NavController) {
    var groupName by remember {mutableStateOf("")}
    var groupDescription by remember {mutableStateOf("")}

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create Group")},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )

                    }
                }
            )
        }
    ){ paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement= Arrangement.spacedBy(16.dp)

        ) {
            Text(
                "Enter new group details",
                style = MaterialTheme.typography.headlineMedium
            )
            //Group name
            OutlinedTextField(
                value = groupName,
                onValueChange = { groupName = it },
                label = { Text("Group Name") },
                modifier = Modifier.fillMaxWidth()
            )
            //Group Description
            OutlinedTextField(
                value = groupDescription,
                onValueChange = { groupDescription = it },
                label = { Text("Group Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )
            //Submit Button
            Button(
                onClick = {
                    println("Creating group: $groupName, $groupDescription")
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = groupName.isNotBlank() && groupDescription.isNotBlank()
            ) {
                Text("Create Group")
            }
        }

    }

}



