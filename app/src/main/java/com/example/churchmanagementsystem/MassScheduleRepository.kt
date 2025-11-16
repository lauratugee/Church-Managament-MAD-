package com.example.churchmanagementsystem.data

import com.example.churchmanagementsystem.data.local.MassScheduleDao
import com.example.churchmanagementsystem.models.MassSchedule
import kotlinx.coroutines.flow.Flow


class MassScheduleRepository( private val massScheduleDao: MassScheduleDao) {
    val allMassSchedules: Flow<List<MassSchedule>> = massScheduleDao.getAllMassSchedules()

    suspend fun insert(massSchedule: MassSchedule) {
        massScheduleDao.insertMassSchedule(massSchedule)
    }
    suspend fun update(massSchedule: MassSchedule) {
        massScheduleDao.updateMassSchedule(massSchedule)

    }

}