package com.example.churchmanagementsystem.util

sealed class DataState {
    data class Success<T>(val data: T) : DataState()
    data class Error(val message: String) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
    object Idle : DataState<Nothing>()
}



