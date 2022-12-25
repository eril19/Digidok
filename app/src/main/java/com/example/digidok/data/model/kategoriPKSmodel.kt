package com.example.digidok.data.model

import com.google.gson.annotations.SerializedName

class kategoriPKSmodel(
    @SerializedName("dataKategoriPks")
    val dataKategoriPks: List<listkategoriPKS>,
) {
    data class listkategoriPKS(
        @SerializedName("value")
        val value: String,
        @SerializedName("label")
        val label: String,
    )

}