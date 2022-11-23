package com.example.digidok

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.LaporanPengajuanModel
import com.example.digidok.R

class LaporanPengajuanAdapter(private val context: Context, private val LaporanPengajuan: List<LaporanPengajuanModel>, val listener: (LaporanPengajuanModel) -> Unit)
    : RecyclerView.Adapter<LaporanPengajuanAdapter.LaporanPengajuanViewHolder>(){

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

        fun bindView(laporanPengajuanModel: LaporanPengajuanModel, listener: (LaporanPengajuanModel) -> Unit){
            id_mitra.text = laporanPengajuanModel.id_mitra
            nama_mitra.text = laporanPengajuanModel.nama_mitra
            no_mitra.text = laporanPengajuanModel.no_mitra
            id_pks.text = laporanPengajuanModel.id_pks
            jenis_kerjasama.text = laporanPengajuanModel.jenis_kerjasama
            no_surat.text = laporanPengajuanModel.no_surat
            jenis_bmd.text = laporanPengajuanModel.jenis_bmd
            nilai_pks.text = laporanPengajuanModel.nilai_pks
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
            else if(laporanPengajuanModel.header_color.equals("Draft", true) ) {
                header_color.background = ContextCompat.getDrawable(header_color.context,
                    android.R.color.darker_gray
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaporanPengajuanViewHolder {
        return LaporanPengajuanViewHolder(
            LayoutInflater.from(context).inflate(R.layout.layout_card_pengajuan, parent, false)
        )
    }

    override fun onBindViewHolder(holder: LaporanPengajuanViewHolder, position: Int) {
        holder.bindView(LaporanPengajuan[position], listener)
    }

    override fun getItemCount(): Int = LaporanPengajuan.size

}
