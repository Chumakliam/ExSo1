package com.example.exada1.PViewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exada1.PDataPackage.PDistrictPackage.CDistrictData

import com.example.exada1.PRepository.CRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class C01MainViewModel(private val oRepositoy: CRepository):ViewModel(){
    init {
        viewModelScope.launch (Dispatchers.IO){
            oRepositoy.C_APIxDistrict()
            oRepositoy.C_APIxProvince()
            oRepositoy.C_APIxCompany()
        }
    }
    val oData : LiveData<CDistrictData>
        get() = oRepositoy.oDistrictData
}