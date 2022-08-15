package com.example.digidok

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.DaftarMitraModel
import com.example.digidok.R

class DaftarMitraAdapter(private val context: Context, private val DaftarMitra: List<DaftarMitraModel>, val listener: (DaftarMitraModel) -> Unit)
    : RecyclerView.Adapter<DaftarMitraAdapter.DaftarMitraViewHolder>(){

    class DaftarMitraViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val id_mitra = view.findViewById<TextView>(R.id.id_mitra)
        val nama_mitra = view.findViewById<TextView>(R.id.nama_mitra)
        val jenis_mitra = view.findViewById<TextView>(R.id.jenis_mitra)
        val status = view.findViewById<TextView>(R.id.status)
        val status_mitra = view.findViewById<TextView>(R.id.status_mitra)
        val npwp = view.findViewById<TextView>(R.id.npwp)
        val npwp_mitra = view.findViewById<TextView>(R.id.npwp_mitra)

        fun bindView(DaftarMitraModel: DaftarMitraModel, listener: (DaftarMitraModel) -> Unit){
            id_mitra.text = DaftarMitraModel.id_mitra
            nama_mitra.text = DaftarMitraModel.nama_mitra
            jenis_mitra.text = DaftarMitraModel.jenis_mitra
            status.text = DaftarMitraModel.status
            status_mitra.text = DaftarMitraModel.status_mitra
            npwp.text = DaftarMitraModel.npwp
            npwp_mitra.text = DaftarMitraModel.npwp_mitra
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaftarMitraViewHolder {
        return DaftarMitraViewHolder(
            LayoutInflater.from(context).inflate(R.layout.layout_card_daftar_mitra, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DaftarMitraViewHolder, position: Int) {
        holder.bindView(DaftarMitra[position], listener)
    }

    override fun getItemCount(): Int = DaftarMitra.size

}
