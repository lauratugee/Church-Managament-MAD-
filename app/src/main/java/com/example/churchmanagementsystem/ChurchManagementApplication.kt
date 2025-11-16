package com.example.churchmanagementsystem

import android.app.Application
import com.example.churchmanagementsystem.api.RetrofitInstance
import com.example.churchmanagementsystem.data.GroupRepository
import com.example.churchmanagementsystem.data.local.AppDatabase
import com.example.churchmanagementsystem.repository.AdminRepository



class ChurchManagementApplication: Application(){
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { GroupRepository(database.groupDao()) }
    val adminRepository by lazy { AdminRepository(RetrofitInstance.api) }
}