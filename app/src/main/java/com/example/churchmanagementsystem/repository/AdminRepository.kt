package com.example.churchmanagementsystem.repository

import com.example.churchmanagementsystem.api.ApiService
import com.example.churchmanagementsystem.models.MassSchedule
import com.example.churchmanagementsystem.models.User
import com.example.churchmanagementsystem.util.DataState


class AdminRepository (private val apiService: ApiService) {
    suspend fun getPendingMembers(): DataState<List<User>> {
        return try {
            val response = apiService.getPendingMembers()
            if (response.isSuccessful && response.body() != null) {
                DataState.Success(response.body()!!)
            } else {
                DataState.Error(response.message() ?: "Failed to fetch")
            }

        } catch (e: Exception) {
            DataState.Error(e.message ?: "Unknown error")
        }
    }

    suspend fun approveMember(userId: Int): DataState<Unit> {
        return try {
            val response = apiService.approveMember(userId)
            if (response.isSuccessful) {
                DataState.Success(Unit)
            } else {
                DataState.Error(response.message() ?: "Failed to approve member")
            }
        } catch (e: Exception) {
            DataState.Error(e.message ?: "Unknown error")
        }
    }

    suspend fun declineMember(userId: Int): DataState<Unit> {
        return try {
            val response = apiService.declineMember(userId)
            if (response.isSuccessful) {
                DataState.Success(Unit)
            } else {
                DataState.Error(response.message() ?: "Failed to decline member")
            }
        } catch (e: Exception) {
            DataState.Error(e.message ?: "Unknown error")
        }
    }

    suspend fun getMassSchedules(): DataState<List<MassSchedule>> {
        return try {
            val response = apiService.getMassSchedules()
            if (response.isSuccessful && response.body() != null) {
                DataState.Success(response.body()!!)
            } else {
                DataState.Error(response.message() ?: "Failed to fetch mass schedules")
            }
        } catch (e: Exception) {
            DataState.Error(e.message ?: "Unknown error")
        }


    }

    suspend fun deleteMassSchedule(scheduleId: Long): Result<Unit> {
        return try {
            apiService.deleteMassSchedule(scheduleId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }

    }

    suspend fun addMassSchedule(massSchedule: MassSchedule): Result<Unit> {
        return try {
            apiService.addMassSchedule(schedule)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }

    }
}




