package com.example.exada1.PViewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CAppViewModelFactory(): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        if (modelClass.isAssignableFrom(C01MainViewModel::class.java)){
            return C01MainViewModel() as T
        }
        throw IllegalArgumentException("UnknownModel")
    }
}