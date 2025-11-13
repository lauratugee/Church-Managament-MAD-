package com.example.churchmanagementsystem.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.churchmanagementsystem.models.User
import com.example.churchmanagementsystem.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel() {
    sealed class DataState<out T> {
        data class Success<T>(val data: T) : DataState<T>()
        data class Error(val message: String) : DataState<Nothing>()
        object Loading : DataState<Nothing>()
        object Idle : DataState<Nothing>()
    }

    private val authRepository = AuthRepository()

    private val _loginState = MutableStateFlow<DataState<User>>(AuthViewModel.DataState.Idle)
    val loginState= _loginState.asStateFlow()


    private val _registrationState = MutableStateFlow<DataState<User>>(AuthViewModel.DataState.Idle)

    val registrationState = _registrationState.asStateFlow()


    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = AuthViewModel.DataState.Loading
            try {
                val userCredentials = mapOf("email" to email, "password" to password)
                val response = authRepository.login (userCredentials )

                if (response.isSuccessful && response.body() != null) {
                    _loginState.value = AuthViewModel.DataState.Success(response.body()!!)
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "Login failed:"
                    _loginState.value =AuthViewModel.DataState.Error(errorMessage)
                }
            } catch (e: Exception) {
                _loginState.value = AuthViewModel.DataState.Error("Login failed: ${e.message}")


            }
        }
    }

    fun register(user: User, password: String) {
        viewModelScope.launch {
            _registrationState.value = AuthViewModel.DataState.Loading

            try {

                val response = authRepository.register(user, password)

                if (response.isSuccessful && response.body() != null) {
                    _registrationState.value = AuthViewModel.DataState.Success(response.body()!!)

                } else {
                    val errorMessage = response.errorBody()?.string() ?: "Registration failed:"
                    _registrationState.value = AuthViewModel.DataState.Error(errorMessage)

                }


            } catch (e: Exception) {
                _registrationState.value = AuthViewModel.DataState.Error("Registration failed: ${e.message}")
            }
        }
    }

    fun logout() {
        _loginState.value = AuthViewModel.DataState.Idle
        _registrationState.value = AuthViewModel.DataState.Idle
    }


}


