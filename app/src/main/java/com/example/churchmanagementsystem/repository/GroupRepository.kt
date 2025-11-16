package com.example.churchmanagementsystem.repository

import com.example.churchmanagementsystem.models.Group
import com.example.churchmanagementsystem.db.GroupDao
import kotlinx.coroutines.flow.Flow



class GroupRepository (private val groupDao: GroupDao){
    val allGroups: Flow<List<Group>> = groupDao.getAllGroups()
    suspend fun insertGroup(group: Group){
        groupDao.insertGroup(group)
    }
    suspend fun refreshGroups(){


    }




}

