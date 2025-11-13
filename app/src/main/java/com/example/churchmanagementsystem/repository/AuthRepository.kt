package com.example.churchmanagementsystem.repository

import com.example.churchmanagementsystem.api.RetrofitInstance
import com.example.churchmanagementsystem.models.User
import retrofit2.Response

class AuthRepository {
    suspend fun loginUser(credentials: Map<String, String>): Response<User> {
        return RetrofitInstance.apiService.loginUser(credentials)
    }

    suspend fun registerUser(user: User): Response<User> {
        return RetrofitInstance.apiService.registerUser(user)
    }

    suspend fun getUserById(userId: Int): Response<User> {
        return RetrofitInstance.apiService.getUsersById(userId)
    }


}