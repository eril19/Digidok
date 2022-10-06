package com.example.digidok.data.model

import com.google.gson.annotations.SerializedName

class BeritaModel(
    @SerializedName("ids")
    val ids: String = "",

    @SerializedName("sts")
    val sts: String = "",

    @SerializedName("uname")
    val uname: String = "",

    @SerializedName("tgl")
    val tgl: String = "",

    @SerializedName("ip")
    val ip: String = "",

    @SerializedName("tipe")
    val tipe: String = "",

    @SerializedName("tanggal")
    val tanggal: String = "",

    @SerializedName("judul")
    val judul: String = "",

    @SerializedName("isi1")
    val isi1: String = "",

    @SerializedName("isi2")
    val isi2: String = "",

    @SerializedName("tglinput")
    val tglinput: String = "",

    @SerializedName("editor")
    val editor: String = "",

    @SerializedName("link")
    val link: String = "",

    @SerializedName("url")
    val url: String = "",

    @SerializedName("kd_cms")
    val kd_cms: String = "",

    @SerializedName("appr")
    val appr: String = "",

    @SerializedName("usrinput")
    val usrinput: String = "",

    @SerializedName("contentnew")
    val contentnew: String = "",

    @SerializedName("row_num")
    val row_num: String = ""

) {}