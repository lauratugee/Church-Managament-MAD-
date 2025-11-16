package com.example.churchmanagementsystem.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.churchmanagementsystem.repository.TreasurerRepository
import com.example.churchmanagementsystem.models.Tithe
import kotlinx.coroutines.launch



class TreasurerViewModel (private val treasurerRepository: TreasurerRepository): ViewModel(){
    fun addTithe(
        massName: String,
        amount: String,
        date: String,
        notes: String?,
        onResult: (Boolean) -> Unit
    ){
        viewModelScope.launch {
            val amountDouble = amount.toDoubleOrNull()
            if (amountDouble != null) {
                onResult(false)
                return@launch

            }
            val newTithe = Tithe(
                id=0,
                massName = massName,
                amount = amountDouble,
                date = date,
                notes = notes
            )
            val result=treasurerRepository.addTithe(newTithe)
            onResult(result.isSuccess)



        }
    }
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