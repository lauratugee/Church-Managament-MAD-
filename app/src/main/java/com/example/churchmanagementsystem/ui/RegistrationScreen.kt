package com.example.churchmanagementsystem.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import  androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.churchmanagementsystem.models.User
import com.example.churchmanagementsystem.viewmodel.AuthViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(onNavigateToLogin: () -> Unit) {
    val authViewModel: AuthViewModel=viewModel()

    var firstName by remember{mutableStateOf("")}
    var lastName by remember{mutableStateOf("")}
    var email by remember{mutableStateOf("")}
    var dateOfBirth by remember{mutableStateOf("")}
    var phoneNumber by remember{mutableStateOf("")}
    var dateJoined by remember{mutableStateOf("")}
    var gender by remember{mutableStateOf("")}
    var maritalStatus by remember{mutableStateOf("")}
    var password by remember{mutableStateOf("")}
    var confirmPassword by remember{mutableStateOf("")}

    val genderOptions=listOf("Male", "Female", "Other")
    val maritalStatusOptions=listOf("Single", "Married", "Prefer not to say")
    var isGenderMenuExpanded by remember{mutableStateOf(false)}
    var isMaritalStatusMenuExpanded by remember{mutableStateOf(false)}

    val registrationState by authViewModel.registrationState.collectAsState()
    val scrollState = rememberScrollState()


    Surface(modifier=Modifier.fillMaxSize()){
        Column(
            modifier=Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text("Membership Registration Form", style=MaterialTheme.typography.headlineMedium)
            Text("Fill in all the fields")
            Spacer(modifier=Modifier.height(24.dp))

            //Email
            OutlinedTextField(value=firstName, onValueChange = {firstName=it}, label= {Text("First Name")}, modifier=Modifier.fillMaxWidth())
            Spacer(modifier=Modifier.height(8.dp))
            OutlinedTextField(value=lastName, onValueChange = {lastName=it}, label= {Text("Last Name")}, modifier=Modifier.fillMaxWidth())
            Spacer(modifier=Modifier.height(8.dp))
            OutlinedTextField(value=email, onValueChange = {email=it}, label= {Text("Email")}, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email), modifier=Modifier.fillMaxWidth())
            Spacer(modifier=Modifier.height(8.dp))
            OutlinedTextField(value=dateOfBirth, onValueChange = {dateOfBirth=it}, label= {Text("Date of Birth(DD-MM-YYYY)")}, modifier=Modifier.fillMaxWidth())
            Spacer(modifier=Modifier.height(8.dp))
            OutlinedTextField(value=phoneNumber, onValueChange = {phoneNumber=it}, label= {Text("Phone Number")}, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone), modifier=Modifier.fillMaxWidth())
            Spacer(modifier=Modifier.height(8.dp))
            OutlinedTextField(value=dateJoined, onValueChange = {dateJoined=it}, label= {Text ("Date Joined(DD-MM-YYYY)")}, modifier=Modifier.fillMaxWidth())
            Spacer(modifier=Modifier.height(8.dp))

            ExposedDropdownMenuBox(expanded = isGenderMenuExpanded, onExpandedChange = { isGenderMenuExpanded= !isGenderMenuExpanded}){
                OutlinedTextField(
                    value=gender,
                    onValueChange = {}, readOnly = true,
                    label= {Text("Gender")},
                    trailingIcon={ExposedDropdownMenuDefaults.TrailingIcon(expanded = isGenderMenuExpanded)},
                    modifier=Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = isGenderMenuExpanded,
                    onDismissRequest = {isGenderMenuExpanded=false}) {
                    genderOptions.forEach { option ->
                        DropdownMenuItem(text = { Text(option) }, onClick = {
                            gender = option
                            isGenderMenuExpanded = false
                        })
                    }
                }
            }
            Spacer(modifier=Modifier.height(8.dp))

            ExposedDropdownMenuBox(expanded = isMaritalStatusMenuExpanded, onExpandedChange = { isMaritalStatusMenuExpanded= !isMaritalStatusMenuExpanded}){
                OutlinedTextField(
                    value=maritalStatus,
                    onValueChange = {},
                    readOnly = true,
                    label= { Text("Marital Status")},
                    trailingIcon={ExposedDropdownMenuDefaults.TrailingIcon(expanded = isMaritalStatusMenuExpanded)},
                    modifier=Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = isMaritalStatusMenuExpanded,
                    onDismissRequest = {isMaritalStatusMenuExpanded=false}
                ){
                    maritalStatusOptions.forEach { option ->
                        DropdownMenuItem(text = { Text(option) }, onClick = {
                            maritalStatus = option
                            isMaritalStatusMenuExpanded = false
                        })
                    }
                }
            }
            Spacer(modifier=Modifier.height(8.dp))

            OutlinedTextField(value=password, onValueChange = {password=it}, label= {Text("Password")}, visualTransformation = PasswordVisualTransformation(), modifier=Modifier.fillMaxWidth())
            Spacer(modifier=Modifier.height(8.dp))
            OutlinedTextField(value=confirmPassword, onValueChange = {confirmPassword=it}, label= {Text("Confirm Password")}, visualTransformation = PasswordVisualTransformation(), modifier=Modifier.fillMaxWidth())
            Spacer(modifier=Modifier.height(24.dp))



            //Register button
            Button(
                onClick={
                    val userToRegister=User(
                        firstName=firstName,
                        lastName=lastName,
                        email=email,
                        dateOfBirth=dateOfBirth,
                        phoneNumber=phoneNumber,
                        dateJoined=dateJoined,
                        gender=gender,
                        maritalStatus=maritalStatus
                    )
                    authViewModel.register(userToRegister, password)
                },
                modifier=Modifier.fillMaxWidth(),
                enabled=registrationState !is AuthViewModel.DataState.Loading &&
                        firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty()
                        && phoneNumber.isNotEmpty() && gender.isNotEmpty() && maritalStatus.isNotEmpty() && password.isNotEmpty() && password== confirmPassword
            ){
                Text("Register")
            }
            Spacer(modifier=Modifier.height(16.dp))

            when(val state=registrationState) {
                is AuthViewModel.DataState.Loading -> {
                    CircularProgressIndicator()
                }

                is AuthViewModel.DataState.Success -> {
                    Text(
                        "Registration successful: ${state.data.email}",
                        color = MaterialTheme.colorScheme.primary
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
            Spacer(modifier=Modifier.height(16.dp))

            TextButton(onClick = onNavigateToLogin) {
                Text("Already have an account? Login")
            }




        }

        }
}