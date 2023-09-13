package com.example.exada1.PApi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object OApiUtilities {
    private const val tC_BASE_URL = "https://dev.ada-soft.com:44340/AdaStandard/API2PSMaster/V5/"

    val oRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(tC_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}