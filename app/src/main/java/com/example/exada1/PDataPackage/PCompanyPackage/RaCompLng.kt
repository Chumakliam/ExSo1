package com.example.exada1.PDataPackage.PCompanyPackage


import com.google.gson.annotations.SerializedName

data class RaCompLng(
    @SerializedName("rnLngID")
    val rnLngID: Int,
    @SerializedName("rtCmpCode")
    val rtCmpCode: String,
    @SerializedName("rtCmpDirector")
    val rtCmpDirector: String,
    @SerializedName("rtCmpName")
    val rtCmpName: String,
    @SerializedName("rtCmpShop")
    val rtCmpShop: String
)