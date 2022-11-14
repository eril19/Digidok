package com.example.digidok.data.model

import com.google.gson.annotations.SerializedName

class daftarKJPPModel(
    @SerializedName("dataKjpp")
    val dataKjpp: List<listDaftarKjpp>,
) {
    data class listDaftarKjpp(
        @SerializedName("no")
        val no: Int,
        @SerializedName("periode")
        val periode: Int,
        @SerializedName("noInduk")
        val noInduk: String = "",
        @SerializedName("nama")
        val nama: String = "",
        @SerializedName("noIzin")
        val noIzin: String,
        @SerializedName("tglIzin")
        val tglIzin: String = "",
        @SerializedName("klasifikasiIzin")
        val klasifikasiIzin: String = "",
        @SerializedName("domisili")
        val domisili: String = "",
        @SerializedName("alamat")
        val alamat: String = "",
        @SerializedName("phone")
        val phone: String = "",
        @SerializedName("fax")
        val fax: String = "",
        @SerializedName("sts")
        val status: Int,
    )
}