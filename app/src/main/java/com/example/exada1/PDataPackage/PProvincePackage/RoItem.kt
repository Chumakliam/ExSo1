package com.example.exada1.PDataPackage.PProvincePackage


import com.google.gson.annotations.SerializedName

data class RoItem(
    @SerializedName("raPvn")
    val raPvn: List<RaPvn>,
    @SerializedName("raPvnLng")
    val raPvnLng: List<RaPvnLng>
)