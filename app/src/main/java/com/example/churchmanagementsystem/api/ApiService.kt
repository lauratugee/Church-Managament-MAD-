package com.example.churchmanagementsystem.api

import com.example.churchmanagementsystem.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @POST("auth/login")
    suspend fun loginUser(@Body credentials: Map<String,String>): Response<User>
    @POST("auth/register")
    suspend fun registerUser(@Body user:User):Response<User>
    @GET("users")
    suspend fun getAllUsers():Response<List<User>>
    @GET("users/{id}")
    suspend fun getUsersById(@Path ("id") userId: Int):Response<User>

}
