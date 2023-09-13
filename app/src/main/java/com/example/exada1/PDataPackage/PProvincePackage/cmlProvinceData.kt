package com.example.exada1.PDataPackage.PProvincePackage


import com.google.gson.annotations.SerializedName

data class cmlProvinceData(
    @SerializedName("roItem")
    val roItem: cmlRoItem,
    @SerializedName("rtCode")
    val rtCode: String,
    @SerializedName("rtDesc")
    val rtDesc: String
)