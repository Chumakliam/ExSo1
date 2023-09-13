package com.example.exada1.PViewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.exada1.PRepository.CRepository

class CAppViewModelFactory(private val oReposotory:CRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        if (modelClass.isAssignableFrom(C01MainViewModel::class.java)){
            return C01MainViewModel(oReposotory) as T
        }
        throw IllegalArgumentException("UnknownModel")
    }
}