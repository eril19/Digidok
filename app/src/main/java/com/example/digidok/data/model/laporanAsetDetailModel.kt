package com.example.digidok.data.model

import com.google.gson.annotations.SerializedName

class laporanAsetDetailModel(
    @SerializedName("dataDetail")
    val dataDetail: List<listDetail>,
) {
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