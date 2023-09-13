package com.example.exada1.PDataPackage.PProvincePackage


import com.google.gson.annotations.SerializedName

data class CProvinceData(
    @SerializedName("roItem")
    val roItem: RoItem,
    @SerializedName("rtCode")
    val rtCode: String,
    @SerializedName("rtDesc")
    val rtDesc: String
)