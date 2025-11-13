package com.example.churchmanagementsystem.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun HomeScreen(
    userEmail: String,
    onLogout: () -> Unit
){
    Surface(modifier=Modifier.fillMaxSize()){
        Column(
            modifier=Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
        Text("Welcome",
            style=MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier=Modifier.height(8.dp))
        Text(
            text= userEmail,
            style=MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier=Modifier.height(32.dp))
        Button(onClick=onLogout){
            Text("Logout")
        }
        }




    }

}