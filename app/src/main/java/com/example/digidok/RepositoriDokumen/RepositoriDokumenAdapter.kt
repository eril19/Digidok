package com.example.digidok.RepositoriDokumen

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.R

class RepositoriDokumemAdapter(private val context: Context, val repositoriDokumenViewModel: RepositoriDokumenViewModel, private var mListener: onItemClickListener)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var RepositoriDokumen : MutableList<RepositoriDokumenModel> = repositoriDokumenViewModel.mData

    interface onItemClickListener{
        fun onItemClick(position: Int,                nama: String,
                        nilai: String,
                        jenisKerjasama: String,
                        pks: String)
    }

    class RepositoriDokumenViewHolder(view: View, listener: onItemClickListener): RecyclerView.ViewHolder(view) {
        val jenis_kerjasama = view.findViewById<TextView>(R.id.jenis_kerjasama)
        val no_surat = view.findViewById<TextView>(R.id.no_surat)
        val nama_mitra = view.findViewById<TextView>(R.id.nama_mitra)
        val header_color = view.findViewById<TextView>(R.id.header_color)
        val cekdokumen = view.findViewById<Button>(R.id.cek_dokumen_btn)
        var nama = ""
        var nilai = ""
        var jenisKerjasama = ""
        var pks = ""

        @SuppressLint("SetTextI18n")

        fun bindView(repositorDashboardModel: RepositoriDokumenModel){
            jenis_kerjasama.text = repositorDashboardModel.jenis_kerjasama
            no_surat.text = repositorDashboardModel.no_surat
            nama_mitra.text = repositorDashboardModel.nama_mitra
            header_color.text = repositorDashboardModel.header_color
            nama = repositorDashboardModel.nama_mitra
            nilai = repositorDashboardModel.harga
            jenisKerjasama = repositorDashboardModel.jenis_kerjasama
            pks = repositorDashboardModel.no_surat

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
                    android.R.color.darker_gray
                )
            }
            else if(repositorDashboardModel.header_color.equals("DISETUJUI", true) ) {
                header_color.background = ContextCompat.getDrawable(header_color.context,
                    R.color.green
                )
            }

        }

        init {
            cekdokumen.setOnClickListener {
                listener.onItemClick(adapterPosition,nama,nilai, jenisKerjasama, pks)
            }
        }

    }

    class LoadingViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_pagination -> {
                val itemView = LayoutInflater.from(context).inflate(R.layout.item_pagination, parent, false)
                return LoadingViewHolder(itemView)
            }
            else -> {
                val itemView = LayoutInflater.from(context).inflate(R.layout.layout_card_repositori, parent, false)
                return RepositoriDokumenViewHolder(itemView, mListener)

            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RepositoriDokumenViewHolder) {
            holder.bindView(RepositoriDokumen[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if((position == itemCount - 1) && repositoriDokumenViewModel.isLastPage.value == false){
            R.layout.item_pagination
        } else {
            R.layout.layout_card_repositori
        }

    }

    override fun getItemCount(): Int {
        val extras = (if(repositoriDokumenViewModel.isLastPage.value == true) 0 else 1)
        return RepositoriDokumen.size + extras
    }

}
