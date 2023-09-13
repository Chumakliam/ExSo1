package com.example.exada1.PDataPackage.PDistrictPackage


import com.google.gson.annotations.SerializedName

data class cmlDistrictData(
    @SerializedName("roItem")
    val roItem: cmlItem,
    @SerializedName("rtCode")
    val rtCode: String,
    @SerializedName("rtDesc")
    val rtDesc: String
)