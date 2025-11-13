package com.example.churchmanagementsystem.models


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User (
    val id: Int? = null,
    val firstName: String,
    val lastName: String,
    val email: String,
    val dateOfBirth: String,
    val phoneNumber: String,
    val dateJoined: String,
    val gender: String = "",
    val maritalStatus: String = "",
    val role: String = "member",
    val isApproved: Boolean = false,
    val createdAt: String? = null
) :Parcelable

       fun User.getFullName():String{
           return "$firstName $lastName"
       }



