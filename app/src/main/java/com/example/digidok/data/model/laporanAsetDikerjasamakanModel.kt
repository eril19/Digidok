package com.example.digidok.data.model

import com.google.gson.annotations.SerializedName

class laporanAsetDikerjasamakanModel(

    @SerializedName("dataDokumen")
    val dataDokumen: List<listLaporanAsetDikerjasamakan>,
    ) {
    data class listLaporanAsetDikerjasamakan(
        @SerializedName("no")
        val no: Int,
        @SerializedName("idPks")
        val idPks: String = "",
        @SerializedName("namaMitra")
        val namaMitra: String = "",
        @SerializedName("nilaiPks")
        val nilaiPks: String = "",
        @SerializedName("kategoriPks")
        val kategoriPks: String = "",
        @SerializedName("periodeAwal")
        val periodeAwal: String = "",
        @SerializedName("periodeAkhir")
        val periodeAkhir: String = "",
        @SerializedName("status")
        val status: Int,
        @SerializedName("statusLabel")
        val statusLabel: String = "",
        @SerializedName("dataDetail")
        val dataDetail: List<listDetail>,
    ){
    data class listDetail(
        @SerializedName("kolok")
        val kolok: String,
        @SerializedName("nalok")
        val nalok: String,
        @SerializedName("nabar")
        val nabar: String,
        @SerializedName("kobar")
        val kobar: String,
        @SerializedName("noreg")
        val noreg: String,
        @SerializedName("keterangan")
        val keterangan: String,
        @SerializedName("luas")
        val luas: String,
        @SerializedName("satuan")
        val satuan: String,
    )
    }
}