package com.example.exada1.PRepository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.exada1.PApi.IApiService
import com.example.exada1.PDataPackage.PCompanyPackage.cmlCompanyData
import com.example.exada1.PDataPackage.PDistrictPackage.cmlDistrictData
import com.example.exada1.PDataPackage.PProvincePackage.cmlProvinceData
import com.example.exada1.PHelper.CSQLiteHelper
import retrofit2.Call
import retrofit2.Response


class CRepository(private val oAppInterface:IApiService,private val oDb:CSQLiteHelper) {

    private val oLiveDistrictData = MutableLiveData<cmlDistrictData>()
    val oDistrictData : LiveData<cmlDistrictData>
        get() = oLiveDistrictData

    fun C_APIxDistrict() {
        val oCall = oAppInterface.C_GEToDistrict()
        oCall.enqueue(object : retrofit2.Callback<cmlDistrictData>{
            override fun onResponse(call: Call<cmlDistrictData>, response: Response<cmlDistrictData>) {
                if (response.isSuccessful) {
                    val oResponse = response.body()
                    oLiveDistrictData.postValue(response.body())
                    Log.d("C_API", "onResponse: "+oResponse.toString())
                    if (oResponse != null) {
                        oDb?.C_SETxinsertDistrict(oResponse)
                    }
                }
            }
            override fun onFailure(call: Call<cmlDistrictData>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

     fun C_APIxProvince() {
        val oCall = oAppInterface.C_GEToProvince()
        oCall.enqueue(object : retrofit2.Callback<cmlProvinceData>{
            override fun onResponse(call: Call<cmlProvinceData>, response: Response<cmlProvinceData>) {
                if (response.isSuccessful) {
                    val oResponse = response.body()
                    Log.d("C_API", "onResponse: "+oResponse.toString())
                    if (oResponse != null) {
                        oDb?.C_SETxInsertProvince(oResponse)
                    }
                }
            }
            override fun onFailure(call: Call<cmlProvinceData>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

     fun C_APIxCompany() {
        val oCall = oAppInterface.C_GEToPdDate()
        oCall.enqueue(object : retrofit2.Callback<cmlCompanyData>{
            override fun onResponse(call: Call<cmlCompanyData>, response: Response<cmlCompanyData>) {
                if (response.isSuccessful) {
                    val oResponse = response.body()
                    Log.d("C_API", "onResponse: "+oResponse.toString())
                    if (oResponse != null) {
                        oDb?.C_SETxInsertDd(oResponse)
                    }
                }
            }
            override fun onFailure(call: Call<cmlCompanyData>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }


}