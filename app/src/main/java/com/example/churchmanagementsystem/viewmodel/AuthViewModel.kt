package com.example.churchmanagementsystem.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.churchmanagementsystem.api.RetrofitInstance
import com.example.churchmanagementsystem.models.User
import com.example.churchmanagementsystem.repository.AuthRepository
import com.example.churchmanagementsystem.util.DataState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel() {

    private val authRepository = AuthRepository(RetrofitInstance.api)


    private val _authState = MutableStateFlow<DataState<User>>(DataState.Idle)
    val authState = _authState.asStateFlow()


    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = DataState.Loading
            _authState.value = authRepository.login(email, password)

            }
        }


    fun register(
        firstName: String,
        lastName: String,
        email: String,
        dateOfBirth: String,
        phoneNumber: String,
        gender: String,
        maritalStatus: String
    ){
        viewModelScope.launch {
            _authState.value=DataState.Loading

            val result=authRepository.register(
                firstName=firstName,
                lastName=lastName,
                email=email,
                dateOfBirth=dateOfBirth,
                phoneNumber=phoneNumber,
                gender=gender,
                maritalStatus=maritalStatus
            )
            _authState.value=result


        }
    }


    fun resetAuthState() {
        _authState.value = DataState.Idle
    }


}


