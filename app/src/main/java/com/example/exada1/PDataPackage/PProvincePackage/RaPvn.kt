package com.example.exada1.PDataPackage.PProvincePackage


import com.google.gson.annotations.SerializedName

data class RaPvn(
    @SerializedName("rdCreateOn")
    val rdCreateOn: String,
    @SerializedName("rdLastUpdOn")
    val rdLastUpdOn: String,
    @SerializedName("rtCreateBy")
    val rtCreateBy: String,
    @SerializedName("rtLastUpdBy")
    val rtLastUpdBy: String,
    @SerializedName("rtPvnCode")
    val rtPvnCode: String,
    @SerializedName("rtPvnLatitude")
    val rtPvnLatitude: String,
    @SerializedName("rtPvnLongitude")
    val rtPvnLongitude: String,
    @SerializedName("rtZneCode")
    val rtZneCode: String
)