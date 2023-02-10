package com.example.digidok.LaporanPengajuanKerjasama

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.DaftarKJPP.DaftarKjppModel
import com.example.digidok.DaftarMitra.DaftarMitraAdapter
import com.example.digidok.R
import java.text.DecimalFormat

class LaporanPengajuanAdapter(private val context: Context, val laporanPengajuanViewModel: LaporanPengajuanViewModel,val listener: (LaporanPengajuanModel) -> Unit)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var LaporanPengajuan: List<LaporanPengajuanModel> = laporanPengajuanViewModel.mData

    class LaporanPengajuanViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val id_mitra = view.findViewById<TextView>(R.id.id_mitra)
        val nama_mitra = view.findViewById<TextView>(R.id.nama_mitra)
        val no_mitra = view.findViewById<TextView>(R.id.no_mitra)
        val id_pks = view.findViewById<TextView>(R.id.id_pks)
        val jenis_kerjasama = view.findViewById<TextView>(R.id.jenis_kerjasama)
        val no_surat = view.findViewById<TextView>(R.id.no_surat)
        val jenis_bmd = view.findViewById<TextView>(R.id.jenis_bmd)
        val nilai_pks = view.findViewById<TextView>(R.id.nilai_pks)
        val detail_pks = view.findViewById<TextView>(R.id.detail_pks)
        val periodeAwal= view.findViewById<TextView>(R.id.tglMulai)
        val periodeAkhir = view.findViewById<TextView>(R.id.tglAkhir)
        val header_color = view.findViewById<TextView>(R.id.header_color)
        var formatter : DecimalFormat = DecimalFormat("#,###")

        @SuppressLint("SetTextI18n")
        fun bindView(laporanPengajuanModel: LaporanPengajuanModel, listener: (LaporanPengajuanModel) -> Unit){
            id_mitra.text = laporanPengajuanModel.id_mitra
            nama_mitra.text = laporanPengajuanModel.nama_mitra
            no_mitra.text = laporanPengajuanModel.no_mitra
            id_pks.text = laporanPengajuanModel.id_pks
            jenis_kerjasama.text = laporanPengajuanModel.jenis_kerjasama
            no_surat.text = laporanPengajuanModel.no_surat
            jenis_bmd.text = laporanPengajuanModel.jenis_bmd

            var nilaiPKS = 0f
            if(laporanPengajuanModel.nilai_pks.isNullOrEmpty() || !laporanPengajuanModel.nilai_pks.equals("-")){
                nilaiPKS = laporanPengajuanModel.nilai_pks.toFloat()
            }

            nilai_pks.text = "Rp. " +  formatter.format(nilaiPKS.toLong())
            detail_pks.text = laporanPengajuanModel.perihal
            periodeAwal.text = laporanPengajuanModel.periodeAwal
            periodeAkhir.text = laporanPengajuanModel.periodeAkhir
            header_color.text = laporanPengajuanModel.header_color
            if (laporanPengajuanModel.header_color.equals("Dikirim", true) ) {
                header_color.background = ContextCompat.getDrawable(header_color.context,
                    R.color.blue
                )
            }
            else if(laporanPengajuanModel.header_color.equals("Dikembalikan", true) ) {
                header_color.background = ContextCompat.getDrawable(header_color.context,
                    android.R.color.holo_orange_dark
                )
            }
            else if(laporanPengajuanModel.header_color.equals("Disetujui", true) ) {
                header_color.background = ContextCompat.getDrawable(header_color.context,
                    R.color.green
                )
            }
            else if(laporanPengajuanModel.header_color.equals("Draft", true) ) {
                header_color.background = ContextCompat.getDrawable(header_color.context,
                    android.R.color.darker_gray
                )
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
                val itemView = LayoutInflater.from(context).inflate(R.layout.layout_card_pengajuan, parent, false)
                return LaporanPengajuanViewHolder(itemView)

            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LaporanPengajuanViewHolder) {
            holder.bindView(LaporanPengajuan[position], listener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if((position == itemCount - 1) && laporanPengajuanViewModel.isLastPage.value == false){
            R.layout.item_pagination
        } else {
            R.layout.layout_card_pengajuan
        }

    }

    override fun getItemCount(): Int {
        val extras = (if(laporanPengajuanViewModel.isLastPage.value == true) 0 else 1)
        return LaporanPengajuan.size + extras
    }

}
