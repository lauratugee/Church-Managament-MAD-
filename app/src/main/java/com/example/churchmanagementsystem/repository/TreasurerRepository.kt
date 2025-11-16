package com.example.churchmanagementsystem.repository

import com.example.churchmanagementsystem.api.ApiService
import com.example.churchmanagementsystem.models.Tithe
import com.example.churchmanagementsystem.models.Fundraiser
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
    suspend fun addFundraiser(fundraiser: Fundraiser): Result<Unit> {
        return try {
            val response=apiService.addFundraiser(fundraiser)
            if(response.isSuccessful){
                Result.success(Unit)
            }else{
                Result.failure(Exception("Failed to add fundraiser record"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    }
