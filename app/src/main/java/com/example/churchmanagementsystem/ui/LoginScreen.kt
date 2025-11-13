package com.example.churchmanagementsystem.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.churchmanagementsystem.viewmodel.AuthViewModel

@Composable
fun LoginScreen() {
    val authViewModel: AuthViewModel = viewModel()

    var email by remember {mutableStateOf("") }
    var password by remember {mutableStateOf("") }

    val loginState by authViewModel.loginState.collectAsState()

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text("Login", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(32.dp))

            //Email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            //Password
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(32.dp))

            //Login
            Button(
                onClick = {
                    authViewModel.login(email, password)
                },
                modifier = Modifier.fillMaxWidth (),
                enabled = loginState !is AuthViewModel.DataState.Loading)
            {
                Text("Login")
            }
            Spacer(modifier = Modifier.height(16.dp))

            when (val state = loginState) {
                is AuthViewModel.DataState.Loading -> {
                    CircularProgressIndicator()
                }

                is AuthViewModel.DataState.Success -> {
                    Text(
                        "Login successful: ${state.data.email}",
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                is AuthViewModel.DataState.Error -> {
                    Text("Login failed: ${state.message}", color = MaterialTheme.colorScheme.error)
                }

                is AuthViewModel.DataState.Idle -> {

                }

            }


        }

    }
}