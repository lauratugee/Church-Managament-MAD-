package com.example.churchmanagementsystem.ui

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.churchmanagementsystem.viewmodel.AuthViewModel
import com.example.churchmanagementsystem.util.DataState


@Composable
fun LoginScreen(
    navController: NavController,
    onNavigateToRegister: () -> Unit,

) {

    val authViewModel: AuthViewModel = viewModel()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val authState by authViewModel.authState.collectAsState()
    val context= LocalContext.current

    LaunchedEffect(authState) {

        when (val state = authState) {
            is DataState.Success -> {
                if (state.data.email.isNotBlank()) {
                    Toast.makeText(context, "Login successful!", Toast.LENGTH_SHORT).show()
                    val userEmail = state.data.email
                    val destination = "home/${userEmail}"


                    navController.navigate(destination) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                    authViewModel.resetAuthState()
                }
            }

            is DataState.Error -> {
                Toast.makeText(context, "Login failed: ${state.message}", Toast.LENGTH_SHORT).show()
                authViewModel.resetAuthState()
            }

            else -> {}
        }
    }




    Box(modifier = Modifier.fillMaxSize()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text("Login", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(32.dp))

        val isUIEnabled = authState !is DataState.Loading

        //Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
            enabled= isUIEnabled
        )
        Spacer(modifier = Modifier.height(8.dp))

        //Password
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            enabled= isUIEnabled
        )
        Spacer(modifier = Modifier.height(32.dp))

        //Login
        Button(
            onClick = {
                authViewModel.login(email, password)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = isUIEnabled && email.isNotEmpty() && password.isNotEmpty()
        )
        {
            Text("Login")
        }
        Spacer(modifier = Modifier.height(16.dp))

        //Register
        TextButton(onClick = onNavigateToRegister, enabled = isUIEnabled) {
            Text("Don't have an account? Register here")
        }
        TextButton(
            onClick = {},
            enabled = isUIEnabled
        ) {
            Text("Forgot password?")
        }
    }
    if (authState is DataState.Loading)
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
}
    }
