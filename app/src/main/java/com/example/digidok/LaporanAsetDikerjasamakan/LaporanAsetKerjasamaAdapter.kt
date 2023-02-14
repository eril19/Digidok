package com.example.digidok.LaporanAsetDikerjasamakan

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
import java.text.DecimalFormat

class LaporanAsetKerjasamaAdapter(private val context: Context, val laporanAsetKerjasamaViewModel: LaporanAsetKerjasamaViewModel, private var mListener: onItemClickListener,
                                  val listener: (LaporanAsetKerjasamaModel) -> Unit)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var LaporanAset: List<LaporanAsetKerjasamaModel> = laporanAsetKerjasamaViewModel.mData


    interface onItemClickListener{
        fun onItemClick(position: Int, nama:String, nilai:String,jenisKerjasama:String,pks:String)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }


    class LaporanAsetViewHolder(view: View, listener: onItemClickListener): RecyclerView.ViewHolder(view) {

        val id_pks = view.findViewById<TextView>(R.id.id_pks)
        val nama_mitra = view.findViewById<TextView>(R.id.nama_mitra)
        val nilai_pks = view.findViewById<TextView>(R.id.nilai_pks)
        val jenis_kerjasama = view.findViewById<TextView>(R.id.jenis_kerjasama)
        val header_color = view.findViewById<TextView>(R.id.header_color)
        val tomboldetail = view.findViewById<Button>(R.id.cek_detail)
        var nama = ""
        var nilai = ""
        var jenisKerjasama = ""
        var pks = ""
        var formatter : DecimalFormat = DecimalFormat("#,###")


        init {
            tomboldetail.setOnClickListener {
                listener.onItemClick(adapterPosition,nama,nilai, jenisKerjasama, pks)
            }
        }

        @SuppressLint("SetTextI18n")
        fun bindView(laporanAsetModel: LaporanAsetKerjasamaModel, listener: (LaporanAsetKerjasamaModel) -> Unit){
            id_pks.text = laporanAsetModel.id_pks
            pks = laporanAsetModel.id_pks
            nama_mitra.text = laporanAsetModel.nama_mitra
            nama = laporanAsetModel.nama_mitra
            var nilaiPKS = 0f
            if(laporanAsetModel.nilai_pks.isNullOrEmpty() || !laporanAsetModel.nilai_pks.equals("-")){
                nilaiPKS = laporanAsetModel.nilai_pks.toFloat()
            }

            nilai_pks.text = "Rp. " +  formatter.format(nilaiPKS.toLong())

            nilai = laporanAsetModel.nilai_pks
            jenis_kerjasama.text = laporanAsetModel.jenis_kerjasama
            jenisKerjasama = laporanAsetModel.jenis_kerjasama
            header_color.text = laporanAsetModel.header_color



            if (laporanAsetModel.header_color.equals("Dikirim", true) ) {
                header_color.background = ContextCompat.getDrawable(header_color.context,
                    R.color.blue
                )
            }
            else if(laporanAsetModel.header_color.equals("Dikembalikan", true) ) {
                header_color.background = ContextCompat.getDrawable(header_color.context,
                    android.R.color.holo_orange_dark
                )
            }
            else if(laporanAsetModel.header_color.equals("Disetujui", true) ) {
                header_color.background = ContextCompat.getDrawable(header_color.context,
                    R.color.green
                )
            }
            else if(laporanAsetModel.header_color.equals("Draft", true) ) {
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
                val itemView = LayoutInflater.from(context).inflate(R.layout.layout_card_laporan_aset, parent, false)
                return LaporanAsetViewHolder(itemView, mListener)

            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LaporanAsetViewHolder) {
            holder.bindView(LaporanAset[position], listener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if((position == itemCount - 1) && laporanAsetKerjasamaViewModel.isLastPage.value == false){
            R.layout.item_pagination
        } else {
            R.layout.layout_card_laporan_aset
        }

    }

    override fun getItemCount(): Int {
        val extras = (if(laporanAsetKerjasamaViewModel.isLastPage.value == true) 0 else 1)
        return LaporanAset.size + extras
    }
}