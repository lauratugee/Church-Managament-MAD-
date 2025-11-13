package com.example.churchmanagementsystem.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.churchmanagementsystem.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit,
    onLoginSuccess: (String) -> Unit
){

    val authViewModel: AuthViewModel = viewModel()


    var email by remember {mutableStateOf("") }
    var password by remember {mutableStateOf("") }

    val loginState by authViewModel.loginState.collectAsState()

    LaunchedEffect(loginState){
        if (loginState is AuthViewModel.DataState.Success){

            val userEmail=(loginState as AuthViewModel.DataState.Success).data.email
            onLoginSuccess(userEmail)
        }
        is AuthViewModel.DataState.Success -> {
            val user= (loginState as AuthViewModel.DataState.Success).data

        LaunchedEffect(key1=user){
            val destination = if(user.role.equals("admin", ignoreCase = true)){
                "admin_dashboard"
            }else{
                "home"
            }
            navController.navigate(destination){
                popUpTo("login"){
                    inclusive=true
                }
            }
        }


    }

    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Login", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(32.dp))

            //Email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
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
                modifier = Modifier.fillMaxWidth(),
                enabled = loginState !is AuthViewModel.DataState.Loading && email.isNotEmpty() && password.isNotEmpty()
            )
            {
                Text("Login")
            }
            Spacer(modifier = Modifier.height(16.dp))

            when (val state = loginState) {
                is AuthViewModel.DataState.Loading -> {
                    CircularProgressIndicator()
                }

                is AuthViewModel.DataState.Error -> {
                    Text("Login failed: ${state.message}", color = MaterialTheme.colorScheme.error)
                }

                else -> {}
            }
            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = onNavigateToRegister) {
                Text("Don't have an account? Register here")


            }

        }
    }
}