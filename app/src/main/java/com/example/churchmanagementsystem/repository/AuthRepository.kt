package com.example.churchmanagementsystem.repository


import com.example.churchmanagementsystem.api.ApiService
import com.example.churchmanagementsystem.models.User
import com.example.churchmanagementsystem.util.DataState
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class AuthRepository(private val apiService: ApiService){


    suspend fun register(user: User): DataState<User> {
        return try {
            val response = apiService.registerUser(user)
            if (response.isSuccessful && response.body() != null) {
                DataState.Success(response.body()!!)
            } else {
                val errorMsg = response.errorBody()?.string() ?: "Unknown error"
                DataState.Error(errorMsg)
            }
        } catch (e: Exception) {
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
                val errorMsg = response.errorBody()?.string() ?: "Unknown error"
                DataState.Error("Error: ${response.code()}-${response.message()}")
            }
        } catch (e: Exception) {
            DataState.Error(e.message ?: "Unknown error")
            }

    }


}




