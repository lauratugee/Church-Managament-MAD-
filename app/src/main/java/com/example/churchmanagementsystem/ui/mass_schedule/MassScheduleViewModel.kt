package com.example.churchmanagementsystem.ui.mass_schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.churchmanagementsystem.data.MassScheduleRepository
import com.example.churchmanagementsystem.models.MassSchedule
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class MassScheduleViewModel ( private val repository: MassScheduleRepository) : ViewModel() {
    val allMassSchedules: StateFlow<List<MassSchedule>> = repository.allMassSchedules.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun addMassSchedule(dayOfWeek: String, startTime: String, endTime: String, language: String) {
        viewModelScope.launch {
            val massSchedule = MassSchedule(
                dayOfWeek = dayOfWeek,
                startTime = startTime,
                endTime = endTime,
                language = language
            )
            repository.insert(massSchedule)
        }
    }
    fun updateMassSchedule(massSchedule: MassSchedule){
        viewModelScope.launch {
            repository.update(massSchedule)
        }
    }

}

class MassScheduleViewModelFactory(private val repository: MassScheduleRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MassScheduleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MassScheduleViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }



}
