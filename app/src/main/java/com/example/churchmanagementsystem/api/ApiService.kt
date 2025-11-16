package com.example.churchmanagementsystem.api

import com.example.churchmanagementsystem.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.DELETE
import retrofit2.http.Path
import com.example.churchmanagementsystem.models.MassSchedule


interface ApiService {
    @POST("register")
    suspend fun registerUser(@Body body: Map<String, @JvmSuppressWildcards Any>): Response<User>

    @POST("login")
    suspend fun loginUser(@Body credentials: Map<String, String>): Response<User>

    @GET("pending_members")
    suspend fun getPendingMembers(): Response<List<User>>

    @POST("approve_member/{userId}")
    suspend fun approveMember(@Path("userId") userId: Int): Response<Unit>

    @POST("decline_member/{userId}")
    suspend fun declineMember(@Path("userId") userId: Int): Response<Unit>

    @GET("api/schedules")
    suspend fun getMassSchedules(): Response<List<MassSchedule>>

    @DELETE("mass-schedules/{scheduleId}")
    suspend fun deleteMassSchedule(@Path("scheduleId") scheduleId: Long): Response<Unit>

    @POST("mass-schedules")
    suspend fun addMassSchedule(@Body schedule: MassSchedule): Response<Unit>


}
