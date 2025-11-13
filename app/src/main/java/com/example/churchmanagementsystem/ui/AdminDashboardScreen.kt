package com.example.churchmanagementsystem.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AdminDashboardScreen(navController: NavController){
    Column(
        modifier=Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) }
        Text("Admin Dashboard", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(32.dp))

        AdminNavButton(text= "Create Group"){
            navController.navigate("create_group")
        }
        AdminNavButton(teext="Update mass schedule"){
            navController.navigate("update_mass_schedule")

        }
        AdminNavButton(text="Approve and update members"){
            navController.navigate("approve_members")
        }
        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = {
            navController.navigate("login") {
                popUpTo("admin_dashboard") {
                    inclusive = true
                }
            }) {
                Text("Logout")
            }
        }

@Composable
fun AdminNavButton(text: String, onClick: () -> Unit){
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(text)
    }
}

