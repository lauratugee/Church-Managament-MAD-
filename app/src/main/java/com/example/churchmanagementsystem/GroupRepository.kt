package com.example.churchmanagementsystem.data

import com.example.churchmanagementsystem.data.local.GroupDao
import com.example.churchmanagementsystem.models.Group
import kotlinx.coroutines.flow.Flow


class GroupRepository (private val groupDao: GroupDao){
    val allGroups: Flow<List<Group>> = groupDao.getAllGroups()

    suspend fun insertGroup(group: Group) {
        groupDao.insertGroup(group)
    }
    suspend fun refreshGroups(){

    }

}