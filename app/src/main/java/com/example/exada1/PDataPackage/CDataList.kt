package com.example.exada1.PDataPackage

import com.google.gson.annotations.SerializedName

data class CDataList(
    @SerializedName("FTSynName")
    var tTitle:String,
    @SerializedName("FDSynLast")
    var tDate:String
) {
}