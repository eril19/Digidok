package com.example.digidok.data.model

import com.google.gson.annotations.SerializedName

class dashboardModel(
    @SerializedName("jumlahKerjasama")
    val jumlahKerjasama: String = "",
    @SerializedName("jumlahNilaiKerjasama")
    val jumlahNilaiKerjasama: String = "",
    @SerializedName("jumlahMitra")
    val jumlahMitra: String = "",
    @SerializedName("dataMitra")
    val dataMitra: List<listDataMitra>,
) {
    data class listDataMitra(
        @SerializedName("totalNilai")
        val totalNilai: String = "",
        @SerializedName("namaMitra")
        val namaMitra: String = "",
        @SerializedName("idMitra")
        val idMitra: String = "",
        @SerializedName("jumlahKerjasama")
        val jumlahKederjasama: String = "",
    )
}