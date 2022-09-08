package com.example.digidok

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.RepositoriDokumenModel
import com.example.digidok.R

class RepositoriDokumemAdapter(private val context: Context, private val RepositoriDokumen: List<RepositoriDokumenModel>, val listener: (RepositoriDokumenModel) -> Unit)
    : RecyclerView.Adapter<RepositoriDokumemAdapter.RepositoriDokumenViewHolder>(){

    class RepositoriDokumenViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val jenis_kerjasama = view.findViewById<TextView>(R.id.jenis_kerjasama)
        val no_surat = view.findViewById<TextView>(R.id.no_surat)
        val nama_mitra = view.findViewById<TextView>(R.id.nama_mitra)
        val header_color = view.findViewById<TextView>(R.id.header_color)

        fun bindView(repositorDashboardModel: RepositoriDokumenModel, listener: (RepositoriDokumenModel) -> Unit){
            jenis_kerjasama.text = repositorDashboardModel.jenis_kerjasama
            no_surat.text = repositorDashboardModel.no_surat
            nama_mitra.text = repositorDashboardModel.nama_mitra
            header_color.text = repositorDashboardModel.header_color
            if (repositorDashboardModel.header_color.equals("Dikirim", true) ) {
                header_color.background = ContextCompat.getDrawable(header_color.context,
                    R.color.blue
                )
            }
            else if(repositorDashboardModel.header_color.equals("Dikembalikan", true) ) {
                header_color.background = ContextCompat.getDrawable(header_color.context,
                    android.R.color.holo_orange_dark
                )
            }
            else if(repositorDashboardModel.header_color.equals("Draft", true) ) {
                header_color.background = ContextCompat.getDrawable(header_color.context,
                    android.R.color.system_neutral2_600
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriDokumenViewHolder {
        return RepositoriDokumenViewHolder(
            LayoutInflater.from(context).inflate(R.layout.layout_card_repositori, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RepositoriDokumenViewHolder, position: Int) {
        holder.bindView(RepositoriDokumen[position], listener)
    }

    override fun getItemCount(): Int = RepositoriDokumen.size

}
