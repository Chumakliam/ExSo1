package com.example.exada1.PDataPackage.PCompanyPackage


import com.google.gson.annotations.SerializedName

data class RaImage(
    @SerializedName("rdCreateOn")
    val rdCreateOn: String,
    @SerializedName("rdLastUpdOn")
    val rdLastUpdOn: String,
    @SerializedName("rnImgID")
    val rnImgID: Int,
    @SerializedName("rnImgSeq")
    val rnImgSeq: Int,
    @SerializedName("rtCreateBy")
    val rtCreateBy: String,
    @SerializedName("rtImgKey")
    val rtImgKey: String,
    @SerializedName("rtImgObj")
    val rtImgObj: String,
    @SerializedName("rtImgRefID")
    val rtImgRefID: String,
    @SerializedName("rtImgTable")
    val rtImgTable: String,
    @SerializedName("rtLastUpdBy")
    val rtLastUpdBy: String
)