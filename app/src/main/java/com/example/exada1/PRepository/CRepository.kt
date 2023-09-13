package com.example.exada1.PRepository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.exada1.PApi.IApiService
import com.example.exada1.PApi.OApiUtilities
import com.example.exada1.PDataPackage.PCompanyPackage.CCompanyData
import com.example.exada1.PDataPackage.PDistrictPackage.CDistrictData
import com.example.exada1.PDataPackage.PProvincePackage.CProvinceData
import com.example.exada1.PHelper.CSQLiteHelper
import retrofit2.Call
import retrofit2.Response


class CRepository(private val oAppInterface:IApiService,private val oDb:CSQLiteHelper) {

    private val oLiveDistrictData = MutableLiveData<CDistrictData>()
    val oDistrictData : LiveData<CDistrictData>
        get() = oLiveDistrictData

    fun C_APIxDistrict() {
        val oCall = oAppInterface.C_GEToDistrict()
        oCall.enqueue(object : retrofit2.Callback<CDistrictData>{
            override fun onResponse(call: Call<CDistrictData>, response: Response<CDistrictData>) {
                if (response.isSuccessful) {
                    val oResponse = response.body()
                    oLiveDistrictData.postValue(response.body())
                    Log.d("C_API", "onResponse: "+oResponse.toString())
                    if (oResponse != null) {
                        oDb?.C_SETxinsertDistrict(oResponse)
                    }
                }
            }
            override fun onFailure(call: Call<CDistrictData>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

     fun C_APIxProvince() {
        val oCall = oAppInterface.C_GEToProvince()
        oCall.enqueue(object : retrofit2.Callback<CProvinceData>{
            override fun onResponse(call: Call<CProvinceData>, response: Response<CProvinceData>) {
                if (response.isSuccessful) {
                    val oResponse = response.body()
                    Log.d("C_API", "onResponse: "+oResponse.toString())
                    if (oResponse != null) {
                        oDb?.C_SETxInsertProvince(oResponse)
                    }
                }
            }
            override fun onFailure(call: Call<CProvinceData>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

     fun C_APIxCompany() {
        val oCall = oAppInterface.C_GEToPdDate()
        oCall.enqueue(object : retrofit2.Callback<CCompanyData>{
            override fun onResponse(call: Call<CCompanyData>, response: Response<CCompanyData>) {
                if (response.isSuccessful) {
                    val oResponse = response.body()
                    Log.d("C_API", "onResponse: "+oResponse.toString())
                    if (oResponse != null) {
                        oDb?.C_SETxInsertDd(oResponse)
                    }
                }
            }
            override fun onFailure(call: Call<CCompanyData>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }


}