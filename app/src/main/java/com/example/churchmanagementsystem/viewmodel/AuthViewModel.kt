package com.example.churchmanagementsystem.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.churchmanagementsystem.models.User
import com.example.churchmanagementsystem.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class AuthViewModel: ViewModel() {
    private val authRepository = AuthRepository()

    sealed interface DataState<out T> {
         data class Success<T>(val data: T) : DataState<T>
        data class Error(val message: String) : DataState<Nothing>
        object Loading : DataState<Nothing>
        object Idle : DataState<Nothing>
    }

    private val _loginState = MutableStateFlow<DataState<User>>(DataState.Idle)
    val loginState: StateFlow<DataState<User>> = _loginState

    private val _registrationState = MutableStateFlow<DataState<User>>(DataState.Idle)

    val registrationState: StateFlow<DataState<User>> = _registrationState


    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = DataState.Loading
            try {
                val credentials = mapOf("email" to email, "password" to password)
                val response = authRepository.loginUser(credentials)
                handleLoginResponse(response)
            } catch (e: Exception) {
                _loginState.value = DataState.Error("Network error: ${e.message}")

            }
        }
    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            _registrationState.value = DataState.Loading

            try {
                val credentials = mapOf("email" to email, "password" to password)
                val response=authRepository.registerUser(credentials)
                handleRegistrationResponse(response)


            } catch (e: Exception) {
                _registrationState.value = DataState.Error("Registration failed: ${e.message}")
            }
        }
    }


    private fun handleLoginResponse(response: Response<User>) {
        if (response.isSuccessful && response.body() != null) {
            _loginState.value = DataState.Success(response.body()!!)
        } else {
            val errorMessage = "Login failed: ${response.message()} (Code: ${response.code()})"
            _loginState.value = DataState.Error(errorMessage)
        }
    }
    private fun handleRegistrationResponse(response: Response<User>){
        if (response.isSuccessful && response.body() != null) {
            _registrationState.value = DataState.Success(response.body()!!)
        } else {
            val errorMessage = "Registration failed: ${response.message()} (Code: ${response.code()})"
            _registrationState.value = DataState.Error(errorMessage)
        }
    }
}


