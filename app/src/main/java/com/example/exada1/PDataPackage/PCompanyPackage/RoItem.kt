package com.example.exada1.PDataPackage.PCompanyPackage


import com.google.gson.annotations.SerializedName

data class RoItem(
    @SerializedName("raComp")
    val raComp: List<RaComp>,
    @SerializedName("raCompLng")
    val raCompLng: List<RaCompLng>,
    @SerializedName("raImage")
    val raImage: List<RaImage>,
    @SerializedName("raUrlObject")
    val raUrlObject: Any
)