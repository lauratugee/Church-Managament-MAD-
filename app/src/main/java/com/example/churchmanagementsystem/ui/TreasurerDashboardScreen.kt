package com.example.churchmanagementsystem.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController



@Composable
fun TreasurerDashboardScreen(navController: NavController){
    Column(
        modifier=Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Text("Treasurer Dashboard", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(32.dp))

        TreasurerNavButton(text= "Dashboard"){}
        TreasurerNavButton(text="Tithes"){navController.navigate("manage_tithes")}
        TreasurerNavButton(text="Fundraisers"){navController.navigate("manage_fundraisers")}
        TreasurerNavButton(text="Reports"){navController.navigate("financial_reports")
        }
        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = {
            navController.navigate("login") {
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = true
                }
            }
        }) {
            Text("Logout")
        }
    }
}

@Composable
fun TreasurerNavButton(text: String, onClick: () -> Unit){
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(text)
    }



    }
