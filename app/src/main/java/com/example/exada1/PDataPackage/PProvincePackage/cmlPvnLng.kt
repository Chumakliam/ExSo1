package com.example.exada1.PDataPackage.PProvincePackage


import com.google.gson.annotations.SerializedName

data class cmlPvnLng(
    @SerializedName("rnLngID")
    val rnLngID: Int,
    @SerializedName("rtPvnCode")
    val rtPvnCode: String,
    @SerializedName("rtPvnName")
    val rtPvnName: String
)