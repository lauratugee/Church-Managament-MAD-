package com.example.churchmanagementsystem.repository


import com.example.churchmanagementsystem.api.RetrofitInstance
import com.example.churchmanagementsystem.models.User

class AuthRepository{
    private val apiService = RetrofitInstance.api



    suspend fun register(user: User, password: String) = apiService.registerUser(
    mapOf(
        "user" to user,
        "password" to password
       )
    )
    suspend fun login(credentials: Map<String, String>) = apiService.loginUser(
    mapOf(
        "email" to credentials["email"]!!,
        "password" to credentials["password"]!!
      )
    )

}




