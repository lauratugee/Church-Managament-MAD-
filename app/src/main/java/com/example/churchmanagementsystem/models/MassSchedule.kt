package com.example.churchmanagementsystem.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "mass_schedules")
data class MassSchedule(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Long = 0,
    @SerializedName("day")
    val dayOfWeek: String,
    @SerializedName("start_time")
    val startTime: String,
    @SerializedName("end_time")
    val endTime: String,
    @SerializedName("description")
    val language: String
)
