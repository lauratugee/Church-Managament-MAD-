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

    sealed class DataState<T> {
        class Success<T>(val data: T) : DataState<T>()
        class Error<T>(val message: String) : DataState<T>()
        object Loading : DataState<Nothing>()
        object Idle : DataState<Nothing>()
    }
    private val _loginState = MutableStateFlow<DataState<User>>(DataState.Idle)
    val loginState: StateFlow<DataState<User>> = _loginState


    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = DataState.Loading
            try {
                val credentials = mapOf("email" to email, "password" to password)
                val response = authRepository.loginUser(credentials)
                handleResponse(response)
            } catch (e: Exception) {
                _loginState.value = DataState.Error("Network error: ${e.message}")

            }
        }
    }

    private fun handleResponse(response: Response<User>) {
        if (response.isSuccessful && response.body() != null) {
            _loginState.value = DataState.Success(response.body()!!)
        } else {
            val errorMessage = "Login failed: ${response.message()} (Code: ${response.code()})"
            _loginState.value = DataState.Error(errorMessage)
        }


    }
}
