package com.example.churchmanagementsystem.api

import com.example.churchmanagementsystem.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("register")
    suspend fun registerUser(@Body body: Map<String, @JvmSuppressWildcards Any>): Response<User>

    @POST("login")
    suspend fun loginUser(@Body credentials: Map<String, String>): Response<User>

}
