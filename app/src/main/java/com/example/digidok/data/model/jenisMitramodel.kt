package com.example.digidok.data.model

import com.google.gson.annotations.SerializedName

class jenisMitramodel(
    @SerializedName("dataJenisMitra")
    val dataJenisMitra: List<listJenisMitra>,
) {
    data class listJenisMitra(
        @SerializedName("value")
        val value: String,
        @SerializedName("label")
        val label: String,
    )

}