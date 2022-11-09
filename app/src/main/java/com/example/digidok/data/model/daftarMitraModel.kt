package com.example.digidok.data.model

import com.google.gson.annotations.SerializedName

class daftarMitraModel(

    @SerializedName("dataMitra")
    val dataMitra: List<listDaftarMitra>,
    )
{
    data class listDaftarMitra(
        @SerializedName("no")
        val no: Int,
        @SerializedName("kodeMitra")
        val kodeMitra: String = "",
        @SerializedName("namaMitra")
        val namaMitra: String = "",
        @SerializedName("tahunGabung")
        val tahunGabung: Int,
        @SerializedName("npwp")
        val npwp: String = "",
        @SerializedName("jenisMitra")
        val jenisMitra: String = "",
        @SerializedName("statusMitra")
        val statusMitra: String = "",
        @SerializedName("status")
        val status: Int,
    )
}