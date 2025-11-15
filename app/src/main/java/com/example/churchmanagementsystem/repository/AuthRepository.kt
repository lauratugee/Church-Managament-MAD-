package com.example.churchmanagementsystem.repository


import com.example.churchmanagementsystem.api.ApiService
import com.example.churchmanagementsystem.models.User
import com.example.churchmanagementsystem.viewModels.AuthViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class AuthRepository(private val apiService: ApiService){

    private typealias DataState<T> = AuthViewModel.DataState<T>


    suspend fun register(
        firstName: String,
        lastName: String,
        email: String,
        dateOfBirth: String,
        phoneNumber: String,
        dateJoined: String,
        gender: String,
        maritalStatus: String
    ): DataState<User> {
        return try{
            val dateJoined=SimpleDateFormat("dd-MM-yyyy",Locale.getDefault()).format(Date())

            val userDetails=mapOf(
                "firstName" to firstName,
                "lastName" to lastName,
                "email" to email,
                "dateOfBirth" to dateOfBirth,
                "phoneNumber" to phoneNumber,
                "dateJoined" to dateJoined,
                "gender" to gender,
                "maritalStatus" to maritalStatus
            )
            val response=apiService.registerUser(userDetails)
            if (response.isSuccessful && response.body()!=null){
                DataState.Success(response.body()!!)
            } else{
                DataState.Error(response.message())

            }
        } catch (e: Exception){
            DataState.Error(e.message ?: "Unknown error")
        }
    }

    suspend fun login(email: String, password: String): DataState<User> {
        return try {
            val credentials = mapOf("email" to email, "password" to password)
            val response = apiService.loginUser(credentials)

            if (response.isSuccessful && response.body() != null) {
                DataState.Success(response.body()!!)
            } else{
                DataState.Error("Error: ${response.code()}-${response.message()}")
            }
        } catch (e: Exception) {
            DataState.Error("Error: ${e.message}")
            }

    }


}




