package com.example.exada1.PApi

import com.example.exada1.PDataPackage.PCompanyPackage.CCompanyData
import com.example.exada1.PDataPackage.PDistrictPackage.CDistrictData
import com.example.exada1.PDataPackage.PProvincePackage.CProvinceData
import retrofit2.Call

import retrofit2.http.GET

interface IApiService  {
    @GET("Company/Download?pdDate=2022-03-02")
     fun C_GEToPdDate() : Call<CCompanyData>

    @GET("Province/Download?pdDate=2000-08-07")
    fun C_GEToProvince() : Call<CProvinceData>

    @GET("District/Download?pdDate=2000-08-08")
    fun C_GEToDistrict() : Call<CDistrictData>
}