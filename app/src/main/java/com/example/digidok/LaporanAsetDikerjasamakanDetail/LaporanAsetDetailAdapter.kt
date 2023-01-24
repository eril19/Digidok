package com.example.digidok.LaporanAsetDikerjasamakanDetail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.LaporanAsetDikerjasamakan.LaporanAsetKerjasamaViewModel
import com.example.digidok.R

class LaporanAsetDetailAdapter(private val context: Context, val laporanAsetKerjasamaViewModel: LaporanAsetKerjasamaViewModel, val listener: (LaporanAsetDetailModel) -> Unit)
    : RecyclerView.Adapter<LaporanAsetDetailAdapter.LaporanAsetDetailViewHolder>(){

    private var LaporanAsetDetail: List<LaporanAsetDetailModel> = laporanAsetKerjasamaViewModel.mDataDetail

    class LaporanAsetDetailViewHolder(view: View): RecyclerView.ViewHolder(view) {
//        val num_laporan = view.findViewById<TextView>(R.id.num_laporan)
        val nama_lokasi = view.findViewById<TextView>(R.id.nama_lokasi)
        val nama_bmd = view.findViewById<TextView>(R.id.nama_bmd)
        val kode_lokasi = view.findViewById<TextView>(R.id.kode_lokasi)
        val kode_barang = view.findViewById<TextView>(R.id.kode_barang)
        val luas_bmd = view.findViewById<TextView>(R.id.luas_bmd)
        val keterangan_bmd = view.findViewById<TextView>(R.id.keterangan_bmd)

        fun bindView(laporanAsetDetailModel: LaporanAsetDetailModel, listener: (LaporanAsetDetailModel) -> Unit){
//            num_laporan.text = laporanAsetDetailModel.num_laporan
            nama_lokasi.text = laporanAsetDetailModel.nama_lokasi
            nama_bmd.text = laporanAsetDetailModel.nama_bmd
            kode_lokasi.text = laporanAsetDetailModel.kode_lokasi
            kode_barang.text = laporanAsetDetailModel.kode_barang
            luas_bmd.text = laporanAsetDetailModel.luas_bmd + laporanAsetDetailModel.satuan
            keterangan_bmd.text = laporanAsetDetailModel.keterangan_bmd
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaporanAsetDetailViewHolder {
        return LaporanAsetDetailViewHolder(
            LayoutInflater.from(context).inflate(R.layout.layout_card_laporan_aset_detail, parent, false)
        )
    }

    override fun onBindViewHolder(holder: LaporanAsetDetailViewHolder, position: Int) {
        holder.bindView(LaporanAsetDetail[position], listener)
    }

    override fun getItemCount(): Int = LaporanAsetDetail.size

}
