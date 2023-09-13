package com.example.exada1.PDataPackage.PCompanyPackage


import com.google.gson.annotations.SerializedName

data class RaComp(
    @SerializedName("rdCreateOn")
    val rdCreateOn: String,
    @SerializedName("rdLastUpdOn")
    val rdLastUpdOn: String,
    @SerializedName("rtBchcode")
    val rtBchcode: String,
    @SerializedName("rtCmpCode")
    val rtCmpCode: String,
    @SerializedName("rtCmpEmail")
    val rtCmpEmail: String,
    @SerializedName("rtCmpFax")
    val rtCmpFax: String,
    @SerializedName("rtCmpRetInOrEx")
    val rtCmpRetInOrEx: String,
    @SerializedName("rtCmpTel")
    val rtCmpTel: String,
    @SerializedName("rtCmpWhsInOrEx")
    val rtCmpWhsInOrEx: String,
    @SerializedName("rtCreateBy")
    val rtCreateBy: String,
    @SerializedName("rtLastUpdBy")
    val rtLastUpdBy: String,
    @SerializedName("rtRteCode")
    val rtRteCode: String,
    @SerializedName("rtVatCode")
    val rtVatCode: String
)