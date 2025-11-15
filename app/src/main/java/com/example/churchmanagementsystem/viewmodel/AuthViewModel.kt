package com.example.churchmanagementsystem.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.churchmanagementsystem.api.RetrofitInstance
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

    private val authRepository = AuthRepository(RetrofitInstance.api)

    private val _loginState = MutableStateFlow<DataState<User>>(AuthViewModel.DataState.Idle)
    val loginState= _loginState.asStateFlow()


    private val _registrationState = MutableStateFlow<DataState<User>>(AuthViewModel.DataState.Idle)

    val registrationState = _registrationState.asStateFlow()




    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = AuthViewModel.DataState.Loading

            val result = authRepository.login(email, password)
            _loginState.value = result

            }
        }


    fun register(
        firstName: String,
        lastName: String,
        email: String,
        dateOfBirth: String,
        phoneNumber: String,
        dateJoined: String,
        gender: String,
        maritalStatus: String
    ){
        viewModelScope.launch {
            _registrationState.value = AuthViewModel.DataState.Loading

            val result=authRepository.register(
                firstName=firstName,
                lastName=lastName,
                email=email,
                dateOfBirth=dateOfBirth,
                phoneNumber=phoneNumber,
                dateJoined=dateJoined,
                gender=gender,
                maritalStatus=maritalStatus
            )
            _registrationState.value=result


        }
    }

    fun logout() {
        _loginState.value = AuthViewModel.DataState.Idle
        _registrationState.value = AuthViewModel.DataState.Idle
    }


}


