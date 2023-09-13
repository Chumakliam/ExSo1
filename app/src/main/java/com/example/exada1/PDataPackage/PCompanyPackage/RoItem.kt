package com.example.exada1.PDataPackage.PCompanyPackage


import com.google.gson.annotations.SerializedName

data class RoItem(
    @SerializedName("raComp")
    val raComp: List<cmlComp>,
    @SerializedName("raCompLng")
    val raCompLng: List<cmlCompLng>,
    @SerializedName("raImage")
    val raImage: List<cmlImage>,
    @SerializedName("raUrlObject")
    val raUrlObject: Any
)