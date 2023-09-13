package com.example.exada1.PDataPackage.PDistrictPackage


import com.google.gson.annotations.SerializedName

data class RoItem(
    @SerializedName("raDistrinct")
    val raDistrinct: List<RaDistrinct>,
    @SerializedName("raDistrinctLng")
    val raDistrinctLng: List<RaDistrinctLng>
)