package com.example.exada1.PDataPackage.PCompanyPackage


import com.google.gson.annotations.SerializedName

data class CCompanyData(
    @SerializedName("roItem")
    val roItem: RoItem,
    @SerializedName("rtCode")
    val rtCode: String,
    @SerializedName("rtDesc")
    val rtDesc: String
)