package com.example.churchmanagementsystem.models

data class Fundraiser (
    val id: Int,
    val name: String,
    val amount: Double,
    val date: String,
    val notes: String?
)

