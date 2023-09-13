package com.example.exada1.PDataPackage.PDistrictPackage


import com.google.gson.annotations.SerializedName

data class cmlItem(
    @SerializedName("raDistrinct")
    val raDistrinct: List<cmlDistrinct>,
    @SerializedName("raDistrinctLng")
    val raDistrinctLng: List<cmlDistrinctLng>
)