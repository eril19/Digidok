package com.example.digidok.data.model

import com.google.gson.annotations.SerializedName

class daftarKJPPModel(
    @SerializedName("dataKjpp")
    val dataKjpp: List<listDaftarKjpp>,
) {
    data class listDaftarKjpp(
        @SerializedName("periode")
        val periode: Int,
        @SerializedName("no_induk")
        val no_induk: String = "",
        @SerializedName("nama")
        val nama: String = "",
        @SerializedName("no_izin")
        val no_izin: Int,
        @SerializedName("tgl_izin")
        val tgl_izin: String = "",
        @SerializedName("klasifikasi_izin")
        val klasifikasi_izin: String = "",
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