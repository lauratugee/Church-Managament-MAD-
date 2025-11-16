package com.example.churchmanagementsystem.ui.group

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.churchmanagementsystem.data.GroupRepository
import com.example.churchmanagementsystem.models.Group
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class GroupViewModel ( private val groupRepository: GroupRepository) : ViewModel() {
    val groups: StateFlow<List<Group>> = groupRepository.allGroups
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addGroup(groupName: String){
        if(groupName.isBlank())return

        viewModelScope.launch {
            groupRepository.insert(Group(name = groupName))
        }
    }


}