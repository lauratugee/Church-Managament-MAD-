package com.example.churchmanagementsystem.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mass_schedules")
data class MassSchedule(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val dayOfWeek: String,
    val startTime: String,
    val endTime: String,
    val language: String
)
