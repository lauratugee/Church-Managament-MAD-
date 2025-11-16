package com.example.churchmanagementsystem

import android.app.Application
import com.example.churchmanagementsystem.data.GroupRepository
import com.example.churchmanagementsystem.data.local.AppDatabase


class ChurchManagementApplication: Application(){
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { GroupRepository(database.groupDao()) }
}