package com.example.exada1.PDataPackage.PDistrictPackage


import com.google.gson.annotations.SerializedName

data class cmlDistrinct(
    @SerializedName("rdCreateOn")
    val rdCreateOn: String,
    @SerializedName("rdLastUpdOn")
    val rdLastUpdOn: String,
    @SerializedName("rtCreateBy")
    val rtCreateBy: String,
    @SerializedName("rtDstCode")
    val rtDstCode: String,
    @SerializedName("rtDstLatitude")
    val rtDstLatitude: String,
    @SerializedName("rtDstLongitude")
    val rtDstLongitude: String,
    @SerializedName("rtDstPost")
    val rtDstPost: String,
    @SerializedName("rtLastUpdBy")
    val rtLastUpdBy: String,
    @SerializedName("rtPvnCode")
    val rtPvnCode: String
)