package com.example.exada1.PDataPackage.PDistrictPackage


import com.google.gson.annotations.SerializedName

data class RaDistrinctLng(
    @SerializedName("rnLngID")
    val rnLngID: Int,
    @SerializedName("rtDstCode")
    val rtDstCode: String,
    @SerializedName("rtDstName")
    val rtDstName: String
)