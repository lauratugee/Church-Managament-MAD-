package com.example.churchmanagementsystem.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.churchmanagementsystem.models.MassSchedule
import com.example.churchmanagementsystem.models.User
import com.example.churchmanagementsystem.repository.AdminRepository
import com.example.churchmanagementsystem.util.DataState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AdminViewModel (private val adminRepository: AdminRepository): ViewModel() {
    private val _pendingMembers = MutableStateFlow<DataState<List<User>>>(DataState.Idle)
    val pendingMembers = _pendingMembers.asStateFlow()

    private val _approvalState = MutableStateFlow<DataState<Unit>>(DataState.Idle)
    val approvalState = _approvalState.asStateFlow()

    private val _massSchedules = MutableStateFlow<DataState<List<MassSchedule>>>(DataState.Idle)
    val massSchedules = _massSchedules.asStateFlow()



    init {
        fetchPendingMembers()
        getMassSchedules()
    }

    fun fetchPendingMembers() {
        viewModelScope.launch {
            _pendingMembers.value = DataState.Loading
            _pendingMembers.value = adminRepository.getPendingMembers()
        }
    }

    fun approveMember(userId: Int) {
        viewModelScope.launch {
            _approvalState.value = DataState.Loading
            val result = adminRepository.approveMember(userId)

            _approvalState.value = result

            if (_approvalState.value is DataState.Success) {
                fetchPendingMembers()
            }

        }
    }

    fun declineMember(userId: Int) {
        viewModelScope.launch {
            _approvalState.value = DataState.Loading
            val result = adminRepository.declineMember(userId)
            _approvalState.value = result

            if (_approvalState.value is DataState.Success) {
                fetchPendingMembers()
            }
        }
    }

    fun resetApprovalState() {
        _approvalState.value = DataState.Idle
    }

    fun getMassSchedules() {
        viewModelScope.launch {
            _massSchedules.value = DataState.Loading
            _massSchedules.value = adminRepository.getMassSchedules()
        }
    }

    fun deleteMassSchedule(scheduleId: Long) {
        viewModelScope.launch {
            val result = adminRepository.deleteMassSchedule(scheduleId)
            if (result.isSuccess) {
                getMassSchedules()
            } else {
                println("Error deleting mass schedule: ${result.exceptionOrNull()?.message}")
            }
        }

    }

    fun addMassSchedule(massSchedule: MassSchedule) {
        viewModelScope.launch {
            val result = adminRepository.addMassSchedule(massSchedule)
            if (result.isSuccess) {
                getMassSchedules()
            } else {
                println("Error adding mass schedule: ${result.exceptionOrNull()?.message}")

            }

        }
    }
}














