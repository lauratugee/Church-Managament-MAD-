package com.example.churchmanagementsystem.repository

import com.example.churchmanagementsystem.api.RetrofitInstance
import com.example.churchmanagementsystem.models.User
import retrofit2.Response

class AuthRepository {
    suspend fun loginUser(credentials: Map<String, String>): Response<User> {
        return RetrofitInstance.api.loginUser(credentials)
    }

    suspend fun registerUser(user:User, password: String): Response<User> {
        val request= RegistrationRequest(user, password)
        return RetrofitInstance.api.registerUser(request)
    }

    suspend fun getUserById(userId: Int): Response<User> {
        return RetrofitInstance.api.getUserById(userId)
    }


}