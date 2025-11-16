package com.example.churchmanagementsystem.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "groups")
data class Group (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String=""
)
