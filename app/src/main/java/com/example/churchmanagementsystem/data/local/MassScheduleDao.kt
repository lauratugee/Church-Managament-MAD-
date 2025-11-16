package com.example.churchmanagementsystem.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.churchmanagementsystem.models.MassSchedule
import kotlinx.coroutines.flow.Flow



@Dao
interface MassScheduleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMassSchedule(massSchedule: MassSchedule)

    @Update
    suspend fun updateMassSchedule(massSchedule: MassSchedule)

    @Query("SELECT * FROM mass_schedules")
    fun getAllMassSchedules(): Flow<List<MassSchedule>>


}