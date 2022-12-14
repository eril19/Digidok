package com.example.digidok

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class DataAsetdiKerjasamakanAdapter(private val context: Context, private val PengajuanKerjaDetail: List<PengajuanKerjasamaDetailModel>,
                                    private var mListener: onItemClickListener, val listener: (PengajuanKerjasamaDetailModel) -> Unit):
RecyclerView.Adapter<DataAsetdiKerjasamakanAdapter.PengajuanKerjasamaDetailViewHolder>(){

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    class PengajuanKerjasamaDetailViewHolder(view: View, listener: onItemClickListener)
        : RecyclerView.ViewHolder(view) {
        val alamat = view.findViewById<TextView>(R.id.alamat_barang)
        val kodeLokasi = view.findViewById<TextView>(R.id.kode_lokasi)
        val kodeBarang = view.findViewById<TextView>(R.id.kode_barang)
        val namaBarang = view.findViewById<TextView>(R.id.nama_barang)
        val namaLokasi = view.findViewById<TextView>(R.id.nama_lokasi)
        val luasManfaat = view.findViewById<TextView>(R.id.luas_manfaat)
        val luas = view.findViewById<TextView>(R.id.luas_barang)
        val cardview = view.findViewById<CardView>(R.id.cardViewDaftarAset)


//        init{
//            cardview.setOnClickListener {
//                listener.onItemClick(adapterPosition)
//            }
//        }

        fun bindView(pengajuanKerjasamaDetailModel: PengajuanKerjasamaDetailModel, listener: (PengajuanKerjasamaDetailModel) -> Unit){
            alamat.text  = pengajuanKerjasamaDetailModel.alamat
            luas.text = pengajuanKerjasamaDetailModel.luas
            luasManfaat.text = pengajuanKerjasamaDetailModel.luasManfaat
            namaLokasi.text = pengajuanKerjasamaDetailModel.namaLokasi
            namaBarang.text  = pengajuanKerjasamaDetailModel.namaBarang
            kodeBarang.text = pengajuanKerjasamaDetailModel.kodeBarang
            kodeLokasi.text = pengajuanKerjasamaDetailModel.kodeLokasi
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PengajuanKerjasamaDetailViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.layout_card_daftar_aset, parent, false)
        return PengajuanKerjasamaDetailViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: PengajuanKerjasamaDetailViewHolder, position: Int) {
        holder.bindView(PengajuanKerjaDetail[position], listener)
    }

    override fun getItemCount(): Int {
        return PengajuanKerjaDetail.size
    }

}