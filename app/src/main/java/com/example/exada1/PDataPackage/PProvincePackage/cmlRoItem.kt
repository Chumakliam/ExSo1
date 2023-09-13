package com.example.exada1.PDataPackage.PProvincePackage


import com.google.gson.annotations.SerializedName

data class cmlRoItem(
    @SerializedName("raPvn")
    val raPvn: List<cmlPvn>,
    @SerializedName("raPvnLng")
    val raPvnLng: List<cmlPvnLng>
)