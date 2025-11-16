package com.example.churchmanagementsystem.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fundraisers")

data class Fundraiser (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val amount: Double,
    val date: String,
    val notes: String?
)

