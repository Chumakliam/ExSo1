package com.example.exada1.PDataPackage.PCompanyPackage


import com.google.gson.annotations.SerializedName

data class cmlCompanyData(
    @SerializedName("roItem")
    val roItem: RoItem,
    @SerializedName("rtCode")
    val rtCode: String,
    @SerializedName("rtDesc")
    val rtDesc: String
)