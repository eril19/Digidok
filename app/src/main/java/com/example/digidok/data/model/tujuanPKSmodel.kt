package com.example.digidok.data.model

import com.google.gson.annotations.SerializedName

class tujuanPKSmodel(
    @SerializedName("dataTujuanPks")
    val dataTujuanPks: List<listTujuanPKS>,
) {
    data class listTujuanPKS(
        @SerializedName("value")
        val value: String,
        @SerializedName("label")
        val label: String,
    )

}