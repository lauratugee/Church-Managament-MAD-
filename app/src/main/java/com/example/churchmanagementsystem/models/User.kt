package com.example.churchmanagementsystem.models


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User (
    val id: Int = 0,
    val first_name: String = "",
    val last_name: String = "",
    val email: String = "",
    val password: String = "",
    val date_of_birth: String = "",
    val phone_number: String = "",
    val date_joined: String = "",
    val gender: String = "",
    val marital_status: String = "",
    val role: String = "member",
    val isApproved: Boolean = false,
    val createdAt: String? = null
) :Parcelable

fun User.getFullName():String{
    return "$first_name $last_name"

}