package com.example.churchmanagementsystem.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tithes")

data class Tithe (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val massName: String,
    val amount: Double,
    val date: String,
    val notes: String
)



