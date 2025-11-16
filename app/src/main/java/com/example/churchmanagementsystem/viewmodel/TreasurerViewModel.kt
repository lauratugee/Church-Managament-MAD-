package com.example.churchmanagementsystem.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.churchmanagementsystem.repository.TreasurerRepository

class TreasurerViewModel (private val treasurerRepository: TreasurerRepository): ViewModel(){
}

class TreasurerViewModelFactory(private val treasurerRepository: TreasurerRepository): ViewModelProvider.Factory{
    override fun<T: ViewModel> create(modelClass: Class<T>): T{
        if(modelClass.isAssignableFrom(TreasurerViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return TreasurerViewModel(treasurerRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}