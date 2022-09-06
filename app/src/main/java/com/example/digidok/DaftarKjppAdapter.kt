package com.example.digidok

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DaftarKjppAdapter(private val context: Context, private val DaftarKJPP: List<DaftarKjppModel>, val listener: (DaftarKjppModel) -> Unit)
    : RecyclerView.Adapter<DaftarKjppAdapter.DaftarKJPPViewHolder>(){

    class DaftarKJPPViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val no_kjpp = view.findViewById<TextView>(R.id.no_kjpp)
        val nama_kjpp = view.findViewById<TextView>(R.id.nama_kjpp)
        val telp_kjpp = view.findViewById<TextView>(R.id.telp_kjpp)
        val no = view.findViewById<TextView>(R.id.no)
        val no_perizinan = view.findViewById<TextView>(R.id.no_perizinan)
        val tgl = view.findViewById<TextView>(R.id.tgl)
        val tgl_perizinan = view.findViewById<TextView>(R.id.tgl_perizinan)
        val klasifikasi = view.findViewById<TextView>(R.id.klasifikasi)
        val klasifikasi_perizinan = view.findViewById<TextView>(R.id.klasifikasi_perizinan)

        fun bindView(daftarKjppModel: DaftarKjppModel, listener: (DaftarKjppModel) -> Unit){
            no_kjpp.text = daftarKjppModel.no_kjpp
            nama_kjpp.text = daftarKjppModel.nama_kjpp
            telp_kjpp.text = daftarKjppModel.telp_kjpp
            no.text = daftarKjppModel.no
            no_perizinan.text = daftarKjppModel.no_perizinan
            tgl.text = daftarKjppModel.tgl
            tgl_perizinan.text = daftarKjppModel.tgl_perizinan
            klasifikasi.text = daftarKjppModel.klasifikasi
            klasifikasi_perizinan.text = daftarKjppModel.klasifikasi_perizinan
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaftarKJPPViewHolder {
        return DaftarKJPPViewHolder(
            LayoutInflater.from(context).inflate(R.layout.layout_card_daftar_kjpp, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DaftarKJPPViewHolder, position: Int) {
        holder.bindView(DaftarKJPP[position], listener)
    }

    override fun getItemCount(): Int = DaftarKJPP.size

}