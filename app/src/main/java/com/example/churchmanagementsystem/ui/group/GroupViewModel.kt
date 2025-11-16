package com.example.churchmanagementsystem.ui.group

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.churchmanagementsystem.repository.GroupRepository
import com.example.churchmanagementsystem.models.Group
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch



class GroupViewModel(private val groupRepository: GroupRepository) : ViewModel() {
    val groups: StateFlow<List<Group>> = groupRepository.allGroups
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addGroup(groupName: String,groupDescription: String) {
        if (groupName.isBlank()) return

        viewModelScope.launch {
            val newGroup = Group(name = groupName)
            groupRepository.insert(newGroup)
        }

    }
    }















