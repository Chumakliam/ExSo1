package com.example.exada1.PDataPackage.PProvincePackage


import com.google.gson.annotations.SerializedName

data class RaPvnLng(
    @SerializedName("rnLngID")
    val rnLngID: Int,
    @SerializedName("rtPvnCode")
    val rtPvnCode: String,
    @SerializedName("rtPvnName")
    val rtPvnName: String
)