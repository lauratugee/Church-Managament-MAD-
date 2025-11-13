package com.example.churchmanagementsystem.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import  androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.churchmanagementsystem.viewmodel.AuthViewModel

@Composable
fun RegistrationScreen() {
    val authViewModel: AuthViewModel=viewModel()

    var email by remember{mutableStateOf("")}
    var password by remember{mutableStateOf("")}
    var confirmPassword by remember{mutableStateOf("")}

    val registrationState by authViewModel.registrationState.collectAsState()

    Surface(modifier=Modifier.fillMaxSize()){
        Column(
            modifier=Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text("Create account", style=MaterialTheme.typography.headlineMedium)
            Spacer(modifier=Modifier.height(32.dp))

            //Email
            OutlinedTextField(
                value=email,
                onValueChange = {email=it},
                label= {Text("Email")},
                modifier=Modifier.fillMaxWidth()
            )
            Spacer(modifier=Modifier.height(8.dp))

            //Password
            OutlinedTextField(
                value=password,
                onValueChange = {password=it},
                label= {Text("Password")},
                visualTransformation = PasswordVisualTransformation(),
                modifier=Modifier.fillMaxWidth()
            )
            Spacer(modifier=Modifier.height(8.dp))

            //Confirm Password
            OutlinedTextField(
                value=confirmPassword,
                onValueChange = {confirmPassword=it},
                label= {Text("Confirm Password")},
                visualTransformation = PasswordVisualTransformation(),
                modifier=Modifier.fillMaxWidth(),

                isError =password.isNotEmpty() && confirmPassword.isNotEmpty() && password != confirmPassword
            )
            Spacer(modifier=Modifier.height(32.dp))

            //Register button
            Button(
                onClick={
                    authViewModel.register(email, password)
                },
                modifier=Modifier.fillMaxWidth(),
                enabled=registrationState !is AuthViewModel.DataState.Loading && email.isNotEmpty() && password.isNotEmpty() && password== confirmPassword
            ){
                Text("Register")
            }
            Spacer(modifier=Modifier.height(16.dp))

            when(val state=registrationState){
                is AuthViewModel.DataState.Loading->{
                    CircularProgressIndicator()
                }
                is AuthViewModel.DataState.Success->{
                    Text(
                        "Registration successful: ${state.data.email}",
                        color=MaterialTheme.colorScheme.primary
                    )
                }
                is AuthViewModel.DataState.Error -> {
                    Text(
                        "Registration failed: ${state.message}",
                        color = MaterialTheme.colorScheme.error
                    )
                }
                is AuthViewModel.DataState.Idle -> {

                }
            }




        }


}
}