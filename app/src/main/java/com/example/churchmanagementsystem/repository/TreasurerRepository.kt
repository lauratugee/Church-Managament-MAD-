package com.example.churchmanagementsystem.repository

import com.example.churchmanagementsystem.api.ApiService
import com.example.churchmanagementsystem.models.Tithe
import java.lang.Exception

class TreasurerRepository (private val apiService: ApiService){
    suspend fun addTithe(tithe: Tithe): Result<Unit> {
        return try {
            val response=apiService.addTithe(tithe)
            if(response.isSuccessful){
                Result.success(Unit)
            }else{
                Result.failure(Exception("Failed to add tithe record"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    }
